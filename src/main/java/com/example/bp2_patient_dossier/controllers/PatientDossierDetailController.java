package com.example.bp2_patient_dossier.controllers;

import com.example.bp2_patient_dossier.PatientDossierApp;
import com.example.bp2_patient_dossier.models.PatientDossier;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

public class PatientDossierDetailController {
    private Stage stage;
    private MySQLConnector connector;
    private PatientDossier patientDossier;
    @FXML private TextField tfDetailVoornaam;
    @FXML private TextField tfDetailAchternaam;
    @FXML private DatePicker dpDetailGeboorteDatum;
    @FXML private TextField tfDetailBSN;
    @FXML private TextField tfDetailAdres;
    @FXML private TextArea taDetailMedisch;
    @FXML private TextArea taDetailMedicatie;

    public PatientDossierDetailController() {
        stage = PatientDossierApp.getMainStage();
        connector = new MySQLConnector();
    }

    public void getPatientDossierByBSN(String bsn) {

        patientDossier = connector.getPatientdossierByBSN(bsn);

        if (patientDossier != null) {
            String bsnString = String.valueOf(patientDossier.getBSN());

            tfDetailVoornaam.setText(patientDossier.getVoornaam());
            tfDetailAchternaam.setText(patientDossier.getAchternaam());
            tfDetailAdres.setText(patientDossier.getAdres());
            dpDetailGeboorteDatum.setValue(patientDossier.getGeboorteDatum());
            tfDetailBSN.setText(bsnString);
            taDetailMedisch.setText(patientDossier.getMedischGeschiedenis());
            taDetailMedicatie.setText(patientDossier.getMedicatie());

            tfDetailVoornaam.setDisable(true);
            tfDetailAchternaam.setDisable(true);
            tfDetailAdres.setDisable(true);
            dpDetailGeboorteDatum.setDisable(true);
            tfDetailBSN.setDisable(true);
            taDetailMedisch.setDisable(true);
            taDetailMedicatie.setDisable(true);
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
