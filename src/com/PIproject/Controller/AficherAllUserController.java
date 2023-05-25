/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.PIproject.Controller;

import com.PIproject.entities.Livreur;
import com.PIproject.entities.User;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

/**
 * FXML Controller class
 *
 * @author bazinfo
 */
public class AficherAllUserController implements Initializable {

    @FXML
    private TableView<Livreur> UsersTV;
    @FXML
    private TableColumn<Livreur,Integer> CinCol;
    @FXML
    private TableColumn<Livreur, String> UserNameCol;
    @FXML
    private TableColumn<Livreur, String> EmailCol;
    @FXML
    private TableColumn<Livreur, String> AdressCol;
    @FXML
    private TableColumn<Livreur, Integer> PhoneCol;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        // Accéder à la liste des éléments dans le TableView
          CinCol.setCellValueFactory(new PropertyValueFactory<>("Cin"));
          UserNameCol.setCellValueFactory(new PropertyValueFactory<>("UserName"));
          EmailCol.setCellValueFactory(new PropertyValueFactory<>("Email"));
          AdressCol.setCellValueFactory(new PropertyValueFactory<>("Adress"));
          PhoneCol.setCellValueFactory(new PropertyValueFactory<>("Phone"));



  
    }
     public void setUsers(Livreur livreur) {
        // Ajouter les utilisateurs au TableView
        UsersTV.getItems().add(livreur);
        //UsersTV.getColumns().addAll(CinCol,UserNameCol,EmailCol,AdressCol,PhoneCol);
        
    }
}
