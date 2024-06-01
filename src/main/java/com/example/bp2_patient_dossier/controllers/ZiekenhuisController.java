package com.example.bp2_patient_dossier.controllers;

import com.example.bp2_patient_dossier.PatientDossierApp;
import com.example.bp2_patient_dossier.models.Ziekenhuis;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ListView;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;

public class ZiekenhuisController {
    @FXML
    private ListView<Ziekenhuis> ziekenhuisList;
    private Ziekenhuis selectedZiekenhuis;
    private Stage stage;
    private MySQLConnector connector;

    public ZiekenhuisController() {
        this.stage = PatientDossierApp.getMainStage();
        connector = new MySQLConnector();
    }

    @FXML
    private void initialize() {
        ArrayList<Ziekenhuis> ziekenhuizen = connector.getZiekenhuizen();
        ObservableList<Ziekenhuis> ziekenhuisObservableList = FXCollections.observableArrayList(ziekenhuizen);
        ziekenhuisList.setItems(ziekenhuisObservableList);
    }

    @FXML
    private void selectZiekenhuis() {
        // Haal het geselecteerde ziekenhuis op uit de lijst
        selectedZiekenhuis = ziekenhuisList.getSelectionModel().getSelectedItem();

        // Controleer of er een ziekenhuis is geselecteerd
        if (selectedZiekenhuis != null) {
            // Voer acties uit op basis van het geselecteerde ziekenhuis
            System.out.println("Geselecteerd ziekenhuis: " + selectedZiekenhuis.getNaam());
        } else {
            System.out.println("Geen ziekenhuis geselecteerd.");
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
    private void navToAddZiekenhuis() {
        System.out.println("navToZiekenhuisToevoegen knop geklikt!");

        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/scenes/add-ziekenhuis-view.fxml"));
            Parent root = loader.load();

            Scene addZiekenhuisScene = new Scene(root);
            addZiekenhuisScene.getStylesheets().add(getClass().getResource("/stylesheets/mainStyle.css").toExternalForm());

            stage.setScene(addZiekenhuisScene);
            stage.show();
        }
        catch (IOException ex) {
            ex.printStackTrace();
        }
    }

}
