package com.esprit.gui;

import com.esprit.entities.Medecin;
import com.esprit.entities.Ordonnance;
import com.esprit.entities.Specialite;
import com.esprit.services.ServiceMedecin;
import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Dialog;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.util.Callback;

public class AjouterMedecinController implements Initializable {

    @FXML
    private TextField idNom;
    @FXML
    private ComboBox<String> idspecialite;
    @FXML
    private TextField idprenom;
    @FXML
    private TableView<Medecin> table;
    @FXML
    private TableColumn<Medecin, String> nom;
    @FXML
    private TableColumn<Medecin, String> prenom;
   @FXML
    private TableColumn<Medecin, String>specialite;
    @FXML
    private TextField email;
    @FXML
    private TableColumn<Medecin, String> email_col;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        nom.setCellValueFactory(new PropertyValueFactory<>("nom"));
        prenom.setCellValueFactory(new PropertyValueFactory<>("prenom"));
        specialite.setCellValueFactory(new PropertyValueFactory<>("specialite"));
        email_col.setCellValueFactory(new PropertyValueFactory<>("email"));
      
    idspecialite.getItems().addAll("CARDIOLOGISTE","DERMATOLOGISTE","GYNECOLOGUE","PEDIATRE");

        ServiceMedecin sm = new ServiceMedecin();
        ObservableList<Medecin> medecinList = FXCollections.observableArrayList(sm.afficher());
        table.setItems(medecinList);

        TableColumn<Medecin, Void> colBtn = new TableColumn<>("Suprimer");
        Callback<TableColumn<Medecin, Void>, TableCell<Medecin, Void>> cellFactory = new Callback<TableColumn<Medecin, Void>, TableCell<Medecin, Void>>() {
            @Override
            public TableCell<Medecin, Void> call(final TableColumn<Medecin, Void> param) {
                final TableCell<Medecin, Void> cell = new TableCell<Medecin, Void>() {

                    private final Button btn = new Button("Suprimer");

                    {
                        btn.setOnAction((ActionEvent event) -> {
                            Medecin data = getTableView().getItems().get(getIndex());
                            System.out.println("selectedData: " + data);

                            // Create the confirmation dialog
                            Dialog<ButtonType> dialog = new Dialog<>();
                            dialog.setTitle("Confirmation Suppression");
                            dialog.setHeaderText("Voulez-vous vraiment supprimer cet élément ?");

                            ButtonType buttonTypeYes = new ButtonType("Oui", ButtonBar.ButtonData.YES);
                            ButtonType buttonTypeNo = new ButtonType("Non", ButtonBar.ButtonData.NO);

                            dialog.getDialogPane().getButtonTypes().addAll(buttonTypeYes, buttonTypeNo);
                            Optional<ButtonType> result = dialog.showAndWait();

                            if (result.isPresent() && result.get() == buttonTypeYes) {
                                // User clicked "Oui", perform deletion
                                sm.supprimer(data);
                                medecinList.remove(data);
                                table.getItems().remove(data);
                                
                            } else {
                                dialog.close();
                            }

                        });}

                    @Override
                    public void updateItem(Void item, boolean empty) {
                        super.updateItem(item, empty);
                        if (empty) {
                            setGraphic(null);
                        } else {
                            setGraphic(btn);
                        }
                    }
                };
                return cell;
            }
        };

        colBtn.setCellFactory(cellFactory);
        table.getColumns().add(colBtn);
        
    }

    @FXML
    private void ajout(ActionEvent event) throws IOException {
        String nom = idNom.getText();
        String prenom = idprenom.getText();
        String specialite = idspecialite.getSelectionModel().getSelectedItem();
        String emailText = email.getText();

        if (nom.isEmpty() || prenom.isEmpty() || specialite == null || emailText.isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION, "Veuillez remplir tous les champs !", ButtonType.OK);
            alert.show();
            return;
        }

        
        String emailRegex = "^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$";
        if (!emailText.matches(emailRegex)) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION, "Veuillez entrer une adresse e-mail valide !", ButtonType.OK);
            alert.show();
            return;
        }
        ServiceMedecin sm = new ServiceMedecin();
        Medecin medecin = new Medecin(idNom.getText(), idprenom.getText(), Specialite.valueOf(idspecialite.getSelectionModel().getSelectedItem()),email.getText());
        Dialog<ButtonType> dialog = new Dialog<>();
        dialog.setTitle("Confirmation Ajout");
        dialog.setHeaderText("Voulez-vous vraiment ajouter ce medecin");

        ButtonType buttonTypeYes = new ButtonType("Oui", ButtonBar.ButtonData.YES);
        ButtonType buttonTypeNo = new ButtonType("Non", ButtonBar.ButtonData.NO);

        dialog.getDialogPane().getButtonTypes().addAll(buttonTypeYes, buttonTypeNo);
        Optional<ButtonType> result = dialog.showAndWait();

        if (result.isPresent() && result.get() == buttonTypeYes) {
        sm.ajouter(medecin);

        table.getItems().add(medecin);

        idNom.clear();
        idprenom.clear();
        email.clear();
        idspecialite.setValue(null);
    }
        else{
            dialog.close();
            
        }
}
}