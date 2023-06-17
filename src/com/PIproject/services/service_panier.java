/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.PIproject.services;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import com.PIproject.entities.Panier;
import com.PIproject.entities.ligne_commande;
import com.PIproject.utils.DataSource;

/**
 *
 * @author usernvme
 */
public class service_panier {

    private Connection cnx = DataSource.getInstance().getCnx();

  public void ajouterPanier(ligne_commande L, int userId) {
    try {
        // Check if panier exists for the given userId
        String selectQuery = "SELECT * FROM panier WHERE userId = ?";
        PreparedStatement selectStmt = cnx.prepareStatement(selectQuery);
        selectStmt.setInt(1, userId);
        ResultSet resultSet = selectStmt.executeQuery();

        if (resultSet.next()) {
            // Panier exists, retrieve the existing panierId and meds
            int panierId = resultSet.getInt("id");
            String medsJson = resultSet.getString("meds");

            // Create a new Panier instance with existing meds
            Panier panier = new Panier(panierId);
            panier.setMedsFromJson(medsJson);

            // Add the medication to the panier
            panier.ajouterMed(L);

            // Update the panier in the database with the updated medications
            String updateQuery = "UPDATE panier SET meds = ? WHERE id = ?";
            PreparedStatement updateStmt = cnx.prepareStatement(updateQuery);
            updateStmt.setObject(1, panier.convertMedsToJson());
            updateStmt.setInt(2, panierId);
            updateStmt.executeUpdate();

            System.out.println("Panier updated!");
        } else {
            // Panier doesn't exist, create a new panier
            String insertQuery = "INSERT INTO panier(userId, meds) VALUES (?, ?)";
            PreparedStatement insertStmt = cnx.prepareStatement(insertQuery);
            insertStmt.setInt(1, userId);

            Panier panier = new Panier();
            panier.ajouterMed(L);

            insertStmt.setObject(2, panier.convertMedsToJson());
            insertStmt.executeUpdate();

            System.out.println("Panier created!");
        }
    } catch (SQLException ex) {
        System.out.println(ex.getMessage());
    }
}
    
  
    /*
        public void creationPanier(Panier panier) {
        try {
            System.out.println("test!");
            

         
            String req = "INSERT INTO panier(meds, quantite) VALUES (?,?);";
            PreparedStatement pst = cnx.prepareStatement(req);
            pst.setObject(1, panier.getMeds());
            System.out.println(panier.getMeds());
            pst.setInt(2, 5);
            pst.executeUpdate();
            System.out.println("panier ajoutée !");

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

    }

    public void modifier(Panier panier) {
        try {

            String req = "UPDATE panier SET meds=? WHERE id=?";
            PreparedStatement pst = cnx.prepareStatement(req);
            pst.setObject(1, panier.getMeds());
            pst.setInt(2,2);
            pst.executeUpdate();
            System.out.println("panier modifiée !");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
     public void supprimer(Panier panier) {
        try {
            String req = "DELETE from commande WHERE id=?";
            PreparedStatement pst = cnx.prepareStatement(req);
            pst.setInt(1, panier.getId());
            pst.executeUpdate();
            System.out.println("Panier supprimé !");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
     }
   */  
    public Panier afficher(int userId) {

        try{
        String selectQuery = "SELECT * FROM panier WHERE userId = ?";
        PreparedStatement selectStmt = cnx.prepareStatement(selectQuery);
        selectStmt.setInt(1, userId);
        ResultSet resultSet = selectStmt.executeQuery();

        if (resultSet.next()) {
            // Panier exists, retrieve the existing panierId and meds
            int panierId = resultSet.getInt("id");
            String medsJson = resultSet.getString("meds");

            // Create a new Panier instance with existing meds
            Panier panier = new Panier(panierId);
            panier.setMedsFromJson(medsJson);
            return panier;
        }
        }
         catch (SQLException ex) {
        System.out.println(ex.getMessage());
    }
        
        return (new Panier());
        
            
   
}
}
