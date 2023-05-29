package com.esprit.services;

import com.esprit.entities.Chat;
import com.esprit.utils.DataSource;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class ServiceChat {
    private Connection cnx = DataSource.getInstance().getCnx();

    public void ajouter(Chat chat) {
        try {
            String req = "INSERT INTO chat(id_chat, id_livreur, id_pharmacie, message) VALUES (?, ?, ?, ?)";
            PreparedStatement pst = cnx.prepareStatement(req);
            pst.setInt(1, chat.getIdChat());
            pst.setInt(2, chat.getIdLivreur());
            pst.setInt(3, chat.getIdPharmacie());
            pst.setString(4, chat.getMessage());
     
            pst.executeUpdate();
            System.out.println("Chat ajouté !");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public void modifier(Chat chat) {
        try {
            String req = "UPDATE chat SET id_livreur=?, id_pharmacie=?, message=? WHERE id_chat=?";
            PreparedStatement pst = cnx.prepareStatement(req);
            pst.setInt(1, chat.getIdLivreur());
            pst.setInt(2, chat.getIdPharmacie());
            pst.setString(3, chat.getMessage());
            
            pst.setInt(5, chat.getIdChat());
            pst.executeUpdate();
            System.out.println("Chat modifié !");
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
                        rs.getString("message")
                     
                );
                list.add(chat);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

        return list;
    }
}
