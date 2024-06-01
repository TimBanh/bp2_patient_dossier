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
        // Haal het geselecteerde ziekenhuis op uit de lijst
        selectedZorgverlener = zorgverlenerListView.getSelectionModel().getSelectedItem();

        // Controleer of er een ziekenhuis is geselecteerd
        if (selectedZorgverlener != null) {
            // Voer acties uit op basis van het geselecteerde ziekenhuis
            System.out.println("Geselecteerd ziekenhuis: " + selectedZorgverlener.getVoornaam() + " " + selectedZorgverlener.getAchternaam());
            lbSelectedZorgverlener.setText(selectedZiekenhuis.toString());

        } else {
            System.out.println("Geen ziekenhuis geselecteerd.");
            lbSelectedZorgverlener.setText(selectedZiekenhuis.toString());
        }
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
        System.out.println("navToZiekenhuisToevoegen knop geklikt!");

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
}
