package com.PIproject.controllers;
import java.time.LocalDate;
import java.sql.Date;
import com.PIproject.entities.Livraison;
import com.PIproject.services.ServiceLivraison;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;
import javafx.fxml.FXML;
import javafx.scene.control.Button;


public class DetailsLivController_1 implements Initializable {

    @FXML
    private TableView<Livraison> LIVRAISONTV;

    @FXML
    private TableColumn<Livraison, Integer> tfIdCommande;
    @FXML
    private TableColumn<Livraison, Integer> tfIdLivreur;
    @FXML
    private TableColumn<Livraison, Integer> supp;

    @FXML
    private TextField tfReference1;
    @FXML
    private TextField tfIdCommande1;

    

    @FXML
    public void callMain(ActionEvent event) {
        Map main = new Map(36.853546749967926,10.207102064868804);
        main.setVisible(true);
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {

      supp.setCellValueFactory(new PropertyValueFactory<>("Reference"));
        tfIdCommande.setCellValueFactory(new PropertyValueFactory<>("idCommande"));
        tfIdLivreur.setCellValueFactory(new PropertyValueFactory<>("Nom"));
        
        TableColumn<Livraison, Void> buttonColumn = new TableColumn<>("ADRESSE");

buttonColumn.setCellFactory(column -> {
    return new TableCell<Livraison, Void>() {
        private final Button button = new Button("Adresse");

        {
            // Définir l'action du bouton
            button.setOnAction(event -> {
                callMain(event);
                // Appeler la méthode souhaitée avec le modèle de données associé
                // par exemple : model.maMethode();
            });
        }

        @Override
        protected void updateItem(Void item, boolean empty) {
            super.updateItem(item, empty);
            if (empty) {
                setGraphic(null);
            } else {
                setGraphic(button);
            }
        }
    };
});


LIVRAISONTV.getColumns().add(buttonColumn);
        LIVRAISONTV.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null) {
                tfReference1.setText(newValue.getReference());
                tfIdCommande1.setText(String.valueOf(newValue.getIdCommande()));
          
            } else {
                clearInputFields();
            }
        });
        

    

        afficherLivraisons();
        
        
    }

  

    private void clearInputFields() {
        tfReference1.clear();
        tfIdCommande1.clear();
   
    }

    public void afficherLivraisons() {
        
        ServiceLivraison serviceLivraison = new ServiceLivraison();
        List<Livraison> livraisons = serviceLivraison.afficherLiv(1);
        LIVRAISONTV.getItems().addAll(livraisons);
    }






}
