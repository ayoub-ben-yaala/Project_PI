/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.PIproject.controllers;

import com.PIproject.entities.Categorie;
import com.PIproject.entities.Type;


import com.PIproject.entities.Medicament;
import com.PIproject.services.ServiceCategorie;
import com.PIproject.services.ServiceMedicament;
import com.PIproject.services.ServiceType;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import static java.util.Collections.list;
import java.util.List;
import java.util.Optional;
import java.util.Properties;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;

import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import javax.swing.JOptionPane;



/**
 * FXML Controller class
 *
 * @author eya trabelsi
 */
public class MedicamentController implements Initializable {
  
    @FXML
    private TextField nommedFT;
    @FXML
    private TextField refFT;
    @FXML
    private TextField DateFT;
    @FXML
    private TextField prixFT;
    @FXML
    private TextField NoticeFT;
    @FXML
    private Button Ajouter;
    @FXML
    private Button Modifier;
    @FXML
    private Button Supprimer;
    @FXML
    private TableView<Medicament> tabmedi;
    @FXML
    private TableColumn<Medicament, String> catcol;
    @FXML
    private TableColumn<Medicament, String> typecol;
    @FXML
    private TableColumn<Medicament, String> nomcol;
    ObservableList<Medicament> list;
    @FXML
    private ChoiceBox<String> choicecat;
    @FXML
    private ChoiceBox<String> choicetyp;
    @FXML
    private TextField NomType;
    @FXML
    private TextField nomMed;
    @FXML
    private TextField NomCat;
    @FXML
    private Button searsh;
    private Medicament MedData;
    @FXML
    private Button Save;
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
         ServiceCategorie serviceCategorie = new ServiceCategorie();
        List<String> categories = serviceCategorie.afficher_categorie();
        ObservableList<String> categorieList = FXCollections.observableArrayList("");
categorieList.addAll(categories);
choicecat.setItems(categorieList);

ServiceType serviceType = new ServiceType();
        List<String> types = serviceType.afficher_type();
        ObservableList<String> typeList = FXCollections.observableArrayList("");
typeList.addAll(types);
choicetyp.setItems(typeList);
       

    
       try {
           afficher();
       } catch (SQLException ex) {
           Logger.getLogger(MedicamentController.class.getName()).log(Level.SEVERE, null, ex);
       }
     

    
    }    
    
    
    @FXML
    public void ajouterMedicament(ActionEvent event) throws SQLException {
    ServiceMedicament sm = new ServiceMedicament();
    int reference = Integer.parseInt(refFT.getText());
    String type = choicetyp.getValue();
    String categorie = choicecat.getValue();
    int prix = Integer.parseInt(prixFT.getText());
    String notice = NoticeFT.getText();
    String dateExpiration = DateFT.getText();
    String nom = nommedFT.getText();
    
    // Contrôle de saisie
    if (reference == 0 || type == null || categorie == null || prix == 0) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION, "Veuillez remplir tous les champs !", ButtonType.OK);
        alert.show();
        return;
    }
    
    // Obtenir les IDs du type et de la catégorie
    int typeId = sm.obtenirIdType(type);
    int categorieId = sm.obtenirIdCategorie(categorie);
    
    Medicament medicament = new Medicament(String.valueOf(categorieId), String.valueOf(typeId), nom, reference, dateExpiration, prix, notice);
    sm.ajouterMedicament(medicament);
    
    JOptionPane.showMessageDialog(null, "Médicament ajouté !");
    afficher();
    
    // Réinitialiser les champs après l'ajout
    refFT.clear();
    choicetyp.getSelectionModel().clearSelection();
    choicecat.getSelectionModel().clearSelection();
    prixFT.clear();
    NoticeFT.clear();
    DateFT.clear();
    nommedFT.clear();
}

    


  /*  @FXML
    private void Ajouter(ActionEvent event) throws SQLException {
        
        
           
     /*   ServiceMedicament sm = new ServiceMedicament();
        
        sm.ajouter(new Medicament (choicecat.getSelectionModel().getSelectedItem(), choicetyp.getSelectionModel().getSelectedItem(), nommedFT.getText(), Integer.parseInt(refFT.getText()), DateFT.getText(), Integer.parseInt(prixFT.getText()), NoticeFT.getText()));
      /*  String nomcat = choicecat.getSelectionModel().getSelectedItem();
         String nomtyp = choicetyp.getSelectionModel().getSelectedItem();
         String nom_medi = nommedFT.getText();  
         int reference = Integer.parseInt(refFT.getText()); 
          String DateExp = DateFT.getText();  
          int prix = Integer.parseInt(prixFT.getText());
          String notice = NoticeFT.getText();
          
          Medicament medicament = new Medicament(nomcat, nomtyp, nom_medi, reference, DateExp, prix, notice);
          sm.ajouter(medicament);
           tabmedi.getItems().add(medicament);
        /*   nomtyp.setValue(null);
            nom_medi.setValue(null);
            reference.clear();
            DateExp.clear();
            prix.clear();
            notice.clear();*/
       //   JOptionPane.showMessageDialog(null, "Medicament ajoutée !");
          
    
   // }
    
     public void setUserData(Medicament medicament) {
        this.MedData = medicament;
        ServiceMedicament servM =new ServiceMedicament();
        choicecat.setValue(MedData.getCategorie());
        choicetyp.setValue(MedData.getType());
        nomMed.setText(MedData.getNom_medi());
        refFT.setText(String.valueOf(MedData.getReference()));
        NoticeFT.setText(MedData.getNotice());
        prixFT.setText(String.valueOf(MedData.getPrix()));
          DateFT.setText(MedData.getDateExp());
        }
    @FXML
    private void Modifier(ActionEvent event) {
        Medicament selectedMed = tabmedi.getSelectionModel().getSelectedItem();
        MedicamentController mController = new MedicamentController();          
            mController.setUserData(selectedMed);
    }
      @FXML
    private void Save(ActionEvent event) throws SQLException {
        String newNomCat = choicecat.getValue();
        String newNomType = choicetyp.getValue();
        String newnomMed =nomMed.getText();
        Integer newrefFT = Integer.parseInt(refFT.getText());
        String newNoticeFT = NoticeFT.getText();        
        Integer newprixFT = Integer.parseInt(prixFT.getText());
        String newDateFT = DateFT.getText();
        
        MedData.setCategorie(newNomCat);
        MedData.setType(newNomType);
        MedData.setNom_medi(newnomMed);
        MedData.setReference(newrefFT);
        MedData.setNotice(newNoticeFT);
        MedData.setPrix(newprixFT);
        MedData.setDateExp(newDateFT);
        MedData.setIdMed(MedData.getIdMed());
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmation");
        alert.setHeaderText(null);
        alert.setContentText("Êtes-vous sûr ?");
        Optional<ButtonType> result = alert.showAndWait();
        if(result.isPresent() && result.get() == ButtonType.OK){
            ServiceMedicament Smed =new ServiceMedicament();
            Smed.modifier(MedData);
            JOptionPane.showMessageDialog(null, "Medicament Modifier !");
            Stage stage = (Stage) Modifier.getScene().getWindow();
            stage.close();
            Smed.afficher();
        }
    }

    @FXML
    private void Supprimer(ActionEvent event) throws SQLException {
                        ServiceMedicament Smed = new ServiceMedicament();
            Medicament selectedMed = tabmedi.getSelectionModel().getSelectedItem();
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
alert.setTitle("Confirmation");
alert.setHeaderText(null);
alert.setContentText("Êtes-vous sûr ?");
Optional<ButtonType> result = alert.showAndWait();

if (result.isPresent() && result.get() == ButtonType.OK && selectedMed != null) {
    Smed.supprimer(selectedMed.getIdMed());
                JOptionPane.showMessageDialog(null, "Medicament Supprimer !");
afficher();

    
}
    }
     private void afficher() throws SQLException{
        catcol.setCellValueFactory(new PropertyValueFactory<>("categorie"));
        typecol.setCellValueFactory(new PropertyValueFactory<>("type"));
        nomcol.setCellValueFactory(new PropertyValueFactory<>("nom_medi"));
        ServiceMedicament m =new ServiceMedicament();
        list = m.afficher();
        tabmedi.setItems(list);
    }

    @FXML
    private void searsh(ActionEvent event) throws SQLException  {
        
        if(!nomMed.getText().isEmpty() ){
        String entredNomMed = nomMed.getText();
        ServiceMedicament servM = new ServiceMedicament();
        
        tabmedi.setItems(servM.rechercheParNomMedicament(entredNomMed));
        }else if(!NomCat.getText().isEmpty()){
        String entredNomCat = NomCat.getText();
        ServiceMedicament servM = new ServiceMedicament();
        tabmedi.setItems(servM.rechercheParCategorie(entredNomCat));
        }else if (!NomType.getText().isEmpty()){
        String entredNomType = NomType.getText();
        ServiceMedicament servM = new ServiceMedicament();
        tabmedi.setItems(servM.rechercheParType(entredNomType));
        }
    
        
        else {
          ServiceMedicament servM = new ServiceMedicament();
        tabmedi.setItems(servM.afficher());  
     //   JOptionPane.showMessageDialog(null, "Merci de remplir un champs pour chercher le medicament !");

        }
    }

    @FXML
    private void GoToPageCategorie(ActionEvent event) throws IOException {
    
        FXMLLoader loader = new FXMLLoader(getClass().getResource("../views/Categories.fxml"));
    Parent root = loader.load();

    Scene scene = new Scene(root);
    Stage primaryStage = (Stage) cat.getScene().getWindow();
    primaryStage.setScene(scene);
    primaryStage.show();
    }

    @FXML
    private void GoToPageType(ActionEvent event) throws IOException {
        
         FXMLLoader loader = new FXMLLoader(getClass().getResource("../views/Types.fxml"));
    Parent root = loader.load();

    Scene scene = new Scene(root);
    Stage primaryStage = (Stage) cat.getScene().getWindow();
    primaryStage.setScene(scene);
    primaryStage.show();
    }

 
    @FXML
    private void logout(ActionEvent event) throws IOException{
        FXMLLoader loader = new FXMLLoader(getClass().getResource("../views/Login.fxml"));
            Parent root = loader.load();
            Scene scene = new Scene(root);
            Stage primaryStage = (Stage) logout.getScene().getWindow();
            primaryStage.setScene(scene);
        
    }

    @FXML
    private void profil(ActionEvent event)throws IOException{
        FXMLLoader loader = new FXMLLoader(getClass().getResource("../views/ModifierProfilePharmacie.fxml"));
            Parent root = loader.load();
            Scene scene = new Scene(root);
            Stage primaryStage = (Stage) logout.getScene().getWindow();
            primaryStage.setScene(scene);
            ModifierProfilePharmacieController modifierProfilePharmacieController = loader.getController();
            modifierProfilePharmacieController.setLoggedInUser(LoginController.loggedInUser);
        
    }

    

}