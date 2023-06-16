/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package pi.gui;

import java.io.IOException;
import java.net.URL;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

/**
 * @author usernvme
 */
public class PIJava extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws IOException {
        URL resource = getClass().getResource("commande_admin.fxml");
        Parent root1 = FXMLLoader.load(resource);
        Scene scene1 = new Scene(root1);

        FXMLLoader loader = new FXMLLoader(getClass().getResource("panier.fxml"));
        Pane root = loader.load();
        System.out.println("loaded");
        Scene scene = new Scene(root);
       Stage sec=new Stage();

        primaryStage.setTitle("Pharmabiotic");
        primaryStage.setScene(scene);
        primaryStage.show();
        sec.setTitle("commande");
        sec.setScene(scene1);
        sec.show();
       
    }
}
