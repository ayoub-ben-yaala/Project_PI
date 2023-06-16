package com.esprit.gui;

import com.esprit.entities.Medecin;
import com.esprit.entities.Ordonnance;
import com.esprit.entities.Specialite;
import com.esprit.mail.Mail;
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
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Dialog;
import javafx.scene.control.Label;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.util.Callback;
import javafx.util.Duration;
import org.controlsfx.control.Notifications;

public class AjouterMedecinController_phar implements Initializable {

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
    private TableColumn<Medecin, String> specialite;
    @FXML
    private TextField email;
    @FXML
    private TableColumn<Medecin, String> email_col;
    @FXML
    private TextField search;
    @FXML
    private Label medecinCountLabel;
    private ObservableList<Medecin> medecinList;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        nom.setCellValueFactory(new PropertyValueFactory<>("nom"));
        prenom.setCellValueFactory(new PropertyValueFactory<>("prenom"));
        specialite.setCellValueFactory(new PropertyValueFactory<>("specialite"));
        email_col.setCellValueFactory(new PropertyValueFactory<>("email")); // column add email

        idspecialite.getItems().addAll("CARDIOLOGISTE", "DERMATOLOGISTE", "GYNECOLOGUE", "PEDIATRE");

        ServiceMedecin sm = new ServiceMedecin();
        medecinList = FXCollections.observableArrayList(sm.afficher());
        table.setItems(medecinList); // table show liste medecin

        int count = sm.countMedecins(); //  methode en l'appelle en service pour le count des medecin
        medecinCountLabel.setText("Total Medecins: " + count);

        TableColumn<Medecin, Void> colBtn = new TableColumn<>("Suprimer");
        Callback<TableColumn<Medecin, Void>, TableCell<Medecin, Void>> cellFactory = new Callback<TableColumn<Medecin, Void>, TableCell<Medecin, Void>>() {
            @Override
            public TableCell<Medecin, Void> call(final TableColumn<Medecin, Void> param) {
                final TableCell<Medecin, Void> cell = new TableCell<Medecin, Void>() {

                    private final Button btn = new Button("Supprimer");

                    {
                        btn.setOnAction((ActionEvent event) -> {
                            Medecin data = getTableView().getItems().get(getIndex());
                            System.out.println("selectedData: " + data);

                            Dialog<ButtonType> dialog = new Dialog<>(); // confiramation supp 
                            dialog.setTitle("Confirmation Suppression"); 
                            dialog.setHeaderText("Voulez-vous vraiment supprimer cet élément ?");

                            ButtonType buttonTypeYes = new ButtonType("Oui", ButtonBar.ButtonData.YES);
                            ButtonType buttonTypeNo = new ButtonType("Non", ButtonBar.ButtonData.NO);

                            dialog.getDialogPane().getButtonTypes().addAll(buttonTypeYes, buttonTypeNo);
                            Optional<ButtonType> result = dialog.showAndWait();// end confiramation supp 

                            if (result.isPresent() && result.get() == buttonTypeYes) { // supp oui 

                                sm.supprimer(data);// apel service
                                medecinList.remove(data);// supp
                                getTableView().getItems().remove(data); //update table
                                int count = medecinList.size(); // update count
                                medecinCountLabel.setText("Total Medecins: " + count); // update label
                                table.refresh();
                                Image image = new Image("com/esprit/images/check.png");//notif

                                ImageView imageView = new ImageView(image);
                                imageView.setFitWidth(50);
                                imageView.setFitHeight(50);

                                Notifications notif = Notifications.create()
                                        .graphic(imageView)
                                        .text("Medecin supprimer avec succée !")
                                        .title("Message d'information")
                                        .hideAfter(Duration.seconds(5)); // end notif

                                notif.show();
                            } else {
                                dialog.close(); // end if  (no)
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
        ///search

        search.textProperty().addListener((observable, oldValue, newValue) -> {
            String searchTerm = newValue.toLowerCase();

            ObservableList<Medecin> filteredList = FXCollections.observableArrayList();
            for (Medecin medecin : medecinList) {
                if (medecin.getNom().toLowerCase().contains(searchTerm)) {
                    filteredList.add(medecin);
                }
            }

            table.setItems(filteredList);
        });
        ////end search

    }

    @FXML
    private void ajout(ActionEvent event) throws IOException { 
        String nom = idNom.getText();
        String prenom = idprenom.getText();
        String specialite = idspecialite.getSelectionModel().getSelectedItem();
        String emailText = email.getText();

        if (nom.isEmpty() || prenom.isEmpty() || specialite == null || emailText.isEmpty()) {// controle de saisie champ vide
            Alert alert = new Alert(Alert.AlertType.INFORMATION, "Veuillez remplir tous les champs !", ButtonType.OK);
            alert.show();
            return;
        }

        String emailRegex = "^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$";// controle de saisie email
        if (!emailText.matches(emailRegex)) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION, "Veuillez entrer une adresse e-mail valide !", ButtonType.OK);
            alert.show();
            return;
        }
        ServiceMedecin sm = new ServiceMedecin();
        Medecin medecin = new Medecin(nom, prenom, Specialite.valueOf(specialite), emailText);
        Dialog<ButtonType> dialog = new Dialog<>();
        dialog.setTitle("Confirmation Ajout");
        dialog.setHeaderText("Voulez-vous vraiment ajouter ce medecin");

        ButtonType buttonTypeYes = new ButtonType("Oui", ButtonBar.ButtonData.YES);
        ButtonType buttonTypeNo = new ButtonType("Non", ButtonBar.ButtonData.NO);

        dialog.getDialogPane().getButtonTypes().addAll(buttonTypeYes, buttonTypeNo);
        Optional<ButtonType> result = dialog.showAndWait();

        if (result.isPresent() && result.get() == buttonTypeYes) {

            sm.ajouter(medecin);

            int count = sm.countMedecins();
            medecinCountLabel.setText("Total Medecins: " + count);

            medecinList.add(medecin);
            Mail.sendEmail(medecin);

            ObservableList<Medecin> filteredList = FXCollections.observableArrayList();
            String searchTerm = search.getText().toLowerCase();
            for (Medecin medc : medecinList) {
                if (medc.getNom().toLowerCase().contains(searchTerm)) {
                    filteredList.add(medc);
                }
            }
            table.setItems(filteredList);

            idNom.clear();
            idprenom.clear();
            email.clear();
            idspecialite.setValue(null);
            Image image = new Image("com/esprit/images/check.png");

            ImageView imageView = new ImageView(image);
            imageView.setFitWidth(50);
            imageView.setFitHeight(50);

            Notifications notif = Notifications.create()
                    .graphic(imageView)
                    .text("Medecin créer avec succée !")
                    .title("Message d'information")
                    .hideAfter(Duration.seconds(5));

            notif.show();

        } else {
            dialog.close();

        }
    }

    @FXML
    private void ord(ActionEvent event) throws IOException {
        
          FXMLLoader loader = new FXMLLoader(getClass().getResource("AjouterOrdonnanace_phar.fxml"));
                Parent root = loader.load();
                Scene scene = new Scene(root);
                Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                stage.setScene(scene);
                stage.show();
    }


    

}
