

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.SocketException;
import java.util.Enumeration;
import java.util.Properties;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPReply;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;
import java.util.Calendar;

import java.text.SimpleDateFormat;


import java.sql.*;

/**
 * Servlet implementation class ftpupload
 */
@WebServlet("/fileUpload99")
public class uploadPlease extends HttpServlet {
	private static final long serialVersionUID = 1L;
	


	Connection conn = null;
	Statement stmt = null;
	PreparedStatement psmt = null;
	ResultSet rs = null;
	Boolean connect = false;
	String connectCheck = "";
	
	String name = "";
	String subject = "";
	
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
    Calendar c1 = Calendar.getInstance();
	String reg_date = sdf.format(c1.getTime()); //java 내부에서 오늘 날짜를 yyyy-mm-dd 형식으로 가져오는 구문
	
	
	String read_count = "1"; //임의로 설정한 조회수, 추후 조회수 기능 구현 필요

       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public uploadPlease() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	///////////////

		//RequestDispatcher dis = request.getRequestDispatcher("UserForm.jsp"); //포워드문
		
		
		PrintWriter out = response.getWriter();
		String fsl = File.separator;
        String root = request.getSession().getServletContext().getRealPath(fsl); //웹서버 컨테이너 정보
	    System.out.println("루트경로: " + root );
        //업로드 폴더에(루트폴더) 
		int size = 5*1024*1024;
	 	String fileName1 = "";
	 	MultipartRequest multi = null;
	try{
			// 파일 업로드 수행 구문
		multi = new MultipartRequest(
				request, //요청정보
				root, // 업로드 절대경로
				size, // 제한사이즈 (5메가)
				"UTF-8", // 인코딩 방식
				new DefaultFileRenamePolicy()
				);

		name = multi.getParameter("name");
		subject = multi.getParameter("subject");
		// 첨부파일 여러개 가져옴 
		Enumeration files = multi.getFileNames();
		// 파일명만 가져온다 
		String file1 = (String)files.nextElement();
		//multi에서 해당 이름을 알려주고 실제 시스템상 명칭 알아냄( 리얼네임 )
		fileName1 = multi.getFilesystemName(file1);
		System.out.println("ssssssssss is OK"+fileName1);
		}catch(Exception e) {
			out.print(e.getMessage());
			System.out.println("file upload fail : " + e.getMessage()); // 로그기록 
		} 
	    File filePath = new File(root);
	    
	    File mFile = new File(filePath,fileName1);
	    String ftpPath = "ftp://10.10.10.109/user/" + name + "/";
	    FtpClient ftp = new FtpClient(ftpPath);
	    try {
			ftp.upload(mFile, fileName1, name);
			System.out.println("upload is OK"+fileName1);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	    
	    
	    //////////////////////////////////////////
	    //여기까지 ftp 업로드부. 이후로 sql insert부
	    //////////////////////////////////////////
	    
	    
	    
		String imgAdd = "http://10.10.10.109/user/" + name + "/" + fileName1; // ftp에 업로드한 이미지 주소를 String화
		String rIndex = "";
		String query_select2 = "select * from dream.user_info where user_id = '" + name + "'"; 
		String query_insert = "";

		try {
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection("jdbc:mysql://10.10.10.109:3306/dream", "user", "1234");
			System.out.println("DB Connection OK!");

			try {
				stmt = conn.createStatement();
				
				rs = stmt.executeQuery(query_select2);
				while (rs.next()) {
					rIndex = rs.getString(1);
				}
				System.out.println("rIndex:"+rIndex);
				
			} catch (Exception e) {
				System.out.println("insert error !");
				e.printStackTrace();

			}
			query_insert = "insert into dream.notice values(null,'" + subject + "','" + imgAdd + "'," + read_count
					+ ",'" + reg_date + "'," + rIndex + ")"; //sql insert문
			try {
				stmt = conn.createStatement();
				stmt.executeUpdate(query_insert);
				
				stmt.close();
				conn.close();
				
			} catch (Exception e) {
				System.out.println("insert2 error !");
				e.printStackTrace();

			}
			
			
		} catch (Exception e) {
			System.out.println("DB Connection error !");
		}
		
		
		request.setAttribute("subject", subject);
		request.setAttribute("imgAdd", imgAdd);
		request.setAttribute("read_count", read_count);
		request.setAttribute("reg_date", reg_date);
		request.setAttribute("name", name);
		//다음 페이지로 제목, 이미지 주소, 조회수, 작성 일자, 작성자명을 넘긴다

		//dis.forward(request, response); //최종 포워딩
		response.sendRedirect("UserForm.jsp");
		System.out.println("forward okay!");

	    
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}

class FtpClient extends uploadPlease{
    private String serverIp;
    private int serverPort;
    private String user;
    private String password;
    private String filePath ; 
    String name;
    
    public FtpClient(String filePath) {
        this.serverIp = "10.10.10.109";
        this.serverPort = 21;
        this.user = "user1";
        this.password = "1234";
        this.filePath = filePath;
    }
    public boolean upload(File fileObj, String FileName, String name) throws SocketException, IOException, Exception {
        FileInputStream fis = null;
        FTPClient ftpClient = new FTPClient();
        
        try {
            ftpClient.connect(serverIp, serverPort);  //ftp 연결
            ftpClient.setControlEncoding("utf-8");    //ftp 인코딩설정
            int reply = ftpClient.getReplyCode();     //응답코드받기
            
            if (!FTPReply.isPositiveCompletion(reply)) {  //응답이 false 라면 연결 해제 exception 발생
                ftpClient.disconnect();
                throw new Exception(serverIp+" FTP 서버 연결 실패");
            }
            
            ftpClient.setSoTimeout(1000 * 10);   //timeout 설정
            ftpClient.login(user, password);     //ftp 로그인
            ftpClient.makeDirectory("/user/" + name); // 작성자 명으로 ftp ftp://10.10.10.109/user/ 내부에 폴더 생성
            ftpClient.changeWorkingDirectory("/user/" + name + "/"); // 경로 업로드 저장위치
            ftpClient.setFileType(FTP.BINARY_FILE_TYPE); //파일타입설정
            ftpClient.enterLocalActiveMode();  //active 모드 설정
            
            fis = new FileInputStream(fileObj);
            return ftpClient.storeFile(FileName, fis); //파일 업로드
        } finally {
            if (ftpClient.isConnected()) {
                ftpClient.disconnect();
            }
            if (fis != null) {
                fis.close();
            }
        }
    }
}




