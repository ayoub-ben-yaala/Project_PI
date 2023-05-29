/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.PIproject.Controller;

import com.PIproject.entities.Livreur;
import com.PIproject.services.ServiceUser;
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
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javax.swing.JOptionPane;

/**
 * FXML Controller class
 *
 * @author bazinfo
 */
public class ModifierUserController implements Initializable {

    @FXML
    private TextField PasswordFT;
    @FXML
    private TextField AdressFT;
    @FXML
    private TextField EmailFT;
    @FXML
    private TextField UserNameFT;
    @FXML
    private TextField PhoneFT;
    @FXML
    private TextField CinFT;
    @FXML
    private TextField NomPharmFT;
    @FXML
    private TextField mattriculeTF;
    @FXML
    private Button update;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
   
    private void modifier(ActionEvent event) {
        
              try {
                  Livreur livreur = new Livreur (Integer.parseInt(CinFT.getText()),UserNameFT.getText(), EmailFT.getText(), Integer.parseInt(PhoneFT.getText()), AdressFT.getText());
        
        ServiceUser Suser =new ServiceUser();
        Suser.modifier(livreur);
        JOptionPane.showMessageDialog(null, "User Updated !");
        
            FXMLLoader loader = new FXMLLoader(getClass().getResource("../Views/Aceuil.fxml"));
            Parent root = loader.load();
            Scene scene = new Scene(root);
            Stage primaryStage = (Stage) update.getScene().getWindow();
            primaryStage.setScene(scene);
            
            
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
