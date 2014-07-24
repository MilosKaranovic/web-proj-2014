package servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import beans.Data;
import beans.User;

/**
 * Servlet implementation class LoginServlet
 */
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public LoginServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		Data podaci = (Data) getServletContext().getAttribute("podaci");

		HttpSession session = request.getSession(false);
		String uname = request.getParameter("username");
		String passwd = request.getParameter("password");
		String uloga = request.getParameter("uloga");
		String logoff = request.getParameter("logoff");
		User user = (User) session.getAttribute("user");

		if (logoff != null && logoff.equals("true")) {
			user.logoff();

			response.setContentType("text/html");
			PrintWriter pout = response.getWriter();
			pout.println("<html>");
			pout.println("<head>");
			pout.println("</head>");
			pout.println("<body>");
			pout.println("Odlogovali ste se!");
			pout.println("<p><a href=" + "\"pocetna.jsp\"" + ">Pocetna</a></p>");
			pout.println("</body>");
			pout.println("</html>");

			/*
			 * RequestDispatcher disp = request
			 * .getRequestDispatcher("pocetna.html"); disp.forward(request,
			 * response);
			 */
			return;
		}

		if (user != null && (logoff!=null || uloga!=null)) {
			if (uname != null && passwd != null) {
				user.setUsername(uname);
				user.setPassword(passwd);
				user.setUloga(uloga);
				if (uloga.equals("user")) {
					user.login(podaci.getKorisnici());
				} else {
					user.login(podaci.getAdministratori());
				}
				if (user.isLoggedIn() && user.getUloga().equals("admin")) {
					user.getTrenutniRacun().setKupac(user.getUsername());
					session.setAttribute("user", user);
					RequestDispatcher disp = request
							.getRequestDispatcher("admin/pocetnaAdmin.jsp");
					disp.forward(request, response);
				}
				if (user.isLoggedIn() && user.getUloga().equals("user")) {
					user.getTrenutniRacun().setKupac(user.getUsername());
					session.setAttribute("user", user);
					RequestDispatcher disp = request
							.getRequestDispatcher("user/pocetnaUser.jsp");
					disp.forward(request, response);
				} 
				if(!user.isLoggedIn()){
					RequestDispatcher disp = getServletContext()
							.getRequestDispatcher("/loginUser/results.jsp");
					disp.forward(request, response);
				}
			} 
		} else {
			// ako user ne postoji, neko je pokusao direktno da gadja ovaj
			// servlet

			RequestDispatcher disp = request
					.getRequestDispatcher("pocetna.jsp");
			// redirektovacemo na pocetnu stranicu
			disp.forward(request, response);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
