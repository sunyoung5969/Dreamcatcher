

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
	
	ArrayList<String> titleList = new ArrayList<String>(); //�������� ������ ����Ʈ
	ArrayList<String> dateList = new ArrayList<String>();//��¥ ������ ����Ʈ
	ArrayList<String> imageList = new ArrayList<String>();//�̹��� �ּҸ� ������ ����Ʈ
	ArrayList<String> viewCountList = new ArrayList<String>();//��ȸ���� ������ ����Ʈ
	
	String searchIndex = ""; //003_search ���������� ����ڰ� �Է��� ���̵�
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
		viewCountList.clear(); //��̸���Ʈ �ʱ�ȭ
		sid = (String) session.getAttribute("id");
		
		RequestDispatcher dis = request.getRequestDispatcher("UserForm.jsp");
		request.setAttribute("centerpage", "004_searchBoard.jsp");
		
		if (sid!= null || !sid.equals("")) {
			searchIndex = sid;
			System.out.println(searchIndex);
			String rIndex = "";
			String query_select = "select * from dream.user_info where user_id = '" + searchIndex + "'"; //������ �Է��� ���̵��� �������� db �˻�
			
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
					System.out.println(rIndex +"> : �ε���1");
				}catch(Exception e) {
					System.out.println("insert1 Fail");
				}
				try {
					String query_select2 = "select * from dream.notice where user_num = '" + rIndex + "'"; //�Է��� ���̵� �ε��� �� ã�� 
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

			result = "�߸��� �Է��Դϴ�.";
			System.out.println("�߸��� �Է��Դϴ�.");

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
