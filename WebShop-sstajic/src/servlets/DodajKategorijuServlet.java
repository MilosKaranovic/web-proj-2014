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
 * Servlet implementation class DodajKategorijuServlet
 */
public class DodajKategorijuServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DodajKategorijuServlet() {
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
		String naziv = request.getParameter("naziv").trim();
		String opis = request.getParameter("opis").trim();
		String podkategorija = request.getParameter("kategorija").trim();

		if(((Data)getServletContext().getAttribute("data")).getKategorije().containsKey(naziv)){
			request.setAttribute("vecpostoji", "da");
			RequestDispatcher disp = request.getRequestDispatcher("admin/kategorije.jsp");
			disp.forward(request, response);
			return;
		}
		
		
		((Data)getServletContext().getAttribute("data")).getKategorije().put(naziv, new Kategorija(naziv, opis, podkategorija));
		
	
		RequestDispatcher disp = request.getRequestDispatcher("admin/kategorije.jsp");
		disp.forward(request, response);
	}

}
