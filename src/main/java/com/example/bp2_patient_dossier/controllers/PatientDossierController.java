package com.example.bp2_patient_dossier.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class PatientDossierController {
    private MySQLConnector mySQLConnector = new MySQLConnector();
    @FXML
    private Button hospitalButton;

    @FXML
    private Button caregiverButton;

    @FXML
    private Button patientButton;

    @FXML
    private void navNaarZiekenhuisLijstScene() {
        System.out.println("Ziekenhuis knop geklikt!");
        // Voeg hier de logica toe voor wat er moet gebeuren als het ziekenhuis knop wordt geklikt
    }

    @FXML
    private void navNaarZorgverlenerLijstScene() {
        System.out.println("Zorgverlener knop geklikt!");
        // Voeg hier de logica toe voor wat er moet gebeuren als de zorgverlener knop wordt geklikt
    }

    @FXML
    private void navNaarPatientScene() {
        System.out.println("Patiënt knop geklikt!");
        // Voeg hier de logica toe voor wat er moet gebeuren als de patiënt knop wordt geklikt
    }
}