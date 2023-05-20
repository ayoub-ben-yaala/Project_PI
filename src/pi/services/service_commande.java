/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pi.services;

import pi.entities.Commande;
import pi.util.DataSource;
import java.sql.Connection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author abdel
 */
public class service_commande {
    
    private Connection cnx = DataSource.getInstance().getCnx();
    
    public void ajouter(Commande c) {
        try {
            String req = "INSERT INTO commande(etat, date, id_pharmacie) VALUES (?,?,?);";
            PreparedStatement pst = cnx.prepareStatement(req);
            pst.setString(1, c.getEtat());
            pst.setObject(2, c.getDate());
            pst.setInt(3, c.getId_phar());
            
            pst.executeUpdate();
            System.out.println("commande ajoutée !");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
    
    public void modifier(Commande c) {
        try {
            String req = "UPDATE commande SET etat=?, date=?,id_pharmacie=?, WHERE id=?";
            PreparedStatement pst = cnx.prepareStatement(req);
            pst.setInt(5, c.getId_commande());
            pst.setString(1, c.getEtat());
            pst.setObject(2,c.getDate());
            pst.setInt(3,c.getId_phar());
            
            pst.executeUpdate();
            System.out.println("Commande modifiée !");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
    
    public void supprimer(Commande c) {
        try {
            String req = "DELETE from commande WHERE id=?";
            PreparedStatement pst = cnx.prepareStatement(req);
            pst.setInt(1, c.getId_commande());
            pst.executeUpdate();
            System.out.println("Commande supprimée !");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
    
    public List<Commande> afficher() {
        List<Commande> list = new ArrayList<>();
        
        String req = "SELECT * FROM commande";
        try {
            PreparedStatement pst = cnx.prepareStatement(req);
            ResultSet rs = pst.executeQuery();
            while(rs.next()) {
                list.add(new Commande(rs.getInt("id"), rs.getString("etat"), rs.getDate("date"),rs.getInt("id_pharmacie"),rs.getFloat("prix_total")));
            }
            System.out.println(list);
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        
        
        return list;
    }
}
