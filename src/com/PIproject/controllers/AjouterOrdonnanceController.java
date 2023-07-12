    /*
     * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
     * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.PIproject.controllers;

import com.PIproject.entities.*;
import com.PIproject.entities.Ordonnance;
import com.PIproject.services.ServiceMedecin;
import com.PIproject.services.ServiceOrdonnancee;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import javafx.scene.control.Dialog;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ButtonBar;

import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.property.SimpleStringProperty;
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
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.util.Callback;
import javafx.util.Duration;
import org.controlsfx.control.Notifications;

public class AjouterOrdonnanceController implements Initializable {

    @FXML
    private TableView<Ordonnance> table;

    @FXML
    private TextField reference1;

    @FXML
    private ComboBox<String> medecin1;

    @FXML
    private ComboBox<String> medicament1;

    @FXML
    private DatePicker date1;

    @FXML
    private TextField statue1;
    @FXML
    private Button ajouter;
    @FXML
    private Button supprimer;
    @FXML
    private TableView<?> table1;
    @FXML
    private TableColumn<Ordonnance, Integer> Reference;
    @FXML
    private TableColumn<Ordonnance, String> Medecin;
    @FXML
    private TableColumn<Ordonnance, String> Medicament;
    @FXML
    private TableColumn<Ordonnance, Date> Date;
    @FXML
    private TableColumn<Ordonnance, String> Statue;
    @FXML
    private AnchorPane modifier;
    private Ordonnance selectedOrdonnance;
    @FXML
    private Button modifierButton;
    @FXML
    private Button listmed;
    @FXML
    private TextField reference;
    @FXML
    private ComboBox<?> medecin;
    @FXML
    private TextField statue;
    @FXML
    private ComboBox<?> medicament;
    @FXML
    private DatePicker date;
    @FXML
    private Button back;

    public void setStage(Stage stage) {
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        Reference.setCellValueFactory(new PropertyValueFactory<>("Reference")); 
        Medecin.setCellValueFactory(new PropertyValueFactory<>("Medecin"));
        Medicament.setCellValueFactory(new PropertyValueFactory<>("Medicament"));
        Date.setCellValueFactory(new PropertyValueFactory<>("date"));
        Statue.setCellValueFactory(new PropertyValueFactory<>("Statut"));
        ServiceOrdonnancee so = new ServiceOrdonnancee();
        table.getItems().addAll(so.afficher());
        ServiceMedecin sm = new ServiceMedecin();
        ObservableList<String> medecinList = FXCollections.observableArrayList(sm.getMedecins());// get medecin par nom et prenom
        ObservableList<String> mediList = FXCollections.observableArrayList(so.afficher_nom_med());// get medecin par nom et prenom

        medecin1.setItems(medecinList);// yaffichihom fcombox medecin
        Medecin.setCellValueFactory(cellData -> {
            Medecin medecin = sm.getMdecinById(cellData.getValue().getId_Medecin());//getMdecinById
            String fullName = medecin.getNom() + " " + medecin.getPrenom(); // fullName nom prenom
            return new SimpleStringProperty(fullName); // retur full name
        });

        /* ServiceOrdonnancee sme = new ServiceOrdonnancee();
         Medicament.setCellValueFactory(cellData -> {
            Medicament medicament = sm.getMdecinById(cellData.getValue().getId_Medecin());//getMdecinById
            String fullName = medicament.getNom_medi();
            return new SimpleStringProperty(fullName);
        });*/
        table.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> { // selection un ligne
            if (newSelection != null) {

                selectedOrdonnance = newSelection;

                reference1.setText(String.valueOf(selectedOrdonnance.getReference()));
                medecin1.setValue(sm.getMdecinById(selectedOrdonnance.getId_Medecin()).getNom() + " " + sm.getMdecinById(selectedOrdonnance.getId_Medecin()).getPrenom());
                medicament1.setItems(mediList);
                medicament1.setValue("Medicament");

                date1.setValue(selectedOrdonnance.getDate().toLocalDate());
                statue1.setText(selectedOrdonnance.getStatut());
            }
        });

        TableColumn<Ordonnance, Void> colBtn = new TableColumn<>("PDF");
        Callback<TableColumn<Ordonnance, Void>, TableCell<Ordonnance, Void>> cellFactory = new Callback<TableColumn<Ordonnance, Void>, TableCell<Ordonnance, Void>>() {
            @Override
            public TableCell<Ordonnance, Void> call(final TableColumn<Ordonnance, Void> param) {
                final TableCell<Ordonnance, Void> cell = new TableCell<Ordonnance, Void>() {

                    private final Button btn = new Button("PDF");

                    {
                        btn.setOnAction((ActionEvent event) -> {
                            Ordonnance data = getTableView().getItems().get(getIndex());
                            System.out.println("selectedData: " + data);

                            Dialog<ButtonType> dialog = new Dialog<>();
                            dialog.setTitle("Confirmation PDF");
                            dialog.setHeaderText("Voulez-vous vraiment télécharger cet élément ?");

                            ButtonType buttonTypeYes = new ButtonType("Oui", ButtonBar.ButtonData.YES);
                            ButtonType buttonTypeNo = new ButtonType("Non", ButtonBar.ButtonData.NO);

                            dialog.getDialogPane().getButtonTypes().addAll(buttonTypeYes, buttonTypeNo);
                            Optional<ButtonType> result = dialog.showAndWait();

                            if (result.isPresent() && result.get() == buttonTypeYes) {

                                generateOrdonnancePDF(data);

                                Image image = new Image("com/esprit/images/check.png");

                                ImageView imageView = new ImageView(image);
                                imageView.setFitWidth(50);
                                imageView.setFitHeight(50);

                                Notifications notif = Notifications.create()
                                        .graphic(imageView)
                                        .text("Ordonnance télécharger avec succés !")
                                        .title("Message de succés")
                                        .hideAfter(Duration.seconds(5));

                                notif.show();
                            } else {
                                dialog.close();
                            }
                        });

                    }

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
    private void ajouter(ActionEvent event) throws IOException {
        String referenceText = reference1.getText();
        String medecinValue = medecin1.getValue();
        String statueText = statue1.getText();

        if (referenceText.isEmpty() || medecinValue == null || statueText.isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION, "Veuillez remplir tous les champs !", ButtonType.OK);
            alert.show();
            return;
        }

        try {
            int ref = Integer.parseInt(referenceText);
            if (ref <= 0) {
                Alert alert = new Alert(Alert.AlertType.INFORMATION, "La référence doit être un nombre entier supérieur à zéro !", ButtonType.OK);
                alert.show();
                return;
            }

            ServiceMedecin sm = new ServiceMedecin();
            int nomMedecin = sm.getMdecinByNom(medecin1.getValue()).getId();

            int nomMedicament = 1;
            Date dateord = java.sql.Date.valueOf(date1.getValue());
            String statut = statue1.getText();

            Ordonnance ordonnance = new Ordonnance(ref, nomMedecin, nomMedicament, dateord, statut);

            Dialog<ButtonType> dialog = new Dialog<>();
            dialog.setTitle("Confirmation Ajout");
            dialog.setHeaderText("Voulez-vous vraiment ajouter cette ordonnance ?");

            ButtonType buttonTypeYes = new ButtonType("Oui", ButtonBar.ButtonData.YES);
            ButtonType buttonTypeNo = new ButtonType("Non", ButtonBar.ButtonData.NO);

            dialog.getDialogPane().getButtonTypes().addAll(buttonTypeYes, buttonTypeNo);
            Optional<ButtonType> result = dialog.showAndWait();

            if (result.isPresent() && result.get() == buttonTypeYes) {

                ServiceOrdonnancee so = new ServiceOrdonnancee();
                so.ajouter(ordonnance);
                table.getItems().add(ordonnance);

                reference1.clear();
                medecin1.setValue(null);
                medicament1.setValue(null);
                date1.setValue(null);
                statue1.clear();
                Image image = new Image("com/esprit/images/check.png");

                ImageView imageView = new ImageView(image);
                imageView.setFitWidth(50);
                imageView.setFitHeight(50);

                Notifications notif = Notifications.create()
                        .graphic(imageView)
                        .text("Ordonnance créer avec succée !")
                        .title("Message d'information")
                        .hideAfter(Duration.seconds(5));

                notif.show();
            }
        } catch (NumberFormatException e) {
            System.out.println("La référence doit être un nombre entier !");
        }
    }

    @FXML
    private void supprimer(ActionEvent event) {
        ServiceOrdonnancee so = new ServiceOrdonnancee();
        Dialog<ButtonType> dialog = new Dialog<>();
        dialog.setTitle("Confirmation de suppression");
        dialog.setHeaderText("Voulez-vous vraiment supprimer cette ordonnance ?");

        ButtonType buttonTypeYes = new ButtonType("Oui", ButtonBar.ButtonData.YES);
        ButtonType buttonTypeNo = new ButtonType("Non", ButtonBar.ButtonData.NO);

        dialog.getDialogPane().getButtonTypes().addAll(buttonTypeYes, buttonTypeNo);
        Optional<ButtonType> result = dialog.showAndWait();

        if (result.isPresent() && result.get() == buttonTypeYes) {
            Ordonnance selectedOrdonnance = table.getSelectionModel().getSelectedItem();
            if (selectedOrdonnance != null) {
                so.supprimer(selectedOrdonnance);
                table.getItems().remove(selectedOrdonnance);
                Image image = new Image("com/esprit/images/check.png");

                ImageView imageView = new ImageView(image);
                imageView.setFitWidth(50);
                imageView.setFitHeight(50);

                Notifications notif = Notifications.create()
                        .graphic(imageView)
                        .text("Ordonnance supprimer avec succée !")
                        .title("Message d'information")
                        .hideAfter(Duration.seconds(5));

                notif.show();
            }
        } else {
            dialog.close();
        }
    }

    @FXML
    private void modifier(ActionEvent event) {
        String referenceText = reference1.getText();
        String medecinValue = medecin1.getValue();
        String statueText = statue1.getText();

        if (referenceText.isEmpty() || medecinValue == null || statueText.isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION, "Veuillez remplir tous les champs !", ButtonType.OK);
            alert.show();
            return;
        }

        try {
            int ref = Integer.parseInt(referenceText);
            if (ref <= 0) {
                Alert alert = new Alert(Alert.AlertType.INFORMATION, "La référence doit être un nombre entier supérieur à zéro !", ButtonType.OK);
                alert.show();
                return;
            }

            String nomMedecin = medecin1.getValue();

            int nomMedicament = 1;
            Date dateord = java.sql.Date.valueOf(date1.getValue());
            String statut = statue1.getText();
            ServiceMedecin sm = new ServiceMedecin();

            if (selectedOrdonnance != null) {

                selectedOrdonnance.setReference(ref);
                selectedOrdonnance.setNom_Medecin(sm.getMdecinByNom(nomMedecin).getId());
                selectedOrdonnance.setNom_Medicament(nomMedicament);
                selectedOrdonnance.setDate(dateord);
                selectedOrdonnance.setStatut(statut);
                Dialog<ButtonType> dialog = new Dialog<>();
                dialog.setTitle("Confirmation de modification");
                dialog.setHeaderText("Voulez-vous vraiment modifier cette ordonnance ?");

                ButtonType buttonTypeYes = new ButtonType("Oui", ButtonBar.ButtonData.YES);
                ButtonType buttonTypeNo = new ButtonType("Non", ButtonBar.ButtonData.NO);

                dialog.getDialogPane().getButtonTypes().addAll(buttonTypeYes, buttonTypeNo);
                Optional<ButtonType> result = dialog.showAndWait();

                if (result.isPresent() && result.get() == buttonTypeYes) {

                    ServiceOrdonnancee so = new ServiceOrdonnancee();
                    so.modifier(selectedOrdonnance);
                    System.out.println(selectedOrdonnance);

                    table.refresh();

                    table.getItems().setAll(so.afficher());

                    reference1.clear();
                    medecin1.setValue(null);
                    medicament1.setValue(null);
                    date1.setValue(null);
                    statue1.clear();
                    selectedOrdonnance = null;
                    Image image = new Image("com/esprit/images/check.png");

                    ImageView imageView = new ImageView(image);
                    imageView.setFitWidth(50);
                    imageView.setFitHeight(50);

                    Notifications notif = Notifications.create()
                            .graphic(imageView)
                            .text("Odronnance modifier avec succée !")
                            .title("Message d'information")
                            .hideAfter(Duration.seconds(5));

                    notif.show();
                } else {
                    dialog.close();
                }

            }
        } catch (NumberFormatException e) {
            System.out.println("La référence doit être un nombre entier !");
        }

    }

    private void generateOrdonnancePDF(Ordonnance ord) {
        try {

            Document document = new Document();

            FileChooser fileChooser = new FileChooser();
            fileChooser.setTitle("Save PDF File");
            fileChooser.setInitialFileName("ordonnance-" + ord.getReference() + ".pdf");
            fileChooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("PDF Files", "*.pdf"));
            File file = fileChooser.showSaveDialog(null);
            if (file != null) {
                PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream(file));

                document.open();
                ServiceMedecin sm = new ServiceMedecin();

                document.add(new Paragraph("Reference : " + ord.getReference()
                        + " \n \n Date : " + ord.getDate()
                        + " \n \n  Medecin : Mme/Mr " + sm.getMdecinById(ord.getId_Medecin()).getNom() + " " + sm.getMdecinById(ord.getId_Medecin()).getPrenom()
                        + "\n \n medicament : " + "medica 1 " + "\n medicament : " + "medica 2 "
                ));

                document.close();

                System.out.println("Ordonnance PDF generated and saved successfully!");
            }
        } catch (DocumentException | FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void listmed(ActionEvent event) throws IOException {
     FXMLLoader loader = new FXMLLoader(getClass().getResource("../views/AjouterMedecin.fxml"));
    Parent root = loader.load();
    Scene scene = new Scene(root);
    Stage primaryStage = (Stage) listmed.getScene().getWindow();
    primaryStage.setScene(scene);
    primaryStage.show();
    }

    @FXML
    private void back(ActionEvent event) throws IOException {
                      FXMLLoader loader = new FXMLLoader(getClass().getResource("../views/Medicament.fxml"));
    Parent root = loader.load();

    Scene scene = new Scene(root);
    Stage primaryStage = (Stage) back.getScene().getWindow();
    primaryStage.setScene(scene);
    primaryStage.show();
    }

}