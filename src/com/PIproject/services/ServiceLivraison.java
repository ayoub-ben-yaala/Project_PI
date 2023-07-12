package com.PIproject.services;

import com.PIproject.entities.Livraison;
import com.PIproject.utils.DataSource;
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

        String req = "SELECT id,reference,id_commande,(select UserName from user where IdUser=l.id_livreur)as nom FROM livraison l";
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
    
        public List<Livraison> afficherLiv(int id) {
        List<Livraison> list = new ArrayList<>();

        String req = "SELECT id,reference,id_commande,(select UserName from user where IdUser=l.id_livreur)as nom FROM livraison l"+" where l.id_livreur = "+id+"";
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

        String req = "SELECT l.id, reference, id_commande, lv.UserName FROM livraison l " +
             "INNER JOIN user lv ON lv.IdUser = l.id_livreur " +
             "WHERE lv.UserName = '" + N + "'";

        try {
            Statement st = cnx.createStatement();
            ResultSet rs = st.executeQuery(req);
            while (rs.next()) {
                list.add(new Livraison(rs.getInt("id"), rs.getString("reference"), rs.getInt("id_commande"), rs.getString("UserName")));
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

        return list;
    }
      public List<String> afficher_livreur() {
        List<String> list = new ArrayList<>();

        String req = "SELECT UserName FROM user where Role='Livreur'";
        try {
            Statement st = cnx.createStatement();
            ResultSet rs = st.executeQuery(req);
            while (rs.next()) {
                list.add(rs.getString("UserName"));
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

        return list;
    }
 public int get_livreur_id(String s) throws SQLException {
    int idLivreur = 0;
    String req = "SELECT IdUser FROM user WHERE UserName = ?";
    try (PreparedStatement preparedStatement = cnx.prepareStatement(req)) {
        preparedStatement.setString(1, s);
        ResultSet rs = preparedStatement.executeQuery();
        if (rs.next()) {
            idLivreur = rs.getInt("IdUser");
        }
    } catch (SQLException ex) {
        System.out.println(ex.getMessage());
    }
    return idLivreur;
}

    @Override
    public boolean supprimer1(Livraison t) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
}