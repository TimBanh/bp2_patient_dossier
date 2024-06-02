package com.example.bp2_patient_dossier.controllers;

import com.example.bp2_patient_dossier.PatientDossierApp;
import com.example.bp2_patient_dossier.models.Patient;
import com.example.bp2_patient_dossier.models.Ziekenhuis;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class PatientDossierFormController {
    private Patient selectedPatient;
    private Ziekenhuis selectedZiekenhuis;
    private Stage stage;
    private MySQLConnector connector;
    @FXML
    private TextField tfVoornaam;
    @FXML private TextField tfAchternaam;
    @FXML private TextField tfGeslacht;
    @FXML private TextField tfBSN;

    public PatientDossierFormController() {
        stage = PatientDossierApp.getMainStage();
        connector = new MySQLConnector();
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
}
