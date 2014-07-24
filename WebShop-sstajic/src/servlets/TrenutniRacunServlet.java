package servlets;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map.Entry;

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
 * Servlet implementation class TrenutniRacunServlet
 */
public class TrenutniRacunServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public TrenutniRacunServlet() {
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
		
		HttpSession session = request.getSession();
		User user = (User) session.getAttribute("user");
			
		HashMap<String, Komponenta> komponente = ((Data)getServletContext().getAttribute("data")).getKomponente();
		HashMap<String, Uredjaj> uredjaji = ((Data)getServletContext().getAttribute("data")).getUredjaji();
		
		Racun trenutniRacun = user.getTrenutniRacun();
		
		ArrayList<String> racunZaIspis =new ArrayList<String>();
		int i=0;
		
		
		
		for(Entry<String, Komponenta> mapEntry : komponente.entrySet()){
				int n=0;
				for (String komp : trenutniRacun.getKomponente()) {
					if(komp.equals(mapEntry.getValue().getNaziv())){
						n++;
					}
				}
				if(n>0){
					racunZaIspis.add(mapEntry.getValue().getNaziv());
					racunZaIspis.add(String.valueOf(n));
					racunZaIspis.add(String.valueOf(mapEntry.getValue().getCena()));
					i+=3;
				}
		}
		
		for(Entry<String, Uredjaj> mapEntry : uredjaji.entrySet()){
			int n=0;
			for (String ure : trenutniRacun.getUredjaji()) {
				if(ure.equals(mapEntry.getValue().getNaziv())){
					n++;
				}
			}
			if(n>0){
				racunZaIspis.add(mapEntry.getValue().getNaziv());
				racunZaIspis.add(String.valueOf(n));
				double cena=0;
				for (String komp : mapEntry.getValue().getKomponente()) {
					cena += komponente.get(komp).getCena();
				}
				racunZaIspis.add(String.valueOf(cena));
				i+=3;
			}
	    }
		
		request.setAttribute("racunZaIspis", racunZaIspis);
		RequestDispatcher disp = request.getRequestDispatcher("user/racun.jsp");
		disp.forward(request, response);
	}

}
