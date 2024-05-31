package com.example.bp2_patient_dossier.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.ListView;

public class ZiekenhuisController {
    @FXML
    private ListView<String> ziekenhuisList;

    @FXML
    private void addZiekenhuis() {
        // Voeg hier logica toe om een nieuw ziekenhuis toe te voegen
        System.out.println("Ziekenhuis toevoegen knop geklikt!");
    }
}
