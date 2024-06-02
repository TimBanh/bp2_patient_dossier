package com.example.bp2_patient_dossier.models;

import java.util.ArrayList;

public class Zorgverlener {
    private int id;
    private String voornaam;
    private String achternaam;
    private String functie;
    private ArrayList<Patient> patienten;

    public Zorgverlener(int id, String voornaam, String achternaam, String functie) {
        this.id = id;
        this.voornaam = voornaam;
        this.achternaam = achternaam;
        this.functie = functie;
        this.patienten = new ArrayList<>();
    }

    public Zorgverlener(String voornaam, String achternaam, String functie) {
        this.voornaam = voornaam;
        this.achternaam = achternaam;
        this.functie = functie;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getVoornaam() {
        return voornaam;
    }

    public void setVoornaam(String voornaam) {
        this.voornaam = voornaam;
    }

    public String getAchternaam() {
        return achternaam;
    }

    public void setAchternaam(String achternaam) {
        this.achternaam = achternaam;
    }

    public String getFunctie() {
        return functie;
    }

    public void setFunctie(String functie) {
        this.functie = functie;
    }

    public ArrayList<Patient> getPatienten() {
        return patienten;
    }

    public void setPatienten(ArrayList<Patient> patienten) {
        this.patienten = patienten;
    }

    @Override
    public String toString() {
        return "Naam: " + voornaam + " " + achternaam + " Functie: " + functie;
    }
}
