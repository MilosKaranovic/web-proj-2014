package servlets;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import beans.Data;
import beans.Komponenta;

/**
 * Servlet implementation class DodajKomponentuServlet
 */
public class DodajKomponentuServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DodajKomponentuServlet() {
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
		String cena = request.getParameter("cena");
		String kolicina = request.getParameter("kolicina");
		String opis = request.getParameter("opis");
		String kategorija = request.getParameter("kategorija");
		String link = request.getParameter("link");
		String slika = request.getParameter("slika");
		
		if(((Data)getServletContext().getAttribute("data")).getKomponente().containsKey(naziv)){
			request.setAttribute("vecpostoji", "da");
			RequestDispatcher disp = request.getRequestDispatcher("admin/komponente.jsp");
			disp.forward(request, response);
			return;
		}
		
		((Data)getServletContext().getAttribute("data")).getKomponente().put(naziv, new Komponenta(naziv, Double.parseDouble(cena), kolicina, opis, kategorija, link, slika));
		
	
		RequestDispatcher disp = request.getRequestDispatcher("admin/komponente.jsp");
		disp.forward(request, response);

	}

}
