/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.esprit.services;
import com.esprit.entities.Médecin;
import com.esprit.utils.DataSource;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class ServiceMédecin implements IService<Médecin> {

    private Connection cnx = DataSource.getInstance().getCnx();

    @Override
    public void ajouter(Médecin medecin) {
        try {
            String req = "INSERT INTO medecin(id, nom, prenom, specialite) VALUES ("
                    + medecin.getId() + ",'" + medecin.getNom() + "','" + medecin.getPrenom() + "','"
                    + medecin.getSpécialité() + "')";
            Statement st = cnx.createStatement();
            st.executeUpdate(req);
            System.out.println("Médecin ajouté !");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public void modifier(Médecin medecin) {
        try {
            String req = "UPDATE medecin SET nom='" + medecin.getNom() + "', prenom='" + medecin.getPrenom() + "', specialite='"
                    + medecin.getSpécialité() + "' WHERE id=" + medecin.getId();
            Statement st = cnx.createStatement();
            st.executeUpdate(req);
            System.out.println("Médecin modifié !");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public void supprimer(Médecin medecin) {
        try {
            String req = "DELETE from medecin WHERE id=" + medecin.getId();
            Statement st = cnx.createStatement();
            st.executeUpdate(req);
            System.out.println("Médecin supprimé !");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @Override
    public List<Médecin> afficher() {
        List<Médecin> list = new ArrayList<>();

        String req = "SELECT * FROM médecin";
        try {
            Statement st = cnx.createStatement();
            ResultSet rs = st.executeQuery(req);
            while (rs.next()) {
                list.add(new Médecin(rs.getInt("id"), rs.getString("nom"), rs.getString("prenom"),
                        rs.getString("spécialité")));
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

        return list;
    }
}
