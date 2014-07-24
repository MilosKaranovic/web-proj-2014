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
import beans.Uredjaj;

/**
 * Servlet implementation class IzmenaUredjajaServlet
 */
public class IzmenaUredjajaServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public IzmenaUredjajaServlet() {
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
		
		HashMap<String, Uredjaj> uredjaji = ((Data)getServletContext().getAttribute("data")).getUredjaji();
		
		Uredjaj uredjaj = uredjaji.get(naziv);
		
		request.setAttribute("uredjajIzServleta", uredjaj);
		request.setAttribute("naziv", uredjaj.getNaziv());
		RequestDispatcher disp = request.getRequestDispatcher("admin/uredjajiIzmena.jsp");
		disp.forward(request, response);
	}

}
