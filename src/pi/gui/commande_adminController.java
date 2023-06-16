/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package pi.gui;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.ComboBoxTableCell;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.input.MouseEvent;
import javafx.util.Callback;
import pi.entities.Commande;
import pi.entities.Pharmacie;
import pi.util.DataSource;
import pi.services.service_commande;

/**
 * FXML Controller class
 *
 * @author usernvme
 */
public class commande_adminController implements Initializable {

    @FXML
    private TableView<Commande> tableCommande;
    @FXML
    private ChoiceBox<String> BoxEtat;
    @FXML
    private DatePicker BoxDate;
    @FXML
    private ChoiceBox<String> BoxPharmacie;
    @FXML
    private TableColumn<Commande, String> ColPharmacie;
    @FXML
    private TableColumn<Commande, String> ColEtat;
    @FXML
    private TableColumn<Commande, LocalDate> ColDate;
    @FXML
    private final TableColumn<Commande, Void> deleteColumn = new TableColumn<>("Delete");

    /**
     * Initializes the controller class.
     */
    Connection conn = DataSource.getInstance().getCnx();
    PreparedStatement pst;
    PreparedStatement pst1;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        selectPharma();
        table();
        ColEtat.setEditable(true);

        ColEtat.setCellFactory(column -> {
            ComboBoxTableCell<Commande, String> cell = new ComboBoxTableCell<>(new MapConverter());
            cell.getItems().addAll(new MapConverter().getValuesList());
            return cell;
        });

        ColEtat.setOnEditCommit((TableColumn.CellEditEvent<Commande, String> t) -> {
            t.getTableView().getItems().get(t.getTablePosition().getRow()).setEtat(t.getNewValue());
            modifier((Commande) t.getTableView().getItems().get(t.getTablePosition().getRow()));
        });

        deleteColumn.setCellFactory((TableColumn<Commande, Void> param) -> new TableCell<Commande, Void>() {
            private final Button deleteButton = new Button("Delete");

            {
                deleteButton.setOnAction((ActionEvent event) -> {
                    Commande commande = (Commande) getTableRow().getItem();

                    // Create a confirmation dialog
                    Alert confirmationDialog = new Alert(Alert.AlertType.CONFIRMATION);
                    confirmationDialog.setTitle("Confirmation");
                    confirmationDialog.setHeaderText("Delete Commande");
                    confirmationDialog.setContentText("Are you sure you want to delete this command?");

                    // Set OK and Cancel buttons
                    ButtonType okButton = new ButtonType("OK");
                    ButtonType cancelButton = new ButtonType("Cancel", ButtonBar.ButtonData.CANCEL_CLOSE);
                    confirmationDialog.getButtonTypes().setAll(okButton, cancelButton);

                    // Show the confirmation dialog and wait for user response
                    Optional<ButtonType> result = confirmationDialog.showAndWait();

                    if (result.isPresent() && result.get() == okButton) {
                        deleteCommande(commande);
                    }
                });
            }

            @Override
            protected void updateItem(Void item, boolean empty) {
                super.updateItem(item, empty);

                if (empty) {
                    setGraphic(null);
                } else {
                    setGraphic(deleteButton);
                }
            }
        });

        ColDate.setCellFactory(column -> {
            return new TableCell<Commande, LocalDate>() {
                private final DatePicker datePicker = new DatePicker();

                {
                    // Update the date value when the DatePicker value changes
                    datePicker.setOnAction(event -> {
                        LocalDate localDate = datePicker.getValue();
                        if (localDate != null) {
                            Date date = Date.from(localDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
                            //java.sql.Date sqlDate = new java.sql.Date(date.getTime());
                            //LocalDate dateLocal = date.toLocalDate();
                            commitEdit(date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate());

                        }
                    });

                    // Show the DatePicker when the cell is clicked
                    this.setOnMouseClicked(event -> {
                        if (event.getClickCount() == 2) {
                            this.setGraphic(datePicker);
                            this.setText(null);
                        }
                    });
                }

                @Override
                protected void updateItem(LocalDate date, boolean empty) {
                    super.updateItem(date, empty);

                    if (empty) {
                        setText(null);
                        setGraphic(null);
                    } else {
                        setText(date.toString());
                        setGraphic(null);
                        ColDate.setEditable(true);

                        ColDate.setOnEditCommit((TableColumn.CellEditEvent<Commande, LocalDate> t) -> {
                            t.getTableView().getItems().get(t.getTablePosition().getRow()).setDate(t.getNewValue());
                            modifier((Commande) t.getTableView().getItems().get(t.getTablePosition().getRow()));
                        });
                    }
                }
            };
        });

        deleteColumn.setPrefWidth(110);
        tableCommande.getColumns().add(deleteColumn);
    }

