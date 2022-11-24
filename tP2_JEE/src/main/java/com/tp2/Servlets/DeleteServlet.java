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
import com.tp2.dao.LieuDbUtil;
import com.tp2.dao.LigneFormationFormateurDbUtil;

/**
 * Servlet implementation class DeleteControler
 */
public class DeleteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	private FormateurDbUtil formateurDbUtil;
	private FormationDbUtil formationDbUtil;
	private LieuDbUtil lieuDbUtil;
	private LigneFormationFormateurDbUtil ligneDbUtil;
	
	
	//JEE Resource injection : Tomcat va injecter l'objet connection pool et l'injecter � la variable datasource
	@Resource(name="jdbc/TP2")
	private DataSource dataSource;
	
	@Override
	public void init() throws ServletException {
		super.init();
		
		// create our student db util ... and pass in the conn pool / datasource
		try {
			ligneDbUtil = new LigneFormationFormateurDbUtil(dataSource);
			lieuDbUtil = new LieuDbUtil(dataSource);
			formateurDbUtil = new FormateurDbUtil(dataSource);
			formationDbUtil = new FormationDbUtil(dataSource);
		}
		catch (Exception exc) {
			throw new ServletException(exc);
		}
	}
    public DeleteServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		try {
			request.setAttribute("lignes", ligneDbUtil.getLigneFormationFormateur());
			request.setAttribute("formateurs", formateurDbUtil.getFormateurs());
			request.setAttribute("formations", formationDbUtil.getFormations());
			request.setAttribute("lieux", lieuDbUtil.getLieux());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		String id_formateur = request.getParameter("idformateur");
		String id_formation =request.getParameter("idformation");
		String id_lieu = request.getParameter("idlieu");
		String id_ligne = request.getParameter("idligne");
		String id_ligne2 = request.getParameter("idligne1");
		
		if( id_formateur != null ) {
			formateurDbUtil.supprimerFormateur(id_formateur);
			response.sendRedirect("/tP2_JEE/AdminServlet");
		}
		if( id_formation != null  ) {
			int id_formation1 = Integer.parseInt(id_formation);
			formationDbUtil.supprimerFormation(id_formation1);
			response.sendRedirect("/tP2_JEE/AdminServlet");
		}
		if( id_lieu != null ) {
			int id_lieu1 = Integer.parseInt(id_lieu);
			lieuDbUtil.supprimerFormation(id_lieu1);
			response.sendRedirect("/tP2_JEE/AdminServlet");
		}
		if( id_ligne != null && id_ligne2 != null ) {
			int id_ligne1 = Integer.parseInt(id_ligne);
			ligneDbUtil.supprimerLigneFormationFormateur(id_ligne1, id_ligne2);
			response.sendRedirect("/tP2_JEE/AdminServlet");
		}
	
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		this.getServletContext().getRequestDispatcher("/WEB-INF/Admin.jsp").forward(request, response);
	}

}
