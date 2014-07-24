package master;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class MasterServlet
 */
public class MasterServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MasterServlet() {
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
		String uloga = request.getParameter("uloga");
		String logoff = request.getParameter("logoff");
		String poziv = request.getParameter("poziv");
		String naziv = request.getParameter("naziv");
		String from = request.getParameter("from");
		
		//Login
		if(poziv.equals("login")){
			if(uloga != null && uloga.equals("user")){
				RequestDispatcher disp = request.getRequestDispatcher("LoginServlet?uloga=user");
				disp.forward(request, response);	
			}
			if(uloga != null && uloga.equals("admin")){
				RequestDispatcher disp = request.getRequestDispatcher("LoginServlet?uloga=admin");
				disp.forward(request, response);	
			}
			if(logoff != null && logoff.equals("true")){
				RequestDispatcher disp = request.getRequestDispatcher("LoginServlet?logoff=true");
				disp.forward(request, response);	
			}
			
			return;
		}
		
		//dodaj komponentu na racun
		if(poziv.equals("dodajNaRacun")){
			if(from != null){
				RequestDispatcher disp = request.getRequestDispatcher("DodajNaRacunServlet");
				disp.forward(request, response);	
				return;
			}
		}
		
		//pretraga komponenti
		if(poziv.equals("pretragaKomponenti")){
				RequestDispatcher disp = request.getRequestDispatcher("PretragaKomponenteServlet");
					disp.forward(request, response);	
					return;	
		}
		
		//pretraga uredjaja
		if(poziv.equals("pretragaUredjaja")){
				RequestDispatcher disp = request.getRequestDispatcher("PretragaUredjajaServlet");
					disp.forward(request, response);	
					return;
		}

		//trenutni racun
		if(poziv.equals("trenutniRacun")){
				RequestDispatcher disp = request.getRequestDispatcher("TrenutniRacunServlet");
					disp.forward(request, response);	
					return;
		}
		
		//dodavanje uredjaja
		if(poziv.equals("dodajUredjaj")){
				RequestDispatcher disp = request.getRequestDispatcher("DodajUredjajServlet");
					disp.forward(request, response);
					return;
		}
		
		//dodavanje komponente
		if(poziv.equals("dodajKomponentu")){
				RequestDispatcher disp = request.getRequestDispatcher("DodajKomponentuServlet");
					disp.forward(request, response);
					return;
		}
		
		//izmena komponente
		if(poziv.equals("izmenaKomponente")){
				RequestDispatcher disp = request.getRequestDispatcher("IzmenaKomponenteServlet");
					disp.forward(request, response);
					return;
		}
		
		//brisi komponentu
		if(poziv.equals("brisiKomponentu")){
				RequestDispatcher disp = request.getRequestDispatcher("BrisiKomponentuServlet");
					disp.forward(request, response);
					return;
		}
		
		
		//dodaj kategoriju
		if(poziv.equals("dodajKategoriju")){
				RequestDispatcher disp = request.getRequestDispatcher("DodajKategorijuServlet");
					disp.forward(request, response);
					return;
		}
		
		//izmeni komponentu
		if(poziv.equals("izmeniKomponentu")){
				RequestDispatcher disp = request.getRequestDispatcher("IzmeniKomponentuServlet");
					disp.forward(request, response);
					return;
		}
		
		//izmena kategorije
		if(poziv.equals("izmenaKategorije")){
				RequestDispatcher disp = request.getRequestDispatcher("IzmenaKategorijeServlet");
					disp.forward(request, response);
					return;
		}
		
		//izmeni kategoriju
		if(poziv.equals("izmeniKategoriju")){
				RequestDispatcher disp = request.getRequestDispatcher("IzmeniKategorijuServlet");
					disp.forward(request, response);
					return;
		}

		//izmena uredjaja
		if(poziv.equals("izmenaUredjaja")){
				RequestDispatcher disp = request.getRequestDispatcher("IzmenaUredjajaServlet");
					disp.forward(request, response);
					return;
		}
		
		//izmeni uredjaj
		if(poziv.equals("izmeniUredjaj")){
				RequestDispatcher disp = request.getRequestDispatcher("IzmeniUredjajServlet");
					disp.forward(request, response);
					return;
		}
		
		//brisi uredjaj
		if(poziv.equals("brisiUredjaj")){
				RequestDispatcher disp = request.getRequestDispatcher("BrisiUredjajServlet");
					disp.forward(request, response);
					return;
		}
		
		//izvestaj od do
		if(poziv.equals("izvestajOdDo")){
				RequestDispatcher disp = request.getRequestDispatcher("IzvestajOdDoServlet");
					disp.forward(request, response);
					return;
		}
		
		//izvestaj od do kategorija
		if(poziv.equals("izvestajOdDoKategorija")){
				RequestDispatcher disp = request.getRequestDispatcher("IzvestajOdDoKategorijaServlet");
					disp.forward(request, response);
					return;
		}
		
		
		RequestDispatcher disp = request.getRequestDispatcher("/WebShop/pocetna.jsp");
		disp.forward(request, response);
	}

}