    public void table() {
        ObservableList<Commande> commandes = FXCollections.observableArrayList();
        String sql = "SELECT * FROM commande";

        try {
            pst = conn.prepareStatement(sql);
            ResultSet rs = pst.executeQuery();

            while (rs.next()) {
                int id_pharmacie = rs.getInt("id_pharmacie");
                int id_commande = rs.getInt("id");
                String etat = rs.getString("etat");
                LocalDate date = rs.getDate("date").toLocalDate();
                Commande commande = new Commande();

                String sql1 = "SELECT * FROM pharmacie where id=?";
                pst1 = conn.prepareStatement(sql1);
                pst1.setInt(1, id_pharmacie);
                ResultSet rs1 = pst1.executeQuery();
                while (rs1.next()) {
                    Pharmacie pharmacie = new Pharmacie(id_pharmacie, rs1.getString("nom"));
                    commande.setEtat(etat);
                    commande.setDate(date);
                    commande.setId_commande(id_commande);
                    commande.setPharmacie(pharmacie);
                }

                commandes.add(commande);
            }

            tableCommande.setItems(commandes);

            ColEtat.setCellValueFactory(new PropertyValueFactory<>("etat"));
            ColDate.setCellValueFactory(new PropertyValueFactory<>("date"));
            ColPharmacie.setCellValueFactory(new PropertyValueFactory<>("pharmacie"));

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    Commande del = null;

    @FXML
    private void supprimer_commande(ActionEvent event) {
        try {
            del = tableCommande.getSelectionModel().getSelectedItem();

            // Create a confirmation dialog
            Alert confirmationDialog = new Alert(Alert.AlertType.CONFIRMATION);
            confirmationDialog.setTitle("Confirmation");
            confirmationDialog.setHeaderText("Delete Commande");
            confirmationDialog.setContentText("Are you sure you want to delete the selected command?");

            // Set OK and Cancel buttons
            ButtonType okButton = new ButtonType("OK");
            ButtonType cancelButton = new ButtonType("Cancel", ButtonBar.ButtonData.CANCEL_CLOSE);
            confirmationDialog.getButtonTypes().setAll(okButton, cancelButton);

            // Show the confirmation dialog and wait for user response
            Optional<ButtonType> result = confirmationDialog.showAndWait();

            if (result.isPresent() && result.get() == okButton) {
                String deleteQuery = "DELETE FROM commande WHERE id = ? ";
                PreparedStatement pst = conn.prepareStatement(deleteQuery);
                pst.setInt(1, del.getId_commande());
                pst.executeUpdate();
                table();
                System.out.println("Successful DELETE");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
private void ajouter_commande(ActionEvent event) {
    String sql = "SELECT id FROM `pharmacie` WHERE nom = ?";
    try {
        pst = conn.prepareStatement(sql);
        pst.setString(1, BoxPharmacie.getValue());
        ResultSet rs = pst.executeQuery();
        while (rs.next()) {
            Pharmacie p = new Pharmacie(rs.getInt("id"));
            String etat = BoxEtat.getValue();
            LocalDate date = BoxDate.getValue();

            if (etat == null) {
                // Display an error dialog
                Alert errorDialog = new Alert(Alert.AlertType.ERROR);
                errorDialog.setTitle("Error");
                errorDialog.setHeaderText("Invalid Input");
                errorDialog.setContentText("Etat value cannot be null.");
                errorDialog.showAndWait();
                return; // Stop execution if etat is null
            }

            // Create a confirmation dialog
            Alert confirmationDialog = new Alert(Alert.AlertType.CONFIRMATION);
            confirmationDialog.setTitle("Confirmation");
            confirmationDialog.setHeaderText("Add Commande");
            confirmationDialog.setContentText("Are you sure you want to add this command?");

            // Set OK and Cancel buttons
            ButtonType okButton = new ButtonType("OK");
            ButtonType cancelButton = new ButtonType("Cancel", ButtonBar.ButtonData.CANCEL_CLOSE);
            confirmationDialog.getButtonTypes().setAll(okButton, cancelButton);

            // Show the confirmation dialog and wait for user response
            Optional<ButtonType> result = confirmationDialog.showAndWait();

            if (result.isPresent() && result.get() == okButton) {
                Commande co = new Commande(etat, date, p);
                ajouter(co);
                table();
                System.out.println("Successful command addition");
            }
        }
    } catch (SQLException ex) {
        ex.printStackTrace();
    }
}


    public void ajouter(Commande c) {
        try {
            String req = "INSERT INTO commande(etat, date, id_pharmacie) VALUES (?,?,?);";
            PreparedStatement pst = conn.prepareStatement(req);
            pst.setString(1, c.getEtat());
            pst.setObject(2, c.getDate());
            pst.setInt(3, c.pharmacie.getId());

            pst.executeUpdate();
            System.out.println("commande ajoutée !");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public void selectPharma() {
        List pharmaList = new ArrayList();
        String reqUser = "SELECT * FROM pharmacie ;";
        try {
            pst = conn.prepareStatement(reqUser);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                String pharmaNom = rs.getString("nom");
                pharmaList.add(pharmaNom);
            }
            BoxPharmacie.getItems().addAll(pharmaList);
            BoxEtat.getItems().addAll("En Cours", "Recue");
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
    Commande mod = null;

    private void modifier(Commande c) {
        try {

            mod = tableCommande.getSelectionModel().getSelectedItem();
            String UpdateData = "UPDATE commande SET etat = ?, date = ? WHERE id = ?";
            PreparedStatement upd = conn.prepareStatement(UpdateData);
            upd.setString(1, c.getEtat());
            upd.setObject(2, c.getDate());

            upd.setInt(3, mod.getId_commande());
            upd.executeUpdate();
            table();
            System.out.println("Successful update");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void deleteCommande(Commande commande) {
        try {
            String deleteQuery = "DELETE FROM commande WHERE id = ?";
            PreparedStatement pst = conn.prepareStatement(deleteQuery);
            pst.setInt(1, commande.getId_commande());
            pst.executeUpdate();
            table();
            System.out.println("Successful DELETE");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
