/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.esprit.services;
import com.esprit.entities.Medecin;
import com.esprit.utils.DataSource;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class ServiceMedecin implements IService<Medecin> {

    private Connection cnx = DataSource.getInstance().getCnx();

    @Override
    public void ajouter(Medecin medecin) {
        try {
            String req = "INSERT INTO médecin( nom, prenom, spécialité) VALUES ('" + medecin.getNom() + "','" + medecin.getPrenom() + "','"
                    + medecin.getSpécialité() + "')";
            Statement st = cnx.createStatement();
            st.executeUpdate(req);
            System.out.println("Médecin ajouté !");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public void modifier(Medecin medecin) {
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

    public void supprimer(Medecin medecin) {
        try {
            String req = "DELETE from médecin WHERE id=" + medecin.getId();
            Statement st = cnx.createStatement();
            st.executeUpdate(req);
            System.out.println("Médecin supprimé !");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    @Override
    public List<Medecin> afficher() {
        List<Medecin> list = new ArrayList<>();

        String req = "SELECT * FROM médecin";
        try {
            Statement st = cnx.createStatement();
            ResultSet rs = st.executeQuery(req);
            while (rs.next()) {
                list.add(new Medecin(rs.getInt("id"), rs.getString("nom"), rs.getString("prenom"),
                        rs.getString("spécialité")));
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

        return list;
    }
    
    public List<String> getMedecins() {
        List<String> list = new ArrayList<>();

        String req = "SELECT nom,prenom FROM médecin";
        try {
            Statement st = cnx.createStatement();
            ResultSet rs = st.executeQuery(req);
            while (rs.next()) {
                String med =rs.getString("nom")+" "+rs.getString("prenom");
                list.add(med);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

        return list;
    }
   


}
