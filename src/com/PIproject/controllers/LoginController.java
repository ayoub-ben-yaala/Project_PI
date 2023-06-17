/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.PIproject.controllers;

import com.PIproject.entities.Livreur;
import com.PIproject.entities.SousPharmacie;
import com.PIproject.entities.SuperPharmacie;
import com.PIproject.entities.User;
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
import static org.mindrot.jbcrypt.BCrypt.checkpw;
import org.mindrot.jbcrypt.BCrypt;


/**
 * FXML Controller class
 *
 * @author bazinfo
 */
public class LoginController implements Initializable {

    @FXML
     TextField EmailFT;
    @FXML
    private TextField PasswordFT;
    @FXML
    private Button Login;
    @FXML
    private Button forgetPassword;
        private Connection cnx = DataSource.getInstance().getCnx();

static User loggedInUser ;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void Connexion(ActionEvent event) throws IOException, SQLException {
        
             String email = EmailFT.getText();
        String password = PasswordFT.getText();

        if (/*(retrieveUserPasswordAndVerif(email,password))&&*/(validateLogin(email, password)) ) {
                   
   
             // Récupérer le rôle de l'utilisateur à partir de la base de données
    String userRole = retrieveUserRole(email);

                // Utiliser une structure conditionnelle pour rediriger vers la page d'accueil appropriée
                switch (userRole) {
                    case "SuperPharmacie":
                        // Redirection vers la page d'accueil de l'administrateur
                        redirectToPharmacieHomePage();
                        break;
                    case "SousPharmacie":
                        // Redirection vers la page d'accueil de l'utilisateur
                        redirectToMiniPharmacieHomePage();
                        break;
                    case "Livreur":
                        // Redirection vers la page d'accueil des invités
                        redirectToLivreurHomePage();
                        break;
                    default:
                        break;
                }

 } else {
        showAlert(Alert.AlertType.ERROR, "Erreur de connexion", "Identifiants incorrects", "Veuillez vérifier votre e-mail et votre mot de passe.");

        }
        
         
             
             
    }
    
       private boolean validateLogin(String email, String password) throws SQLException {
       
            String query = "SELECT * FROM user WHERE Email = ? AND Password = ? And Statut='Active'";
            PreparedStatement statement = cnx.prepareStatement(query);
            statement.setString(1, email);
            statement.setString(2, password);
            ResultSet resultSet = statement.executeQuery();
if (resultSet.next() )  {
    // Récupérer le rôle de l'utilisateur connecté
    String userRole = retrieveUserRole(email);

                switch (userRole) {
                    case "SuperPharmacie":
                        loggedInUser = new SuperPharmacie(
                                resultSet.getString("MatriculeFiscale"),
                                resultSet.getInt("idUser"),
                                resultSet.getString("UserName"),
                                resultSet.getString("Email"),
                                resultSet.getString("Password"),
                                resultSet.getInt("Phone"),
                                resultSet.getString("Adress"),
                                resultSet.getString("Statut")
                        );            
                        break;
                    case "SousPharmacie":
                        loggedInUser = new SousPharmacie(
                                resultSet.getString("NomPharmacie"),
                                resultSet.getString("MatriculeFiscale"),
                                resultSet.getInt("idUser"),
                                resultSet.getString("UserName"),
                                resultSet.getString("Email"),
                                resultSet.getString("Password"),
                                resultSet.getInt("Phone"),
                                resultSet.getString("Adress"),
                                resultSet.getString("Statut")
                        );              break;
                    case "Livreur":
                        loggedInUser = new Livreur(
                                resultSet.getInt("Cin"),
                                resultSet.getInt("idUser"),
                                resultSet.getString("UserName"),
                                resultSet.getString("Email"),
                                resultSet.getString("Password"),
                                resultSet.getInt("Phone"),
                                resultSet.getString("Adress"),
                                resultSet.getString("Statut")
                        );              break;
                    default:
                        break;
                }

    return true;
} else {
    return false; // Aucun utilisateur trouvé avec les informations de connexion fournies      
       
    }}
       private String retrieveUserRole(String email) {
    String userRole = "";
    
    try {
        String query = "SELECT Role FROM user WHERE Email = ?";
        PreparedStatement statement = cnx.prepareStatement(query);
        statement.setString(1, email);
        
        ResultSet resultSet = statement.executeQuery();
        
        if (resultSet.next()) {
            userRole = resultSet.getString("Role");
        }
    } catch (SQLException e) {
        // Gérer l'erreur de récupération du rôle de l'utilisateur
        
    }
    
    return userRole;
}
       
       
         private boolean retrieveUserPasswordAndVerif(String email,String password) throws SQLException {
    String userPassword = "";
    
   
        String query = "SELECT Password FROM user WHERE Email = ?";
        PreparedStatement statement = cnx.prepareStatement(query);
        statement.setString(1, email);
        
        ResultSet resultSet = statement.executeQuery();
        
        if (resultSet.next()) {
            userPassword = resultSet.getString("Password");
            
        }
        return BCrypt.checkpw(password,userPassword);
}     
       private void redirectToLivreurHomePage() {
    try {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("../views/AceuilLivreur.fxml"));
        Parent root = loader.load();
        
        // Créer une nouvelle scène avec la page d'accueil des invités
        Scene scene = new Scene(root);
        
        // Obtenir la fenêtre actuelle et la changer avec la nouvelle scène
        Stage stage = (Stage) Login.getScene().getWindow();
        stage.setScene(scene);
        stage.show();
        
    } catch (IOException e) {
        e.printStackTrace();
        // Gérer l'erreur de chargement de la page d'accueil des invités
    }
}
       private void redirectToPharmacieHomePage() {
    try {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("../views/Medicament.fxml"));
        Parent root = loader.load();
        
        // Créer une nouvelle scène avec la page d'accueil des invités
        Scene scene = new Scene(root);
        
        // Obtenir la fenêtre actuelle et la changer avec la nouvelle scène
        Stage stage = (Stage) Login.getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    } catch (IOException e) {
        e.printStackTrace();
        // Gérer l'erreur de chargement de la page d'accueil des invités
    }
}
       private void redirectToMiniPharmacieHomePage() {
    try {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("../views/Medicamentphar.fxml"));
        Parent root = loader.load();
        
        // Créer une nouvelle scène avec la page d'accueil des invités
        Scene scene = new Scene(root);
        
        // Obtenir la fenêtre actuelle et la changer avec la nouvelle scène
        Stage stage = (Stage) Login.getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    } catch (IOException e) {
        e.printStackTrace();
        // Gérer l'erreur de chargement de la page d'accueil des invités
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
