/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.PIproject.controllers;


import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URL;
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
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.mail.PasswordAuthentication;

/**
 * FXML Controller class
 * 
 * Author: bazinfo
 */
public class ForgetPasswordController implements Initializable {

    @FXML
    private TextField codeFT;
    @FXML
    private Button send;

    /**
     * Initializes the controller class.
     */
    private static int code;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // Generate a random code when initializing the controller
        code = generateRandomCode();
        sendSecurityCode();
    }

    private int generateRandomCode() {
        // Generate a random 6-digit code
        Random random = new Random();
        int code = random.nextInt(900000) + 100000;
        return code;
    }

    public static void sendSecurityCode() {
        final String username = "ayoubbenyaala@gmail.com";
        final String password = "rkefznprclkzjeab";
        Properties properties = new Properties();
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.starttls.enable", "true");
        properties.put("mail.smtp.host", "smtp.gmail.com");
        properties.put("mail.smtp.port", "587");

        Session session = Session.getInstance(properties, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(username, password);
            }
        });

        try {
            Message message = new MimeMessage(session);
            try {
                message.setFrom(new InternetAddress(username, ""));
            } catch (UnsupportedEncodingException ex) {
                Logger.getLogger(ForgetPasswordController.class.getName()).log(Level.SEVERE, null, ex);
            }
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(LoginController.loggedInUser.getEmail()));
            message.setSubject("Code de sécurité");
            message.setText("Votre code de sécurité est : " + code);

            Transport.send(message);

            System.out.println("E-mail envoyé avec succès !");
        } catch (MessagingException e) {
            System.out.println("Erreur lors de l'envoi de l'e-mail : " + e.getMessage());
        }
    }

    @FXML
    private boolean verifyCode(int enteredCode) {
        return enteredCode == code;
    }

    @FXML
    private void SendCodeVerif(ActionEvent event) throws IOException {
        int enteredCode = Integer.parseInt(codeFT.getText());

        if (verifyCode(enteredCode)) {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("../views/ChangerPassword.fxml"));
            Parent root = loader.load();
            Scene scene = new Scene(root);
            Stage primaryStage = (Stage) send.getScene().getWindow();
            primaryStage.setScene(scene);
        } else {
            showAlert(Alert.AlertType.ERROR, "Code Invalide", "", "Veuillez vérifier le code envoyé à votre e-mail.");
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
