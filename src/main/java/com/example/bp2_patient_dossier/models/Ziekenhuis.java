package com.example.bp2_patient_dossier.models;

import java.util.ArrayList;

public class Ziekenhuis {
    private int id;
    private String naam;
    private String locatie;
    private ArrayList<Patient> patienten;
    private ArrayList<Zorgverlener> zorgverleners;

    public Ziekenhuis(int id, String naam, String locatie) {
        this.id = id;
        this.naam = naam;
        this.locatie = locatie;
        this.patienten = new ArrayList<>();
        this.zorgverleners = new ArrayList<>();
    }

    public Ziekenhuis (String naam, String locatie) {
        this.naam = naam;
        this.locatie = locatie;
        this.patienten = new ArrayList<>();
        this.zorgverleners = new ArrayList<>();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNaam() {
        return naam;
    }

    public void setNaam(String naam) {
        this.naam = naam;
    }

    public String getLocatie() {
        return locatie;
    }

    public void setLocatie(String locatie) {
        this.locatie = locatie;
    }

    public ArrayList<Patient> getPatienten() {
        return patienten;
    }

    public void setPatienten(ArrayList<Patient> patienten) {
        this.patienten = patienten;
    }

    public ArrayList<Zorgverlener> getZorgverleners() {
        return zorgverleners;
    }

    public void setZorgverleners(ArrayList<Zorgverlener> zorgverleners) {
        this.zorgverleners = zorgverleners;
    }

    @Override
    public String toString() {
        return "Naam: " + naam + " Locatie: " + locatie;
    }
}
