

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.sql.*;
import java.util.ArrayList;

/**
 * Servlet implementation class searchServlet
 */
@WebServlet("/searchthis")
public class searchServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	Connection conn = null;
	Statement stmt = null;
	Statement stmt2 = null;
	PreparedStatement psmt = null;
	ResultSet rs = null;
	Boolean connect = false;
	String connectCheck = "";
	
	ArrayList<String> titleList = new ArrayList<String>(); //글제목을 저장할 리스트
	ArrayList<String> dateList = new ArrayList<String>();//날짜 저장할 리스트
	ArrayList<String> imageList = new ArrayList<String>();//이미지 주소를 저장할 리스트
	ArrayList<String> viewCountList = new ArrayList<String>();//조회수를 저장할 리스트
	
	String searchIndex = ""; //003_search 페이지에서 사용자가 입력한 아이디
	String result = "";
	String sid = "";
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public searchServlet() {
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
		viewCountList.clear(); //어레이리스트 초기화
		sid = (String) session.getAttribute("id");
		
		RequestDispatcher dis = request.getRequestDispatcher("UserForm.jsp");
		request.setAttribute("centerpage", "004_searchBoard.jsp");
		
		if (sid!= null || !sid.equals("")) {
			searchIndex = sid;
			System.out.println(searchIndex);
			String rIndex = "";
			String query_select = "select * from dream.user_info where user_id = '" + searchIndex + "'"; //유저가 입력한 아이디값을 바탕으로 db 검색
			
			try {
				Class.forName("com.mysql.jdbc.Driver");
				conn = DriverManager.getConnection("jdbc:mysql://10.10.10.109:3306/dream", "user", "1234");
				System.out.println("DB Connection OK!");
				try {
					stmt = conn.createStatement();
					rs = stmt.executeQuery(query_select);

					while (rs.next()) {
						rIndex = rs.getString(1);
					}
					System.out.println(rIndex +"> : 인덱스1");
				}catch(Exception e) {
					System.out.println("insert1 Fail");
				}
				try {
					String query_select2 = "select * from dream.notice where user_num = '" + rIndex + "'"; //입력한 아이디 인덱스 로 찾기 
					stmt = conn.createStatement();
					rs = stmt.executeQuery(query_select2);

					while (rs.next()) {
						titleList.add(rs.getString(2));
						imageList.add(rs.getString(3));
						viewCountList.add(rs.getString(4));
						dateList.add(rs.getString(5));
					}

					System.out.println("query select okay");
				} catch (Exception e) {
					System.out.println("select error !");
					e.printStackTrace();

				}
				request.setAttribute("name", searchIndex);
				request.setAttribute("titleList", titleList);
				request.setAttribute("imageList", imageList);
				request.setAttribute("viewCountList", viewCountList);
				request.setAttribute("dateList", dateList);
				request.setAttribute("result", result);
				
				dis.forward(request, response);

				conn.close();
			} catch (Exception e) {
				System.out.println("DB Connection error !");
			}

		} else {

			result = "잘못된 입력입니다.";
			System.out.println("잘못된 입력입니다.");

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
