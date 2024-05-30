package com.example.bp2_patient_dossier.models;

public class PatiëntDossier {
    private int id;
    private String voornaam;
    private String achternaam;
    private String geslacht;
    private int BSN;
    private String medischGeschiedenis;
    private String medicatie;

    public PatiëntDossier(int id, String voornaam, String achternaam, String geslacht, int BSN, String medischGeschiedenis, String medicatie) {
        this.id = id;
        this.voornaam = voornaam;
        this.achternaam = achternaam;
        this.geslacht = geslacht;
        this.BSN = BSN;
        this.medischGeschiedenis = medischGeschiedenis;
        this.medicatie = medicatie;
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

    public String getMedischGeschiedenis() {
        return medischGeschiedenis;
    }

    public void setMedischGeschiedenis(String medischGeschiedenis) {
        this.medischGeschiedenis = medischGeschiedenis;
    }

    public String getMedicatie() {
        return medicatie;
    }

    public void setMedicatie(String medicatie) {
        this.medicatie = medicatie;
    }

    @Override
    public String toString() {
        return "PatiëntDossier{" +
                "id=" + id +
                ", voornaam='" + voornaam + '\'' +
                ", achternaam='" + achternaam + '\'' +
                ", geslacht='" + geslacht + '\'' +
                ", BSN=" + BSN +
                ", medischGeschiedenis='" + medischGeschiedenis + '\'' +
                ", medicatie='" + medicatie + '\'' +
                '}';
    }
}
