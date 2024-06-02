package com.example.bp2_patient_dossier.controllers;

import com.example.bp2_patient_dossier.PatientDossierApp;
import com.example.bp2_patient_dossier.models.Ziekenhuis;
import com.example.bp2_patient_dossier.models.Zorgverlener;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;

public class ZorgverlenerListController {
    @FXML
    private ListView<Zorgverlener> zorgverlenerListView;
    @FXML
    private Zorgverlener selectedZorgverlener;
    @FXML
    private Ziekenhuis selectedZiekenhuis;
    @FXML
    private Label lbSelectedZorgverlener;
    private Stage stage;
    private MySQLConnector connector;

    public ZorgverlenerListController() {
        stage = PatientDossierApp.getMainStage();
        connector = new MySQLConnector();
    }

    public void setSelectedZiekenhuis(Ziekenhuis selectedZiekenhuis) {
        this.selectedZiekenhuis = selectedZiekenhuis;

        if (selectedZiekenhuis != null) {
            ArrayList<Zorgverlener> zorgverleners = connector.getZorgverleners(selectedZiekenhuis);
            ObservableList<Zorgverlener> ziekenhuisObservableList = FXCollections.observableArrayList(zorgverleners);
            zorgverlenerListView.setItems(ziekenhuisObservableList);
        }
    }

    @FXML
    private void selectZorgverlener() {
        // Haal het geselecteerde Zorgverlener op uit de lijst
        selectedZorgverlener = zorgverlenerListView.getSelectionModel().getSelectedItem();

        // Controleer of er een Zorgverlener is geselecteerd
        if (selectedZorgverlener != null) {
            // Voer acties uit op basis van het geselecteerde Zorgverlener
            System.out.println("Geselecteerd ziekenhuis: " + selectedZorgverlener.getVoornaam() + " " + selectedZorgverlener.getAchternaam());
            lbSelectedZorgverlener.setText(selectedZorgverlener.toString());

        } else {
            System.out.println("Geen ziekenhuis geselecteerd.");
            lbSelectedZorgverlener.setText(selectedZorgverlener.toString());
        }
    }

    @FXML
    private void deleteZorgverlener() {
        System.out.println("deleteZorgverlener knop geklikt!");

        if (selectedZorgverlener == null) {
            lbSelectedZorgverlener.setText("Selecteer een zorgverlener!");
            return;
        }

        connector.deleteZorgverlener(selectedZorgverlener);

        ArrayList<Zorgverlener> zorgverleners = connector.getZorgverleners(selectedZiekenhuis);
        ObservableList<Zorgverlener> ziekenhuisObservableList = FXCollections.observableArrayList(zorgverleners);
        zorgverlenerListView.setItems(ziekenhuisObservableList);

        selectedZorgverlener = null;
        lbSelectedZorgverlener.setText("");
    }

    @FXML
    private void navToHome() {
        System.out.println("Home knop geklikt!");
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/scenes/start-view.fxml"));
            Parent root = loader.load();

            Scene startScene = new Scene(root);
            startScene.getStylesheets().add(getClass().getResource("/stylesheets/mainStyle.css").toExternalForm());

            stage.setScene(startScene);
            stage.show();
        }
        catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    @FXML
    private void navToAddZorgverlener() {
        System.out.println("navToAddZorgverlener knop geklikt!");

        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/scenes/add-zorgverlener-view.fxml"));
            Parent root = loader.load();

            Scene addZorgverlenerScene = new Scene(root);
            addZorgverlenerScene.getStylesheets().add(getClass().getResource("/stylesheets/mainStyle.css").toExternalForm());

            ZorgverlenerFormController zorgverlenerFormController = loader.getController();
            zorgverlenerFormController.setSelectedZiekenhuis(this.selectedZiekenhuis);

            stage.setScene(addZorgverlenerScene);
            stage.show();
        }
        catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    @FXML
    private void navToEditZorgverlener() {
        System.out.println("navToEditZorgverlener knop geklikt!");

        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/scenes/edit-zorgverlener-view.fxml"));
            Parent root = loader.load();

            Scene editZorgverlenerScene = new Scene(root);
            editZorgverlenerScene.getStylesheets().add(getClass().getResource("/stylesheets/mainStyle.css").toExternalForm());

            ZorgverlenerFormController zorgverlenerFormController = loader.getController();
            zorgverlenerFormController.setSelectedZorgverlener(this.selectedZorgverlener);
            zorgverlenerFormController.setSelectedZiekenhuis(this.selectedZiekenhuis);

            stage.setScene(editZorgverlenerScene);
            stage.show();
        }
        catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    @FXML
    private void navToZiekenhuisListScene() {
        System.out.println("Ziekenhuis knop geklikt!");

        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/scenes/ziekenhuis-list-view.fxml"));
            Parent root = loader.load();

            Scene ziekenhuisScene = new Scene(root);
            ziekenhuisScene.getStylesheets().add(getClass().getResource("/stylesheets/mainStyle.css").toExternalForm());

            stage.setScene(ziekenhuisScene);
            stage.show();
        }
        catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}
