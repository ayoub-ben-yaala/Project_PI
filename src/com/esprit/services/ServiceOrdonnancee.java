/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.esprit.services;

import com.esprit.entities.Ordonnance;
import com.esprit.utils.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class ServiceOrdonnancee implements IService<Ordonnance> {

    private Connection cnx = DataSource.getInstance().getCnx();

    @Override
    public void ajouter(Ordonnance ordonnance) {
        try {
            String req = "INSERT INTO `ordonnance`(`id`, `reference`, `Nom_medecin`, `Nom_medicament`, `date_ordonnance`, `statut`)VALUES (?,?,?,?,?,?)";
            PreparedStatement st = cnx.prepareStatement(req);
            st.setInt(1, ordonnance.getId());
            st.setInt(2, ordonnance.getReference());
            st.setString(3, ordonnance.getNom_Medecin());
            st.setString(4, ordonnance.getNom_Medicament());
            st.setDate(5, ordonnance.getDateOrdonnance());
            st.setString(6, ordonnance.getStatut());
            st.executeUpdate();
            System.out.println("Ordonnance ajoutée !");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @Override
    public void modifier(Ordonnance ordonnance) {
        try {
            String req = "UPDATE ordonnance SET  reference='" + ordonnance.getReference() + "', Nom_Medicament='" + ordonnance.getNom_Medicament() + "', Nom_medecin='"
                    + ordonnance.getNom_Medecin() + "', date_ordonnance='" + ordonnance.getDateOrdonnance() + "', statut='"
                    + ordonnance.getStatut() + "' WHERE id=" + ordonnance.getId();
            Statement st = cnx.createStatement();
            st.executeUpdate(req);
            System.out.println("Ordonnance modifiée !");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @Override
    public void supprimer(Ordonnance ordonnance) {
        try {
            String req = "DELETE from ordonnance WHERE id=" + ordonnance.getId();
            Statement st = cnx.createStatement();
            st.executeUpdate(req);
            System.out.println("Ordonnance supprimée !");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @Override
    public List<Ordonnance> afficher() {
        List<Ordonnance> list = new ArrayList<>();

        String req = "SELECT * FROM ordonnance";
        try {
            Statement st = cnx.createStatement();
            ResultSet rs = st.executeQuery(req);
            while (rs.next()) {
                list.add(new Ordonnance(rs.getInt("id"), rs.getInt("Reference"), rs.getString("Nom_medecin"), rs.getString("Nom_Medicament"),
                        rs.getDate("date_ordonnance"), rs.getString("statut")));
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

        return list;
    }

}