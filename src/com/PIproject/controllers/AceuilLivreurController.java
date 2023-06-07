/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.PIproject.controllers;

import com.PIproject.entities.Livreur;
import com.PIproject.entities.User;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author bazinfo
 */
public class AceuilLivreurController implements Initializable {

    @FXML
    private Button logout;
    @FXML
    private Button profile;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

      @FXML
    private void logout(ActionEvent event) throws IOException{
        FXMLLoader loader = new FXMLLoader(getClass().getResource("../views/Login.fxml"));
            Parent root = loader.load();
            Scene scene = new Scene(root);
            Stage primaryStage = (Stage) logout.getScene().getWindow();
            primaryStage.setScene(scene);
        
    }

  
    
    @FXML
    private void profil(ActionEvent event)throws IOException{
      
        FXMLLoader loader = new FXMLLoader(getClass().getResource("../views/ModifierProfileLivreur.fxml"));
            Parent root = loader.load();
            Scene scene = new Scene(root);
            Stage primaryStage = (Stage) logout.getScene().getWindow();
            primaryStage.setScene(scene);
            ModifierProfileLivreurController modifierProfileLivreurController = loader.getController();
modifierProfileLivreurController.setLoggedInUser(LoginController.loggedInUser);
        
    }
    
}
