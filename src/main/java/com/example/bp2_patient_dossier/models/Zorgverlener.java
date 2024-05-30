package com.example.bp2_patient_dossier.models;

import java.util.ArrayList;

public class Zorgverlener {
    private int id;
    private String voornaam;
    private String achternaam;
    private String functie;
    private ArrayList<Patient> patienten;

    public Zorgverlener(int id, String voornaam, String achternaam, String functie, ArrayList<Patient> patienten) {
        this.id = id;
        this.voornaam = voornaam;
        this.achternaam = achternaam;
        this.functie = functie;
        this.patienten = patienten;
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
        return "Zorgverlener{" +
                "id=" + id +
                ", voornaam='" + voornaam + '\'' +
                ", achternaam='" + achternaam + '\'' +
                ", functie='" + functie + '\'' +
                '}';
    }
}
