package servlets;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import beans.Data;

/**
 * Servlet implementation class for Servlet: HelloServlet
 * 
 */
public class PocetniServlet extends javax.servlet.http.HttpServlet implements javax.servlet.Servlet {

	private static final long serialVersionUID = -617497354114194809L;

	public PocetniServlet() {
		super();
		
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		Data podaci = new Data();
		if(getServletContext().getAttribute("podaci") == null){
			getServletContext().setAttribute("podaci", podaci);
			request.getSession().invalidate();
		}		
		RequestDispatcher disp = request.getRequestDispatcher("pocetna.jsp");
		disp.forward(request, response);

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		

		
		

	}
}