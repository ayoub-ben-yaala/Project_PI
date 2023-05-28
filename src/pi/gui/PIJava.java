/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package pi.gui;

import java.io.IOException;

import java.util.Date;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import pi.entities.Commande;
import pi.entities.Panier;
import pi.services.service_commande;
import pi.services.service_panier;

/**
 *
 * @author usernvme
 */
public class PIJava extends Application {

    
    public static void main (String[] args)
    {
    launch(args);
    }
    @Override
    public void start(Stage primaryStage) {
        try {
            //Parent root = FXMLLoader.load(getClass().getResource("commande_admin.fxml"));
            //Scene scene = new Scene(root);

            FXMLLoader loader = new FXMLLoader(getClass().getResource("commande_admin.fxml"));
            Pane root = loader.load();
            System.out.println("loaded");
            Scene scene = new Scene(root);
            
            primaryStage.setTitle("Pharmabiotic");
            primaryStage.setScene(scene);
            primaryStage.show();
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }

    }
}
