package servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import beans.Data;
import beans.Racun;

/**
 * Servlet implementation class IzvestajOdDoServlet
 */
public class IzvestajOdDoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public IzvestajOdDoServlet() {
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
		ArrayList<String> rezultat = new ArrayList<String>();
		try {
			Date dateOd = new SimpleDateFormat("yyyy-MM-dd").parse(datumOd);
			Date dateDo = new SimpleDateFormat("yyyy-MM-dd").parse(datumDo);
			
			ArrayList<Racun> racuni = ((Data)getServletContext().getAttribute("data")).getRacuni();
			
			DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
			
			double ukupanPrihod = 0;
			dateDo.setDate(dateDo.getDate()+1);
			while(dateOd.before(dateDo)){
				rezultat.add(dateFormat.format(dateOd));
				double prihod = 0;
				for (Racun racun : racuni) {
					if(racun.getDatum().getDate() == dateOd.getDate() && racun.getDatum().getMonth() == dateOd.getMonth() && racun.getDatum().getYear() == dateOd.getYear()){
						prihod += racun.getUkupnaCena() + (racun.getUkupnaCena() * racun.getPorez() / 100);
						prihod = (double)Math.round(prihod * 100) / 100;
					}
				}
				rezultat.add(String.valueOf(prihod));
				ukupanPrihod += prihod;
				dateOd.setDate(dateOd.getDate()+1);
			}
			
			ukupanPrihod = (double)Math.round(ukupanPrihod * 100) / 100;
			request.setAttribute("izvestaj", rezultat);
			request.setAttribute("ukupanPrihod", String.valueOf(ukupanPrihod));
			RequestDispatcher disp = request.getRequestDispatcher("admin/izvestajOdDo.jsp");
			disp.forward(request, response);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			RequestDispatcher disp = request.getRequestDispatcher("admin/pocetnaAdmin.jsp");
			disp.forward(request, response);	
		}
	}

}
