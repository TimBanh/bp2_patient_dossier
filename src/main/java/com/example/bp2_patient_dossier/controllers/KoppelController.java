package com.example.bp2_patient_dossier.controllers;

import com.example.bp2_patient_dossier.PatientDossierApp;
import com.example.bp2_patient_dossier.models.Patient;
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

public class KoppelController {
    private Stage stage;
    private MySQLConnector connector;
    private Ziekenhuis selectedZiekenhuis;
    private Zorgverlener selectedZorgverlener;
    private Patient selectedPatient;
    private ArrayList<Patient> patientArrayList;
    @FXML private ListView<Patient> patientListView;
    @FXML private Label lbErrorMessage;

    public KoppelController() {
        stage = PatientDossierApp.getMainStage();
        connector = new MySQLConnector();
    }

    public void setSelectedZiekenhuis(Ziekenhuis selectedZiekenhuis) {
        this.selectedZiekenhuis = selectedZiekenhuis;
    }

    public void setSelectedZorgverlener(Zorgverlener selectedZorgverlener) {
        this.selectedZorgverlener = selectedZorgverlener;

        patientArrayList = connector.getUnassignedPatientenByZorgverler(this.selectedZorgverlener);

        if (this.selectedZorgverlener != null) {
            ObservableList<Patient> patientObservableList = FXCollections.observableArrayList(patientArrayList);
            patientListView.setItems(patientObservableList);
        }
    }

    @FXML
    private void setSelectedPatient() {
        // Haal het geselecteerde Patiënt op uit de lijst
        selectedPatient = patientListView.getSelectionModel().getSelectedItem();

        // Controleer of er een Patiënt is geselecteerd
        if (selectedPatient != null) {
            // Voer acties uit op basis van het geselecteerde Patiënt
            System.out.println("Geselecteerd ziekenhuis: " + selectedPatient.getVoornaam() + " " + selectedPatient.getAchternaam());
            lbErrorMessage.setText(selectedPatient.toString());
        } else {
            System.out.println("Geen ziekenhuis geselecteerd.");
            lbErrorMessage.setText("Geen patiënt geselecteerd");
        }
    }

    @FXML
    private void navToZorgverlenerList() throws IOException {
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

    @FXML
    private void koppelPatient() {
        System.out.println("koppelPatient knop geklikt!");

        if (selectedPatient == null) {
            lbErrorMessage.setText("Selecteer een patiënt!");
            return;
        }

        connector.addKoppelPatientZorgverlener(selectedPatient, selectedZorgverlener);

        navToEditZorgverlener();
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
            zorgverlenerFormController.getPatientenByZorgverlener();

            stage.setScene(editZorgverlenerScene);
            stage.show();
        }
        catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}
