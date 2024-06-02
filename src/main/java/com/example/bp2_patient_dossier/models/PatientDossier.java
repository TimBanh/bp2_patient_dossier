package com.example.bp2_patient_dossier.models;

import java.time.LocalDate;

public class PatientDossier {
    private int id;
    private String voornaam;
    private String achternaam;
    private LocalDate geboorteDatum;
    private String adres;
    private int BSN;
    private String medischGeschiedenis;
    private String medicatie;

    public PatientDossier(int id, String voornaam, String achternaam, LocalDate geboorteDatum, String adres, int BSN, String medischGeschiedenis, String medicatie) {
        this.id = id;
        this.voornaam = voornaam;
        this.achternaam = achternaam;
        this.geboorteDatum = geboorteDatum;
        this.adres = adres;
        this.BSN = BSN;
        this.medischGeschiedenis = medischGeschiedenis;
        this.medicatie = medicatie;
    }

    public PatientDossier(String voornaam, String achternaam, LocalDate geboorteDatum, String adres, int BSN, String medischGeschiedenis, String medicatie) {
        this.voornaam = voornaam;
        this.achternaam = achternaam;
        this.geboorteDatum = geboorteDatum;
        this.adres = adres;
        this.BSN = BSN;
        this.medischGeschiedenis = medischGeschiedenis;
        this.medicatie = medicatie;
    }

    public PatientDossier(String voornaam, String achternaam, LocalDate geboorteDatum, String adres, int BSN) {
        this.voornaam = voornaam;
        this.achternaam = achternaam;
        this.geboorteDatum = geboorteDatum;
        this.adres = adres;
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

    public LocalDate getGeboorteDatum() {
        return geboorteDatum;
    }

    public void setGeboorteDatum(LocalDate geboorteDatum) {
        this.geboorteDatum = geboorteDatum;
    }

    public String getAdres() {
        return adres;
    }

    public void setAdres(String adres) {
        this.adres = adres;
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
                ", geslacht='" + geboorteDatum.toString() + '\'' +
                ", BSN=" + BSN +
                ", medischGeschiedenis='" + medischGeschiedenis + '\'' +
                ", medicatie='" + medicatie + '\'' +
                '}';
    }
}
