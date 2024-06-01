package com.example.bp2_patient_dossier;

import com.example.bp2_patient_dossier.controllers.PatientDossierController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class PatientDossierApp extends Application {
    private static Stage mainStage;
    public static int globalWidth = 1000;
    public static int globalHeight = 600;
    @Override
    public void start(Stage stage) throws IOException {
        mainStage = stage;
        FXMLLoader fxmlLoader = new FXMLLoader(PatientDossierApp.class.getResource("/scenes/start-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        scene.getStylesheets().add(getClass().getResource("/stylesheets/mainStyle.css").toExternalForm());
        mainStage.setTitle("Elektrisch patiënt dossier!");
        mainStage.setScene(scene);
        mainStage.show();

    }

    public static Stage getMainStage() {
        return mainStage;
    }

    public static void main(String[] args) {
        launch();
    }
}