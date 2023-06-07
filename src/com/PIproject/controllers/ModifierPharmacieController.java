/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.PIproject.controllers;

import com.PIproject.entities.Livreur;
import com.PIproject.entities.SousPharmacie;
import com.PIproject.entities.User;
import com.PIproject.services.ServiceUser;
import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javax.swing.JOptionPane;

/**
 * FXML Controller class
 *
 * @author bazinfo
 */
public class ModifierPharmacieController implements Initializable {

   
    @FXML
    private TextField AdressFT;
    @FXML
    private TextField EmailFT;
    @FXML
    private TextField UserNameFT;
    @FXML
    private TextField PhoneFT;
    @FXML
    private TextField NomPharmFT;  
    @FXML
    private TextField mattriculeTF;
    @FXML
    private Button update;
    private User userData;
    @FXML
    private Button Président;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

     public void setUserData(User user) {
        this.userData = user;
        
        UserNameFT.setText(userData.getUserName());
        EmailFT.setText(userData.getEmail());
        PhoneFT.setText(String.valueOf(userData.getPhone()));
        AdressFT.setText(userData.getAdress());
        SousPharmacie sousPharmacie = (SousPharmacie) user;
        mattriculeTF.setText(sousPharmacie.getMatriculeFiscale());
          NomPharmFT.setText(sousPharmacie.getNomPharmacie());
        }
         
    
    @FXML
    private void modifier(ActionEvent event) {
        
              try {
                  
                   String newUserName = UserNameFT.getText();
                   String newEmail = EmailFT.getText();
                   Integer newPhone = Integer.parseInt(PhoneFT.getText());
                   String newAdress =AdressFT.getText();
                  
                   
                   userData.setUserName(newUserName);
                   userData.setEmail(newEmail);
                   userData.setPassword("open");
                   userData.setPhone(newPhone);
                   userData.setAdress(newAdress);
                   userData.setIdUser(userData.getIdUser());
              
                   
                   
        SousPharmacie sousPharmacie = (SousPharmacie) userData;
        String newNomPharmacie = NomPharmFT.getText();
        sousPharmacie.setNomPharmacie(newNomPharmacie);
         String newMatricule = mattriculeTF.getText();
        sousPharmacie.setMatriculeFiscale(newMatricule);
     
                    
               Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("Confirmation");
                alert.setHeaderText(null);
                alert.setContentText("Êtes-vous sûr ?");
                Optional<ButtonType> result = alert.showAndWait();
        if(result.isPresent() && result.get() == ButtonType.OK){
             ServiceUser Suser =new ServiceUser();
            Suser.modifier(userData);
            JOptionPane.showMessageDialog(null, "Pharmacie Modifier !");
            Stage stage = (Stage) update.getScene().getWindow();
            stage.close();
            Suser.afficherPharmacie();
            
            FXMLLoader loader = new FXMLLoader(getClass().getResource("../views/GestionPharmacie.fxml"));
            Parent root = loader.load();
            Scene scene = new Scene(root);
            
            
            Stage primaryStage = (Stage) update.getScene().getWindow();
            primaryStage.setScene(scene);  
             primaryStage.setScene(scene);
            GestionPharmacieController GestionPharmacieController = loader.getController();
            
            GestionPharmacieController.afficher();
            GestionPharmacieController.refreshPage();          
                
                  }
                  else{
                   
                    Stage stage = (Stage) update.getScene().getWindow();
                    stage.close();
                  }
            
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


      @FXML
    private void Président(ActionEvent event) throws IOException {
        // Stage stage = (Stage) Add.getScene().getWindow();
          //  stage.close();
        
             
            FXMLLoader loader = new FXMLLoader(getClass().getResource("../views/GestionPharmacie.fxml"));
            Parent root = loader.load();
 

            Scene scene = new Scene(root);
            Stage primaryStage = (Stage) Président.getScene().getWindow();
            primaryStage.setScene(scene);
            
    }
}
