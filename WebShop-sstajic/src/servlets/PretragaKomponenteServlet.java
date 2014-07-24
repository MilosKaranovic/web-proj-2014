package servlets;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import beans.Data;
import beans.Komponenta;
import beans.User;

/**
 * Servlet implementation class PretragaKomponenteServlet
 */
public class PretragaKomponenteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public PretragaKomponenteServlet() {
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
		String cenaOd = request.getParameter("cenaOd");
		String cenaDo = request.getParameter("cenaDo");
		String kolicinaOd = request.getParameter("kolicinaOd");
		String kolicinaDo = request.getParameter("kolicinaDo");
		String opis = request.getParameter("opis");
		String kategorija = request.getParameter("kategorija");
		
		String from = request.getParameter("from");
		
		HttpSession session = request.getSession();
		User user = (User) session.getAttribute("user");
		
		HashMap<String, Komponenta> rezultat = new HashMap<String, Komponenta>();
		HashMap<String, Komponenta> komponente = ((Data) getServletContext().getAttribute("data")).getKomponente();
		
		
		if(cenaOd != null && cenaDo!=null ){
		if(cenaOd.length()==0 || cenaDo.length()==0 ){
			if(from != null && from.equals("user")){
				RequestDispatcher disp = request.getRequestDispatcher("user/komponente.jsp");
				disp.forward(request, response);
			}else{
				RequestDispatcher disp = request.getRequestDispatcher("admin/komponente.jsp");
				disp.forward(request, response);
			}
			return;
		}
		}
		
		if(kolicinaOd != null && kolicinaDo!=null ){
		if(kolicinaOd.length()==0 || kolicinaDo.length()==0 ){
			if(from != null && from.equals("user")){
				RequestDispatcher disp = request.getRequestDispatcher("user/komponente.jsp");
				disp.forward(request, response);
			}else{
				RequestDispatcher disp = request.getRequestDispatcher("admin/komponente.jsp");
				disp.forward(request, response);
			}
			return;
		}
		}
		
		
		if(naziv != null){
			if(komponente.containsKey(naziv)){
				rezultat.put(naziv, komponente.get(naziv));
			}
		}
		
		if(cenaOd != null && cenaDo != null){
			Double od = Double.parseDouble(cenaOd);
			Double doo = Double.parseDouble(cenaDo);
			for(Entry<String, Komponenta> mapEntry : komponente.entrySet()){
			   if(mapEntry.getValue().getCena() >= od && mapEntry.getValue().getCena() <= doo){
				   rezultat.put(mapEntry.getKey(), mapEntry.getValue());
			   }
			}	
		}
		
		if(kolicinaOd != null && kolicinaDo != null){
			Integer od = Integer.parseInt(kolicinaOd);
			Integer doo = Integer.parseInt(kolicinaDo);
			for(Entry<String, Komponenta> mapEntry : komponente.entrySet()){
			   if(Integer.parseInt(mapEntry.getValue().getKolicina()) >= od && Integer.parseInt(mapEntry.getValue().getKolicina()) <= doo){
				   rezultat.put(mapEntry.getKey(), mapEntry.getValue());
			   }
			}	
		}
	
		if(opis != null){
			for(Entry<String, Komponenta> mapEntry : komponente.entrySet()){
			   if(mapEntry.getValue().getOpis().equals(opis)){
				   rezultat.put(mapEntry.getKey(), mapEntry.getValue());
			   }
			}	
		}
		
		if(kategorija != null){
			for(Entry<String, Komponenta> mapEntry : komponente.entrySet()){
			   if(mapEntry.getValue().getKategorija().equals(kategorija)){
				   rezultat.put(mapEntry.getKey(), mapEntry.getValue());
			   }
			}	
		}
		
		if(rezultat.size() == 0){
			rezultat.put("", new Komponenta());
		}
		
		request.setAttribute("rezultat", rezultat);
		if(from != null && from.equals("user")){
			RequestDispatcher disp = request.getRequestDispatcher("user/komponente.jsp");
			disp.forward(request, response);
		}else{
			RequestDispatcher disp = request.getRequestDispatcher("admin/komponente.jsp");
			disp.forward(request, response);
		}
	}

}
