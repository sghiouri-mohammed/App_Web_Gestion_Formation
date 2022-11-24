package com.tp2.Servlets;

import jakarta.annotation.Resource;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

import javax.sql.DataSource;

import com.tp2.dao.FormationDbUtil;
import com.tp2.dao.LieuDbUtil;
import com.tp2.model.Formation;

/**
 * Servlet implementation class UpdateFormationServelt
 */
public class UpdateFormationServelt extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	private FormationDbUtil formationDbUtil;
	private LieuDbUtil lieuDbUtil;

	//JEE Resource injection : Tomcat va injecter l'objet connection pool et l'injecter � la variable datasource
	@Resource(name="jdbc/TP2")
	private DataSource dataSource;
	
	@Override
	public void init() throws ServletException {
		super.init();
		
		// create our student db util ... and pass in the conn pool / datasource
		try {
			formationDbUtil = new FormationDbUtil(dataSource);
			lieuDbUtil = new LieuDbUtil(dataSource);
		}
		catch (Exception exc) {
			throw new ServletException(exc);
		}
	}
    public UpdateFormationServelt() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			request.setAttribute("lieux", lieuDbUtil.getLieux());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		request.setAttribute("idF", request.getParameter("idformation"));
		this.getServletContext().getRequestDispatcher("/WEB-INF/updateFormation.jsp").forward(request, response);
	}

	
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int id_lieu = 0;
		String theme = request.getParameter("theme");

		
		if ( request.getParameter("lieuForm") != null ) {
			id_lieu = Integer.parseInt(request.getParameter("lieuForm"));	
		}
		
		Formation formation = new Formation();
		if ( request.getParameter("idFo") != null) {
			formation.setId(Integer.parseInt(request.getParameter("idFo")));
		}
		formation.setId_lieu(id_lieu);
		formation.setTheme(theme);
		
		formationDbUtil.updateFormation(formation);
		request.setAttribute("success", "La formation est modifiee avec succes ! ");
		
		this.getServletContext().getRequestDispatcher("/WEB-INF/updateFormation.jsp").forward(request, response);
	}

}




