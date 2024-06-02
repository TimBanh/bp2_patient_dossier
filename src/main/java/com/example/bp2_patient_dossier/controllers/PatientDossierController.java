package com.example.bp2_patient_dossier.controllers;

import com.example.bp2_patient_dossier.PatientDossierApp;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;

public class PatientDossierController {
    private MySQLConnector mySQLConnector = new MySQLConnector();
    private Stage stage;

    @FXML
    private Button hospitalButton;
    @FXML
    private Button caregiverButton;
    @FXML
    private Button patientButton;

    public PatientDossierController() {
        this.stage = PatientDossierApp.getMainStage();
    }

    @FXML
    private void navNaarZiekenhuisLijstScene() {
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


    @FXML
    private void navNaarPatientScene() {
        System.out.println("Patiënt knop geklikt!");
        // Voeg hier de logica toe voor wat er moet gebeuren als de patiënt knop wordt geklikt
    }
}