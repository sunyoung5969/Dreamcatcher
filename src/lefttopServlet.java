

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.sound.midi.Soundbank;

import sta.StaticC;

/**
 * Servlet implementation class lefttopServlet
 */
@WebServlet("/lefttop")
public class lefttopServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public lefttopServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		HttpSession session = request.getSession();
		
		int left = Integer.parseInt(String.valueOf(request.getParameter("LEFT")));
		int top = Integer.parseInt(String.valueOf(request.getParameter("TOP")));
		int index = Integer.parseInt(String.valueOf(request.getParameter("INDEX")));
		String width = String.valueOf(request.getParameter("WIDTH"));
		
		System.out.println("index°ª" + index);
		StaticC.left_arr.set(index, left);
		StaticC.top_arr.set(index, top);
		StaticC.width_arr.set(index, width);
		
		RequestDispatcher rs = request.getRequestDispatcher("MainForm.jsp");
		rs.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
