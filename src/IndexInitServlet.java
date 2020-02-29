
import java.io.IOException;
import java.sql.*;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;



/**
 * Servlet implementation class IndexInitServlet
 */
@WebServlet("/START")
public class IndexInitServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public IndexInitServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub

		HttpSession session = request.getSession();

		Connection conn = null;
		Statement stmt = null;
		PreparedStatement pstmt = null;
		ResultSet rs1 = null;
		ResultSet rs2 = null;
		ResultSet rs3 = null;
		ResultSet rs4 = null;
		Boolean connect = false;

		String[] imgPath_RING = new String[30];
		String[] imgPath_STRING = new String[30];
		String[] imgPath_FEATHER = new String[30];
		String[] imgPath_DECO = new String[30];

		try {
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection("jdbc:mysql://10.10.10.109:3306/dream",
			"user", "1234");
			//conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/dream", "root", "123456");
			System.out.println(conn + ": DB connect");

			String select_img_ring = "select * from dream.dream_img where img_category=1";
			String select_img_string = "select * from dream.dream_img where img_category=2";
			String select_img_feather = "select * from dream.dream_img where img_category=3";
			String select_img_deco = "select * from dream.dream_img where img_category=4";

			String select_img_addfunc = "select * from dream.dream_img where img_category=5";

			stmt = conn.createStatement();
			rs1 = stmt.executeQuery(select_img_ring);

			int i = 0;

			while (rs1.next()) {
				imgPath_RING[i] = rs1.getString(3);
				i++;
			}
			rs2 = stmt.executeQuery(select_img_string);
			i = 0;
			while (rs2.next()) {
				imgPath_STRING[i] = rs2.getString(3);
				i++;
			}
			rs3 = stmt.executeQuery(select_img_feather);
			i = 0;
			while (rs3.next()) {
				imgPath_FEATHER[i] = rs3.getString(3);
				i++;
			}
			rs4 = stmt.executeQuery(select_img_deco);
			i = 0;
			while (rs4.next()) {
				imgPath_DECO[i] = rs4.getString(3);
				i++;
			}

		} catch (Exception e) {
			System.out.println("DB connect ");

		}

		session.setAttribute("RING", imgPath_RING);
		session.setAttribute("STRING", imgPath_STRING);
		session.setAttribute("FEATHER", imgPath_FEATHER);
		session.setAttribute("DECO", imgPath_DECO);

		RequestDispatcher rd = request.getRequestDispatcher("MainForm.jsp");
		rd.forward(request, response);

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
