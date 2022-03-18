package com.dao;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.dto.Ville;

@Repository
public class VilleDAO {
	
	
	Connection connexion;
	
	public static Connection getConnection() throws SQLException {
        Connection c = DriverManager.getConnection("jdbc:mysql://localhost:3306/twic", "root", "");
        return c; 
    }
	
	public static List<Ville> recupererVilles() {
        List<Ville> villes = new ArrayList<Ville>();
        Statement statement = null;
        ResultSet resultat = null;
        Connection connexion = null;
        try {
        	connexion = getConnection();
            statement = connexion.createStatement();

            // Exécution de la requête
            resultat = statement.executeQuery("SELECT * FROM ville_france order by Nom_commune ASC;");
            
            // Récupération des données
            while (resultat.next()) {
            	String code = resultat.getString("Code_commune_INSEE");
                String nom = resultat.getString("Nom_commune");
                String codePostal = resultat.getString("Code_Postal");
                String libelle = resultat.getString("Libelle_acheminement");
                String ligne5 = resultat.getString("Ligne_5");
                String latitude = resultat.getString("Latitude");
                String longitude = resultat.getString("Longitude");
                villes.add(new Ville(code, nom, codePostal, libelle, ligne5, latitude, longitude));
            }
        } catch (SQLException e) {
        } finally {
            // Fermeture de la connexion
            try {
                if (resultat != null)
                    resultat.close();
                if (statement != null)
                    statement.close();
                if (connexion != null)
                    connexion.close();
            } catch (SQLException ignore) {
            }
        }
        
        return villes;
    }
	
	public static List<Ville> AjouterVille() {
        List<Ville> villes = new ArrayList<Ville>();
        Statement statement = null;
        ResultSet resultat = null;
        Connection connexion = null;
        try {
        	connexion = getConnection();
            statement = connexion.createStatement();

            // Exécution de la requête
            resultat = statement.executeQuery("SELECT * FROM ville_france order by Nom_commune ASC;");
            
            // Récupération des données
        } catch (SQLException e) {
        } finally {
            // Fermeture de la connexion
            try {
                if (resultat != null)
                    resultat.close();
                if (statement != null)
                    statement.close();
                if (connexion != null)
                    connexion.close();
            } catch (SQLException ignore) {
            }
        }
        
        return villes;
    }

}
