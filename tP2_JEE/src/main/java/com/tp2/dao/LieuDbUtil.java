package com.tp2.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import javax.sql.DataSource;

import com.tp2.model.Formation;
import com.tp2.model.Lieu;

public class LieuDbUtil {
	private DataSource dataSource;

	public LieuDbUtil(DataSource theDataSource) {
		dataSource = theDataSource;
	}
	
	public List<Lieu> getLieux() throws Exception {
		
		List<Lieu> lieux = new ArrayList<>();
		
		Connection connexion = null;
		Statement statement =null;
		ResultSet resultat = null;
		
		try {
			connexion = dataSource.getConnection();
			statement =  connexion.createStatement();
			resultat = statement.executeQuery(" select * from Lieu");
			
			while(resultat.next()) {
				int id = resultat.getInt("id");
				String adresse = resultat.getString("adresse");
				String ville = resultat.getString("ville");

				//creer un user (object)
				Lieu tempFormat = new Lieu();
				tempFormat.setAdresse(adresse);
				tempFormat.setId(id);
				tempFormat.setVille(ville);
				
				//ajouter a la table users
				lieux.add(tempFormat);
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
		return lieux;
	}
	
	public void ajouterLieu(Lieu lieu) {
		Connection connexion = null;
		PreparedStatement preparedstatement;
		
		try {
			connexion = dataSource.getConnection();
			preparedstatement = connexion.prepareStatement("insert into Lieu(id,adresse,ville) VALUES (?,?,?);");
			preparedstatement.setInt(1, lieu.getId());
			preparedstatement.setString(2, lieu.getAdresse());
			preparedstatement.setString(3,lieu.getVille() );
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
			preparedstatement = connexion.prepareStatement("delete from Lieu where id=?;");
			preparedstatement.setInt(1, id);
			preparedstatement.executeUpdate(); 
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void updateLieu( Lieu lieu ) {
		Connection connexion = null;
		PreparedStatement preparedstatement;
		
		try {
			connexion = dataSource.getConnection();
			preparedstatement = connexion.prepareStatement("update Lieu set adresse=?, ville=? where id=? ;");
			preparedstatement.setString(1,lieu.getAdresse() );
			preparedstatement.setString(2,lieu.getVille());
			preparedstatement.setInt(3,lieu.getId() );
			preparedstatement.executeUpdate(); 
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
