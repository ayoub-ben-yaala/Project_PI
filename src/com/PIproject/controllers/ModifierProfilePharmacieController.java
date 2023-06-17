/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.PIproject.controllers;

import com.PIproject.entities.Livreur;
import com.PIproject.entities.SousPharmacie;
import com.PIproject.entities.SuperPharmacie;
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
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javax.swing.JOptionPane;

/**
 * FXML Controller class
 *
 * @author bazinfo
 */
public class ModifierProfilePharmacieController implements Initializable {

    @FXML
    private TextField AdressFT;
    @FXML
    private Button update;
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
    private User currentUser;
    @FXML
    private PasswordField Password;
    @FXML
    private Button Président;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
   void setLoggedInUser(User user) {
        this.currentUser = user;
        UserNameFT.setText(currentUser.getUserName());
        EmailFT.setText(currentUser.getEmail());
        PhoneFT.setText(String.valueOf(currentUser.getPhone()));
        AdressFT.setText(currentUser.getAdress());
        ServiceUser serv = new ServiceUser();
        String userRole = serv.retrieveUserRole(user.getEmail());
         switch (userRole) {
              case "SousPharmacie":
       SousPharmacie sousPharmacie = (SousPharmacie) user;
        mattriculeTF.setText(sousPharmacie.getMatriculeFiscale());
          NomPharmFT.setText(sousPharmacie.getNomPharmacie());
          break;
           case "SuperPharmacie":
               SuperPharmacie superPharmacie = (SuperPharmacie) user;
        mattriculeTF.setText(superPharmacie.getMatriculeFiscale());
          
          break;
         }
    }
     @FXML
    private void modifier(ActionEvent event) {
        
              try {
                  
                   String newUserName = UserNameFT.getText();
                   String newEmail = EmailFT.getText();
                   Integer newPhone = Integer.parseInt(PhoneFT.getText());
                   String newAdress =AdressFT.getText();
                   String newPassword =Password.getText();
                  
                   
                   currentUser.setUserName(newUserName);
                   currentUser.setEmail(newEmail);
                   currentUser.setPassword(newPassword);
                   currentUser.setPhone(newPhone);
                   currentUser.setAdress(newAdress);
                   currentUser.setIdUser(currentUser.getIdUser());
              ServiceUser serv = new ServiceUser();
        String userRole = serv.retrieveUserRole(LoginController.loggedInUser.getEmail());
                   switch (userRole) {
              case "SousPharmacie":
                   
        SousPharmacie sousPharmacie = (SousPharmacie) currentUser;
        String newNomPharmacie = NomPharmFT.getText();
        sousPharmacie.setNomPharmacie(newNomPharmacie);
         String newMatricule = mattriculeTF.getText();
        sousPharmacie.setMatriculeFiscale(newMatricule);
        break;
        case "SuperPharmacie":
            SuperPharmacie superPharmacie = (SuperPharmacie) currentUser;
         String newMatricules = mattriculeTF.getText();
        superPharmacie.setMatriculeFiscale(newMatricules);
        break;
                   }
        
     
                    
               Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("Confirmation");
                alert.setHeaderText(null);
                alert.setContentText("Êtes-vous sûr ?");
                Optional<ButtonType> result = alert.showAndWait();
        if(result.isPresent() && result.get() == ButtonType.OK){
             ServiceUser Suser =new ServiceUser();
            Suser.modifier(currentUser);
            JOptionPane.showMessageDialog(null, "Profile Modifier !");
                   
            
            FXMLLoader loader = new FXMLLoader(getClass().getResource("../views/Medicament.fxml"));
            Parent root = loader.load();
            Scene scene = new Scene(root);
            Stage primaryStage = (Stage) update.getScene().getWindow();
            primaryStage.setScene(scene);
            
            
                     
                
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
        
             
            FXMLLoader loader = new FXMLLoader(getClass().getResource("../views/Medicament.fxml"));
            Parent root = loader.load();
 

            Scene scene = new Scene(root);
            Stage primaryStage = (Stage) Président.getScene().getWindow();
            primaryStage.setScene(scene);
            
    }

    
}
