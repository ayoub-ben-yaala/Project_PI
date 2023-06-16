/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package pi.gui;

import java.util.List;
import java.util.Map;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import pi.entities.Medicament;
import pi.entities.Panier;
import pi.entities.TableData;
import pi.entities.ligne_commande;
import pi.services.service_panier;

public class panier_controller {


    @FXML
    private ChoiceBox<String> BoxMed;

    @FXML
    private TextField qteMed;

    @FXML
    private TableView<Object> tablePanier;

    @FXML
    private TableColumn<TableData, String> colMed;

    @FXML
    private TableColumn<TableData, Float> PrixUnitaire;

    @FXML
    private TableColumn<TableData, Integer> quantite;

    @FXML
    private TableColumn<TableData, Float> coutUnitaire;

    @FXML
    public void initialize() {

        List<Medicament> allMedications = Medicament.getAll();

        // Create an observable list to hold the medication names
        ObservableList<String> medicationNames = FXCollections.observableArrayList();

        // Extract the names from the medications and add them to the observable list
        for (Medicament medication : allMedications) {
            medicationNames.add(medication.nom);
        }

        // Populate the choice box with the medication names
        BoxMed.setItems(medicationNames);

        service_panier panierService = new service_panier();
        //panierService.afficher(idPanier); idpanier = user.getPanier();
        Panier panier = panierService.afficher(1); 

        // Clear the existing items in the table
        // Set up the cell value factories for the TableColumn instances
        colMed.setCellValueFactory(new PropertyValueFactory<>("colMed"));
        quantite.setCellValueFactory(new PropertyValueFactory<>("quantite"));
        PrixUnitaire.setCellValueFactory(new PropertyValueFactory<>("PrixUnitaire"));
        coutUnitaire.setCellValueFactory(new PropertyValueFactory<>("coutUnitaire"));

        // Populate the table with the panier data
        ObservableList<Object> tableData = FXCollections.observableArrayList();
        for (Map.Entry<Integer, Integer> entry : panier.getMeds().entrySet()) {
            int medId = entry.getKey();
            int qte = entry.getValue();

            // Retrieve the medication name based on the ID
            Medicament med = Medicament.getIdById(medId); // Replace with the actual method
            if (med == null) {
                System.out.println("attempted to get medicament with ID" + medId + "which doesn't exist in the database.");
                continue;
            }
            // Create a new ligne_commande instance with the medication details
            TableData row = new TableData(med.nom, qte, med.prix, med.prix * qte);

            // Add the ligne_commande instance to the table
            tableData.add(row);
        }
        tablePanier.setItems(tableData);
    }

    @FXML
    void ajouter_commande(ActionEvent event) {
        try {
            String selectedMedName = BoxMed.getSelectionModel().getSelectedItem();
            Medicament m = new Medicament();
            int medId = m.getIdByName(selectedMedName);
            int quantite = Integer.parseInt(qteMed.getText());
            if (quantite > 0) {
                // Display an error message in red

                ligne_commande ligneCommande = new ligne_commande(1, quantite, medId); // You can set the appropriate id as needed
                service_panier panierService = new service_panier();
                //panierService.ajouterPanier(ligneCOmmande,idPanier); idpanier = user.getPanier();
                panierService.ajouterPanier(ligneCommande, 1);
                System.out.println("Ligne de commande ajoutée au panier !");
            }
            initialize();
        } catch (NumberFormatException e) {
            System.out.println("Veuillez entrer une quantité valide.");
        }
    }
}
