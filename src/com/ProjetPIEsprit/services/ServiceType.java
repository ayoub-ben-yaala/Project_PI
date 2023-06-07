/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ProjetPIEsprit.services;

import com.ProjetPIEsprit.entities.Type;
import com.ProjetPIEsprit.utils.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
/**
 *
 * @author eya trabelsi
 */
public class ServiceType {
    
        private Connection cnx = DataSource.getInstance().getCnx();
    
    public void ajouter(Type t) {
        try {
            String req = "INSERT INTO type(nomtype) VALUES (?);";
            PreparedStatement pst = cnx.prepareStatement(req);
            pst.setString(1, t.getNomtype());
            pst.executeUpdate();
            System.out.println("Type ajoutée !");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
    
    public void modifier(Type t) {
        try {
            String req = "UPDATE type SET nomtype=? WHERE id_type=?";
            PreparedStatement pst = cnx.prepareStatement(req);
            
            pst.setString(1, t.getNomtype());
            pst.setInt(2, t.getid_type());
            pst.executeUpdate();
            System.out.println("Type modifiée !");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
    
    public void supprimer(Type t) {
        try {
            String req = "DELETE from type WHERE id_type=?";
            PreparedStatement pst = cnx.prepareStatement(req);
            pst.setInt(1, t.getid_type());
            pst.executeUpdate();
            System.out.println("Type supprimée !");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
    
    public ObservableList<Type> afficher() {
        ObservableList<Type> list = FXCollections.observableArrayList();
                
        
        String req = "SELECT * FROM type";
        try {
            PreparedStatement pst = cnx.prepareStatement(req);
            ResultSet rs = pst.executeQuery();
            while(rs.next()) {
                list.add(new Type(rs.getInt("id_type"), rs.getString("nomtype")));
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        
        
        return list;
}
     public ObservableList<Type> afficher2() {
        ObservableList<Type> list = FXCollections.observableArrayList();
                
        
        String req = "SELECT nomtype FROM type";
        try {
            PreparedStatement pst = cnx.prepareStatement(req);
            ResultSet rs = pst.executeQuery();
            while(rs.next()) {
                list.add(new Type(rs.getString("nomtype")));
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        
        
        return list;
}
     
              public int get_type_id(String s) throws SQLException {
    int id_type = 0;
    String req = "SELECT id FROM type WHERE nomtype = ?";
    try (PreparedStatement preparedStatement = cnx.prepareStatement(req)) {
        preparedStatement.setString(1, s);
        ResultSet rs = preparedStatement.executeQuery();
        if (rs.next()) {
            id_type = rs.getInt("id");
        }
    } catch (SQLException ex) {
        System.out.println(ex.getMessage());
    }
    return id_type;
}
     
           public List<String> afficher_type() {
        List<String> list = new ArrayList<>();

        String req = "SELECT nomtype FROM type";
        try {
            Statement st = cnx.createStatement();
            ResultSet rs = st.executeQuery(req);
            while (rs.next()) {
                list.add(rs.getString("nomtype"));
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

        return list;
    }

  public List<String> afficher_typeByID(int id) {
        List<String> list = new ArrayList<>();

        String req = "SELECT nomtype FROM type where id_type=?";
        try {
            Statement st = cnx.createStatement();
            ResultSet rs = st.executeQuery(req);
            while (rs.next()) {
                list.add(rs.getString("nomtype"));
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

        return list;
    }
}