package servlets;

import java.io.IOException;
import java.util.Date;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import beans.Data;
import beans.Komponenta;
import beans.Racun;
import beans.User;

/**
 * Servlet implementation class KupovinaServlet
 */
public class KupovinaServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public KupovinaServlet() {
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
		
		Racun trenutniRacun = user.getTrenutniRacun();
		trenutniRacun.setDatum(new Date());
		((Data)getServletContext().getAttribute("data")).dodajRacun(trenutniRacun);
		
		Racun noviRacun = new Racun();
		noviRacun.setKupac(user.getUsername());
		((User)request.getSession().getAttribute("user")).setTrenutniRacun(noviRacun);
		
		RequestDispatcher disp = request.getRequestDispatcher("user/pocetnaUser.jsp");
		disp.forward(request, response);
	}

}
