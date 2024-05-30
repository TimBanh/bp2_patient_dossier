package com.example.bp2_patient_dossier.models;

public class Patient {
    private int id;
    private String voornaam;
    private String achternaam;
    private String geslacht;
    private int BSN;

    public Patient(int id, String voornaam, String achternaam, String geslacht, int BSN) {
        this.id = id;
        this.voornaam = voornaam;
        this.achternaam = achternaam;
        this.geslacht = geslacht;
        this.BSN = BSN;
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

    public String getGeslacht() {
        return geslacht;
    }

    public void setGeslacht(String geslacht) {
        this.geslacht = geslacht;
    }

    public int getBSN() {
        return BSN;
    }

    public void setBSN(int BSN) {
        this.BSN = BSN;
    }

    @Override
    public String toString() {
        return "Patient{" +
                "id=" + id +
                ", voornaam='" + voornaam + '\'' +
                ", achternaam='" + achternaam + '\'' +
                ", geslacht='" + geslacht + '\'' +
                ", BSN=" + BSN +
                '}';
    }
}
