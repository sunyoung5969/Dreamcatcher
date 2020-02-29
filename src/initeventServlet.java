

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import sta.StaticC;

/**
 * Servlet implementation class initeventServlet
 */
@WebServlet("/INIT")
public class initeventServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public initeventServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		HttpSession session = request.getSession();

		for(int i =0; i<1000; i++) {
			session.removeAttribute("IMG_PATH"+i);
		}

		StaticC.img_path_arr.clear();
		StaticC.left_arr.clear();
		StaticC.top_arr.clear();
		StaticC.width_arr.clear();
		StaticC.stduplication = false;
		StaticC.arrycheck = false;
		
		RequestDispatcher rd = request.getRequestDispatcher("MainForm.jsp");
		rd.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
