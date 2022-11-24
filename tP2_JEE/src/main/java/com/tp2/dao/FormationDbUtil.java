package com.tp2.dao;
//
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import javax.sql.DataSource;
import com.tp2.model.Formation;
//
public class FormationDbUtil {
	private DataSource dataSource;

	public FormationDbUtil(DataSource theDataSource) {
		dataSource = theDataSource;
	}
	
	public List<Formation> getFormations() throws Exception {
		
		List<Formation> formations = new ArrayList<>();
		
		Connection connexion = null;
		Statement statement =null;
		ResultSet resultat = null;
		
		try {
			connexion = dataSource.getConnection();
			statement =  connexion.createStatement();
			resultat = statement.executeQuery(" select * from Formation");
			
			while(resultat.next()) {
				int id = resultat.getInt("id");
				String theme = resultat.getString("theme");
				int lieu = resultat.getInt("id_lieu");

				
				//creer un user (object)
				Formation tempFormat = new Formation();
				tempFormat.setId(id);
				tempFormat.setId_lieu(lieu);
				tempFormat.setTheme(theme);
				
				//ajouter a la table users
				formations.add(tempFormat);
			}
		 }catch(SQLException e) {
		 }finally {
			try {
				if (resultat != null)
					resultat.close();
				
				if (statement != null)
					statement.close();
				
				if (connexion != null)
					connexion. close();
				}catch (SQLException ignore) {}
			}		
		return formations;
	}
	
	
	public void ajouterFormation(Formation formation)
	{

		Connection connexion = null;
		PreparedStatement preparedstatement;
		
		try {
			connexion = dataSource.getConnection();
			preparedstatement = connexion.prepareStatement("insert into Formation(id,theme,id_lieu) VALUES (?,?,?);");
  			preparedstatement.setInt(1, formation.getId());
			preparedstatement.setString(2, formation.getTheme());
			preparedstatement.setInt(3, formation.getId_lieu());
			preparedstatement.executeUpdate(); 
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void supprimerFormation(int id) {
		Connection connexion = null;
		PreparedStatement preparedstatement;
		
		try {
			connexion = dataSource.getConnection();
			preparedstatement = connexion.prepareStatement("delete from Formation where id=?;");
			preparedstatement.setInt(1, id);
			preparedstatement.executeUpdate(); 
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	
//	

	public void updateFormation( Formation formation ) {
		Connection connexion = null;
		PreparedStatement preparedstatement;
		
		try {
			connexion = dataSource.getConnection();
			preparedstatement = connexion.prepareStatement("update Formation set theme=?, id_lieu=? where id=? ;");
			preparedstatement.setString(1, formation.getTheme());
			preparedstatement.setInt(2, formation.getId_lieu());
			preparedstatement.setInt(3, formation.getId());
			preparedstatement.executeUpdate(); 
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
//	
//	
}
