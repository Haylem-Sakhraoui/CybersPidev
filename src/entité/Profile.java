/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entité;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Profile {
    private int id_profile;
    private String image;
    private String _nom;
    private String titre;
    private String description;
    private double hourly_rate;

    public Profile(int id_profile, String image, String _nom, String titre, String description, double hourly_rate) {
        this.id_profile = id_profile;
        this.image = image;
        this._nom = _nom;
        this.titre = titre;
        this.description = description;
        this.hourly_rate = hourly_rate;
    }

    public int getId_profile() {
        return id_profile;
    }

    public void setId_profile(int id_profile) {
        this.id_profile = id_profile;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getNom() {
        return _nom;
    }

    public void setNom(String nom) {
        this._nom = nom;
    }

    public String getTitre() {
        return titre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getHourlyRate() {
        return hourly_rate;
    }

    public void setHourlyRate(double hourly_rate) {
        this.hourly_rate = hourly_rate;
    }

    public void ajouterProfile() {
        Connection conn = null;
        PreparedStatement pstmt = null;
        String sql = "INSERT INTO profile (image, nom, titre, description, hourly_rate) VALUES (?, ?, ?, ?, ?)";
        
        try {
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/esprit", "root", "");
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, image);
            pstmt.setString(2, _nom);
            pstmt.setString(3, titre);
            pstmt.setString(4, description);
            pstmt.setDouble(5, hourly_rate);
            pstmt.executeUpdate();
            System.out.println("Profil ajouté avec succès.");
        } catch (SQLException e) {
            System.out.println("Erreur lors de l'ajout du profil : " + e.getMessage());
        } finally {
            try {
                if (pstmt != null) pstmt.close();
                if (conn != null) conn.close();
            } catch (SQLException e) {
                System.out.println("Erreur lors de la fermeture des connexions : " + e.getMessage());
            }
        }
    }

    public static List<Profile> recupererProfiles() {
        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;
        List<Profile> profiles = new ArrayList<>();
        String sql = "SELECT * FROM profile";
        
        try {
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/esprit", "root", "");
            stmt = conn.createStatement();
            rs = stmt.executeQuery(sql);
            while (rs.next()) {
                int id_profile = rs.getInt("id_profile");
                String image = rs.getString("image");
                String nom = rs.getString("nom");
                String titre = rs.getString("titre");
                String description = rs.getString("description");
                double hourly_rate = rs.getDouble("hourly_rate");
                Profile profile = new Profile(id_profile, image, nom, titre            , description, hourly_rate);
            profiles.add(profile);
        }
    } catch (SQLException e) {
        System.out.println("Erreur lors de la récupération des profils : " + e.getMessage());
    } finally {
        try {
            if (rs != null) rs.close();
            if (stmt != null) stmt.close();
            if (conn != null) conn.close();
        } catch (SQLException e) {
            System.out.println("Erreur lors de la fermeture des connexions : " + e.getMessage());
        }
    }
    
    return profiles;
}

public void modifierProfile() {
    Connection conn = null;
    PreparedStatement pstmt = null;
    String sql = "UPDATE profile SET image = ?, nom = ?, titre = ?, description = ?, hourly_rate = ? WHERE id_profile = ?";
    
    try {
        conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/esprit", "root", "");
        pstmt = conn.prepareStatement(sql);
        pstmt.setString(1, image);
        pstmt.setString(2, _nom);
        pstmt.setString(3, titre);
        pstmt.setString(4, description);
        pstmt.setDouble(5, hourly_rate);
        pstmt.setInt(6, id_profile);
        pstmt.executeUpdate();
        System.out.println("Profil modifié avec succès.");
    } catch (SQLException e) {
        System.out.println("Erreur lors de la modification du profil : " + e.getMessage());
    } finally {
        try {
            if (pstmt != null) pstmt.close();
            if (conn != null) conn.close();
        } catch (SQLException e) {
            System.out.println("Erreur lors de la fermeture des connexions : " + e.getMessage());
        }
    }
}

public void supprimerProfile() {
    Connection conn = null;
    PreparedStatement pstmt = null;
    String sql = "DELETE FROM profile WHERE id_profile = ?";
    
    try {
        conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/esprit", "root", "");
        pstmt = conn.prepareStatement(sql);
        pstmt.setInt(1, id_profile);
        pstmt.executeUpdate();
        System.out.println("Profil supprimé avec succès.");
    } catch (SQLException e) {
        System.out.println("Erreur lors de la suppression du profil : " + e.getMessage());
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
