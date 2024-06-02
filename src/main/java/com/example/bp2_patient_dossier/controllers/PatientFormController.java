package com.example.bp2_patient_dossier.controllers;

import com.example.bp2_patient_dossier.PatientDossierApp;
import com.example.bp2_patient_dossier.models.Patient;
import com.example.bp2_patient_dossier.models.Ziekenhuis;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

public class PatientFormController {
    private Patient selectedPatient;
    private Ziekenhuis selectedZiekenhuis;
    private Stage stage;
    private MySQLConnector connector;
    @FXML private TextField tfVoornaam;
    @FXML private TextField tfAchternaam;
    @FXML private TextField tfGeslacht;
    @FXML private TextField tfBSN;
    @FXML private Label lbAddPatientErrorMessage;
    @FXML private Label lbEditPatientErrorMessage;

    public PatientFormController() {
        stage = PatientDossierApp.getMainStage();
        connector = new MySQLConnector();
    }

    @FXML
    private void addPatient() {
        System.out.println("addPatient knop geklikt!");

        if (tfVoornaam.getText().equals("") || tfAchternaam.getText().equals("") || tfGeslacht.getText().equals("") || tfBSN.getText().equals("")) {
            lbAddPatientErrorMessage.setText("Alle velden moet ingevuld zijn!");
            return;
        }

        String voornaam = tfVoornaam.getText();
        String achternaam = tfAchternaam.getText();
        String geslacht = tfGeslacht.getText();
        int bsn = Integer.parseInt(tfBSN.getText());

        Patient patient = new Patient(voornaam, achternaam, geslacht, bsn);

        connector.addPatient(patient, selectedZiekenhuis);

        navToPatientList();
    }

    @FXML
    private void editPatient() throws IOException {
        System.out.println("editPatient knop geklikt!");

        if (tfVoornaam.getText().equals("") || tfAchternaam.getText().equals("") || tfGeslacht.getText().equals("") || tfBSN.getText().equals("")) {
            lbEditPatientErrorMessage.setText("Alle velden moet ingevuld zijn!");
            return;
        }

        String voornaam = tfVoornaam.getText();
        String achternaam = tfAchternaam.getText();
        String geslacht = tfGeslacht.getText();
        int bsn = Integer.parseInt(tfBSN.getText());

        selectedPatient.setVoornaam(voornaam);
        selectedPatient.setAchternaam(achternaam);
        selectedPatient.setGeslacht(geslacht);
        selectedPatient.setBSN(bsn);

        Patient patient = selectedPatient;

        connector.editPatient(patient);

        navToPatientList();
    }

    public void setSelectedPatient(Patient selectedPatient) {
        this.selectedPatient = selectedPatient;

        String bsnString = String.valueOf(selectedPatient.getBSN());

        tfVoornaam.setText(this.selectedPatient.getVoornaam());
        tfAchternaam.setText(this.selectedPatient.getAchternaam());
        tfGeslacht.setText(this.selectedPatient.getGeslacht());
        tfBSN.setText(bsnString);
    }


    public void setSelectedZiekenhuis(Ziekenhuis selectedZiekenhuis) {
        this.selectedZiekenhuis = selectedZiekenhuis;
    }

    public void navToPatientList() {
        System.out.println("navToPatientList knop geklikt!");

        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/scenes/patient-list-view.fxml"));
            Parent root = loader.load();

            Scene zorgverlenerListScene = new Scene(root);
            zorgverlenerListScene.getStylesheets().add(getClass().getResource("/stylesheets/mainStyle.css").toExternalForm());

            PatientListController patientListController = loader.getController();
            patientListController.setSelectedZiekenhuis(selectedZiekenhuis);

            stage.setScene(zorgverlenerListScene);
            stage.show();
        }
        catch (IOException ex) {
            ex.printStackTrace();
        }
    }



}
