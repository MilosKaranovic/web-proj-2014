package servlets;

import java.io.IOException;
import java.util.HashMap;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import beans.Data;
import beans.Kategorija;
import beans.Uredjaj;

/**
 * Servlet implementation class IzmenaKategorijeServlet
 */
public class IzmenaKategorijeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public IzmenaKategorijeServlet() {
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
		
		HashMap<String, Kategorija> kategorije = ((Data)getServletContext().getAttribute("data")).getKategorije();
		
		Kategorija kategorija = kategorije.get(naziv);
		
		request.setAttribute("kategorijaIzServleta", kategorija);
		request.setAttribute("naziv", kategorija.getNaziv());
		RequestDispatcher disp = request.getRequestDispatcher("admin/kategorijeIzmena.jsp");
		disp.forward(request, response);
		
	}

}
