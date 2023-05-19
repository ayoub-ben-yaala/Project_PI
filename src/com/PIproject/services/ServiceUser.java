/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.PIproject.services;

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
            String req = "INSERT INTO User(UserName,Email,Password,Phone, Adress,Role) VALUES (?,?,?,?,?,?);";
            PreparedStatement pst = cnx.prepareStatement(req);
            pst.setString(1, user.getUserName());
            pst.setString(2, user.getEmail());
            pst.setString(3, user.getPassword());
             pst.setString(4, user.getPhone());
            pst.setString(5, user.getAdress());
            pst.setString(6, user.getRole());
            pst.executeUpdate();
            System.out.println("Utilisateur ajoutée !");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
    
    public void modifier(User user) {
        try {
            String req = "UPDATE User SET UserName=?, Email=?, Password=?, Phone=?, Adress=?, Role=? WHERE IdUser=?";
            PreparedStatement pst = cnx.prepareStatement(req);
            pst.setInt(7,user.getIdUser());
            pst.setString(1, user.getUserName());
            pst.setString(2, user.getEmail());
            pst.setString(3, user.getPassword());
            pst.setString(4, user.getPhone());
            pst.setString(5, user.getAdress());
            pst.setString(6, user.getRole());

            pst.executeUpdate();
            System.out.println("Utilisateur modifiée !");
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
                list.add(new User(rs.getInt("idUser"), rs.getString("UserName"), rs.getString("Email"), rs.getString("Password"), rs.getString("Phone"), rs.getString("Adress"), rs.getString("Role")));
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        
        return list;
    }
}
