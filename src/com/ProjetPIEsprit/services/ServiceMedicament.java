/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ProjetPIEsprit.services;

import com.ProjetPIEsprit.entities.Categorie;
import com.ProjetPIEsprit.entities.Medicament;
import com.ProjetPIEsprit.utils.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author eya trabelsi
 */
public class ServiceMedicament {
    
 private Connection cnx = DataSource.getInstance().getCnx();
    
   public void ajouter(Medicament m) {
    try {
        String req = "INSERT INTO medicament(nom_medi, reference, notice, prix, DateExp, id_categorie, id_type) VALUES (?, ?, ?, ?, ?, ?, ?)";
        PreparedStatement pst = cnx.prepareStatement(req);
        pst.setString(1, m.getnom_medi());
        pst.setInt(2, m.getreference());
        pst.setString(3, m.getnotice());
        pst.setInt(4, m.getprix());
        pst.setString(5, m.getDateExp());
        pst.setInt(6, m.getCat().getid_categorie());
        pst.setInt(7, m.getTy().getid_type());
        pst.executeUpdate();
        System.out.println("Medicament ajouté !");
    } catch (SQLException ex) {
        System.out.println(ex.getMessage());
    }
}
    
    public void modifier(Medicament m) {
        try {
            String req = "UPDATE medicament SET nom_medi=?, reference=?, notice=?, prix=? , DateExp=?, id_categorie=?, id_type=? WHERE reference=?";
            PreparedStatement pst = cnx.prepareStatement(req);
            pst.setString(1, m.getnom_medi());
            pst.setInt(2, m.getreference());
            pst.setString(3, m.getnotice());
            pst.setInt(4, m.getprix());
            pst.setString(5, m.getDateExp());
            pst.setInt(6, m.getCat().getid_categorie());
            pst.setInt(7, m.getTy().getid_type());
            pst.executeUpdate();
            System.out.println("Medicament modifiée !");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
    
    public void supprimer(Medicament m) {
        try {
            String req = "DELETE from medicament WHERE reference=?";
            PreparedStatement pst = cnx.prepareStatement(req);
            pst.setInt(1, m.getreference());
            pst.executeUpdate();
            System.out.println("Medicament supprimée !");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
      
   
   public List<Medicament> afficher() {
        List<Medicament> list = new ArrayList<>();
        
        String req = "SELECT * FROM medicament";
        try {
            PreparedStatement pst = cnx.prepareStatement(req);
            ResultSet rs = pst.executeQuery();
            while(rs.next()) {
                list.add(new Medicament(rs.getString("nom_medi"), rs.getInt("reference"), rs.getString("notice"), rs.getInt("prix"), rs.getString("DateExp")));
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        
        
        return list;
    }
}
