package servlets;

import java.io.IOException;
import java.util.HashMap;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import beans.Data;
import beans.Komponenta;

/**
 * Servlet implementation class IzmenaKomponenteServlet
 */
public class IzmenaKomponenteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public IzmenaKomponenteServlet() {
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
		
		String naziv = request.getParameter("naziv");
		
		HashMap<String, Komponenta> komponente = ((Data)getServletContext().getAttribute("data")).getKomponente();
		
		Komponenta komponenta = komponente.get(naziv);
		
		request.setAttribute("komponenta", komponenta);
		request.setAttribute("naziv", komponenta.getNaziv());
		RequestDispatcher disp = request.getRequestDispatcher("admin/komponenteIzmena.jsp");
		disp.forward(request, response);
	}

}
