package com.esprit.services;

import com.esprit.entities.Chat;
import com.esprit.entities.Livraison;
import com.esprit.utils.DataSource;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class ServiceChat {
    private Connection cnx = DataSource.getInstance().getCnx();

    public void ajouter(Chat chat) {
        Date date = new Date(0);
        try {
            String req = "INSERT INTO chat(id_chat, id_livreur, id_pharmacie, message,date_msg) VALUES (?, ?, ?, ?,?)";
            PreparedStatement pst = cnx.prepareStatement(req);
            pst.setInt(1, chat.getIdChat());
            pst.setInt(2, chat.getIdLivreur());
            pst.setInt(3, chat.getIdPharmacie());
            pst.setString(4, chat.getMessage());
            pst.setDate(5, date);
            pst.executeUpdate();
            System.out.println("Chat ajouté !");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }



    public void supprimer(Chat chat) {
        try {
            String req = "DELETE FROM chat WHERE id_chat=?";
            PreparedStatement pst = cnx.prepareStatement(req);
            pst.setInt(1, chat.getIdChat());
            pst.executeUpdate();
            System.out.println("Chat supprimé !");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public List<Chat> afficher() {
        List<Chat> list = new ArrayList<>();

        String req = "SELECT * FROM chat";
        try {
            Statement st = cnx.createStatement();
            ResultSet rs = st.executeQuery(req);
            while (rs.next()) {
                Chat chat = new Chat(
                        rs.getInt("id_chat"),
                        rs.getInt("id_livreur"),
                        rs.getInt("id_pharmacie"),
                        rs.getString("message"),
                        rs.getDate("date_msg")
                );
                list.add(chat);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

        return list;
    }
    
        public List<String> afficherPhar_Liv(int id,String role) {
        
if (    "livreur".equals(role)){
    List<String> list = new ArrayList<>();
        String req = "select nom from sous_pharmacie where id in (SELECT distinct id_pharmacie from chat where id_livreur="+id+  ")" ;
        try {
            Statement st = cnx.createStatement();
            ResultSet rs = st.executeQuery(req);
            while (rs.next()) {
                list.add(rs.getString("nom"));
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
          return list;
} else {
    List<String> list = new ArrayList<>();
        String req = "select nom_livreur from livreur where id in (SELECT distinct id_livreur from chat where id_pharmacie="+id+  ")" ;
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
       
    }

              public List<String> afficherMsg(int id_liv,int id_phar) {
        List<String> list = new ArrayList<>();

        String req = "select message from chat where id_livreur= "+id_liv+" and id_pharmacie= "+id_phar;
        try {
            Statement st = cnx.createStatement();
            ResultSet rs = st.executeQuery(req);
            while (rs.next()) {
                list.add(rs.getString("message"));
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

        return list;
    }
              
  public int id_phar(String nom) {
    int id = 0;
    String req = "SELECT id FROM sous_pharmacie WHERE nom='" + nom + "'";
    try {
        Statement st = cnx.createStatement();
        ResultSet rs = st.executeQuery(req);

        if (rs.next()) { // Déplace le curseur sur la première ligne de résultats
            id = rs.getInt("id");
        }

        rs.close(); // Fermer le ResultSet
        st.close(); // Fermer la déclaration
    } catch (SQLException ex) {
        System.out.println(ex.getMessage());
    }
    return id;
}
  public int id_Liv(String nom) {
    int id = 0;
     String req = "SELECT id FROM livreur where nom_livreur='"+nom+"'";
    try {
        Statement st = cnx.createStatement();
        ResultSet rs = st.executeQuery(req);

        if (rs.next()) { // Déplace le curseur sur la première ligne de résultats
            id = rs.getInt("id");
        }

        rs.close(); // Fermer le ResultSet
        st.close(); // Fermer la déclaration
    } catch (SQLException ex) {
        System.out.println(ex.getMessage());
    }
    return id;
}
  
                      
}
