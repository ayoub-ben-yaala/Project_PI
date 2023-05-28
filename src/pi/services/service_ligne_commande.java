/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pi.services;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import pi.entities.Commande;
import pi.entities.ligne_commande;
import pi.util.DataSource;

/**
 *
 * @author usernvme
 */
public class service_ligne_commande {
    
    private Connection cnx = DataSource.getInstance().getCnx();
    
    public void ajouter(ligne_commande Lc) {
        try {
            String req = "INSERT INTO ligne_commande(id_med, quantite) VALUES (?,?);";
            PreparedStatement pst = cnx.prepareStatement(req);
            pst.setInt(1, Lc.getId_med());
            pst.setInt(2, Lc.getquantite());
           
            pst.executeUpdate();
            System.out.println("Ligne Commande ajoutée !");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
    
    public void modifier(ligne_commande Lc) {
        try {
            String req = "UPDATE ligne_commande SET id_med=?, quantite=? WHERE id=?";
            PreparedStatement pst = cnx.prepareStatement(req);
            pst.setInt(1, Lc.getId_med());
            pst.setInt(2, Lc.getquantite());
           
            pst.setInt(3,Lc.getId());
            
            pst.executeUpdate();
            System.out.println("Ligne Commande modifiée !");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
    
    public void supprimer(ligne_commande Lc) {
        try {
            String req = "DELETE from ligne_commande WHERE id=?";
            PreparedStatement pst = cnx.prepareStatement(req);
            pst.setInt(1, Lc.getId_med());
            pst.executeUpdate();
            System.out.println("Ligne Commande supprimée !");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
    
   
    
    
    
    
    
}
