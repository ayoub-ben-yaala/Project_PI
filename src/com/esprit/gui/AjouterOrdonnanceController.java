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
import java.util.ResourceBundle;
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
    private ComboBox<Ordonnance> medicament;

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

    public void setStage(Stage stage) {
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        Reference.setCellValueFactory(new PropertyValueFactory<>("Reference"));
        Medecin.setCellValueFactory(new PropertyValueFactory<>("Nom_Medecin"));
        Medicament.setCellValueFactory(new PropertyValueFactory<>("Nom_Medicament"));
        Date.setCellValueFactory(new PropertyValueFactory<>("date"));
        Statue.setCellValueFactory(new PropertyValueFactory<>("Statut"));
        ServiceOrdonnancee so = new ServiceOrdonnancee();
        table.getItems().addAll(so.afficher());
        ServiceMedecin sm = new ServiceMedecin();
        ObservableList<String> medecinList = FXCollections.observableArrayList(sm.getMedecins());
        medecin.setItems(medecinList);

    }

    @FXML
    private void ajouter(ActionEvent event) throws IOException {
        try {
            int ref = Integer.parseInt(this.reference.getText());
            String nomMedecin = medecin.getValue(); // Assurez-vous que idMedecin est de type String
            String nomMedicament = "hello"; // Assurez-vous que idMedicament est de type String
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

}
