/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.ProjetPIEsprit.controllers;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author eya trabelsi
 */
public class MedicamentpharController implements Initializable {

    @FXML
    private TableView<?> tabmedi;
    @FXML
    private TableColumn<?, ?> catcol;
    @FXML
    private TableColumn<?, ?> typecol;
    @FXML
    private TableColumn<?, ?> nomcol;
    @FXML
    private TextField nomMed;
    @FXML
    private TextField NomCat;
    @FXML
    private TextField NomType;
    @FXML
    private Button searsh;
    @FXML
    private Button cat;
    @FXML
    private Button type;
    @FXML
    private Button chat;
    @FXML
    private Button ord;
    @FXML
    private Button liv;
    @FXML
    private Button profil;
    @FXML
    private Button logout;
  

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void searsh(ActionEvent event) {
    }

    @FXML
    private void GoToPageCategorie(ActionEvent event) {
    }

    @FXML
    private void GoToPageType(ActionEvent event) {
    }

   
}
