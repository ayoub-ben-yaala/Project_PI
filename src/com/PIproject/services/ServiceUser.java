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
            String reqLivreur = "INSERT INTO User(UserName,Email,Password,Phone, Adress,CIN,Role) VALUES (?,?,?,?,?,?,?);";
            PreparedStatement pstLivreur = cnx.prepareStatement(reqLivreur);
            pstLivreur.setString(1, user.getUserName());
            pstLivreur.setString(2, user.getEmail());
            pstLivreur.setString(3, user.getPassword());
            pstLivreur.setString(4, user.getPhone());
            pstLivreur.setString(5, user.getAdress());
            pstLivreur.setInt(6, ((Livreur) user).getCIN());
            pstLivreur.setString(7,"Livreur");
            pstLivreur.executeUpdate();
            System.out.println("Livreur ajoutée !");
            break;
            
            case "SousPharmacie":
            String reqSousPharmacie = "INSERT INTO User(UserName,Email,Password,Phone, Adress,NomPharmacie,Role) VALUES (?,?,?,?,?,?,?);";
            PreparedStatement pstSousPharmacie = cnx.prepareStatement(reqSousPharmacie);
            pstSousPharmacie.setString(1, user.getUserName());
            pstSousPharmacie.setString(2, user.getEmail());
            pstSousPharmacie.setString(3, user.getPassword());
            pstSousPharmacie.setString(4, user.getPhone());
            pstSousPharmacie.setString(5, user.getAdress());
            pstSousPharmacie.setString(6, ((SousPharmacie) user).getNomPharmacie());
            pstSousPharmacie.setString(7,"SousPharmacie");
            pstSousPharmacie.executeUpdate();
            System.out.println("Sous Pharmacie ajoutée !");
            break;
            
            case "SuperPharmacie":
            String reqSuperPharmacie = "INSERT INTO User(UserName,Email,Password,Phone, Adress,MatriculeFiscale,Role) VALUES (?,?,?,?,?,?,?);";
            PreparedStatement pstSuperPharmacie = cnx.prepareStatement(reqSuperPharmacie);
            pstSuperPharmacie.setString(1, user.getUserName());
            pstSuperPharmacie.setString(2, user.getEmail());
            pstSuperPharmacie.setString(3, user.getPassword());
            pstSuperPharmacie.setString(4, user.getPhone());
            pstSuperPharmacie.setString(5, user.getAdress());
            pstSuperPharmacie.setString(6,((SuperPharmacie) user).getMatriculeFiscale());
            pstSuperPharmacie.setString(7,"SuperPharmacie"); 
            pstSuperPharmacie.executeUpdate();
            System.out.println("Super Pharmacie ajoutée !");
            break;     
             }
            /*
            String req = "INSERT INTO User(UserName,Email,Password,Phone, Adress,NomPharmacie,CIN,MatriculeFiscale,Role) VALUES (?,?,?,?,,?,?,?,?,?);";
            PreparedStatement pst = cnx.prepareStatement(req);
            pst.setString(1, user.getUserName());
            pst.setString(2, user.getEmail());
            pst.setString(3, user.getPassword());
             pst.setString(4, user.getPhone());
            pst.setString(5, user.getAdress());
            if (user instanceof Livreur){
            pst.setString(6,"Livreur");
            pst.setInt(8, ((Livreur) user).getCIN());
            }else if (user instanceof SousPharmacie){
            pst.setString(6,"SousPharmacie");
            pst.setString(7, ((SousPharmacie) user).getNomPharmacie());
            }else
            pst.setString(6,"SuperPharmacie");
            pst.setString(9,((SuperPharmacie) user).getMatriculeFiscale());
            pst.executeUpdate();
            System.out.println("Utilisateur ajoutée !");*/
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
    
    public void modifier(User user) {
        try {
            
            switch (user.getClass().getSimpleName()) {
            case "Livreur":
            String reqLivreur = "UPDATE User SET UserName=?, Email=?, Password=?, Phone=?, Adress=?,CIN=? WHERE IdUser=?";
            PreparedStatement pstLivreur = cnx.prepareStatement(reqLivreur);
            pstLivreur.setInt(7,user.getIdUser());
            pstLivreur.setString(1, user.getUserName());
            pstLivreur.setString(2, user.getEmail());
            pstLivreur.setString(3, user.getPassword());
            pstLivreur.setString(4, user.getPhone());
            pstLivreur.setString(5, user.getAdress());
            pstLivreur.setInt(6, ((Livreur) user).getCIN());
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
            pstSousPharmacie.setString(4, user.getPhone());
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
            pstSuperPharmacie.setString(4, user.getPhone());
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
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
    
    public void supprimer(User user) {
        try {
            String req = "DELETE from User WHERE IdUser=?";
            PreparedStatement pst = cnx.prepareStatement(req);
            pst.setInt(1, user.getIdUser());
            pst.executeUpdate();
            System.out.println("Utilisateur supprimée !");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
    
    public List<User> afficher() {
        List<User> list = new ArrayList<>();
        
        String req = "SELECT * FROM User";
        try {
            PreparedStatement pst = cnx.prepareStatement(req);
            ResultSet rs = pst.executeQuery();
            while(rs.next()) {
                list.add(new User(rs.getInt("idUser"), rs.getString("UserName"), rs.getString("Email"), rs.getString("Password"), rs.getString("Phone"), rs.getString("Adress")));
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        
        return list;
    }
}
