package com.tp2.Servlets;

import jakarta.annotation.Resource;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

import javax.sql.DataSource;

import com.tp2.dao.FormateurDbUtil;
import com.tp2.model.Formateur;

/**
 * Servlet implementation class InsererFormateurServlet
 */
public class InsererFormateurServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	private FormateurDbUtil formateurDbUtil;

	//JEE Resource injection : Tomcat va injecter l'objet connection pool et l'injecter � la variable datasource
	@Resource(name="jdbc/TP2")
	private DataSource dataSource;
	
	@Override
	public void init() throws ServletException {
		super.init();
		
		// create our student db util ... and pass in the conn pool / datasource
		try {
			formateurDbUtil = new FormateurDbUtil(dataSource);
		}
		catch (Exception exc) {
			throw new ServletException(exc);
		}
	}
    public InsererFormateurServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.getServletContext().getRequestDispatcher("/WEB-INF/ajouterFormateur.jsp").forward(request, response);
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int age = Integer.parseInt(request.getParameter("age"));
		String nom = request.getParameter("name");
		String cin = request.getParameter("cin");
		
		Formateur formateur = new Formateur();
		formateur.setAge(age);
		formateur.setCIN(cin);
		formateur.setNom(nom);
		formateurDbUtil.ajouterFormateur(formateur);
		
		request.setAttribute("success", "Le formateur est ajoute avec succes ! ");
		
		this.getServletContext().getRequestDispatcher("/WEB-INF/ajouterFormateur.jsp").forward(request, response);
	}

}
