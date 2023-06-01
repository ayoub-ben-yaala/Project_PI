/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.PIproject.controllers;

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
public class AceuilController implements Initializable {

    @FXML
    private Button Pharmacie;
    @FXML
    private Button Livreur;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void GestionPharmacie(ActionEvent event) throws IOException {
       // Stage stage = (Stage) Pharmacie.getScene().getWindow();
         //   stage.close();
         FXMLLoader loader = new FXMLLoader(getClass().getResource("../views/GestionPharmacie.fxml"));
            Parent root1 = loader.load();
            Scene scene = new Scene(root1);
            Stage primaryStage = (Stage) Pharmacie.getScene().getWindow();
            primaryStage.setScene(scene);  
             
             
             
    }

    @FXML
    private void GestionLivreur(ActionEvent event) throws IOException {
            // Stage stage = (Stage) Livreur.getScene().getWindow();
            //stage.close();
FXMLLoader loader = new FXMLLoader(getClass().getResource("../views/GestionLivreur.fxml"));
            Parent root = loader.load();
            Scene scene = new Scene(root);
            Stage primaryStage = (Stage) Livreur.getScene().getWindow();
            primaryStage.setScene(scene);
            
            
    }
    
}
