package com.tp2.Servlets;

import jakarta.annotation.Resource;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

import javax.sql.DataSource;

import com.tp2.dao.LieuDbUtil;
import com.tp2.model.Formateur;
import com.tp2.model.Lieu;

/**
 * Servlet implementation class InsererLieuServlet
 */
public class InsererLieuServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	LieuDbUtil lieuDbUtil;
    
	//JEE Resource injection : Tomcat va injecter l'objet connection pool et l'injecter � la variable datasource
	@Resource(name="jdbc/TP2")
	private DataSource dataSource;
	
	@Override
	public void init() throws ServletException {
		super.init();
		
		// create our student db util ... and pass in the conn pool / datasource
		try {
			lieuDbUtil = new LieuDbUtil(dataSource);
		}
		catch (Exception exc) {
			throw new ServletException(exc);
		}
	}
	
    public InsererLieuServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

    
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		this.getServletContext().getRequestDispatcher("/WEB-INF/ajouterLieu.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int id = Integer.parseInt(request.getParameter("idL"));
		String adresse = request.getParameter("adresse");
		String ville = request.getParameter("ville");
		
		Lieu lieu = new Lieu();
		lieu.setId(id);
		lieu.setAdresse(adresse);
		lieu.setVille(ville);
		lieuDbUtil.ajouterLieu(lieu);
		request.setAttribute("success", "Le lieu est ajoute avec succes ! ");
		
		this.getServletContext().getRequestDispatcher("/WEB-INF/ajouterLieu.jsp").forward(request, response);
	}

}
