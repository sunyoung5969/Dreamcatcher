

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
	String reg_date = sdf.format(c1.getTime()); //java ���ο��� ���� ��¥�� yyyy-mm-dd �������� �������� ����
	
	
	String read_count = "1"; //���Ƿ� ������ ��ȸ��, ���� ��ȸ�� ��� ���� �ʿ�

       
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

		//RequestDispatcher dis = request.getRequestDispatcher("UserForm.jsp"); //�����幮
		
		
		PrintWriter out = response.getWriter();
		String fsl = File.separator;
        String root = request.getSession().getServletContext().getRealPath(fsl); //������ �����̳� ����
	    System.out.println("��Ʈ���: " + root );
        //���ε� ������(��Ʈ����) 
		int size = 5*1024*1024;
	 	String fileName1 = "";
	 	MultipartRequest multi = null;
	try{
			// ���� ���ε� ���� ����
		multi = new MultipartRequest(
				request, //��û����
				root, // ���ε� ������
				size, // ���ѻ����� (5�ް�)
				"UTF-8", // ���ڵ� ���
				new DefaultFileRenamePolicy()
				);

		name = multi.getParameter("name");
		subject = multi.getParameter("subject");
		// ÷������ ������ ������ 
		Enumeration files = multi.getFileNames();
		// ���ϸ� �����´� 
		String file1 = (String)files.nextElement();
		//multi���� �ش� �̸��� �˷��ְ� ���� �ý��ۻ� ��Ī �˾Ƴ�( ������� )
		fileName1 = multi.getFilesystemName(file1);
		System.out.println("ssssssssss is OK"+fileName1);
		}catch(Exception e) {
			out.print(e.getMessage());
			System.out.println("file upload fail : " + e.getMessage()); // �αױ�� 
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
	    //������� ftp ���ε��. ���ķ� sql insert��
	    //////////////////////////////////////////
	    
	    
	    
		String imgAdd = "http://10.10.10.109/user/" + name + "/" + fileName1; // ftp�� ���ε��� �̹��� �ּҸ� Stringȭ
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
					+ ",'" + reg_date + "'," + rIndex + ")"; //sql insert��
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
		//���� �������� ����, �̹��� �ּ�, ��ȸ��, �ۼ� ����, �ۼ��ڸ��� �ѱ��

		//dis.forward(request, response); //���� ������
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
            ftpClient.connect(serverIp, serverPort);  //ftp ����
            ftpClient.setControlEncoding("utf-8");    //ftp ���ڵ�����
            int reply = ftpClient.getReplyCode();     //�����ڵ�ޱ�
            
            if (!FTPReply.isPositiveCompletion(reply)) {  //������ false ��� ���� ���� exception �߻�
                ftpClient.disconnect();
                throw new Exception(serverIp+" FTP ���� ���� ����");
            }
            
            ftpClient.setSoTimeout(1000 * 10);   //timeout ����
            ftpClient.login(user, password);     //ftp �α���
            ftpClient.makeDirectory("/user/" + name); // �ۼ��� ������ ftp ftp://10.10.10.109/user/ ���ο� ���� ����
            ftpClient.changeWorkingDirectory("/user/" + name + "/"); // ��� ���ε� ������ġ
            ftpClient.setFileType(FTP.BINARY_FILE_TYPE); //����Ÿ�Լ���
            ftpClient.enterLocalActiveMode();  //active ��� ����
            
            fis = new FileInputStream(fileObj);
            return ftpClient.storeFile(FileName, fis); //���� ���ε�
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




