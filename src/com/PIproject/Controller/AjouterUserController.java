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
public class AjouterUserController implements Initializable {

    @FXML
    private TextField UserNameFT;
    @FXML
    private TextField EmailFT;
    @FXML
    private TextField PasswordFT;
    @FXML
    private TextField AdressFT;
    @FXML
    private TextField PhoneFT;
    @FXML
    private Button Add;
    @FXML
    private TextField CinFT;
    
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void AddUser() throws IOException {
      
        Livreur livreur = new Livreur (Integer.parseInt(CinFT.getText()),UserNameFT.getText(), EmailFT.getText(), PhoneFT.getText(), AdressFT.getText());
        
        ServiceUser Suser =new ServiceUser();
        Suser.ajouter(new Livreur (Integer.parseInt(CinFT.getText()),UserNameFT.getText(), EmailFT.getText(), PasswordFT.getText(), PhoneFT.getText(), AdressFT.getText()));

        JOptionPane.showMessageDialog(null, "User Added !");
        
        // List<Livreur> livreurs = new ArrayList<>();
         //   livreurs.add(livreur);
        
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("../Views/aficherAllUser.fxml"));
            Parent root = loader.load();

            AficherAllUserController afficher = loader.getController();

            afficher.setUsers(livreur);

            Scene scene = new Scene(root);
            Stage primaryStage = (Stage) Add.getScene().getWindow();
            primaryStage.setScene(scene);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
}
