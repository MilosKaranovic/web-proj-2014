package servlets;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import beans.Data;
import beans.Uredjaj;
import beans.User;

/**
 * Servlet implementation class DodajUredjajServlet
 */
public class DodajUredjajServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DodajUredjajServlet() {
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
		String[] komponente = request.getParameterValues("komponente");
		ArrayList<String> listaKomponenti = new ArrayList<String>();
		String[] komada = request.getParameterValues("komada");

		
		if(komponente != null){
			for (int i = 0; i < komponente.length; i++) {
				for(int k=0; k < Integer.parseInt(komada[i]); k++){
					listaKomponenti.add(komponente[i]); 
				}
			}
		}
		
		//provera da li vec postoji
		if(((Data)getServletContext().getAttribute("data")).getUredjaji().containsKey(naziv)){
			request.setAttribute("vecpostoji", "da");
			if(  ((User)request.getSession().getAttribute("user")).getUloga().equals("user")){
				RequestDispatcher disp = request.getRequestDispatcher("user/uredjaji.jsp");
				disp.forward(request, response);
			}else{
				RequestDispatcher disp = request.getRequestDispatcher("admin/uredjaji.jsp");
				disp.forward(request, response);
			}
			return;
		}
		

		
		((Data)getServletContext().getAttribute("data")).getUredjaji().put(naziv, new Uredjaj(naziv, opis, listaKomponenti));
		
		if(  ((User)request.getSession().getAttribute("user")).getUloga().equals("user")){
			RequestDispatcher disp = request.getRequestDispatcher("user/uredjaji.jsp");
			disp.forward(request, response);
		}else{
			RequestDispatcher disp = request.getRequestDispatcher("admin/uredjaji.jsp");
			disp.forward(request, response);
		}
	}

}
