package servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import beans.Data;
import beans.Komponenta;
import beans.Racun;
import beans.Uredjaj;
import beans.User;

/**
 * Servlet implementation class DodajNaRacunServlet
 */
public class DodajNaRacunServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DodajNaRacunServlet() {
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
		String from = request.getParameter("from");
		String komada = request.getParameter("komada");
		
		if(naziv.length()>0 && from.length()>0 && komada.length()>0 && komada != null){
		
		HttpSession session = request.getSession();
		User user = (User) session.getAttribute("user");
			
		HashMap<String, Komponenta> komponente = ((Data)getServletContext().getAttribute("data")).getKomponente();
		HashMap<String, Uredjaj> uredjaji = ((Data)getServletContext().getAttribute("data")).getUredjaji();
		
		Racun trenutniRacun = user.getTrenutniRacun();
		
		if(from != null && from.equals("komponenta")){
			for(int i = 0; i<Integer.parseInt(komada); i++){
				trenutniRacun.dodajKomponentu(naziv);
				
				
				double cena = trenutniRacun.getUkupnaCena() + komponente.get(naziv).getCena();
				trenutniRacun.setUkupnaCena(cena);
			}
			trenutniRacun.setKupac(user.getUsername());
			((User)request.getSession().getAttribute("user")).setTrenutniRacun(trenutniRacun);
			
			RequestDispatcher disp = request.getRequestDispatcher("user/komponente.jsp");
			disp.forward(request, response);
		}
		
		if(from != null && from.equals("uredjaj")){
			for(int i = 0; i<Integer.parseInt(komada); i++){
				trenutniRacun.dodajUredjaj(naziv);
				Uredjaj uredjaj = uredjaji.get(naziv);
				for (String komp : uredjaj.getKomponente()) {
					double cena = trenutniRacun.getUkupnaCena() + komponente.get(komp).getCena();
					trenutniRacun.setUkupnaCena(cena);	
				}
			}
			trenutniRacun.setKupac(user.getUsername());
			((User)request.getSession().getAttribute("user")).setTrenutniRacun(trenutniRacun);
			
			RequestDispatcher disp = request.getRequestDispatcher("user/uredjaji.jsp");
			disp.forward(request, response);
		}
		}
		else{
			if(from != null && from.equals("uredjaj")){
			RequestDispatcher disp = request.getRequestDispatcher("user/uredjaji.jsp");
			disp.forward(request, response);
			}
			
			if(from != null && from.equals("komponenta")){
				RequestDispatcher disp = request.getRequestDispatcher("user/komponente.jsp");
				disp.forward(request, response);
				}
		}
	}

}
