package com.tp2.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import javax.sql.DataSource;
import com.tp2.model.LigneFormationFormateur;

public class LigneFormationFormateurDbUtil {
	
	private DataSource dataSource;

	public LigneFormationFormateurDbUtil(DataSource theDataSource) {
		dataSource = theDataSource;
	}
	
	public List<LigneFormationFormateur> getLigneFormationFormateur() throws Exception {
		
		List<LigneFormationFormateur> ligneformationformateur = new ArrayList<>();
		
		Connection connexion = null;
		Statement statement =null;
		ResultSet resultat = null;
		
		try {
			
			connexion = dataSource.getConnection();
			statement =  connexion.createStatement();
			resultat = statement.executeQuery(" select * from LignFormationFormateur");
			
			while(resultat.next()) {
				int id_formation = resultat.getInt("id_formation");
				String cin_formateur = resultat.getString("cin_formateur");

				//creer un user (object)
				LigneFormationFormateur tempLigne = new LigneFormationFormateur();
				tempLigne.setCin_formateur(cin_formateur);
				tempLigne.setId_formation(id_formation);				
				//ajouter a la table users
				ligneformationformateur.add(tempLigne);
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
		return ligneformationformateur;
	}
	
	public void ajouterLigneFormationFormateur(LigneFormationFormateur ligne) {
		Connection connexion = null;
		PreparedStatement preparedstatement;
		
		try {
			connexion = dataSource.getConnection();
			preparedstatement = connexion.prepareStatement("insert into LignFormationFormateur(id_formation,cin_formateur) VALUES (?,?);");
			preparedstatement.setInt(1, ligne.getId_formation());
			preparedstatement.setString(2, ligne.getCin_formateur());
			preparedstatement.executeUpdate(); 
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void supprimerLigneFormationFormateur(int id, String cin) {
		Connection connexion = null;
		PreparedStatement preparedstatement;
		
		try {
			connexion = dataSource.getConnection();
			preparedstatement = connexion.prepareStatement("delete from LignFormationFormateur where id_formation = ? and cin_formateur=? ;");
			preparedstatement.setInt(1, id);
			preparedstatement.setString(2, cin);
			preparedstatement.executeUpdate(); 
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
