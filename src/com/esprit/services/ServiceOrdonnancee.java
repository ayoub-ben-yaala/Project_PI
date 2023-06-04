/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.esprit.services;

import com.esprit.entities.Ordonnance;
import com.esprit.utils.DataSource;
import java.sql.Connection;
import java.sql.Date;
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
            String req = "INSERT INTO `ordonnance`(`id`, `reference`, `id_medecin`, `id_medicament`, `date_ordonnance`, `statut`)VALUES (?,?,?,?,?,?)";
            PreparedStatement st = cnx.prepareStatement(req);
            st.setInt(1, ordonnance.getId());
            st.setInt(2, ordonnance.getReference());
            st.setInt(3, ordonnance.getId_Medecin());
            st.setInt(4, ordonnance.getId_Medicament());
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
        String req = "UPDATE ordonnance SET reference=?, id_Medicament=?, id_medecin=?, date_ordonnance=?, statut=? WHERE id=?";
        PreparedStatement st = cnx.prepareStatement(req);
        st.setInt(1, ordonnance.getReference());
        st.setInt(2, ordonnance.getId_Medicament());
        st.setInt(3, ordonnance.getId_Medecin());
        st.setDate(4, ordonnance.getDateOrdonnance());
        st.setString(5, ordonnance.getStatut());
        st.setInt(6, ordonnance.getId());
        st.executeUpdate();

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
                list.add(new Ordonnance(rs.getInt("id"), rs.getInt("Reference"), rs.getInt("id_medecin"), rs.getInt("id_Medicament"),
                        rs.getDate("date_ordonnance"), rs.getString("statut")));
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

        return list;
    }

    public List<Ordonnance> getOrdonnancesByMedecin(int nomMedecin) {

        List<Ordonnance> ordonnances = new ArrayList<>();

        String query = "SELECT * FROM ordonnance WHERE nom_medecin = ?";

        try (PreparedStatement statement = cnx.prepareStatement(query)) {
            statement.setInt(1, nomMedecin);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {

                int reference = resultSet.getInt("reference");
                int nomMedicament = resultSet.getInt("id_medicament");
                Date date = resultSet.getDate("date_ordonnance");
                String statut = resultSet.getString("statut");

                Ordonnance ordonnance = new Ordonnance(reference, nomMedecin, nomMedicament, date, statut);
                ordonnances.add(ordonnance);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return ordonnances;
    }
}
