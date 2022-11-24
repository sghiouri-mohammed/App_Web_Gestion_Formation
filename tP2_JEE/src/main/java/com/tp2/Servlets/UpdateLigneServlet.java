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
import com.tp2.dao.FormationDbUtil;
import com.tp2.dao.LigneFormationFormateurDbUtil;

/**
 * Servlet implementation class UpdateLigneServlet
 */
public class UpdateLigneServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private LigneFormationFormateurDbUtil ligneDbUtil;
	private FormationDbUtil formationDbUtil;
	private FormateurDbUtil formateurDbUtil;

	
	//JEE Resource injection : Tomcat va injecter l'objet connection pool et l'injecter � la variable datasource
	@Resource(name="jdbc/TP2")
	private DataSource dataSource;
	
	@Override
	public void init() throws ServletException {
		super.init();
		
		// create our student db util ... and pass in the conn pool / datasource
		
		try {
			ligneDbUtil = new LigneFormationFormateurDbUtil(dataSource);
			formationDbUtil = new FormationDbUtil(dataSource);
			formateurDbUtil = new FormateurDbUtil(dataSource);
		}
		catch (Exception exc) {
			throw new ServletException(exc);
		}
	} 
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UpdateLigneServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
