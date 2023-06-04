/*
     * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
     * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.esprit.gui;

import com.esprit.entities.Medecin;
import com.esprit.entities.Ordonnance;
import com.esprit.services.ServiceMedecin;
import com.esprit.services.ServiceOrdonnancee;

import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.util.List;
import java.util.ResourceBundle;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.util.Callback;

public class AjouterOrdonnanceController implements Initializable {

    @FXML
    private TableView<Ordonnance> table;

    @FXML
    private TextField reference;

    @FXML
    private ComboBox<String> medecin;

    @FXML
    private ComboBox<String> medicament;

    @FXML
    private DatePicker date;

    @FXML
    private TextField statue;
    @FXML
    private Button ajouter;
    @FXML
    private Button supprimer;
    @FXML
    private TableView<?> table1;
    @FXML
    private TableColumn<Ordonnance, Integer> Reference;
    @FXML
    private TableColumn<Ordonnance, String> Medecin;
    @FXML
    private TableColumn<Ordonnance, String> Medicament;
    @FXML
    private TableColumn<Ordonnance, Date> Date;
    @FXML
    private TableColumn<Ordonnance, String> Statue;
    @FXML
    private AnchorPane modifier;
    private Ordonnance selectedOrdonnance;
    @FXML
    private Button modifierButton;

    public void setStage(Stage stage) {
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        Reference.setCellValueFactory(new PropertyValueFactory<>("Reference"));
        Medecin.setCellValueFactory(new PropertyValueFactory<>("Medecin"));
        Medicament.setCellValueFactory(new PropertyValueFactory<>("Medicament"));
        Date.setCellValueFactory(new PropertyValueFactory<>("date"));
        Statue.setCellValueFactory(new PropertyValueFactory<>("Statut"));
        ServiceOrdonnancee so = new ServiceOrdonnancee();
        table.getItems().addAll(so.afficher());
        ServiceMedecin sm = new ServiceMedecin();
        ObservableList<String> medecinList = FXCollections.observableArrayList(sm.getMedecins());
        medecin.setItems(medecinList);
        Medecin.setCellValueFactory(cellData -> {
            Medecin medecin = sm.getMdecinById(cellData.getValue().getId_Medecin());
            String fullName = medecin.getNom()+ " " + medecin.getPrenom();
            return new SimpleStringProperty(fullName);
        });
        
       /* ServiceMedicament sme = new ServiceMedicament();
         Medicament.setCellValueFactory(cellData -> {
            Medicament medicament = sme.getMedicamentById(cellData.getValue().getId_Medicament());
            String fullName = medicament.getNom();
            return new SimpleStringProperty(fullName);
        });*/
       
       
      /*  medecin.setOnAction(event -> {
        int selectedMedecin = medecin.getValue();
        List<Ordonnance> ordonnances = so.getOrdonnancesByMedecin(selectedMedecin);
        table.getItems().setAll(ordonnances);
    });*/
        table.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
        if (newSelection != null) {
            // Set the selectedOrdonnance variable to the newly selected item
            selectedOrdonnance = newSelection;

            // Populate the input fields with the selected ordonnance's data
            reference.setText(String.valueOf(selectedOrdonnance.getReference()));
            medecin.setValue(sm.getMdecinById(selectedOrdonnance.getId_Medecin()).getNom()+" "+sm.getMdecinById(selectedOrdonnance.getId_Medecin()).getPrenom());
            medicament.setValue("hello");
            date.setValue(selectedOrdonnance.getDate().toLocalDate());
            statue.setText(selectedOrdonnance.getStatut());
        }
    });
}
    
    

    @FXML
    private void ajouter(ActionEvent event) throws IOException {
        
        try {
            int ref = Integer.parseInt(this.reference.getText());
            ServiceMedecin sm = new ServiceMedecin();
            int nomMedecin = sm.getMdecinByNom(medecin.getValue()).getId(); // Assurez-vous que idMedecin est de type String
            
            int nomMedicament = 1; // Assurez-vous que idMedicament est de type String
            Date dateord = java.sql.Date.valueOf(date.getValue()); // Convertir LocalDate en Date
            String statut = statue.getText();

            Ordonnance ordonnance = new Ordonnance(ref, nomMedecin, nomMedicament, dateord, statut);
            

            ServiceOrdonnancee so = new ServiceOrdonnancee();
           so.ajouter(ordonnance);
            table.getItems().add(ordonnance);

            reference.clear();
            medecin.setValue(null);
            medicament.setValue(null);
            date.setValue(null);
            statue.clear();
        } catch (NumberFormatException e) {
            // Gérer l'exception si la conversion de la référence en entier échoue
            System.out.println("La référence doit être un nombre entier.");
        }
    }


    @FXML
    private void supprimer(ActionEvent event) {
          ServiceOrdonnancee so = new ServiceOrdonnancee();
          so.supprimer(table.getSelectionModel().getSelectedItem());
          table.getItems().remove(0);
         
       
    }

 @FXML
private void modifier(ActionEvent event) {
    try {
        int ref = Integer.parseInt(reference.getText());
        String nomMedecin = medecin.getValue();
        ServiceMedecin sm =new ServiceMedecin();
        int nomMedicament =1;
        Date dateord = java.sql.Date.valueOf(date.getValue());
        String statut = statue.getText();

        // Check if there is a selected ordonnance
        if (selectedOrdonnance != null) {
            // Update the selected ordonnance with the modified data
            selectedOrdonnance.setReference(ref);
            selectedOrdonnance.setNom_Medecin(sm.getMdecinByNom(nomMedecin).getId());
            selectedOrdonnance.setNom_Medicament(nomMedicament);
            selectedOrdonnance.setDate(dateord);
            selectedOrdonnance.setStatut(statut);

            ServiceOrdonnancee so = new ServiceOrdonnancee();
            so.modifier(selectedOrdonnance);
            System.out.println(selectedOrdonnance);

            // Refresh the table to reflect the changes
            table.refresh();

            // Reload the data from the database and update the table
            
            table.getItems().setAll(so.afficher());
        } else {
            System.out.println("Aucune ordonnance sélectionnée.");
        }
        
        // Clear the input fields and selectedOrdonnance variable
        reference.clear();
        medecin.setValue(null);
        medicament.setValue(null);
        date.setValue(null);
        statue.clear();
        selectedOrdonnance = null;
    } catch (NumberFormatException e) {
        System.out.println("La référence doit être un nombre entier.");
    }
}




    }


