package com.esprit.gui;

import com.esprit.entities.Medecin;
import com.esprit.services.ServiceMedecin;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
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
    private TextField idspecialite;
    @FXML
    private TextField idprenom;
    @FXML
    private TableView<Medecin> table;
    @FXML
    private TableColumn<Medecin, String> nom;
    @FXML
    private TableColumn<Medecin, String> prenom;
    @FXML
    private TableColumn<Medecin, String> specialite;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        nom.setCellValueFactory(new PropertyValueFactory<>("nom"));
        prenom.setCellValueFactory(new PropertyValueFactory<>("prenom"));
        specialite.setCellValueFactory(new PropertyValueFactory<>("spécialité"));

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
                            sm.supprimer(data);
                            medecinList.remove(data);
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
        ServiceMedecin sm = new ServiceMedecin();
        Medecin medecin = new Medecin(idNom.getText(), idprenom.getText(), idspecialite.getText());
        sm.ajouter(medecin);

        table.getItems().add(medecin);

        idNom.clear();
        idprenom.clear();
        idspecialite.clear();
    }
}
