package com.example.bp2_patient_dossier.controllers;

import com.example.bp2_patient_dossier.PatientDossierApp;
import com.example.bp2_patient_dossier.models.Patient;
import com.example.bp2_patient_dossier.models.PatientDossier;
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
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;

public class ZorgverlenerFormController {
    @FXML private TextField tfVoornaam;
    @FXML private TextField tfAchternaam;
    @FXML private TextField tfFunctie;
    @FXML private Label lbErrorMessage;
    @FXML private Label lbEditErrorMessage;
    @FXML private ListView<Patient> patientenList;
    private Zorgverlener selectedZorgverlener;
    private Ziekenhuis selectedZiekenhuis;
    private Patient selectedPatient;
    private ArrayList<Patient> patientArrayList;
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

        if (voornaam.equals("") || achternaam.equals("") || functie.equals("")) {
            lbErrorMessage.setText("Alle velden moet ingevuld zijn!");
            return;
        }

        Zorgverlener zorgverlener = new Zorgverlener(voornaam, achternaam, functie);

        connector.addZorgverlener(zorgverlener, selectedZiekenhuis);

        navToZorgverlenerList();
    }

    @FXML
    private void setSelectedPatient() {
        // Haal het geselecteerde Patiënt op uit de lijst
        selectedPatient = patientenList.getSelectionModel().getSelectedItem();

        // Controleer of er een Patiënt is geselecteerd
        if (selectedPatient != null) {
            // Voer acties uit op basis van het geselecteerde Patiënt
            System.out.println("Geselecteerd ziekenhuis: " + selectedPatient.getVoornaam() + " " + selectedPatient.getAchternaam());
        } else {
            System.out.println("Geen ziekenhuis geselecteerd.");
            lbEditErrorMessage.setText("Geen patiënt geselecteerd");
        }
    }

    @FXML
    private void editZorgverlener() throws IOException {
        System.out.println("editZorgverlener knop geklikt!");

        selectedZorgverlener.setVoornaam(tfVoornaam.getText());
        selectedZorgverlener.setAchternaam(tfAchternaam.getText());
        selectedZorgverlener.setFunctie(tfFunctie.getText());

        if (selectedZorgverlener.getVoornaam().equals("") || selectedZorgverlener.getVoornaam().equals("") || selectedZorgverlener.getFunctie().equals("")) {
            lbEditErrorMessage.setText("Alle velden moet ingevuld zijn!");
            return;
        }

        Zorgverlener zorgverlener = selectedZorgverlener;

        connector.editZorgverlener(zorgverlener);

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

    public void getPatientenByZorgverlener() {
        patientArrayList = connector.getPatientenByZorgverlener(selectedZorgverlener);

        if (patientArrayList.isEmpty()){
            return;
        }

        ObservableList<Patient> patientObservableList = FXCollections.observableArrayList(patientArrayList);
        patientenList.setItems(patientObservableList);
    }

    public void deleteKoppelPatientZorgverlener() {
        if (selectedPatient == null) {
            lbErrorMessage.setText("Selecteer een patiënt");
            return;
        }

        connector.deleteKoppelPatientZorgverlener(selectedPatient, selectedZorgverlener);

        getPatientenByZorgverlener();
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

    @FXML
    private void navToKoppelPatient() {
        System.out.println("Home knop geklikt!");
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/scenes/connect-patient-zorgverlener-view.fxml"));
            Parent root = loader.load();

            Scene startScene = new Scene(root);
            startScene.getStylesheets().add(getClass().getResource("/stylesheets/mainStyle.css").toExternalForm());

            KoppelController koppelController = loader.getController();
            koppelController.setSelectedZorgverlener(selectedZorgverlener);
            koppelController.setSelectedZiekenhuis(selectedZiekenhuis);

            stage.setScene(startScene);
            stage.show();
        }
        catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}
