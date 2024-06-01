package com.example.bp2_patient_dossier.controllers;

import com.example.bp2_patient_dossier.PatientDossierApp;
import com.example.bp2_patient_dossier.models.Ziekenhuis;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

public class ZiekenhuisFormController {
    private MySQLConnector connector;
    private Stage stage;
    @FXML
    private TextField tfNaam;
    @FXML private TextField tfLocatie;

    public ZiekenhuisFormController() {
        this.connector  = new MySQLConnector();
        this.stage = PatientDossierApp.getMainStage();
    }

    @FXML
    private void addZiekenhuis() {
        System.out.println("addZiekenhuis knop geklikt!");

        String naam = tfNaam.getText();
        String locatie = tfLocatie.getText();

        Ziekenhuis ziekenhuis = new Ziekenhuis(naam, locatie);

        connector.addZiekenhuis(ziekenhuis);

        navToZiekenhuisLijstScene();
    }

    @FXML
    private void navToZiekenhuisLijstScene() {
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
}
