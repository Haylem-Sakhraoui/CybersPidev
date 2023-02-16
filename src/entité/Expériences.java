/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entité;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class Expériences {
    private int id;
    private String nom_entreprise;
    private String poste;
    private java.util.Date date_debut;
    private java.util.Date date_fin;
    
    public Expériences(int id, String nom_entreprise, String poste, java.util.Date date_debut, java.util.Date date_fin) {
        this.id = id;
        this.nom_entreprise = nom_entreprise;
        this.poste = poste;
        this.date_debut = date_debut;
        this.date_fin = date_fin;
    }
    
    public int getId() {
        return id;
    }
    
    public String getNomEntreprise() {
        return nom_entreprise;
    }
    
    public String getPoste() {
        return poste;
    }
    
    public java.util.Date getDateDebut() {
        return date_debut;
    }
    
    public java.util.Date getDateFin() {
        return date_fin;
    }
    
    public void setId(int id) {
        this.id = id;
    }
    
    public void setNomEntreprise(String nom_entreprise) {
        this.nom_entreprise = nom_entreprise;
    }
    
    public void setPoste(String poste) {
        this.poste = poste;
    }
    
    public void setDateDebut(java.util.Date date_debut) {
        this.date_debut = date_debut;
    }
    
    public void setDateFin(java.util.Date date_fin) {
        this.date_fin = date_fin;
    }
    
    public void ajouterExperience() {
        Connection conn = null;
        PreparedStatement pstmt = null;
        String sql = "INSERT INTO expériences (id, nom_entreprise, poste, date_debut, date_fin) VALUES (?, ?, ?, ?, ?)";
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        
        try {
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/ma_base_de_donnees", "mon_utilisateur", "mon_mot_de_passe");
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, id);
            pstmt.setString(2, nom_entreprise);
            pstmt.setString(3, poste);
            pstmt.setString(4, sdf.format(date_debut));
            pstmt.setString(5, sdf.format(date_fin));
            pstmt.executeUpdate();
            System.out.println("Expérience ajoutee avec succes.");
        } catch (SQLException e) {
            System.out.println("Erreur lors de l'ajout de l'experience : " + e.getMessage());
        } finally {
            try {
                if (pstmt != null) pstmt.close();
                if (conn != null) conn.close();
            } catch (SQLException e) {
                System.out.println("Erreur lors de la fermeture des connexions : " + e.getMessage());
            }
        }
    }
    
    public static List<Expériences> listerExperiences() {
        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;
        List<Expériences> experiences = new ArrayList<>();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        
        try {
        conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/esprit", "root", "");
        stmt = conn.createStatement();
        rs = stmt.executeQuery("SELECT * FROM expériences");
        while (rs.next()) {
            int id = rs.getInt("id");
            String nom_entreprise = rs.getString("nom_entreprise");
            String poste = rs.getString("poste");
            java.util.Date date_debut = sdf.parse(rs.getString("date_debut"));
            java.util.Date date_fin = sdf.parse(rs.getString("date_fin"));
            Expériences experience = new Expériences(id, nom_entreprise, poste, date_debut, date_fin);
            experiences.add(experience);
        }
    } catch (SQLException e) {
        System.out.println("Erreur lors de la lecture des experiences : " + e.getMessage());
    } catch (ParseException e) {
        System.out.println("Erreur : " + e.getMessage());
    } finally {
        try {
            if (rs != null) rs.close();
            if (stmt != null) stmt.close();
            if (conn != null) conn.close();
        } catch (SQLException e) {
            System.out.println("Erreur lors de la fermeture des connexions : " + e.getMessage());
        }
    }
    return experiences;
}

public void modifierExperience() {
    Connection conn = null;
    PreparedStatement pstmt = null;
    String sql = "UPDATE expériences SET nom_entreprise=?, poste=?, date_debut=?, date_fin=? WHERE id=?";
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
    
    try {
        conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/esprit", "root", "");
        pstmt = conn.prepareStatement(sql);
        pstmt.setString(1, nom_entreprise);
        pstmt.setString(2, poste);
        pstmt.setString(3, sdf.format(date_debut));
        pstmt.setString(4, sdf.format(date_fin));
        pstmt.setInt(5, id);
        pstmt.executeUpdate();
        System.out.println("Experience modifiee avec succes.");
    } catch (SQLException e) {
        System.out.println("Erreur lors de la modification de l'experience : " + e.getMessage());
    } finally {
        try {
            if (pstmt != null) pstmt.close();
            if (conn != null) conn.close();
        } catch (SQLException e) {
            System.out.println("Erreur lors de la fermeture des connexions : " + e.getMessage());
        }
    }
}

public void supprimerExperience() {
    Connection conn = null;
    PreparedStatement pstmt = null;
    String sql = "DELETE FROM expériences WHERE id=?";
    
    try {
        conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/esprit", "root", "");
        pstmt = conn.prepareStatement(sql);
        pstmt.setInt(1, id);
        pstmt.executeUpdate();
        System.out.println("Experience supprimee avec succes.");
    } catch (SQLException e) {
        System.out.println("Erreur lors de la suppression de l'experience : " + e.getMessage());
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
