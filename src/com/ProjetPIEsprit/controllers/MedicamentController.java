/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.ProjetPIEsprit.controllers;

import com.ProjetPIEsprit.entities.Categorie;
import com.ProjetPIEsprit.entities.Type;

import com.ProjetPIEsprit.entities.Medicament;
import com.ProjetPIEsprit.services.ServiceCategorie;
import com.ProjetPIEsprit.services.ServiceMedicament;
import com.ProjetPIEsprit.services.ServiceType;
import java.net.URL;
import java.sql.SQLException;
import static java.util.Collections.list;
import java.util.List;
import java.util.ResourceBundle;
import java.util.stream.Collectors;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javax.swing.JOptionPane;

/**
 * FXML Controller class
 *
 * @author eya trabelsi
 */
public class MedicamentController implements Initializable {
   ServiceMedicament sm = new ServiceMedicament() ;
    @FXML
    private TextField nommedFT;
    @FXML
    private TextField refFT;
    @FXML
    private TextField DateFT;
    @FXML
    private TextField prixFT;
    @FXML
    private TextField NoticeFT;
    @FXML
    private Button Ajouter;
    @FXML
    private Button Modifier;
    @FXML
    private Button Supprimer;
    @FXML
    private TableView<Medicament> tabmedi;
    @FXML
    private TableColumn<Medicament, String> catcol;
    @FXML
    private TableColumn<Medicament, String> typecol;
    @FXML
    private TableColumn<Medicament, String> nomcol;
    ObservableList<Medicament> list;
    @FXML
    private ChoiceBox<String> choicecat;
    @FXML
    private ChoiceBox<String> choicetyp;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
         ServiceCategorie serviceCategorie = new ServiceCategorie();
        List<String> categories = serviceCategorie.afficher_categorie();
        ObservableList<String> categorieList = FXCollections.observableArrayList("");
categorieList.addAll(categories);
choicecat.setItems(categorieList);

ServiceType serviceType = new ServiceType();
        List<String> types = serviceType.afficher_type();
        ObservableList<String> typeList = FXCollections.observableArrayList("");
typeList.addAll(types);
choicetyp.setItems(typeList);
        afficher();

   ServiceMedicament serviceM = new ServiceMedicament();
        List<Medicament> medicaments = serviceM.afficher();
        tabmedi.getItems().addAll(medicaments);

    
    }    
    
    
    public void ajouterMedicament(ActionEvent event) throws SQLException {
        int reference = Integer.parseInt(refFT.getText());
        String type = choicetyp.getValue();
        String categorie = choicecat.getValue();
        int prix = Integer.parseInt(prixFT.getText());
        String notice = NoticeFT.getText();
        String dateExpiration = DateFT.getText();
        String nom = nommedFT.getText();
        Medicament medicament = new Medicament(categorie, type, nom, reference, dateExpiration, prix, notice);
        int typeId = sm.obtenirIdType(type);
        int categorieId = sm.obtenirIdCategorie(categorie);
    
    // Définir les IDs du type et de la catégorie dans l'objet Medicament
    medicament.setType(String.valueOf(typeId));
    medicament.setCategorie(String.valueOf(categorieId));
        sm.ajouterMedicament(medicament);
        
        // Réinitialiser les champs après l'ajout
        refFT.clear();
        choicetyp.getSelectionModel().clearSelection();
        choicecat.getSelectionModel().clearSelection();
        prixFT.clear();
        NoticeFT.clear();
        DateFT.clear();
        nommedFT.clear();
    }
    


  /*  @FXML
    private void Ajouter(ActionEvent event) throws SQLException {
        
        
           
     /*   ServiceMedicament sm = new ServiceMedicament();
        
        sm.ajouter(new Medicament (choicecat.getSelectionModel().getSelectedItem(), choicetyp.getSelectionModel().getSelectedItem(), nommedFT.getText(), Integer.parseInt(refFT.getText()), DateFT.getText(), Integer.parseInt(prixFT.getText()), NoticeFT.getText()));
      /*  String nomcat = choicecat.getSelectionModel().getSelectedItem();
         String nomtyp = choicetyp.getSelectionModel().getSelectedItem();
         String nom_medi = nommedFT.getText();  
         int reference = Integer.parseInt(refFT.getText()); 
          String DateExp = DateFT.getText();  
          int prix = Integer.parseInt(prixFT.getText());
          String notice = NoticeFT.getText();
          
          Medicament medicament = new Medicament(nomcat, nomtyp, nom_medi, reference, DateExp, prix, notice);
          sm.ajouter(medicament);
           tabmedi.getItems().add(medicament);
        /*   nomtyp.setValue(null);
            nom_medi.setValue(null);
            reference.clear();
            DateExp.clear();
            prix.clear();
            notice.clear();*/
       //   JOptionPane.showMessageDialog(null, "Medicament ajoutée !");
          
    
   // }
    @FXML
    private void Modifier(ActionEvent event) {
    }

    @FXML
    private void Supprimer(ActionEvent event) {
    }
    
     private void afficher(){
        catcol.setCellValueFactory(new PropertyValueFactory<>("nomcategorie"));
        typecol.setCellValueFactory(new PropertyValueFactory<>("nomtype"));
        nomcol.setCellValueFactory(new PropertyValueFactory<>("nom_medi"));
        ServiceMedicament m =new ServiceMedicament();
        list = m.afficher();
        tabmedi.setItems(list);
    }
    
}
