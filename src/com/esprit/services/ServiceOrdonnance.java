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

public class ServiceOrdonnance implements IService<Ordonnance> {

    private  Connection cnx = DataSource.getInstance().getCnx();
public void ajouter(Ordonnance ordonnance) {
    try {
        String req = "INSERT INTO ordonnance(id, reference, id_medicament, id_medecin, date_ordonnance, statut) VALUES ("
                + ordonnance.getId() + ",'" + ordonnance.getReference() + "'," + ordonnance.getId_Medicament() + ","
                + ordonnance.getid_Medecin() + ",'" + ordonnance.getDateOrdonnance() + "','" + ordonnance.getStatut() + "')";
        Statement st = cnx.createStatement();
        st.executeUpdate(req);
        System.out.println("Ordonnance ajoutée !");
    } catch (SQLException ex) {
        System.out.println(ex.getMessage());
    }
}


    @Override
    public void modifier(Ordonnance ordonnance) {
        try {
            String req = "UPDATE ordonnance SET  reference='"+ordonnance.getReference()   + "', id_medicament='" + ordonnance.getId_Medicament() + "', id_medecin='"
                    + ordonnance.getid_Medecin() + "', date_ordonnance='" + ordonnance.getDateOrdonnance() + "', statut='"
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
                list.add(new Ordonnance( rs.getInt("id"),rs.getInt("Reference"), rs.getInt("id_medecin"), rs.getInt("id_medicament"),
                        rs.getDate("date_ordonnance"), rs.getString("statut")));
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

        return list;
    }
}