package com.esprit.GUI;

import com.esprit.entities.Chat;
import com.esprit.services.ServiceChat;
import com.esprit.services.ServiceChat;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class ConversationController {
    
   

    @FXML
    private TextArea chatTextArea;
    private List<String> messages;

    @FXML
    private TextField messageTextField;

    @FXML
    private Button sendButton;
    
    @FXML
    private ListView<String> chatList;

    private ServiceChat chatService;

    public ConversationController() {
        // Initialise le service de chat
        chatService = new ServiceChat();
    }

    @FXML
    public void initialize() {
        // Aucune initialisation spécifique requise pour l'instant
        fillChatList();
        
     
    }
    
@FXML
    private void fillChatList() {
       List<String> listData = chatService.afficherPhar_Liv(1,"pharmacie");

        // Remplissez la liste avec les données récupérées
        chatList.getItems().addAll(listData);
        // Ajoutez votre logique pour remplir la liste avec les données appropriées
    }
    @FXML
public void handleItemClick() {
String selectedValue = chatList.getSelectionModel().getSelectedItem().toString();
    
getMesaages(1,chatService.id_Liv(selectedValue));
}
    @FXML
    private void sendMessage() {
        String message = messageTextField.getText().trim();

        if (!message.isEmpty()) {
            // Créer un nouvel objet Chat
   
            Chat chat = new Chat(0, 1, 1, message, null);

            // Ajouter le message à la base de données
            chatService.ajouter(chat);
String selectedValue = chatList.getSelectionModel().getSelectedItem().toString();
            getMesaages(1, chatService.id_Liv(selectedValue));
          

            // Effacer le champ de texte après l'envoi du message
            messageTextField.clear();
        }
    }
      @FXML
    private void getMesaages(int id_l,int id_p) {




            // Afficher le message dans la zone de texte
            messages =chatService.afficherMsg(id_l, id_p);
            chatTextArea.clear();
               for (String messag : messages) {
                   
        chatTextArea.appendText(messag + "\n");
    }
           

          
        
    }
}
