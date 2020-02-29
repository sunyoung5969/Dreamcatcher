

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class cssjoinservlet
 */
@WebServlet("/cssjoin")
public class csssignupservlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	
	Connection conn = null;
	Statement stmt = null;
	PreparedStatement psmt = null;
	ResultSet rs = null;
	Boolean connect = false; 
	String connectCheck = "";

    /**
     * @see HttpServlet#HttpServlet()
     */
    public csssignupservlet() {
        super();

    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		RequestDispatcher dis = request.getRequestDispatcher("joinCompleted.jsp");
		PrintWriter out = response.getWriter();
		response.setContentType("text/html;charset=UTF-8");

		String id = request.getParameter("joinid"); // joinPage.jsp에서 입력받은 id
		String pw = request.getParameter("joinpw"); // joinPage.jsp에서 입력받은 pw
		String nickname = request.getParameter("joinnickname"); // joinPage.jsp에서 입력받은 nickname

		String query_insert = "insert into dream.user_info values(null,'" + id + "','" + pw + "','" + nickname + "')";

		try {

			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection("jdbc:mysql://10.10.10.109:3306/dream", "user", "1234");
			System.out.println("DB Connection OK!");

			try {
				stmt = conn.createStatement();
				stmt.executeUpdate(query_insert);
				System.out.println(id);
				System.out.println(pw);
				dis.forward(request, response);

			} catch (Exception e) {
				System.out.println("query insert failed !");
				e.printStackTrace();
			}

		} catch (Exception e) {

			System.out.println("DB Connection error !");
			e.printStackTrace();
		}

		// request.setAttribute("id", id);
		// request.setAttribute("nickname", nickname);

	

	}
	

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
