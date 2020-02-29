
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
 * Servlet implementation class RightClickEventServlet
 */
@WebServlet("/RightClick")
public class RightClickEventServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public RightClickEventServlet() {
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

		String path = request.getParameter("path");
		String index = request.getParameter("index");
		String rpage = request.getParameter("rightpage");
		String cpage = request.getParameter("centerpage");

		request.setAttribute("rightpage", rpage);
		session.setAttribute("centerpage", cpage);

		boolean duplication = false;

		int index_Array = 0;
//		if (StaticC.checkArry.size() > 0) {
		for (int i = 0; i < StaticC.img_path_arr.size(); i++) {
			if (StaticC.img_path_arr.get(i).equals(path)) {
				// ���� �̹����� ��ΰ� ȭ�鿡 �����Ѵٸ� Ʈ��
				duplication = true;
				break;
			}
			index_Array++;
		}
//		}

		if (duplication == false) {

			StaticC.img_path_arr.add(path);
			StaticC.top_arr.add(100);
			StaticC.width_arr.add("");
			StaticC.left_arr.add(300);
			
			if (StaticC.img_path_arr.size() > 0) {
				//�̹��� ��� ���� ����Ʈ�� �����Ͱ� ������ Ʈ��
				for (int i = 0; i < StaticC.img_path_arr.size(); i++) {
					// �̹��� ��� �����ŭ ���ǿ� �̹��� ��ΰ� ����
					session.setAttribute("IMG_PATH" + i, StaticC.img_path_arr.get(i));

				}
			}
			
			RequestDispatcher rd = request.getRequestDispatcher("MainForm.jsp");
			rd.forward(request, response);
			return;

		} else if (duplication == true) {

			StaticC.img_path_arr.remove(index_Array);
			if(StaticC.left_arr.size() > index_Array ) {
				// Ȯ�ι�ư�� �ȴ����� �ٷ� �������� ��� �н�
				StaticC.left_arr.remove(index_Array);
				StaticC.top_arr.remove(index_Array);
				StaticC.width_arr.remove(index_Array);
			}
			for (int i = 0; i < 1000; i++) { // ������ �ִ� �̹����� ����
				if (session.getAttribute("IMG_PATH" + i) == null)
					break;
				session.removeAttribute("IMG_PATH" + i);
			}

			if (StaticC.img_path_arr.size() > 0) { // �ٽ� ���� ����� �̹����� �־��ֱ�
				for (int i = 0; i < StaticC.img_path_arr.size(); i++) {
					session.setAttribute("IMG_PATH" + i, StaticC.img_path_arr.get(i));
				}
			}
			
			if(StaticC.img_path_arr.size() == 0)
			{	// �������� �ƹ��͵� �ȳ��������� �ʱ�ȭ �۾�
				RequestDispatcher back = request.getRequestDispatcher("INIT");
				back.forward(request, response);
				return;
			}

			StaticC.stduplication = true;
			

			RequestDispatcher back = request.getRequestDispatcher("MainForm.jsp");
			back.forward(request, response);
			return;
		}
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
