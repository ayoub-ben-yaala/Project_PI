/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.PIproject.controllers;

import com.PIproject.entities.SousPharmacie;
import com.PIproject.entities.User;
import com.PIproject.services.ServiceUser;
import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import javax.swing.JOptionPane;

/**
 * FXML Controller class
 *
 * @author bazinfo
 */
public class GestionPharmacieController implements Initializable {

    @FXML
    private TableView<User> UsersTV;
    @FXML
    private TableColumn<User , String> UserNameCol;
    @FXML
    private TableColumn<User , String> EmailCol;
    @FXML
    private TableColumn<User , String> AdressCol;
    @FXML
    private TableColumn<User , Integer> PhoneCol;
    @FXML
    private TableColumn<User , String> PharmCol;
    @FXML
    private TableColumn<User , String> MatriculeCol;
    @FXML
    private Button ajouter;
    @FXML
    private Button modifier;
    @FXML
    private Button supprimer;
    ObservableList<User> Data;
    @FXML
    private Button Président;
    @FXML
    private Button activer;
    @FXML
    private TableColumn<?, ?> StatutCol;

    /**
     * Initializes the controller class.
     */
    public void refreshPage() {
    
        ServiceUser serv =new ServiceUser();
        ObservableList<User> updatedData = serv.afficherPharmacie(); 
    
    UsersTV.getItems().clear();
    
    UsersTV.getItems().addAll(updatedData);
    
    UsersTV.refresh();
}
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        afficher();
            refreshPage();
    }    

    public void afficher(){
    
    ServiceUser Suser =new ServiceUser();
       
        // Accéder à la liste des éléments dans le TableView
          UserNameCol.setCellValueFactory(new PropertyValueFactory<>("UserName"));
          EmailCol.setCellValueFactory(new PropertyValueFactory<>("Email"));
          AdressCol.setCellValueFactory(new PropertyValueFactory<>("Adress"));
          PhoneCol.setCellValueFactory(new PropertyValueFactory<>("Phone"));
          PharmCol.setCellValueFactory(new PropertyValueFactory<>("NomPharmacie"));
          MatriculeCol.setCellValueFactory(new PropertyValueFactory<>("MatriculeFiscale"));
          StatutCol.setCellValueFactory(new PropertyValueFactory<>("Statut"));
          Data =Suser.afficherPharmacie();
          UsersTV.setItems(Data);
    }
    @FXML
    private void AjouterPharmacie(ActionEvent event) {
              try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("../views/AjouterPharmacie.fxml"));
            Parent root = loader.load();
            Scene scene = new Scene(root);
            Stage primaryStage = (Stage) ajouter.getScene().getWindow();
            primaryStage.setScene(scene);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void ModifierPharmacie(ActionEvent event) {
        User selectedUser = UsersTV.getSelectionModel().getSelectedItem();
    if (selectedUser != null) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("../views/ModifierPharmacie.fxml"));
            Parent root = loader.load();
            
            ModifierPharmacieController modifierPharmacieController = loader.getController();          
            modifierPharmacieController.setUserData(selectedUser);
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    }

    @FXML
    private void SupprimerPharmacie(ActionEvent event) {
        
                ServiceUser Suser = new ServiceUser();
            User selectedUser = UsersTV.getSelectionModel().getSelectedItem();
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
alert.setTitle("Confirmation");
alert.setHeaderText(null);
alert.setContentText("Êtes-vous sûr ?");
Optional<ButtonType> result = alert.showAndWait();

if (result.isPresent() && result.get() == ButtonType.OK && selectedUser != null) {
    Suser.supprimer(selectedUser);
    afficher();
    refreshPage();
} else {
    afficher();
    refreshPage();
}

    }

    @FXML
    private void Président(ActionEvent event) throws IOException {
        // Stage stage = (Stage) Add.getScene().getWindow();
          //  stage.close();
        
             
            FXMLLoader loader = new FXMLLoader(getClass().getResource("../views/AceuilPharmacie.fxml"));
            Parent root = loader.load();
 

            Scene scene = new Scene(root);
            Stage primaryStage = (Stage) Président.getScene().getWindow();
            primaryStage.setScene(scene);
            
    }

    @FXML
    private void ActiverPharmacie(ActionEvent event) {
        
                      ServiceUser Suser = new ServiceUser();
            User selectedUser = UsersTV.getSelectionModel().getSelectedItem();
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
alert.setTitle("Confirmation");
alert.setHeaderText(null);
alert.setContentText("Êtes-vous sûr ?");
Optional<ButtonType> result = alert.showAndWait();

if (result.isPresent() && result.get() == ButtonType.OK && selectedUser != null) {
    Suser.supprimer1(selectedUser);
    afficher();
    refreshPage();
} else {
    afficher();
    refreshPage();
}

    }
    
    
}
