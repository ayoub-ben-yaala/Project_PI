
/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.ProjetPIEsprit.controllers;

import com.ProjetPIEsprit.entities.Categorie;
import com.ProjetPIEsprit.services.ServiceCategorie;
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
import javafx.scene.control.ButtonType;
import javafx.stage.Stage;
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
    @FXML
    private Button save;
   
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
    private void Modifier(ActionEvent tt) {
        Categorie selectedCategorie = tabcategories.getSelectionModel().getSelectedItem();
         categorieTF.setText(selectedCategorie.getNomcategorie());
        
    }

    @FXML
    private void save(ActionEvent event) {
        ServiceCategorie m = new ServiceCategorie();
        Categorie categorie = new Categorie(categorieTF.getText());
                   m.modifier(categorie);
                   JOptionPane.showMessageDialog(null, "Categorie Modifiée !");

                afficher();
                
        
    }
}
