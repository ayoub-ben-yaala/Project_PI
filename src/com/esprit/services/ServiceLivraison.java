package com.esprit.services;

import com.esprit.entities.Livraison;
import com.esprit.utils.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class ServiceLivraison implements IService<Livraison> {

    private Connection cnx = DataSource.getInstance().getCnx();

    public void ajouter(Livraison livraison) {
        try {
            String req = "INSERT INTO livraison(id, reference, id_commande, id_livreur) VALUES ("
                    + livraison.getId() + ",'" + livraison.getReference() + "'," + livraison.getIdCommande() + "," + livraison.getIdLivreur() + ")";
            Statement st = cnx.createStatement();
            st.executeUpdate(req);
            System.out.println("Livraison ajoutée !");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public void modifier(Livraison livraison) {
        try {
            String req = "UPDATE livraison SET reference='" + livraison.getReference() + "', id_commande=" + livraison.getIdCommande()
                    + ", id_livreur=" + livraison.getIdLivreur() + " WHERE id=" + livraison.getId();
            Statement st = cnx.createStatement();
            st.executeUpdate(req);
            System.out.println("Livraison modifiée !");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public void supprimer(Livraison livraison) {
        try {
            String req = "DELETE from livraison WHERE id=" + livraison.getId();
            Statement st = cnx.createStatement();
            st.executeUpdate(req);
            System.out.println("Livraison supprimée !");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public List<Livraison> afficher() {
        List<Livraison> list = new ArrayList<>();

        String req = "SELECT id,reference,id_commande,(select nom_livreur from livreur where id=l.id_livreur)as nom FROM livraison l";
        try {
            Statement st = cnx.createStatement();
            ResultSet rs = st.executeQuery(req);
            while (rs.next()) {
                list.add(new Livraison(rs.getInt("id"), rs.getString("reference"), rs.getInt("id_commande"), rs.getString("nom")));
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

        return list;
    }
    
    public List<Livraison> Search( String N) {
        List<Livraison> list = new ArrayList<>();

        String req = "SELECT l.id, reference, id_commande, lv.nom_livreur FROM livraison l " +
             "INNER JOIN livreur lv ON lv.id = l.id_livreur " +
             "WHERE lv.nom_livreur = '" + N + "'";

        try {
            Statement st = cnx.createStatement();
            ResultSet rs = st.executeQuery(req);
            while (rs.next()) {
                list.add(new Livraison(rs.getInt("id"), rs.getString("reference"), rs.getInt("id_commande"), rs.getString("nom_livreur")));
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

        return list;
    }
      public List<String> afficher_livreur() {
        List<String> list = new ArrayList<>();

        String req = "SELECT nom_livreur FROM livreur";
        try {
            Statement st = cnx.createStatement();
            ResultSet rs = st.executeQuery(req);
            while (rs.next()) {
                list.add(rs.getString("nom_livreur"));
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

        return list;
    }
 public int get_livreur_id(String s) throws SQLException {
    int idLivreur = 0;
    String req = "SELECT id FROM livreur WHERE nom_livreur = ?";
    try (PreparedStatement preparedStatement = cnx.prepareStatement(req)) {
        preparedStatement.setString(1, s);
        ResultSet rs = preparedStatement.executeQuery();
        if (rs.next()) {
            idLivreur = rs.getInt("id");
        }
    } catch (SQLException ex) {
        System.out.println(ex.getMessage());
    }
    return idLivreur;
}
    
}