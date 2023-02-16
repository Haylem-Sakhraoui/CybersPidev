/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entité;

import java.sql.*;
import java.util.ArrayList;

public class Compétences {
    private int id;
    private String nom;
    
    public Compétences(int id, String nom) {
        this.id = id;
        this.nom = nom;
    }
    
     public int getId() {
        return id;
    }
    
    public String getNom() {
        return nom;
    }
    
    public void setId(int id) {
        this.id = id;
    }
    
    public void setNom(String nom) {
        this.nom = nom;
    }
    
    public void ajouterCompetence() {
        Connection conn = null;
        PreparedStatement pstmt = null;
        String sql = "INSERT INTO competences (nom) VALUES (?)";
        
        try {
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/ma_base_de_donnees", "mon_utilisateur", "mon_mot_de_passe");
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, nom);
            pstmt.executeUpdate();
            System.out.println("Competence ajoutee avec succes.");
        } catch (SQLException e) {
            System.out.println("Erreur lors de l'ajout de la competence : " + e.getMessage());
        } finally {
            try {
                if (pstmt != null) pstmt.close();
                if (conn != null) conn.close();
            } catch (SQLException e) {
                System.out.println("Erreur lors de la fermeture des connexions : " + e.getMessage());
            }
        }
    }
    
    public static ArrayList<Compétences> recupererCompetences() {
        ArrayList<Compétences> competences = new ArrayList<>();
        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;
        
        try {
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/esprit", "root", "");
            stmt = conn.createStatement();
            rs = stmt.executeQuery("SELECT * FROM competences");
            while (rs.next()) {
                int id = rs.getInt("id");
                String nom = rs.getString("nom");
                Compétences competence = new Compétences(id, nom);
                competences.add(competence);
            }
        } catch (SQLException e) {
            System.out.println("Erreur lors de la lecture des competences : " + e.getMessage());
        } finally {
            try {
                if (rs != null) rs.close();
                if (stmt != null) stmt.close();
                if (conn != null) conn.close();
            } catch (SQLException e) {
                System.out.println("Erreur lors de la fermeture des connexions : " + e.getMessage());
            }
        }
        return competences;
    }
    
    public void modifierCompetence() {
        Connection conn = null;
        PreparedStatement pstmt = null;
        String sql = "UPDATE compétences SET nom=? WHERE id=?";
        
        try {
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/esprit", "root", "");
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, nom);
            pstmt.setInt(2, id);
            pstmt.executeUpdate();
            System.out.println("Compétence modifiee avec succes.");
        } catch (SQLException e) {
            System.out.println("Erreur lors de la modification de la competence : " + e.getMessage());
        } finally {
            try {
                if (pstmt != null) pstmt.close();
                if (conn != null) conn.close();
            } catch (SQLException e) {
                System.out.println("Erreur lors de la fermeture des connexions : " + e.getMessage());
            }
        }
    }
    
        public void supprimerCompetence() {
        Connection conn = null;
        PreparedStatement pstmt = null;
        String sql = "DELETE FROM compétences WHERE id=?";
        
        try {
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/esprit", "root", "");
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, id);
            pstmt.executeUpdate();
            System.out.println("Compétence supprimee avec succes.");
        } catch (SQLException e) {
            System.out.println("Erreur lors de la suppression de la competence : " + e.getMessage());
        } finally {
            try {
                if (pstmt != null) pstmt.close();
                if (conn != null) conn.close();
            } catch (SQLException e) {
                System.out.println("Erreur lors de la fermeture des connexions : " + e.getMessage());
            }
        }
    }
}

