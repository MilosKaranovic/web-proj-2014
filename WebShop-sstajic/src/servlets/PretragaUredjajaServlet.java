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

import org.apache.catalina.tribes.group.interceptors.TwoPhaseCommitInterceptor.MapEntry;

import beans.Data;
import beans.Komponenta;
import beans.Uredjaj;

/**
 * Servlet implementation class PretragaUredjajaServlet
 */
public class PretragaUredjajaServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public PretragaUredjajaServlet() {
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
		String[] komponentePretraga = request.getParameterValues("komponente");
		
		String from = request.getParameter("from");
		
		HashMap<String, Uredjaj> rezultat = new HashMap<String, Uredjaj>();
		HashMap<String, Uredjaj> uredjaji = ((Data) getServletContext().getAttribute("data")).getUredjaji();
		
		if(naziv != null){
			if(uredjaji.containsKey(naziv)){
				rezultat.put(naziv, uredjaji.get(naziv));
			}
		}
	
		if(opis != null){
			for(Entry<String, Uredjaj> mapEntry : uredjaji.entrySet()){
			   if(mapEntry.getValue().getOpis().equals(opis)){
				   rezultat.put(mapEntry.getKey(), mapEntry.getValue());
			   }
			}	
		}
		
		if(komponentePretraga != null){
			for(Entry<String, Uredjaj> mapEntry : uredjaji.entrySet()){
				for (int i = 0; i < komponentePretraga.length; i++) {
					if(!mapEntry.getValue().sadrziKomponentu(komponentePretraga[i])){
						rezultat.remove(mapEntry.getKey());
						break;
					}
					rezultat.put(mapEntry.getKey(), mapEntry.getValue());
				}		
			}
		}
		
		
		if(rezultat.size() == 0){
			rezultat.put("", new Uredjaj());
		}
		request.setAttribute("rezultat", rezultat);
		if(from != null && from.equals("user")){
			RequestDispatcher disp = request.getRequestDispatcher("user/uredjaji.jsp");
			disp.forward(request, response);
		}else{
			RequestDispatcher disp = request.getRequestDispatcher("admin/uredjaji.jsp");
			disp.forward(request, response);
		}
	}

}
