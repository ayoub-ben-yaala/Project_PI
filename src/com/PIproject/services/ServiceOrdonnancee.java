
package com.PIproject.services;

import com.PIproject.entities.Medicament;
import com.PIproject.entities.Ordonnance;
import com.PIproject.utils.DataSource;
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
        String req = "INSERT INTO `ordonnance`(`reference`, `id_medecin`, `id_medicament`, `date_ordonnance`, `statut`) VALUES (?,?,?,?,?)";
        PreparedStatement st = cnx.prepareStatement(req, Statement.RETURN_GENERATED_KEYS);
        st.setInt(1, ordonnance.getReference());
        st.setInt(2, ordonnance.getId_Medecin());
        st.setInt(3, ordonnance.getId_Medicament());
        st.setDate(4, ordonnance.getDateOrdonnance());
        st.setString(5, ordonnance.getStatut());
        
        st.executeUpdate();
        
        
        ResultSet generatedKeys = st.getGeneratedKeys();
        if (generatedKeys.next()) {
            int generatedId = generatedKeys.getInt(1);
            ordonnance.setId(generatedId); 
        }
        
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
public boolean supprimer1(Ordonnance ordonnance) {
    try {
        System.out.println("Deleting Ordonnance with ID: " + ordonnance.getId());
       
        String deleteOrdonnanceReq = "DELETE FROM ordonnance WHERE id = ?";
        PreparedStatement deleteOrdonnanceStmt = cnx.prepareStatement(deleteOrdonnanceReq);
        deleteOrdonnanceStmt.setInt(1, ordonnance.getId());
        int affectedRows = deleteOrdonnanceStmt.executeUpdate();
        deleteOrdonnanceStmt.close();

        return affectedRows > 0;
    } catch (SQLException e) {
        System.out.println(e.getMessage());
        return false;
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
    
    public List<String> afficher_nom_med() {
        List<String> list = new ArrayList<>();

        String req = "SELECT nom_medi FROM medicament";
           
        try {
            Statement st = cnx.createStatement();
            ResultSet rs = st.executeQuery(req);
            while (rs.next()) {
                String medi = rs.getString("nom_medi");
                list.add(medi);
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

    @Override
    public void supprimer(Ordonnance user) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    
}