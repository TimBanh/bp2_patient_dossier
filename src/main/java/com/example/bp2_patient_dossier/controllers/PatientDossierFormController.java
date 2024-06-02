package com.example.bp2_patient_dossier.controllers;

import com.example.bp2_patient_dossier.PatientDossierApp;
import com.example.bp2_patient_dossier.models.Patient;
import com.example.bp2_patient_dossier.models.PatientDossier;
import com.example.bp2_patient_dossier.models.Ziekenhuis;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.time.LocalDate;

public class PatientDossierFormController {
    private Patient selectedPatient;
    private Ziekenhuis selectedZiekenhuis;
    private PatientDossier patientDossier;
    private Stage stage;
    private MySQLConnector connector;
    @FXML private TextField tfVoornaam;
    @FXML private TextField tfAchternaam;
    @FXML private DatePicker dpGeboorteDatum;
    @FXML private TextField tfBSN;
    @FXML private TextField tfAdres;
    @FXML private TextArea taMedisch;
    @FXML private TextArea taMedicatie;
    @FXML private Label lbAddErrorMessage;

    @FXML private TextField tfBSNDossier;

    public PatientDossierFormController() {
        stage = PatientDossierApp.getMainStage();
        connector = new MySQLConnector();
    }

    @FXML
    private void addPatientDossier() {
        System.out.println("addPatient knop geklikt!");

        if (tfVoornaam.getText().equals("") || tfAchternaam.getText().equals("") || tfAdres.getText().equals("")
                || dpGeboorteDatum.getValue() ==  null || tfBSN.getText().equals("")) {
            lbAddErrorMessage.setText("Voornaam, achternaam, adres, geboortedatum en adres velden moeten ingevuld zijn!");
            return;
        }

        String voornaam = tfVoornaam.getText();
        String achternaam = tfAchternaam.getText();
        String adres = tfAdres.getText();
        LocalDate geboorteDatum = dpGeboorteDatum.getValue();
        int bsn = Integer.parseInt(tfBSN.getText());
        String medischGeschiedenis = taMedisch.getText();
        String medicatie = taMedicatie.getText();

        PatientDossier patientDossier = new PatientDossier(voornaam, achternaam, geboorteDatum, adres, bsn, medischGeschiedenis, medicatie);

        connector.addPatientDossier(patientDossier, selectedPatient);

        navToPatientList();
    }

    @FXML
    private void editPatientDossier() {
        System.out.println("editPatientDossier knop geklikt!");

        if (tfVoornaam.getText().equals("") || tfAchternaam.getText().equals("") || tfAdres.getText().equals("")
                || dpGeboorteDatum.getValue() ==  null || tfBSN.getText().equals("")) {
            lbAddErrorMessage.setText("Voornaam, achternaam, adres, geboortedatum en adres velden moeten ingevuld zijn!");
            return;
        }
        LocalDate geboorteDatum = dpGeboorteDatum.getValue();
        int bsn = Integer.parseInt(tfBSN.getText());

        patientDossier.setVoornaam(tfVoornaam.getText());
        patientDossier.setAchternaam(tfAchternaam.getText());
        patientDossier.setGeboorteDatum(geboorteDatum);
        patientDossier.setAdres(tfAdres.getText());
        patientDossier.setBSN(bsn);
        patientDossier.setMedischGeschiedenis(taMedisch.getText());
        patientDossier.setMedicatie(taMedicatie.getText());

        connector.editPatientDossier(patientDossier);

        navToPatientList();
    }

    public void setSelectedPatient(Patient selectedPatient) {
        this.selectedPatient = selectedPatient;

        String bsnString = String.valueOf(selectedPatient.getBSN());

        tfVoornaam.setText(this.selectedPatient.getVoornaam());
        tfAchternaam.setText(this.selectedPatient.getAchternaam());
        tfBSN.setText(bsnString);
    }

    public void getPatientDossier() {
        patientDossier = connector.getPatientenDossier(selectedPatient);

        if (patientDossier != null) {
            String bsnString = String.valueOf(patientDossier.getBSN());

            tfVoornaam.setText(patientDossier.getVoornaam());
            tfAchternaam.setText(patientDossier.getAchternaam());
            tfAdres.setText(patientDossier.getAdres());
            dpGeboorteDatum.setValue(patientDossier.getGeboorteDatum());
            tfBSN.setText(bsnString);
            taMedisch.setText(patientDossier.getMedischGeschiedenis());
            taMedicatie.setText(patientDossier.getMedicatie());
        }
    }


    public void setSelectedZiekenhuis(Ziekenhuis selectedZiekenhuis) {
        this.selectedZiekenhuis = selectedZiekenhuis;
    }

    @FXML
    public void navToPatientList() {
        System.out.println("navToPatientList knop geklikt!");

        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/scenes/patient-list-view.fxml"));
            Parent root = loader.load();

            Scene patienListScene = new Scene(root);
            patienListScene.getStylesheets().add(getClass().getResource("/stylesheets/mainStyle.css").toExternalForm());

            PatientListController patientListController = loader.getController();
            patientListController.setSelectedZiekenhuis(selectedZiekenhuis);

            stage.setScene(patienListScene);
            stage.show();
        }
        catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    @FXML
    public void navToPatientDossierDetail() {
        System.out.println("navToPatientDossierDetail knop geklikt!");
        String bsn = tfBSNDossier.getText();

        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/scenes/detail-patientdossier-view.fxml"));
            Parent root = loader.load();

            Scene patientDossierDetailScene = new Scene(root);
            patientDossierDetailScene.getStylesheets().add(getClass().getResource("/stylesheets/mainStyle.css").toExternalForm());

            PatientDossierDetailController patientDossierDetailController = loader.getController();
            patientDossierDetailController.getPatientDossierByBSN(bsn);

            stage.setScene(patientDossierDetailScene);
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
