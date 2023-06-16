package com.ProjetPIEsprit.services;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import com.ProjetPIEsprit.entities.Categorie;
import com.ProjetPIEsprit.utils.DataSource;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class ServiceCategorie {

    private Connection cnx = DataSource.getInstance().getCnx();

    public void ajouter(Categorie c) {
        try {
            String req = "INSERT INTO categorie(nomcategorie) VALUES (?);";
            PreparedStatement pst = cnx.prepareStatement(req);
            pst.setString(1, c.getNomcategorie());
            pst.executeUpdate();
            System.out.println("Categorie ajoutée !");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public void modifier(Categorie c) {
        try {
            String req = "UPDATE categorie SET nomcategorie = ? WHERE id_categorie = ?";
            PreparedStatement pst = cnx.prepareStatement(req);
            pst.setString(1, c.getNomcategorie());
            pst.setInt(2, c.getid_categorie());
            pst.executeUpdate();
            System.out.println("Categorie modifiée !");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public void supprimer(Categorie c) {
        try {
            String req = "DELETE FROM categorie WHERE id_categorie = ?";
            PreparedStatement pst = cnx.prepareStatement(req);
            pst.setInt(1, c.getid_categorie());
            pst.executeUpdate();
            System.out.println("Categorie supprimée !");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public ObservableList<Categorie> afficher() {
        ObservableList<Categorie> list = FXCollections.observableArrayList();

        try {
            String req = "SELECT * FROM categorie";
            PreparedStatement pst = cnx.prepareStatement(req);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                list.add(new Categorie(rs.getInt("id_categorie"), rs.getString("nomcategorie")));
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

        return list;
    }

    public ObservableList<Categorie> afficher1() {
        ObservableList<Categorie> list = FXCollections.observableArrayList();

        try {
            String req = "SELECT nomcategorie FROM categorie";
            PreparedStatement pst = cnx.prepareStatement(req);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                list.add(new Categorie(rs.getString("nomcategorie")));
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

        return list;
    }

    public int get_categorie_id(String s) throws SQLException {
        int id_categorie = 0;
        String req = "SELECT id_categorie FROM categorie WHERE nomcategorie = ?";
        try (PreparedStatement preparedStatement = cnx.prepareStatement(req)) {
            preparedStatement.setString(1, s);
            ResultSet rs = preparedStatement.executeQuery();
            if (rs.next()) {
                id_categorie = rs.getInt("id_categorie");
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return id_categorie;
    }

public List<String> afficher_categorie() {
        List<String> list = new ArrayList<>();

        String req = "SELECT nomcategorie FROM categorie";
        try (Statement st = cnx.createStatement();
             ResultSet rs = st.executeQuery(req)) {
            while (rs.next()) {
                String nomcategorie = rs.getString("nomcategorie");
                if (nomcategorie != null) {
                    list.add(nomcategorie);
                }
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

        return list;
    }

    public List<String> afficher_categorieByID(int id) {
        List<String> list = new ArrayList<>();

        String req = "SELECT nomcategorie FROM categorie WHERE id_categorie = ?";
        try {
            PreparedStatement pst = cnx.prepareStatement(req);
            pst.setInt(1, id);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                list.add(rs.getString("nomcategorie"));
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

        return list;
    }
}
