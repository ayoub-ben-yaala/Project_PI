package com.PIproject.controllers;

import com.PIproject.entities.Chat;
import com.PIproject.services.ServiceChat;
import com.PIproject.services.ServiceChat;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class ConversationController {
    
   

    @FXML
    private TextArea chatTextArea;
    private List<String> messages;

    @FXML
    private TextField messageTextField;

    
    @FXML
    private ListView<String> chatList;

    private ServiceChat chatService;
    @FXML
    private Button retour;

    public ConversationController() {
        // Initialise le service de chat
        chatService = new ServiceChat();
    }

    public void initialize() {
        // Aucune initialisation spécifique requise pour l'instant
        fillChatList();
        
        messages =chatService.afficherMsg(1, 1);
                       for (String messag : messages) {
                   
        chatTextArea.appendText(messag + "\n");
    }
    }
    
    private void fillChatList() {
       List<String> listData = chatService.afficherPhar(1);

        // Remplissez la liste avec les données récupérées
        chatList.getItems().addAll(listData);
        // Ajoutez votre logique pour remplir la liste avec les données appropriées
    }
    @FXML
public void handleItemClick() {
getMesaages();
}
    @FXML
    private void sendMessage() {
        String message = messageTextField.getText().trim();

        if (!message.isEmpty()) {
            // Créer un nouvel objet Chat
            Chat chat = new Chat(0, 0, 0, message, null);

            // Ajouter le message à la base de données
            chatService.ajouter(chat);

            // Afficher le message dans la zone de texte
            messages =chatService.afficherMsg(1, 1);
            chatTextArea.clear();
               for (String messag : messages) {
                   
        chatTextArea.appendText(messag + "\n");
    }
            chatTextArea.appendText("You: " + message + "\n");

            // Effacer le champ de texte après l'envoi du message
            messageTextField.clear();
        }
    }
    private void getMesaages() {
        String message = messageTextField.getText().trim();



            // Afficher le message dans la zone de texte
            messages =chatService.afficherMsg(1, 1);
            chatTextArea.clear();
               for (String messag : messages) {
                   
        chatTextArea.appendText(messag + "\n");
    }
           

          
        
    }

     @FXML
    private void retour(ActionEvent event) throws IOException {
             FXMLLoader loader = new FXMLLoader(getClass().getResource("../views/Medicament.fxml"));
    Parent root = loader.load();

    Scene scene = new Scene(root);
    Stage primaryStage = (Stage) retour.getScene().getWindow();
    primaryStage.setScene(scene);
    primaryStage.show();
    }
}
