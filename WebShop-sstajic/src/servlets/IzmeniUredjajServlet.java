package servlets;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import beans.Data;
import beans.Kategorija;
import beans.Uredjaj;

/**
 * Servlet implementation class IzmeniUredjajServlet
 */
public class IzmeniUredjajServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public IzmeniUredjajServlet() {
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
		String[] komponente = request.getParameterValues("komponente");
		ArrayList<String> listaKomponenti = new ArrayList<String>();
		
		if(komponente != null){
			for (int i = 0; i < komponente.length; i++) {
				listaKomponenti.add(komponente[i]); 
			}
		}
		
		((Data)getServletContext().getAttribute("data")).getUredjaji().remove(naziv);
		((Data)getServletContext().getAttribute("data")).getUredjaji().put(naziv, new Uredjaj(naziv, opis, listaKomponenti));
		
	
		RequestDispatcher disp = request.getRequestDispatcher("admin/uredjaji.jsp");
		disp.forward(request, response);
	}

}
