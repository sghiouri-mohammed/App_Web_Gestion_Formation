package com.tp2.Servlets;

import jakarta.annotation.Resource;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

import javax.sql.DataSource;

import com.tp2.dao.FormateurDbUtil;
import com.tp2.dao.FormationDbUtil;
import com.tp2.dao.LigneFormationFormateurDbUtil;
import com.tp2.model.LigneFormationFormateur;

/**
 * Servlet implementation class InsererLigneFormationFormateurServlet
 */
public class InsererLigneFormationFormateurServlet extends HttpServlet {
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
	
    public InsererLigneFormationFormateurServlet() {
        super();
        // TODO Auto-generated constructor stub
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			request.setAttribute("formateurs", formateurDbUtil.getFormateurs());
			request.setAttribute("formations", formationDbUtil.getFormations());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		this.getServletContext().getRequestDispatcher("/WEB-INF/ajouterLigne.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int d = 0;
		int id_formation = Integer.parseInt(request.getParameter("id"));
		String cin_form = request.getParameter("cin");
		try {
			for (LigneFormationFormateur ligne : ligneDbUtil.getLigneFormationFormateur()) {
				if ( ligne.getCin_formateur().equals(cin_form) && ligne.getId_formation() == id_formation ) {
					d=1;
				}
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if ( d==1 ) {
			request.setAttribute("success", "Ce formateur possede deja cette formation !!  ");
		}else {
			LigneFormationFormateur ligneform = new LigneFormationFormateur();
			ligneform.setCin_formateur(cin_form);
			ligneform.setId_formation(id_formation);
			ligneDbUtil.ajouterLigneFormationFormateur(ligneform);
			request.setAttribute("success", "La ligne est ajoute avec succes ! ");
		}
				
		this.getServletContext().getRequestDispatcher("/WEB-INF/ajouterLigne.jsp").forward(request, response);;
	}

}
