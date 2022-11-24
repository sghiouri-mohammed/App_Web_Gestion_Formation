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
import com.tp2.dao.LieuDbUtil;
import com.tp2.dao.LigneFormationFormateurDbUtil;


public class AdminServlet extends HttpServlet {
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
    
    public AdminServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		try {
			request.setAttribute("lignes", ligneDbUtil.getLigneFormationFormateur());
			request.setAttribute("formateurs", formateurDbUtil.getFormateurs());
			request.setAttribute("formations", formationDbUtil.getFormations());
			request.setAttribute("lieux", lieuDbUtil.getLieux());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		this.getServletContext().getRequestDispatcher("/WEB-INF/Admin.jsp").forward(request, response);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
