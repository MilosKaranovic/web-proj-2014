package servlets;

import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map.Entry;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import beans.Data;
import beans.Kategorija;
import beans.Komponenta;
import beans.Racun;
import beans.Uredjaj;

/**
 * Servlet implementation class IzvestajOdDoKategorijaServlet
 */
public class IzvestajOdDoKategorijaServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public IzvestajOdDoKategorijaServlet() {
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
		String datumOd = request.getParameter("datumOd");
		String datumDo = request.getParameter("datumDo");
		String kategorija = request.getParameter("kategorija");
		ArrayList<String> prviRed = new ArrayList<String>();
		ArrayList<String> drugiRed = new ArrayList<String>();
		ArrayList<String> rezultat = new ArrayList<String>();
		ArrayList<String> prihod = new ArrayList<String>();
		
		
		
		
		HashMap<String, Komponenta> komponente = ((Data)getServletContext().getAttribute("data")).getKomponente();
		HashMap<String, Uredjaj> uredjaji = ((Data)getServletContext().getAttribute("data")).getUredjaji();
		HashMap<String, Kategorija> kategorije = ((Data)getServletContext().getAttribute("data")).getKategorije();
		
		//Nalazenje svih podkategorija
		ArrayList<String> sveKategorije = new ArrayList<String>();
		sveKategorije.add(kategorija);
		for(Entry<String, Kategorija> mapEntry : kategorije.entrySet()){
			if(mapEntry.getValue().getPodkategorija().equals(kategorija)){
				sveKategorije.add(mapEntry.getValue().getNaziv());
			}
		}
		
		//Nalazenje svih komponenti iz kategorije
		ArrayList<String> komponenteIzKategorije = new ArrayList<String>();
		for(Entry<String, Komponenta> mapEntry : komponente.entrySet()){
			if(komponentaUKategoriji(mapEntry.getValue().getKategorija(), sveKategorije)){
				komponenteIzKategorije.add(mapEntry.getValue().getNaziv());
			}
		}
		
		try {
			Date dateOd = new SimpleDateFormat("yyyy-MM-dd").parse(datumOd);
			Date dateDo = new SimpleDateFormat("yyyy-MM-dd").parse(datumDo);
			
			ArrayList<Racun> racuni = ((Data)getServletContext().getAttribute("data")).getRacuni();
			
			DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
			
			prviRed.add(kategorija);
			prviRed.add(dateFormat.format(dateOd));
			prviRed.add(dateFormat.format(dateDo));
			
			
			Date doPlusJedanDan = dateDo;
			doPlusJedanDan.setDate(doPlusJedanDan.getDate() + 1);
			double ukupanPrihod = 0;
			int prodatoKomponenti = 0;
			for (String komponentaKategorije : komponenteIzKategorije) {
				double prihodKomponente = 0;
					for (Racun racun : racuni) {
						
						if(racun.getDatum().after(dateOd) && racun.getDatum().before(doPlusJedanDan)){
	
						for (String komponentaRacuna : racun.getKomponente()) {
							if(komponentaRacuna.equals(komponentaKategorije)){
								prodatoKomponenti++;
								prihodKomponente += komponente.get(komponentaKategorije).getCena() + (komponente.get(komponentaKategorije).getCena() * racun.getPorez() / 100);
							}
						}
						
						for (String uredjajRacuna : racun.getUredjaji()) {
							for (String komponentaUredjaja : uredjaji.get(uredjajRacuna).getKomponente()) {
								if(komponentaUredjaja.equals(komponentaKategorije)){
									prodatoKomponenti++;
									prihodKomponente += komponente.get(komponentaKategorije).getCena() + (komponente.get(komponentaKategorije).getCena() * racun.getPorez() / 100);;
								}
							}
						}
						}
						
					}
				
				ukupanPrihod += prihodKomponente;
				prihodKomponente = (double)Math.round(prihodKomponente * 100) / 100;
				rezultat.add(komponentaKategorije);
				rezultat.add(String.valueOf(prihodKomponente));
			}
				
			
			drugiRed.add("Prodato komponenti:");
			drugiRed.add(String.valueOf(prodatoKomponenti));
			
			ukupanPrihod = (double)Math.round(ukupanPrihod * 100) / 100;
			prihod.add("Ukupan prihod:");
			prihod.add(String.valueOf(ukupanPrihod));
			
			request.setAttribute("prviRed", prviRed);
			request.setAttribute("drugiRed", drugiRed);
			request.setAttribute("rezultat", rezultat);
			request.setAttribute("ukupanPrihod", prihod);
			RequestDispatcher disp = request.getRequestDispatcher("admin/izvestajOdDoKategorija.jsp");
			disp.forward(request, response);	
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			RequestDispatcher disp = request.getRequestDispatcher("admin/pocetnaAdmin.jsp");
			disp.forward(request, response);	
		}
		
	}

	
	private boolean komponentaUKategoriji(String kategorija, ArrayList<String> kategorije){
		
		for (String kat : kategorije) {
			if(kategorija.equals(kat)){
				return true;
			}
		}
		
		return false;
	}
}
