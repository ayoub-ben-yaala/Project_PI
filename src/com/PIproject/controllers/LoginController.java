/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.PIproject.controllers;

import com.PIproject.utils.DataSource;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Random;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javax.swing.JOptionPane;

/**
 * FXML Controller class
 *
 * @author bazinfo
 */
public class LoginController implements Initializable {

    @FXML
    private TextField EmailFT;
    @FXML
    private TextField PasswordFT;
    @FXML
    private Button Login;
    @FXML
    private Button forgetPassword;
        private Connection cnx = DataSource.getInstance().getCnx();


    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void Connexion(ActionEvent event) throws IOException {
        
             String email = EmailFT.getText();
        String password = PasswordFT.getText();

        if (validateLogin(email, password)) {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("../views/Aceuil.fxml"));
            Parent root = loader.load();
            Scene scene = new Scene(root);            
            Stage primaryStage = (Stage) Login.getScene().getWindow();
            primaryStage.setScene(scene);  
             primaryStage.setScene(scene);
        } else {
            
            showAlert(Alert.AlertType.ERROR, "Erreur de connexion", "Identifiants incorrects", "Veuillez vérifier votre e-mail et votre mot de passe.");
        }
        
         
             
             
    }
    
       private boolean validateLogin(String email, String password) {
        try {
            String query = "SELECT * FROM user WHERE Email = ? AND Password = ?";
            PreparedStatement statement = cnx.prepareStatement(query);
            statement.setString(1, email);
            statement.setString(2, password);
            ResultSet resultSet = statement.executeQuery();

            return resultSet.next(); 
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
        private void showAlert(Alert.AlertType alertType, String title, String header, String content) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(header);
        alert.setContentText(content);
        alert.showAndWait();
    }

    @FXML
    private void forgetPassword(ActionEvent event) throws IOException {
        
      
        FXMLLoader loader = new FXMLLoader(getClass().getResource("../views/ForgetPassword.fxml"));
            Parent root = loader.load();
            Scene scene = new Scene(root);
            Stage primaryStage = (Stage) forgetPassword.getScene().getWindow();
            primaryStage.setScene(scene);  
             primaryStage.setScene(scene);
             
             
    }
  

}
