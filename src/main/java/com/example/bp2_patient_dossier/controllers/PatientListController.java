package com.example.bp2_patient_dossier.controllers;

import com.example.bp2_patient_dossier.PatientDossierApp;
import com.example.bp2_patient_dossier.models.Patient;
import com.example.bp2_patient_dossier.models.Ziekenhuis;
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

public class PatientListController {
    private Stage stage;
    private MySQLConnector connector;
    private Ziekenhuis selectedZiekenhuis;
    private Patient selectedPatient;
    @FXML private ListView<Patient> patientListView;
    @FXML private Label lbSelectedPatiënt;

    public PatientListController() {
        stage = PatientDossierApp.getMainStage();
        connector = new MySQLConnector();
    }

    public void setSelectedZiekenhuis(Ziekenhuis selectedZiekenhuis) {
        this.selectedZiekenhuis = selectedZiekenhuis;

        if (selectedZiekenhuis != null) {
            ArrayList<Patient> patienten = connector.getPatienten(selectedZiekenhuis);
            ObservableList<Patient> patientObservableList = FXCollections.observableArrayList(patienten);
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
            lbSelectedPatiënt.setText(selectedPatient.toString());

        } else {
            System.out.println("Geen ziekenhuis geselecteerd.");
            lbSelectedPatiënt.setText("Geen ziekenhuis geselecteerd");
        }
    }

    @FXML
    private void deletePatient() {
        System.out.println("deletePatient knop geklikt!");

        if (selectedPatient == null) {
            lbSelectedPatiënt.setText("Selecteer een Patient!");
            return;
        }

        connector.deletePatient(selectedPatient);

        ArrayList<Patient> patienten = connector.getPatienten(selectedZiekenhuis);
        ObservableList<Patient> patientObservableList = FXCollections.observableArrayList(patienten);
        patientListView.setItems(patientObservableList);

        selectedPatient = null;
        lbSelectedPatiënt.setText("");
    }

    @FXML
    private void navToEditPatient() {
        System.out.println("navToEditPatient knop geklikt!");

        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/scenes/edit-patient-view.fxml"));
            Parent root = loader.load();

            Scene addZorgverlenerScene = new Scene(root);
            addZorgverlenerScene.getStylesheets().add(getClass().getResource("/stylesheets/mainStyle.css").toExternalForm());

            PatientFormController patientFormController = loader.getController();
            patientFormController.setSelectedPatient(selectedPatient);
            patientFormController.setSelectedZiekenhuis(selectedZiekenhuis);

            stage.setScene(addZorgverlenerScene);
            stage.show();
        }
        catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    @FXML
    private void navToAddPatient() {
        System.out.println("navToAddPatient knop geklikt!");

        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/scenes/add-patient-view.fxml"));
            Parent root = loader.load();

            Scene addZorgverlenerScene = new Scene(root);
            addZorgverlenerScene.getStylesheets().add(getClass().getResource("/stylesheets/mainStyle.css").toExternalForm());

            PatientFormController patientFormController = loader.getController();
            patientFormController.setSelectedZiekenhuis(selectedZiekenhuis);

            stage.setScene(addZorgverlenerScene);
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

    @FXML
    private void navToAddPatientDossier() {
        System.out.println("navToAddPatientDossier knop geklikt!");

        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/scenes/add-patientdossier-view.fxml"));
            Parent root = loader.load();

            Scene addPatientDossierScene = new Scene(root);
            addPatientDossierScene.getStylesheets().add(getClass().getResource("/stylesheets/mainStyle.css").toExternalForm());

            PatientDossierFormController patientDossierFormController = loader.getController();
            patientDossierFormController.setSelectedPatient(selectedPatient);
            patientDossierFormController.setSelectedZiekenhuis(selectedZiekenhuis);

            stage.setScene(addPatientDossierScene);
            stage.show();
        }
        catch (IOException ex) {
            ex.printStackTrace();
        }
    }

}
