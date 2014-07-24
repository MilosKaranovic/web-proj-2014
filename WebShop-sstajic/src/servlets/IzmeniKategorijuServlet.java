package servlets;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import beans.Data;
import beans.Kategorija;
import beans.Komponenta;

/**
 * Servlet implementation class IzmeniKategorijuServlet
 */
public class IzmeniKategorijuServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public IzmeniKategorijuServlet() {
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
		String opis = request.getParameter("opis");
		String podkategorija = request.getParameter("podkategorija");
		
		((Data)getServletContext().getAttribute("data")).getKategorije().remove(naziv);
		((Data)getServletContext().getAttribute("data")).getKategorije().put(naziv, new Kategorija(naziv, opis, podkategorija));
		
	
		RequestDispatcher disp = request.getRequestDispatcher("admin/kategorije.jsp");
		disp.forward(request, response);
	}

}
