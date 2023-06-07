/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ProjetPIEsprit.services;

import com.ProjetPIEsprit.entities.Categorie;
import com.ProjetPIEsprit.entities.Medicament;
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
public class ServiceMedicament {
    
 private Connection cnx = DataSource.getInstance().getCnx();
    
 public void ajouterMedicament(Medicament medicament) {
      int typeId = obtenirIdType(medicament.getType());
      int categorieId = obtenirIdCategorie(medicament.getCategorie());
    String query = "INSERT INTO medicament (nom_medi, reference, notice, prix, DateExp, id_categorie, id_type) VALUES (?, ?, ?, ?, ?, ?, ?)";
   
    try (PreparedStatement statement = cnx.prepareStatement(query)) {
        statement.setInt(6, categorieId);
        statement.setInt(7, typeId);
        statement.setString(1, medicament.getnom_medi());
        statement.setInt(2, medicament.getreference());
        statement.setString(5, medicament.getDateExp());
        statement.setInt(4, medicament.getprix());
       statement.setString(3, medicament.getnotice());
        statement.executeUpdate();
    } catch (SQLException e) {
        e.printStackTrace();
    }
}
 
 public int obtenirIdType(String nomType) {
    String query = "SELECT id_type FROM type WHERE nomtype = ?";
    int typeId = 1; // Valeur par défaut en cas d'échec
   
    try (PreparedStatement statement = cnx.prepareStatement(query)) {
        statement.setString(1, nomType);
        ResultSet resultSet = statement.executeQuery();
       
        if (resultSet.next()) {
            typeId = resultSet.getInt("id_type");
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }
   
    return typeId;
}

public int obtenirIdCategorie(String nomCategorie) {
    String query = "SELECT id_categorie FROM categorie WHERE nomcategorie = ?";
    int categorieId = 1; // Valeur par défaut en cas d'échec
   
    try (PreparedStatement statement = cnx.prepareStatement(query)) {
        statement.setString(1, nomCategorie);
        ResultSet resultSet = statement.executeQuery();
       
        if (resultSet.next()) {
            categorieId = resultSet.getInt("id_categorie");
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }
   
    return categorieId;
}
   /*public void ajouter(Medicament m) {
   try {
        String req = "INSERT INTO medicament(nomcat, nomtyp, nom_medi, reference, DateExp, prix, notice) VALUES (?, ?, ?, ?, ?, ?, ?)";
        PreparedStatement pst = cnx.prepareStatement(req);
        pst.setString(3, m.getnom_medi());
        pst.setInt(4, m.getreference());
        pst.setString(7, m.getnotice());
        pst.setInt(6, m.getprix());
        pst.setString(5, m.getDateExp());
        pst.setString(1, m.getNomcat());
        pst.setString(2, m.getNomtyp());
        pst.executeUpdate();
        System.out.println("Medicament ajouté !");
    } catch (SQLException ex) {
        System.out.println(ex.getMessage());
    }
}*/
    
    public void modifier(Medicament m) {
       /* try {
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
    */}
    
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
      
   
   public ObservableList<Medicament> afficher() {
        ObservableList<Medicament> list = FXCollections.observableArrayList();
        
        String req ="SELECT m.nom_medi,(SELECT nomtype FROM Type WHERE id_type = m.id_type) AS nomtype,(SELECT nomcategorie FROM Categorie WHERE id_categorie = m.id_categorie) AS nomcat FROM medicament m";

        try {
            PreparedStatement pst = cnx.prepareStatement(req);
            ResultSet rs = pst.executeQuery();
            while(rs.next()) {
                list.add(new Medicament(rs.getString("nom_medi"), rs.getString("nomcat"),rs.getString("nomtype")));
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }        
        return list;
    }
}
