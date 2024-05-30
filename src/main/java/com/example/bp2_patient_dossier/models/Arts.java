package com.example.bp2_patient_dossier.models;

import java.util.ArrayList;

public class Arts extends Zorgverlener{

    public Arts(int id, String voornaam, String achternaam, String functie, ArrayList<Patient> patienten) {
        super(id, voornaam, achternaam, functie, patienten);
    }
}
