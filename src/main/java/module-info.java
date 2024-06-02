module com.example.bp2_patient_dossier {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;


    opens com.example.bp2_patient_dossier to javafx.fxml;
    exports com.example.bp2_patient_dossier;
    exports com.example.bp2_patient_dossier.controllers;
    opens com.example.bp2_patient_dossier.controllers to javafx.fxml;
}