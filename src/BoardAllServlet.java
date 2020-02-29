

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class BoardAllServlet
 */
@WebServlet("/BoardAll")
public class BoardAllServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	Connection conn = null;
	Statement stmt = null;
	Statement stmt2 = null;
	PreparedStatement psmt = null;
	ResultSet rs = null;
	ResultSet rs2 = null;
	Boolean connect = false;
	String connectCheck = "";
	String writeid = "";
	String writenum = "";
	
	ArrayList<String> titleList = new ArrayList<String>(); //글제목을 저장할 리스트
	ArrayList<String> dateList = new ArrayList<String>();//날짜 저장할 리스트
	ArrayList<String> imageList = new ArrayList<String>();//이미지 주소를 저장할 리스트
	ArrayList<String> nameList = new ArrayList<String>();//이미지 주소를 저장할 리스트
	

	String result = "";
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public BoardAllServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		HttpSession session = request.getSession();
		titleList.clear();
		dateList.clear();
		imageList.clear();
		nameList.clear();
		
		RequestDispatcher dis = request.getRequestDispatcher("UserForm.jsp");
		request.setAttribute("centerpage", "006_BoardAll.jsp");
		
	
			
			String query_select = "select * from dream.notice"; 
			
			try {
				Class.forName("com.mysql.jdbc.Driver");
				conn = DriverManager.getConnection("jdbc:mysql://10.10.10.109:3306/dream", "user", "1234");
				System.out.println("DB Connection OK!");
				try {
					stmt = conn.createStatement();
					stmt2 = conn.createStatement();
					rs = stmt.executeQuery(query_select);

				
				}catch(Exception e) {
					System.out.println("insert1 Fail");
				}
				try {
					stmt = conn.createStatement();
					rs = stmt.executeQuery(query_select);

					while (rs.next()) {
						titleList.add(rs.getString(2));
						imageList.add(rs.getString(3));
						dateList.add(rs.getString(5));
						writenum = rs.getString(6);
						
						
						String query_select2 = "select * from dream.user_info where user_num =" + writenum; 

						rs2 = stmt2.executeQuery(query_select2);
						if(rs2.next()) {
							writeid = rs2.getString(2);
							System.out.println(writeid);
							nameList.add(writeid);
							rs2.close();
							}

					}


					System.out.println("query select okay");
				} catch (Exception e) {
					System.out.println("select error !");
					e.printStackTrace();

				}
	
				request.setAttribute("titleList", titleList);
				request.setAttribute("imageList", imageList);
				request.setAttribute("dateList", dateList);
				request.setAttribute("nameList", nameList);
				
				dis.forward(request, response);

				conn.close();
			} catch (Exception e) {
				System.out.println("DB Connection error !");
			}

		} 

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
