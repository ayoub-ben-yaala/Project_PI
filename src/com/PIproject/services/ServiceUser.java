/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.PIproject.services;

import com.PIproject.entities.Livreur;
import com.PIproject.entities.SousPharmacie;
import com.PIproject.entities.SuperPharmacie;
import com.PIproject.entities.User;
import com.PIproject.utils.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javax.swing.JOptionPane;
import org.mindrot.jbcrypt.BCrypt;


/**
 *
 * @Ayoub Ben Yaala
 */

public class ServiceUser {
    
    private Connection cnx = DataSource.getInstance().getCnx();
    
    public void ajouter(User user) {
        try {
            
         switch (user.getClass().getSimpleName()) {
            case "Livreur":
            String reqLivreur = "INSERT INTO User(UserName,Email,Password,Phone, Adress,CIN,Role,Statut) VALUES (?,?,?,?,?,?,?,?);";
            PreparedStatement pstLivreur = cnx.prepareStatement(reqLivreur);
            pstLivreur.setString(1, user.getUserName());
            pstLivreur.setString(2, user.getEmail());
            pstLivreur.setString(3,user.getPassword() );
            pstLivreur.setInt(4, user.getPhone());
            pstLivreur.setString(5, user.getAdress());
            pstLivreur.setInt(6, ((Livreur) user).getCin());
            pstLivreur.setString(7,"Livreur");
            pstLivreur.setString(8,"En Attente");

            pstLivreur.executeUpdate();
            System.out.println("Livreur ajoutée !");
            break;
            
            case "SousPharmacie":
            String reqSousPharmacie = "INSERT INTO User(UserName,Email,Password,Phone, Adress,NomPharmacie,Role,Statut) VALUES (?,?,?,?,?,?,?,?);";
            PreparedStatement pstSousPharmacie = cnx.prepareStatement(reqSousPharmacie);
            pstSousPharmacie.setString(1, user.getUserName());
            pstSousPharmacie.setString(2, user.getEmail());
            pstSousPharmacie.setString(3,user.getPassword() );
            pstSousPharmacie.setInt(4, user.getPhone());
            pstSousPharmacie.setString(5, user.getAdress());
            pstSousPharmacie.setString(6, ((SousPharmacie) user).getNomPharmacie());
            pstSousPharmacie.setString(7,"SousPharmacie");
            pstSousPharmacie.setString(8,"suspendue");
            pstSousPharmacie.executeUpdate();
            System.out.println("Sous Pharmacie ajoutée !");
            break;
            /*
            case "SuperPharmacie":
            String reqSuperPharmacie = "INSERT INTO User(UserName,Email,Password,Phone, Adress,MatriculeFiscale,Role) VALUES (?,?,?,?,?,?,?);";
            PreparedStatement pstSuperPharmacie = cnx.prepareStatement(reqSuperPharmacie);
            pstSuperPharmacie.setString(1, user.getUserName());
            pstSuperPharmacie.setString(2, user.getEmail());
            pstSuperPharmacie.setString(3, user.getPassword());
            pstSuperPharmacie.setInt(4, user.getPhone());
            pstSuperPharmacie.setString(5, user.getAdress());
            pstSuperPharmacie.setString(6,((SuperPharmacie) user).getMatriculeFiscale());
            pstSuperPharmacie.setString(7,"SuperPharmacie"); 
            pstSuperPharmacie.executeUpdate();
            System.out.println("Super Pharmacie ajoutée !");
            break;    */ 
             }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
   
    

public void modifier(User user) {
    try {
        String req;
        if (user instanceof Livreur) {
            req = "UPDATE User SET UserName=?, Email=?, Password=?, Phone=?, Adress=?, CIN=?  WHERE IdUser=?";
        } else if (user instanceof SousPharmacie) {
            req = "UPDATE User SET UserName=?, Email=?, Password=?, Phone=?, Adress=?, NomPharmacie=?, MatriculeFiscale=? WHERE IdUser=?";
        } else {
            System.out.println("Type d'utilisateur non pris en charge");
            return;
        }

        PreparedStatement pst = cnx.prepareStatement(req);
        pst.setString(1, user.getUserName());
        pst.setString(2, user.getEmail());
        pst.setString(3,user.getPassword() );
        pst.setInt(4, user.getPhone());
        pst.setString(5, user.getAdress());
        
        if (user instanceof Livreur) {
            pst.setInt(6, ((Livreur) user).getCin());
            pst.setInt(7, user.getIdUser());
        } else if (user instanceof SousPharmacie) {
            pst.setString(6, ((SousPharmacie) user).getNomPharmacie());
            pst.setString(7, ((SousPharmacie) user).getMatriculeFiscale());
            pst.setInt(8, user.getIdUser());
        }

        
        
        pst.executeUpdate();
        System.out.println("Utilisateur modifié !");
    } catch (SQLException ex) {
        System.out.println(ex.getMessage());
    }
}
    public void modifier1(User user) {
        /*
        try {
            
            switch (user.getClass().getSimpleName()) {
            case "Livreur":
            String reqLivreur = "UPDATE User SET UserName=?, Email=?, Password=?, Phone=?, Adress=?,CIN=? WHERE IdUser=?";
            PreparedStatement pstLivreur = cnx.prepareStatement(reqLivreur);
            pstLivreur.setInt(7,user.getIdUser());
            pstLivreur.setString(1, user.getUserName());
            pstLivreur.setString(2, user.getEmail());
            pstLivreur.setString(3, user.getPassword());
            pstLivreur.setInt(4, user.getPhone());
            pstLivreur.setString(5, user.getAdress());
            pstLivreur.setInt(6, ((Livreur) user).getCin());
            pstLivreur.executeUpdate();
            System.out.println("Livreur modifiée !");
            break;
            
            case "SousPharmacie":
            String reqSousPharmacie = "UPDATE User SET UserName=?, Email=?, Password=?, Phone=?, Adress=? ,NomPharmacie=?, MatriculeFiscale=? , Role=? WHERE IdUser=?";                
            PreparedStatement pstSousPharmacie = cnx.prepareStatement(reqSousPharmacie);
            pstSousPharmacie.setInt(8,user.getIdUser());
            pstSousPharmacie.setString(1, user.getUserName());
            pstSousPharmacie.setString(2, user.getEmail());
            pstSousPharmacie.setString(3, user.getPassword());
            pstSousPharmacie.setInt(4, user.getPhone());
            pstSousPharmacie.setString(5, user.getAdress());
            pstSousPharmacie.setString(6, ((SousPharmacie) user).getNomPharmacie());
            pstSousPharmacie.setString(7, ((SousPharmacie) user).getMatriculeFiscale());
            pstSousPharmacie.executeUpdate();
            System.out.println("Sous Pharmacie modifiée !");
            break;
            
            case "SuperPharmacie":
            String reqSuperPharmacie = "UPDATE User SET UserName=?, Email=?, Password=?, Phone=?, Adress=?, MatriculeFiscale=?  WHERE IdUser=?";                                
            PreparedStatement pstSuperPharmacie = cnx.prepareStatement(reqSuperPharmacie);
            pstSuperPharmacie.setInt(7,user.getIdUser());
            pstSuperPharmacie.setString(1, user.getUserName());
            pstSuperPharmacie.setString(2, user.getEmail());
            pstSuperPharmacie.setString(3, user.getPassword());
            pstSuperPharmacie.setInt(4, user.getPhone());
            pstSuperPharmacie.setString(5, user.getAdress());
            pstSuperPharmacie.setString(6,((SuperPharmacie) user).getMatriculeFiscale());
            pstSuperPharmacie.executeUpdate();
            System.out.println("Super Pharmacie modifiée !");
            break;     
             }
            
            /*String req = "UPDATE User SET UserName=?, Email=?, Password=?, Phone=?, Adress=? WHERE IdUser=?";
            PreparedStatement pst = cnx.prepareStatement(req);
            pst.setInt(7,user.getIdUser());
            pst.setString(1, user.getUserName());
            pst.setString(2, user.getEmail());
            pst.setString(3, user.getPassword());
            pst.setString(4, user.getPhone());
            pst.setString(5, user.getAdress());
            pst.executeUpdate();
            System.out.println("Utilisateur modifiée !");*/
       // } catch (SQLException ex) {
          //  System.out.println(ex.getMessage());
        //}
    }
          public String retrieveUserRole(String email) {
    String userRole = "";
    
    try {
        String query = "SELECT Role FROM user WHERE Email = ?";
        PreparedStatement statement = cnx.prepareStatement(query);
        statement.setString(1, email);
        
        ResultSet resultSet = statement.executeQuery();
        
        if (resultSet.next()) {
            userRole = resultSet.getString("Role");
        }
    } catch (SQLException e) {
        // Gérer l'erreur de récupération du rôle de l'utilisateur
        
    }
    
    return userRole;
}
    public void supprimer(User user) {
        try {
            
            String req = "UPDATE User SET Statut='Désactiver' WHERE IdUser=?";
            PreparedStatement pst = cnx.prepareStatement(req);
            pst.setInt(1, user.getIdUser());
            pst.executeUpdate();
            System.out.println("Compte Désactiver !");
            JOptionPane.showMessageDialog(null, "Compte Désactiver !");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
    
    
      public void supprimer1(User user) {
        try {
            
            String req = "UPDATE User SET Statut='Active' WHERE IdUser=?";
            PreparedStatement pst = cnx.prepareStatement(req);
            pst.setInt(1, user.getIdUser());
            pst.executeUpdate();
            System.out.println("Compte Atctiver !");
            JOptionPane.showMessageDialog(null, "Compte Activer !");

             } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
      }
    
    
 public ObservableList<User> afficherPharmacie() {
     ObservableList<User> utilisateurs =  FXCollections.observableArrayList();

    try {
        
            String reqSousPharmacie = "SELECT * FROM user WHERE Role = 'SousPharmacie'";
        PreparedStatement pstSousPharmacie = cnx.prepareStatement(reqSousPharmacie);
        ResultSet rsSousPharmacie = pstSousPharmacie.executeQuery();
        while (rsSousPharmacie.next()) {
            SousPharmacie sousPharmacie = new SousPharmacie(
                    rsSousPharmacie.getString("NomPharmacie"),
                    rsSousPharmacie.getString("MatriculeFiscale"),
                    rsSousPharmacie.getInt("idUser"),
                    rsSousPharmacie.getString("UserName"),
                    rsSousPharmacie.getString("Email"),
                    rsSousPharmacie.getInt("Phone"),
                    rsSousPharmacie.getString("Adress"),
                    rsSousPharmacie.getString("Statut")
            );
            utilisateurs.add(sousPharmacie);
        }
        
    
                
    } catch (SQLException ex) {
        System.out.println(ex.getMessage());
    }
    
    return utilisateurs;
}

 public ObservableList<User> afficherLivreur() {
     ObservableList<User> utilisateurs =  FXCollections.observableArrayList();

    try {
        
           
        // Récupérer les livreurs
        String reqLivreur = "SELECT * FROM user WHERE Role = 'Livreur'";
        PreparedStatement pstLivreur = cnx.prepareStatement(reqLivreur);
        ResultSet rsLivreur = pstLivreur.executeQuery();
        while (rsLivreur.next()) {
            Livreur livreur = new Livreur(
                    rsLivreur.getInt("CIN"),
                    rsLivreur.getInt("idUser"),
                    rsLivreur.getString("UserName"),
                    rsLivreur.getString("Email"),
                    rsLivreur.getInt("Phone"),
                    rsLivreur.getString("Adress"),
                    rsLivreur.getString("Statut")
            );
            utilisateurs.add(livreur);
        }
       
    
                
    } catch (SQLException ex) {
        System.out.println(ex.getMessage());
    }
    
    return utilisateurs;
}
}
