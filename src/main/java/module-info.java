module com.example.bp2_patient_dossier {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.bp2_patient_dossier to javafx.fxml;
    exports com.example.bp2_patient_dossier;
}