/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.PIproject.Controller;

import com.PIproject.entities.Livreur;
import com.PIproject.entities.User;
import com.PIproject.services.ServiceUser;
import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.beans.Observable;
import javafx.collections.FXCollections;
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
public class AceuilController implements Initializable {

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
    private TableColumn<User, Integer> CinCol;
    @FXML
    private TableColumn<User, String> PharmCol;
    @FXML
    private TableColumn<User, String> MatriculeCol;
    
    ObservableList<User> Data;
    @FXML
    private Button ajouter;
    @FXML
    private Button modifier;
    private TextField PasswordFT;
    private TextField AdressFT;
    private TextField EmailFT;
    private TextField UserNameFT;
    private TextField PhoneFT;
    private TextField CinFT;
    private Button update;
    @FXML
    private Button supprimer;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    afficher();

  }    
     

    @FXML
    private void handleModifierButton(ActionEvent event) {
       User selectedUser = UsersTV.getSelectionModel().getSelectedItem();
    if (selectedUser != null) {
          try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("../Views/ModifierUser.fxml"));
            Parent root = loader.load();
            Scene scene = new Scene(root);
            Stage primaryStage = (Stage) modifier.getScene().getWindow();
            primaryStage.setScene(scene);
            
            
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    }


    @FXML
    private void handleSupprimerButton(ActionEvent event) {
            ServiceUser Suser = new ServiceUser();
            User selectedUser = UsersTV.getSelectionModel().getSelectedItem();
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
alert.setTitle("Confirmation");
alert.setHeaderText(null);
alert.setContentText("Êtes-vous sûr ?");
Optional<ButtonType> result = alert.showAndWait();

if (result.isPresent() && result.get() == ButtonType.OK && selectedUser != null) {
    Suser.supprimer(selectedUser);
    JOptionPane.showMessageDialog(null, "User Deleted !");
    afficher();
} else {
    afficher();
}

    }
    
    public void afficher(){
    
    ServiceUser Suser =new ServiceUser();
       
        // Accéder à la liste des éléments dans le TableView
          UserNameCol.setCellValueFactory(new PropertyValueFactory<>("UserName"));
          EmailCol.setCellValueFactory(new PropertyValueFactory<>("Email"));
          AdressCol.setCellValueFactory(new PropertyValueFactory<>("Adress"));
          PhoneCol.setCellValueFactory(new PropertyValueFactory<>("Phone"));
          CinCol.setCellValueFactory(new PropertyValueFactory<>("Cin"));
          PharmCol.setCellValueFactory(new PropertyValueFactory<>("NomPharmacie"));
          MatriculeCol.setCellValueFactory(new PropertyValueFactory<>("MatriculeFiscale"));
          Data =Suser.afficher();
          UsersTV.setItems(Data);
    }

    @FXML
    private void ajouterUserInterface(ActionEvent event) {
        
          try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("../Views/AjouterUser.fxml"));
            Parent root = loader.load();
 

            Scene scene = new Scene(root);
            Stage primaryStage = (Stage) ajouter.getScene().getWindow();
            primaryStage.setScene(scene);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    
}
