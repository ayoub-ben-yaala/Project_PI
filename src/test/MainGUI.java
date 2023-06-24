/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMain.java to edit this template
 */
package test;


import java.io.IOException;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.scene.image.Image;
/**
 *
 * 
 */
public class MainGUI extends Application {
    
    @Override
    public void start(Stage primaryStage) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("../com/esprit/GUI/Conversation.fxml"));
        Parent root = loader.load();
        
        Scene scene = new Scene(root);
        primaryStage.setScene(scene);        
        primaryStage.setTitle("PIDEV");
        primaryStage.getIcons().add(new Image("/com/esprit/utils/logo.png") );
        primaryStage.setTitle("Pharmabiotic");
        primaryStage.show();
    }


    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}