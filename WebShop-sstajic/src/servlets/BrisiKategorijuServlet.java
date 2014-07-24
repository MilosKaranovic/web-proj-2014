package servlets;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import beans.Data;

/**
 * Servlet implementation class BrisiKategorijuServlet
 */
public class BrisiKategorijuServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public BrisiKategorijuServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		Data podaci = (Data)getServletContext().getAttribute("data");
		
		String naziv = request.getParameter("naziv");
		if(naziv != null){
			podaci.getKategorije().remove(naziv);
			getServletContext().removeAttribute("data");
			getServletContext().setAttribute("data", podaci);

		}
		
		RequestDispatcher disp = request.getRequestDispatcher("admin/kategorije.jsp");
		disp.forward(request, response);
	}

}
