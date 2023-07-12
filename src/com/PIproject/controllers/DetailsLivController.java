package com.PIproject.controllers;
import java.time.LocalDate;
import java.sql.Date;
import com.PIproject.entities.Livraison;
import com.PIproject.services.ServiceLivraison;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;


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
    private ChoiceBox<String> livreurChoiceBox;
    
       @FXML
    private Button retour;

    public void callMain(ActionEvent event) {
        Map main = new Map(36.853546749967926,10.207102064868804);
        main.setVisible(true);
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        
        
        ServiceLivraison serviceLivraison = new ServiceLivraison();
        List<String> livreurs = serviceLivraison.afficher_livreur();
        ObservableList<String> livreurList = FXCollections.observableArrayList("Selectioner");
livreurList.addAll(livreurs);
livreurChoiceBox.setItems(livreurList);


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
        TableColumn<Livraison, Void> buttonColumn = new TableColumn<>("Action");
        buttonColumn.setCellFactory(column -> {
    return new TableCell<Livraison, Void>() {
        private final Button button = new Button("Adresse");

        {
            // Définir l'action du bouton
            button.setOnAction(event -> {
              callMain(event);
                // Appeler la méthode souhaitée avec le modèle de données associé
                // par exemple : model.maMethode();
            });
        }

        @Override
        protected void updateItem(Void item, boolean empty) {
            super.updateItem(item, empty);
            if (empty) {
                setGraphic(null);
            } else {
                setGraphic(button);
            }
        }
    };
});
LIVRAISONTV.getColumns().add(buttonColumn);
        afficherLivraisons();
        
        
    }

    @FXML
    public void updateLivraison() throws SQLException {
        ServiceLivraison serviceLivraison = new ServiceLivraison();
        Livraison selectedLivraison = LIVRAISONTV.getSelectionModel().getSelectedItem();
        if (selectedLivraison != null) {
            String reference = tfReference1.getText();
            int idCommande = Integer.parseInt(tfIdCommande1.getText());

            String nomLivreur = livreurChoiceBox.getSelectionModel().getSelectedItem();
            int idLivreur = serviceLivraison.get_livreur_id(nomLivreur);
            if ("Selectioner".equals(nomLivreur)){
} else {
                
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
    @FXML
        public void Search() {
        String nomLivreur = livreurChoiceBox.getSelectionModel().getSelectedItem();
        LIVRAISONTV.getItems().clear();
        if ("Selectioner".equals(nomLivreur)){
        afficherLivraisons();
    } else{
        ServiceLivraison serviceLivraison = new ServiceLivraison();
        
        List<Livraison> livraisons = serviceLivraison.Search(nomLivreur);
        LIVRAISONTV.getItems().addAll(livraisons);}
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
        if ("Selectioner".equals(nomLivreur)){
    } else {
            
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

    @FXML
    private void retour(ActionEvent event) throws IOException {
             FXMLLoader loader = new FXMLLoader(getClass().getResource("../views/Medicament.fxml"));
    Parent root = loader.load();

    Scene scene = new Scene(root);
    Stage primaryStage = (Stage) retour.getScene().getWindow();
    primaryStage.setScene(scene);
    primaryStage.show();
    }
}
