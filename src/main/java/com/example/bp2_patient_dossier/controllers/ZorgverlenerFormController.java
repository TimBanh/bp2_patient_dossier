package com.example.bp2_patient_dossier.controllers;

import com.example.bp2_patient_dossier.PatientDossierApp;
import com.example.bp2_patient_dossier.models.Ziekenhuis;
import com.example.bp2_patient_dossier.models.Zorgverlener;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

public class ZorgverlenerFormController {
    @FXML private TextField tfVoornaam;
    @FXML private TextField tfAchternaam;
    @FXML private TextField tfFunctie;
    private Zorgverlener selectedZorgverlener;
    private Ziekenhuis selectedZiekenhuis;
    private Stage stage;
    private MySQLConnector connector;

    public ZorgverlenerFormController() {
        stage = PatientDossierApp.getMainStage();
        connector = new MySQLConnector();
    }

    @FXML
    private void addZorgverlener() throws IOException {
        System.out.println("addZiekenhuis knop geklikt!");

        String voornaam = tfVoornaam.getText();
        String achternaam = tfAchternaam.getText();
        String functie = tfFunctie.getText();

        Zorgverlener zorgverlener = new Zorgverlener(voornaam, achternaam, functie);

        connector.addZorgverlener(zorgverlener, selectedZiekenhuis);

        navToZorgverlenerList();
    }

    public void setSelectedZiekenhuis(Ziekenhuis selectedZiekenhuis) {
        this.selectedZiekenhuis = selectedZiekenhuis;
    }

    public void setSelectedZorgverlener(Zorgverlener selectedZorgverlener) {
        this.selectedZorgverlener = selectedZorgverlener;

        tfVoornaam.setText(this.selectedZorgverlener.getVoornaam());
        tfAchternaam.setText(this.selectedZorgverlener.getAchternaam());
        tfFunctie.setText(this.selectedZorgverlener.getFunctie());
    }

    @FXML
    private void navToZorgverlenerList() throws IOException{
        System.out.println("navToZorgverlener knop geklikt!");

        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/scenes/zorgverlener-list-view.fxml"));
            Parent root = loader.load();

            Scene zorgverlenerListScene = new Scene(root);
            zorgverlenerListScene.getStylesheets().add(getClass().getResource("/stylesheets/mainStyle.css").toExternalForm());

            ZorgverlenerListController zorgverlenerListController = loader.getController();
            zorgverlenerListController.setSelectedZiekenhuis(selectedZiekenhuis);

            stage.setScene(zorgverlenerListScene);
            stage.show();
        }
        catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}
