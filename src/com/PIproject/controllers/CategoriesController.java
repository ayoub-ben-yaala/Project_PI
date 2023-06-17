
/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.PIproject.controllers;

import com.PIproject.entities.Categorie;
import com.PIproject.services.ServiceCategorie;
import java.awt.Font;
import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import javafx.collections.ObservableList;

import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javax.swing.JOptionPane;
import javafx.collections.ObservableList;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javax.swing.UIManager;
/**
 * FXML Controller class
 *
 * @author eya trabelsi
 */
public class CategoriesController implements Initializable {


    @FXML
    private TextField categorieTF;
    @FXML
    private Button Ajouter;
    @FXML
    private TableView<Categorie> tabcategories;
    @FXML
    private TableColumn<Categorie,String> cat;
    // ObservableList<Categorie> dataList;
    ObservableList<Categorie> listb;
    @FXML
    private Button Supprimer;
    @FXML
    private Button Modifier;
    
    private Categorie Newcat;
    @FXML
    private Button Save;
    @FXML
    private Button pre;
   
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        cat.setCellValueFactory(new PropertyValueFactory<>("nomcategorie"));
         afficher();
        // Accéder à la liste des éléments dans le TableView
         
    } 
    
    
    
    @FXML
    private void Ajouter(ActionEvent event) throws IOException {
        Label label = new Label("Nom de la Categorie : *");
        label.getStyleClass().add("mandatory-label"); 



String categorie = categorieTF.getText().trim();
if (categorie.isEmpty()) {
    label.setTextFill(Color.RED);

    Alert alert = new Alert(AlertType.ERROR);
    alert.setTitle("Erreur de saisie");
    alert.setHeaderText(null);
    alert.setContentText("Veuillez entrer une catégorie !");
    alert.showAndWait();

    return; // Arrêter l'exécution de la méthode si la validation échoue
}
      
          ServiceCategorie sc = new ServiceCategorie();
        sc.ajouter(new Categorie(categorieTF.getText()));
        JOptionPane.showMessageDialog(null, "Categorie ajoutée !");
       afficher();
    }
    private void afficher(){
        cat.setCellValueFactory(new PropertyValueFactory<>("nomcategorie"));
        ServiceCategorie c =new ServiceCategorie();
        listb = c.afficher();
        tabcategories.setItems(listb);
    }
    
    

    @FXML
    private void Supprimer(ActionEvent event) {
                ServiceCategorie s =new ServiceCategorie();

        Categorie selectedCategorie = tabcategories.getSelectionModel().getSelectedItem();
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmation");
        alert.setHeaderText(null);
        alert.setContentText("Confirmer ?");
        Optional<ButtonType> result = alert.showAndWait();
        if (result.isPresent() && result.get() == ButtonType.OK && selectedCategorie != null){
            s.supprimer(selectedCategorie);
            JOptionPane.showMessageDialog(null, "Categorie supprimée !");
           afficher();
        }
        else {
             afficher();
        }
    }

      @FXML
    private void Save(ActionEvent tt) {
      
        
         String newNameCat = categorieTF.getText();
        Newcat.setNomcategorie(newNameCat);
        Newcat.setid_categorie(Newcat.getid_categorie());
        ServiceCategorie serv = new ServiceCategorie();
        serv.modifier(Newcat);
                           JOptionPane.showMessageDialog(null, "Categorie Modifiée !");
           categorieTF.setText(null);
        afficher();
    }
    @FXML
    private void Modifier() {
   Categorie selectedCategorie = tabcategories.getSelectionModel().getSelectedItem();

        this.Newcat= selectedCategorie;
         categorieTF.setText(Newcat.getNomcategorie());
        
    }

       @FXML
    private void retour(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("../views/Medicament.fxml"));
    Parent root = loader.load();

    Scene scene = new Scene(root);
    Stage primaryStage = (Stage) pre.getScene().getWindow();
    primaryStage.setScene(scene);
    primaryStage.show();
    }
    
}