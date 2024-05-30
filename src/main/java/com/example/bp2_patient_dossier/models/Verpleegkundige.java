package com.example.bp2_patient_dossier.models;

import java.util.ArrayList;

public class Verpleegkundige extends Zorgverlener{
    public Verpleegkundige(int id, String voornaam, String achternaam, String functie, ArrayList<Patient> patienten) {
        super(id, voornaam, achternaam, functie, patienten);
    }
}
