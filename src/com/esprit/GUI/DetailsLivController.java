package com.esprit.GUI;

import com.esprit.entities.Livraison;
import com.esprit.services.ServiceLivraison;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;

public class DetailsLivController implements Initializable {

    @FXML
    private TableView<Livraison> LIVRAISONTV;
    @FXML
    private TableColumn<Livraison, String> tfReference;
    @FXML
    private TableColumn<Livraison, Integer> tfIdCommande;
    @FXML
    private TableColumn<Livraison, Integer> tfIdLivreur;
    @FXML
    private TableColumn<Livraison, Integer> supp;
    @FXML
    private TextField tfReference1;
    @FXML
    private TextField tfIdCommande1;
    @FXML
    private TextField tfIdLivreur1;
    @FXML
    private VBox checkboxList;
    @FXML
    private ChoiceBox<String> livreurChoiceBox;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        ServiceLivraison serviceLivraison = new ServiceLivraison();
        List<String> livreurs = serviceLivraison.afficher_livreur();
        livreurChoiceBox.setItems(FXCollections.observableArrayList(livreurs));

        supp.setCellValueFactory(new PropertyValueFactory<>("Reference"));
        tfIdCommande.setCellValueFactory(new PropertyValueFactory<>("idCommande"));
        tfIdLivreur.setCellValueFactory(new PropertyValueFactory<>("Nom"));
        LIVRAISONTV.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null) {
                tfReference1.setText(newValue.getReference());
                tfIdCommande1.setText(String.valueOf(newValue.getIdCommande()));
                livreurChoiceBox.getSelectionModel().select(newValue.getNom());
            } else {
                clearInputFields();
            }
        });

        tfReference.setCellFactory(column -> {
            return new TableCell<Livraison, String>() {
                private final Button deleteButton = new Button("Delete");

                {
                    deleteButton.setOnAction(event -> {
                        Livraison livraison = (Livraison) getTableRow().getItem();
                        if (livraison != null) {
                            supprimerLivraison(livraison);
                        }
                    });
                }

                @Override
                protected void updateItem(String item, boolean empty) {
                    super.updateItem(item, empty);
                    if (empty) {
                        setGraphic(null);
                    } else {
                        setGraphic(deleteButton);
                        deleteButton.setOnAction(event -> {
                            Livraison livraison = (Livraison) getTableRow().getItem();
                            if (livraison != null) {
                                Alert confirmationAlert = new Alert(Alert.AlertType.CONFIRMATION);
                                confirmationAlert.setTitle("Confirmation");
                                confirmationAlert.setHeaderText("Delete Confirmation");
                                confirmationAlert.setContentText("Are you sure you want to delete this item?");

                                Optional<ButtonType> result = confirmationAlert.showAndWait();
                                if (result.isPresent() && result.get() == ButtonType.OK) {
                                    supprimerLivraison(livraison);
                                }
                            }
                        });
                    }
                }
            };
        });

        afficherLivraisons();
    }

    public void updateLivraison() throws SQLException {
        ServiceLivraison serviceLivraison = new ServiceLivraison();
        Livraison selectedLivraison = LIVRAISONTV.getSelectionModel().getSelectedItem();
        if (selectedLivraison != null) {
            String reference = tfReference1.getText();
            int idCommande = Integer.parseInt(tfIdCommande1.getText());

            String nomLivreur = livreurChoiceBox.getSelectionModel().getSelectedItem();
            int idLivreur = serviceLivraison.get_livreur_id(nomLivreur);

            Alert confirmationAlert = new Alert(Alert.AlertType.CONFIRMATION);
            confirmationAlert.setTitle("Confirmation");
            confirmationAlert.setHeaderText("Update Confirmation");
            confirmationAlert.setContentText("Are you sure you want to update this item?");

            Optional<ButtonType> result = confirmationAlert.showAndWait();
            if (result.isPresent() && result.get() == ButtonType.OK) {
                selectedLivraison.setReference(reference);
                selectedLivraison.setIdCommande(idCommande);
                selectedLivraison.setIdLivreur(idLivreur);

                serviceLivraison.modifier(selectedLivraison);

                clearInputFields();
                LIVRAISONTV.refresh();
                LIVRAISONTV.getItems().clear();
                afficherLivraisons();
            }
        }
    }

    private void clearInputFields() {
        tfReference1.clear();
        tfIdCommande1.clear();
        livreurChoiceBox.getSelectionModel().clearSelection();
    }

    public void afficherLivraisons() {
        ServiceLivraison serviceLivraison = new ServiceLivraison();
        List<Livraison> livraisons = serviceLivraison.afficher();
        LIVRAISONTV.getItems().addAll(livraisons);
    }

    public void supprimerLivraison(Livraison livraison) {
        ServiceLivraison serviceLivraison = new ServiceLivraison();
        serviceLivraison.supprimer(livraison);
        LIVRAISONTV.getItems().remove(livraison);
        System.out.println("Livraison supprimée !");
    }

    @FXML
    private void ajouterLivraison() throws SQLException {
        ServiceLivraison serviceLivraison = new ServiceLivraison();
        String reference = tfReference1.getText();
        int idCommande = Integer.parseInt(tfIdCommande1.getText());

        String nomLivreur = livreurChoiceBox.getSelectionModel().getSelectedItem();
        int idLivreur = serviceLivraison.get_livreur_id(nomLivreur);

        Alert confirmationAlert = new Alert(Alert.AlertType.CONFIRMATION);
        confirmationAlert.setTitle("Confirmation");
        confirmationAlert.setHeaderText("Add Confirmation");
        confirmationAlert.setContentText("Are you sure you want to add this delivery?");

        Optional<ButtonType> result = confirmationAlert.showAndWait();
        if (result.isPresent() && result.get() == ButtonType.OK) {
            Livraison livraison = new Livraison(reference, idCommande, idLivreur);
            serviceLivraison.ajouter(livraison);
            clearInputFields();
            LIVRAISONTV.refresh();
            LIVRAISONTV.getItems().clear();
            afficherLivraisons();
        }
    }
}
