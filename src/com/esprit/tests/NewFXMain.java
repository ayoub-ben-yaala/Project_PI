/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMain.java to edit this template
 */
package com.esprit.tests;

import java.io.IOException;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

/**
 *
 *
 */
public class NewFXMain extends Application {

    @Override
    public void start(Stage primaryStage) throws IOException {
        //FXMLLoader loader = new FXMLLoader(getClass().getResource("../gui/AjouterMedecin.fxml"));
        FXMLLoader loader = new FXMLLoader(getClass().getResource("../gui/AjouterOrdonnanace.fxml"));
        Parent root = loader.load();
        Scene scene = new Scene(root);
        primaryStage.getIcons().add(new Image("/com/esprit/images/logo.png"));
        primaryStage.setTitle("Pharmabiotic");

        primaryStage.setScene(scene);

        primaryStage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

}
