package com.tp2.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import javax.sql.DataSource;

import com.tp2.model.Formateur;
import com.tp2.model.Formation;

public class FormateurDbUtil {
	
	private DataSource dataSource;

	public FormateurDbUtil(DataSource theDataSource) {
		dataSource = theDataSource;
	}
	
	public List<Formateur> getFormateurs() throws Exception {
		
		List<Formateur> formateurs = new ArrayList<>();
		
		Connection connexion = null;
		Statement statement =null;
		ResultSet resultat = null;
		
		try {
			connexion = dataSource.getConnection();
			statement =  connexion.createStatement();
			resultat = statement.executeQuery(" select * from Formateur");
			
			while(resultat.next()) {
				int age = resultat.getInt("age");
				String cin = resultat.getString("CIN");
				String nom = resultat.getString("nom");

				
				//creer un user (object)
				Formateur tempFormat = new Formateur();
				tempFormat.setAge(age);
				tempFormat.setCIN(cin);
				tempFormat.setNom(nom);
				
				//ajouter a la table users
				formateurs.add(tempFormat);
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
		return formateurs;
	}
	
	public void ajouterFormateur(Formateur formateur)
	{

		Connection connexion = null;
		PreparedStatement preparedstatement;
		
		try {
			connexion = dataSource.getConnection();
			preparedstatement = connexion.prepareStatement("insert into Formateur(CIN,nom,age) VALUES (?,?,?);");
			preparedstatement.setString(1, formateur.getCIN());
			preparedstatement.setString(2, formateur.getNom());
			preparedstatement.setInt(3, formateur.getAge());
			preparedstatement.executeUpdate(); 
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void supprimerFormateur(String cin) {
		Connection connexion = null;
		PreparedStatement preparedstatement;
		
		try {
			connexion = dataSource.getConnection();
			preparedstatement = connexion.prepareStatement("delete from Formateur where cin=?;");
			preparedstatement.setString(1, cin);
			preparedstatement.executeUpdate(); 
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}