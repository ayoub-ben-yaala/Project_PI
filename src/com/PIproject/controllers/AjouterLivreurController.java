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
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javax.swing.JOptionPane;

/**
 * FXML Controller class
 *
 * @author bazinfo
 */
public class AjouterLivreurController implements Initializable {

    @FXML
    private TextField UserNameFT;
    @FXML
    private TextField EmailFT;
    
    @FXML
    private TextField AdressFT;
    @FXML
    private TextField PhoneFT;
    private Button Add;
    @FXML
    private TextField CinFT;
    @FXML
    private Button add;
    @FXML
    private Button Président;

    
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    


    @FXML
    private void AddLivreur(ActionEvent event) throws IOException{
        
          
        Livreur livreur = new Livreur (Integer.parseInt(CinFT.getText()),UserNameFT.getText(), EmailFT.getText(),"open", Integer.parseInt(PhoneFT.getText()), AdressFT.getText(),"En attend");
        
        ServiceUser Suser =new ServiceUser();
        Suser.ajouter(livreur);

        JOptionPane.showMessageDialog(null, "Livreur Ajouter !");
        
           Stage stage = (Stage) Add.getScene().getWindow();
            stage.close();
        try {
             
            FXMLLoader loader = new FXMLLoader(getClass().getResource("../views/GestionLivreur.fxml"));
            Parent root = loader.load();
 

            Scene scene = new Scene(root);
            Stage primaryStage = (Stage) Add.getScene().getWindow();
            primaryStage.setScene(scene);
            
            
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void Président(ActionEvent event) throws IOException {
        // Stage stage = (Stage) Add.getScene().getWindow();
          //  stage.close();
        
             
            FXMLLoader loader = new FXMLLoader(getClass().getResource("../views/GestionLivreur.fxml"));
            Parent root = loader.load();
 

            Scene scene = new Scene(root);
            Stage primaryStage = (Stage) Add.getScene().getWindow();
            primaryStage.setScene(scene);
            
    }
    
}
