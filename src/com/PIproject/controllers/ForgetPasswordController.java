/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.PIproject.controllers;

import com.mysql.cj.Session;
import com.mysql.cj.protocol.Message;
import com.sun.xml.internal.messaging.saaj.packaging.mime.MessagingException;
import com.sun.xml.internal.org.jvnet.mimepull.MIMEMessage;
import java.io.IOException;
import java.net.Authenticator;
import java.net.PasswordAuthentication;
import java.net.URL;
import java.util.Properties;
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
import sun.rmi.transport.Transport;

/**
 * FXML Controller class
 *
 * @author bazinfo
 */
public class ForgetPasswordController implements Initializable {

    @FXML
    private TextField codeFT;
    @FXML
    private Button send;

    /**
     * Initializes the controller class.
     */
    private int code = generateRandomCode();
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
         
         System.out.println("Code aléatoire : " + code);
    }    
    
      private int generateRandomCode() {
    // Générer un code aléatoire à 6 chiffres
        Random random = new Random();
    int code = random.nextInt(900000) + 100000;
    
    return code;
}
    /*
    public static void sendSecurityCode(String recipientEmail, String securityCode) {
        Properties properties = new Properties();
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.starttls.enable", "true");
        properties.put("mail.smtp.host", "smtp.gmail.com");
        properties.put("mail.smtp.port", "587");

        Session session = Session.getInstance(properties, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication("ayoubbenyaala@gmail.com", "ayoubbenyaala@gmail.com@@");
            }
        });

        try {
            Message message = new MIMEMessage(session);
            message.setFrom(new InternetAddress(EMAIL_USERNAME));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(recipientEmail));
            message.setSubject("Code de sécurité");
            message.setText("Votre code de sécurité est : " + securityCode);

            Transport.send(message);

            System.out.println("E-mail envoyé avec succès !");
        } catch (MessagingException e) {
            System.out.println("Erreur lors de l'envoi de l'e-mail : " + e.getMessage());
        }
    }*/
      
    @FXML
     private boolean verifyCode(int enteredCode) {
    return enteredCode == code;
}
    
    @FXML
    private void SendCodeVerif(ActionEvent event) throws IOException {
            //Stage stage = (Stage) send.getScene().getWindow();
            //stage.close();
            int enteredCode = Integer.parseInt(codeFT.getText());
                int generatedCode = code;
    
    if (verifyCode(enteredCode)) {
         FXMLLoader loader = new FXMLLoader(getClass().getResource("../views/ChangerPassword.fxml"));
            Parent root = loader.load();
            Scene scene = new Scene(root);
            Stage primaryStage = (Stage) send.getScene().getWindow();
            primaryStage.setScene(scene);  
             primaryStage.setScene(scene);
    } else {
      showAlert(Alert.AlertType.ERROR, "Code Invalide", "", "Veuillez vérifier le code envoyer a votre mail.");
/*FXMLLoader loader = new FXMLLoader(getClass().getResource("../views/Login.fxml"));
            Parent root = loader.load();
            Scene scene = new Scene(root);
            Stage primaryStage = (Stage) send.getScene().getWindow();
            primaryStage.setScene(scene);  
             primaryStage.setScene(scene);*/
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
