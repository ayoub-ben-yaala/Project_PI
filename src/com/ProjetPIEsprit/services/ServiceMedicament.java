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
        statement.setString(1, medicament.getNom_medi());
        statement.setInt(2, medicament.getReference());
        statement.setString(5, medicament.getDateExp());
        statement.setInt(4, medicament.getPrix());
       statement.setString(3, medicament.getNotice());
        statement.executeUpdate();
    } catch (SQLException e) {
        e.printStackTrace();
    }
}
 
 public int obtenirIdType(String nomType) {
    String query = "SELECT id_type FROM type WHERE nomtype = ?";
    int typeId = 0 ; // Valeur par défaut en cas d'échec
   
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
 
  public String obtenirnomType(int IdType) {
    String query = "SELECT nomtype FROM type WHERE id_type = ?";
    String typenom = ""; // Valeur par défaut en cas d'échec
   
    try (PreparedStatement statement = cnx.prepareStatement(query)) {
        statement.setInt(1, IdType);
        ResultSet resultSet = statement.executeQuery();
       
        if (resultSet.next()){
            typenom = resultSet.getString("nomtype");
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }
   
    return typenom;
}
public String obtenirNomCategorie(int IdCategorie) {
    String query = "SELECT nomcategorie FROM categorie WHERE id_categorie = ?";
    String nomcategorie = ""; // Valeur par défaut en cas d'échec
   
    try (PreparedStatement statement = cnx.prepareStatement(query)) {
        statement.setInt(1, IdCategorie);
        ResultSet resultSet = statement.executeQuery();
       
        if (resultSet.next()) {
            nomcategorie = resultSet.getString("nomcategorie");
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }
   
    return nomcategorie;
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
    
    public void modifier1(Medicament m) {
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
     public void modifier(Medicament medicament) throws SQLException {
         String nomType = obtenirnomType(obtenirIdType(medicament.getType()));
         String nomCategorie = obtenirNomCategorie(obtenirIdCategorie(medicament.getCategorie()));
        String query = "UPDATE medicament SET  id_categorie = ?, id_type = ?,reference = ?, notice = ?, DateExp = ? , prix = ? WHERE id_medi = ?";
        try (PreparedStatement statement = cnx.prepareStatement(query)) {
            statement.setInt(1, medicament.getReference());
            statement.setString(2,nomCategorie );
            statement.setString(3, nomType);
            statement.setInt(4, medicament.getPrix());
            statement.setString(5, medicament.getNotice());
            statement.setString(6, medicament.getDateExp());
            statement.setInt(7, medicament.getIdMed());
            statement.executeUpdate();
        }
    }
    public void supprimer1(Medicament m) {
        try {
            String req = "DELETE from medicament WHERE reference=?";
            PreparedStatement pst = cnx.prepareStatement(req);
            pst.setInt(1, m.getReference());
            pst.executeUpdate();
            System.out.println("Medicament supprimée !");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
     public void supprimer(int idMedicament) throws SQLException {
        
        try {
            String query = "DELETE FROM medicament WHERE id_medi = ?";
            PreparedStatement statement = cnx.prepareStatement(query); 
            
            statement.setInt(1, idMedicament);
            statement.executeUpdate();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
}
    }
      
   
   public ObservableList<Medicament> afficher1() {
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
    public ObservableList<Medicament> afficher() throws SQLException {
        //List<Medicament> medicaments = new ArrayList<>();
                ObservableList<Medicament> medicaments = FXCollections.observableArrayList();

        String query = "SELECT * FROM medicament";
        try (PreparedStatement statement = cnx.prepareStatement(query)) {
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    Medicament medicament = new Medicament();
                    String nomType = obtenirnomType(Integer.parseInt(resultSet.getString("id_type")));
                    String nomCategorie = obtenirNomCategorie(Integer.parseInt(resultSet.getString("id_categorie")));
                    medicament.setNom_medi(resultSet.getString("nom_medi"));
                    medicament.setReference(resultSet.getInt("reference"));
                    medicament.setCategorie(nomCategorie);
                    medicament.setType(nomType);
                    medicament.setPrix(resultSet.getInt("prix"));
                    medicament.setNotice(resultSet.getString("notice"));
                    medicament.setDateExp(resultSet.getString("DateExp"));
                    medicaments.add(medicament);
                }
            }
        }
        return medicaments;
    }
    
    
    public ObservableList<Medicament> rechercheParNomMedicament(String nomMedicament) throws SQLException {
   // List<Medicament> medicaments = new ArrayList<>();
      ObservableList<Medicament> medicaments = FXCollections.observableArrayList();
    String query = "SELECT * FROM medicament WHERE nom_medi LIKE ?";
    try (PreparedStatement statement = cnx.prepareStatement(query)) {
        statement.setString(1, "%" + nomMedicament + "%");
        try (ResultSet resultSet = statement.executeQuery()) {
            while (resultSet.next()) {
                Medicament medicament = new Medicament();
                medicament.setNom_medi(resultSet.getString("nom_medi"));
           
                medicament.setCategorie(obtenirNomCategorie(Integer.parseInt(resultSet.getString("id_categorie"))));
                medicament.setType(obtenirnomType(Integer.parseInt(resultSet.getString("id_type"))));
             
                
                medicaments.add(medicament);
            }
        }
    }
    return medicaments;
}
    
        public ObservableList<Medicament> rechercheParType(String Type) throws SQLException {
       // List<Medicament> medicaments = new ArrayList<>();
        ObservableList<Medicament> medicaments = FXCollections.observableArrayList();
        int id_type =  obtenirIdType(Type);
           
        String query = "SELECT * FROM medicament WHERE id_type = ?";
        try (PreparedStatement statement = cnx.prepareStatement(query)) {
            statement.setInt(1, id_type);
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    Medicament medicament = new Medicament();
                   medicament.setNom_medi(resultSet.getString("nom_medi"));
                medicament.setReference(resultSet.getInt("reference"));
                medicament.setCategorie(obtenirNomCategorie(Integer.parseInt(resultSet.getString("id_categorie"))));
                medicament.setType(obtenirnomType(Integer.parseInt(resultSet.getString("id_type"))));
             
                medicament.setPrix(resultSet.getInt("prix"));
                medicament.setNotice(resultSet.getString("notice"));
                medicament.setDateExp(resultSet.getString("DateExp"));
                    medicaments.add(medicament);
                }
            }
        }
        return medicaments;
    }
        
        
         public ObservableList<Medicament> rechercheParCategorie(String Categorie) throws SQLException {
        ObservableList<Medicament> medicaments = FXCollections.observableArrayList();
           int id_categorie = obtenirIdCategorie(Categorie);
        String query = "SELECT * FROM medicament WHERE id_categorie = ?";
        try (PreparedStatement statement = cnx.prepareStatement(query)) {
            statement.setInt(1, id_categorie);
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    Medicament medicament = new Medicament();
                      medicament.setNom_medi(resultSet.getString("nom_medi"));
                medicament.setReference(resultSet.getInt("reference"));
                medicament.setCategorie(obtenirNomCategorie(Integer.parseInt(resultSet.getString("id_categorie"))));
                medicament.setType(obtenirnomType(Integer.parseInt(resultSet.getString("id_type"))));
             
                medicament.setPrix(resultSet.getInt("prix"));
                medicament.setNotice(resultSet.getString("notice"));
                medicament.setDateExp(resultSet.getString("DateExp"));
                    medicaments.add(medicament);
                }
            }
        }
        return medicaments;
    }
    
}
