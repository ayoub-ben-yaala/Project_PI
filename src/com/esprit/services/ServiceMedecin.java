/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.esprit.services;

import com.esprit.entities.Medecin;
import com.esprit.entities.Specialite;
import com.esprit.utils.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
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
            String req = "INSERT INTO medecin( nom, prenom, specialite,email) VALUES ('" + medecin.getNom() + "','" + medecin.getPrenom() + "','"
                    + medecin.getSpecialite() + "','"+medecin.getEmail()+"')";
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
                    + medecin.getSpecialite() +"', email='"+medecin.getEmail()+ "' WHERE id=" + medecin.getId();
            Statement st = cnx.createStatement();
            st.executeUpdate(req);
            System.out.println("Médecin modifié !");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public void supprimer(Medecin medecin) {
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
    public List<Medecin> afficher() {
        List<Medecin> list = new ArrayList<>();

        String req = "SELECT * FROM medecin";
        try {
            Statement st = cnx.createStatement();
            ResultSet rs = st.executeQuery(req);
            while (rs.next()) {
                String retrievedValue = rs.getObject(4).toString().trim();
                Specialite spec = Specialite.valueOf(retrievedValue);
                System.out.println(spec);

                list.add(new Medecin(rs.getInt("id"), rs.getString("nom"), rs.getString("prenom"), spec ,rs.getString("email")));
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

        return list;
    }

    public List<String> getMedecins() {
        List<String> list = new ArrayList<>();

        String req = "SELECT nom,prenom FROM medecin";
        try {
            Statement st = cnx.createStatement();
            ResultSet rs = st.executeQuery(req);
            while (rs.next()) {
                String med = rs.getString("nom") + " " + rs.getString("prenom");
                list.add(med);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

        return list;
    }
      public Medecin getMdecinById(int id) {
        Medecin medecin = new Medecin();

        String req = "SELECT * FROM medecin where id="+id;
        try {
            Statement st = cnx.createStatement();
            ResultSet rs = st.executeQuery(req);
            while (rs.next()) {
                String retrievedValue = rs.getObject(4).toString().trim();
                Specialite spec = Specialite.valueOf(retrievedValue);
                
                medecin.setId(rs.getInt("id"));
                medecin.setNom(rs.getString("nom"));
                medecin.setPrenom(rs.getString("prenom"));
                medecin.setSpecialite(spec);
                medecin.setEmail(rs.getString("email"));
                
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

        return medecin;
    }
        public Medecin getMdecinByNom(String nom) {
        Medecin medecin = new Medecin();

        String req = "SELECT * FROM medecin where CONCAT(nom, ' ', prenom) = ?";
        try {
            PreparedStatement st = cnx.prepareStatement(req);
        st.setString(1, nom);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                String retrievedValue = rs.getObject(4).toString().trim();
                Specialite spec = Specialite.valueOf(retrievedValue);
                
                medecin.setId(rs.getInt("id"));
                medecin.setNom(rs.getString("nom"));
                medecin.setPrenom(rs.getString("prenom"));
                medecin.setSpecialite(spec);
                medecin.setEmail(rs.getString("email"));
                
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

        return medecin;
    }

}
