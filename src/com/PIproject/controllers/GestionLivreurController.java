/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.PIproject.controllers;

import com.PIproject.entities.Livreur;
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
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import javafx.util.Callback;
import javax.swing.JOptionPane;


/**
 * FXML Controller class
 *
 * @author bazinfo
 */
public class GestionLivreurController implements Initializable {

    @FXML
    private TableView<User> UsersTV;
    @FXML
    private TableColumn<User,String> UserNameCol;
    @FXML
    private TableColumn<User, String> EmailCol;
    @FXML
    private TableColumn<User, String> AdressCol;
    @FXML
    private TableColumn<User, Integer> PhoneCol;
    @FXML
    private TableColumn<Livreur, Integer> CinCol;
   
    ObservableList<User> Data;
    @FXML
    private Button ajouter;
    @FXML
    private Button modifier;
    @FXML
    private Button supprimer;
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
        ObservableList<User> updatedData = serv.afficherLivreur(); 
    
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
          CinCol.setCellValueFactory(new PropertyValueFactory<>("Cin"));
          StatutCol.setCellValueFactory(new PropertyValueFactory<>("Statut"));
          Data =Suser.afficherLivreur();
          UsersTV.setItems(Data);
    }

  

    @FXML
    private void AjouterLivreur(ActionEvent event) {
            try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("../views/AjouterLivreur.fxml"));
            Parent root = loader.load();
            Scene scene = new Scene(root);
            Stage primaryStage = (Stage) ajouter.getScene().getWindow();
            primaryStage.setScene(scene);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void ModifierLivreur(ActionEvent event) {
        
    User selectedUser = UsersTV.getSelectionModel().getSelectedItem();
    if (selectedUser != null) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("../views/ModifierLivreur.fxml"));
            Parent root = loader.load();
            
            ModifierLivreurController modifierLivreurController = loader.getController();          
            modifierLivreurController.setUserData(selectedUser);
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

    @FXML
    private void SupprimerLivreur(ActionEvent event) {
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
} else {
    afficher();
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
    private void ActiverLivreur(ActionEvent event) {
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
} else {
    afficher();
}

    }

  
    
}
