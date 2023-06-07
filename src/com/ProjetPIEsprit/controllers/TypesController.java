/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.ProjetPIEsprit.controllers;

import com.ProjetPIEsprit.entities.Type;
import com.ProjetPIEsprit.services.ServiceType;
import java.net.URL;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;

import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javax.swing.JOptionPane;
/**
 * FXML Controller class
 *
 * @author eya trabelsi
 */
public class TypesController implements Initializable {


    @FXML
    private TextField typeTF;
    @FXML
    private Button Ajouter;
    @FXML
    private TableView<Type> tabtypes;
    @FXML
    private TableColumn<Type, String> coltype;
   // ObservableList<Type> dataList;
    ObservableList<Type> listb;
    @FXML
    private Button Supprimer;
    @FXML
    private Button Modifier;
    private Type Newtyp;
    @FXML
    private Button Save;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        coltype.setCellValueFactory(new PropertyValueFactory<>("nomtype"));
         afficher();
    }    
    
    @FXML
    private void Ajouter(ActionEvent event) {
        ServiceType sc = new ServiceType();
        sc.ajouter(new Type(typeTF.getText()));
        JOptionPane.showMessageDialog(null, "Type ajoutée !");
       afficher();
    }
 private void afficher(){
        coltype.setCellValueFactory(new PropertyValueFactory<>("nomtype"));
        ServiceType t =new ServiceType();
        listb = t.afficher();
        tabtypes.setItems(listb);
    }

    @FXML
    private void Supprimer(ActionEvent event) {
               ServiceType t =new ServiceType();

        Type selectedType = tabtypes.getSelectionModel().getSelectedItem();
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmation");
        alert.setHeaderText(null);
        alert.setContentText("Confirmer ?");
        Optional<ButtonType> result = alert.showAndWait();
        if (result.isPresent() && result.get() == ButtonType.OK && selectedType != null){
            t.supprimer(selectedType);
            JOptionPane.showMessageDialog(null, "Type supprimée !");
           afficher();
        }
        else {
             afficher();
        }
    }

          @FXML
    private void Save(ActionEvent tt) {
      
        
         String newNameTyp = typeTF.getText();
        Newtyp.setNomtype(newNameTyp);
        Newtyp.setid_type(Newtyp.getid_type());
        ServiceType serv = new ServiceType();
        serv.modifier(Newtyp);
                           JOptionPane.showMessageDialog(null, "Type Modifiée !");
           typeTF.setText(null);
        afficher();
    }
    @FXML
    private void Modifier() {
   Type selectedType = tabtypes.getSelectionModel().getSelectedItem();

        this.Newtyp= selectedType;
         typeTF.setText(Newtyp.getNomtype());
        
    }
    

    }

 