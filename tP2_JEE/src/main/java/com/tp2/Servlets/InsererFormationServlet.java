package com.tp2.Servlets;

import jakarta.annotation.Resource;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

import javax.sql.DataSource;

import com.tp2.dao.FormationDbUtil;
import com.tp2.dao.LieuDbUtil;
import com.tp2.model.Formation;


public class InsererFormationServlet extends HttpServlet {
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
 
    public InsererFormationServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			request.setAttribute("lieux", lieuDbUtil.getLieux());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		this.getServletContext().getRequestDispatcher("/WEB-INF/ajouterFormation.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int id = Integer.parseInt(request.getParameter("idF"));
		String theme = request.getParameter("theme");
		int id_form = Integer.parseInt(request.getParameter("lieuForm"));
		
		Formation F1 = new Formation();
		F1.setId(id);
		F1.setTheme(theme);
		if ( request.getParameter("lieuForm").equals("")) {
			F1.setId_lieu(0);
		}else {
			F1.setId_lieu(id_form);
		}
		formationDbUtil.ajouterFormation(F1);
		request.setAttribute("success", "La formation est ajoutee avec succes ! ");
		this.getServletContext().getRequestDispatcher("/WEB-INF/ajouterFormation.jsp").forward(request, response);
	}

}
