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
import pi.entities.Panier;
import pi.entities.ligne_commande;
import pi.util.DataSource;

/**
 *
 * @author usernvme
 */
public class service_panier {

    private Connection cnx = DataSource.getInstance().getCnx();

    public void ajouterPanier(ligne_commande L) {
        try {
            System.out.println("test!");
            Panier panier = new Panier();

            panier.ajouterMed(L);
            String req = "INSERT INTO panier(meds) VALUES (?);";
            PreparedStatement pst = cnx.prepareStatement(req);
            pst.setObject(1, panier.getMeds());
            System.out.println(panier.getMeds());
           
            pst.executeUpdate();
            System.out.println("panier ajoutée !");

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

    }
    
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
     
    public List<Panier> afficher() {
        List<Panier> list = new ArrayList<>();
        
        String req = "SELECT * FROM panier";
        try {
            PreparedStatement pst = cnx.prepareStatement(req);
            ResultSet rs = pst.executeQuery();
            while(rs.next()) {
                list.add(new Panier(rs.getInt("id"), rs.getInt("quantite"), rs.getInt("meds")));
            }
            System.out.println(list);
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        
        
        return list;
    }
}
