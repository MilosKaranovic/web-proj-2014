package servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import beans.Data;
import beans.Komponenta;

/**
 * Servlet implementation class BrisiKomponentuServlet
 */
public class BrisiKomponentuServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public BrisiKomponentuServlet() {
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
			podaci.getKomponente().remove(naziv);
			getServletContext().removeAttribute("data");
			getServletContext().setAttribute("data", podaci);
			
		/*	response.setContentType("text/html");
			PrintWriter pout = response.getWriter();
			pout.println("<html>");
			pout.println("<head>");
			pout.println("</head>");
			pout.println("<body>");
			pout.println(naziv);
			pout.println(podaci.getKomponente().size());
			pout.println("</body>");
			pout.println("</html>");*/
		}
		
		RequestDispatcher disp = request.getRequestDispatcher("admin/komponente.jsp");
		disp.forward(request, response);
		
	}

}
