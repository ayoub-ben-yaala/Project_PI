/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.PIproject.controllers;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
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
public class ChangerPasswordController implements Initializable {

    @FXML
    private TextField passwordFT;
    @FXML
    private TextField CpasswordFT;
    @FXML
    private Button send;
    @FXML
    private TextField emailFT;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void NewPassword(ActionEvent event) throws IOException, SQLException {
        String newPassword = passwordFT.getText();
        String confirmPassword = CpasswordFT.getText();
        
        if (newPassword.equals(confirmPassword)) {
             try {
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/piproject", "root", "");
            String query = "UPDATE user SET Password = ? WHERE email = ?";
                 PreparedStatement statement = conn.prepareStatement(query);
                statement.setString(1, newPassword);
                statement.setString(2, emailFT.getText()); 
                
                statement.executeUpdate();
                
                conn.close();
                JOptionPane.showMessageDialog(null, "Nouveau Mot de passe Enregistrer !");
            // Stage stage = (Stage) send.getScene().getWindow();
            //stage.close();
           FXMLLoader loader = new FXMLLoader(getClass().getResource("../views/Login.fxml"));
            Parent root = loader.load();
            Scene scene = new Scene(root);
            
            
            Stage primaryStage = (Stage) send.getScene().getWindow();
            primaryStage.setScene(scene);  
             primaryStage.setScene(scene);
             }catch(SQLException e){
             
              e.printStackTrace();
             
             }
        }else{
                   
        showAlert(Alert.AlertType.ERROR, "ERROR", "", "Les deux mot de passe doit etre egaux, veuillez vérifier votre email .");

            
                }
        }
         private void showAlert(Alert.AlertType alertType, String title, String header, String content) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(header);
        alert.setContentText(content);
        alert.showAndWait();
    }  
              
 }
    




   