package com.esprit.services;

import com.esprit.entities.RendezVous;
import com.esprit.utils.DataSource;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class ServiceRendezVous implements IService<RendezVous> {

    private Connection cnx = DataSource.getInstance().getCnx();

    public void ajouter(RendezVous rendezVous) {
        try {
            String req = "INSERT INTO rendezvous(id, id_livraison, date_livraison) VALUES ("
                    + rendezVous.getId() + "," + rendezVous.getIdLivraison() + ",'" + rendezVous.getDateLivraison() + "')";
            Statement st = cnx.createStatement();
            st.executeUpdate(req);
            System.out.println("Rendez-vous ajouté !");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public void modifier(RendezVous rendezVous) {
        try {
            String req = "UPDATE rendez_vous SET id_livraison=" + rendezVous.getIdLivraison()
                    + ", date_livraison='" + rendezVous.getDateLivraison() + "' WHERE id=" + rendezVous.getId();
            Statement st = cnx.createStatement();
            st.executeUpdate(req);
            System.out.println("Rendez-vous modifié !");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public void supprimer(RendezVous rendezVous) {
        try {
            String req = "DELETE from rendez_vous WHERE id=" + rendezVous.getId();
            Statement st = cnx.createStatement();
            st.executeUpdate(req);
            System.out.println("Rendez-vous supprimé !");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public List<RendezVous> afficher() {
        List<RendezVous> list = new ArrayList<>();

        String req = "SELECT * FROM rendez_vous";
        try {
            Statement st = cnx.createStatement();
            ResultSet rs = st.executeQuery(req);
            while (rs.next()) {
                list.add(new RendezVous(rs.getInt("id"), rs.getInt("id_livraison"), rs.getDate("date_livraison")));
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

        return list;
    }
}