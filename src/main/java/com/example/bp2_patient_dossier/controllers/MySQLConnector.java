package com.example.bp2_patient_dossier.controllers;

import com.example.bp2_patient_dossier.models.Patient;
import com.example.bp2_patient_dossier.models.Ziekenhuis;
import com.example.bp2_patient_dossier.models.Zorgverlener;

import java.sql.*;
import java.util.ArrayList;

public class MySQLConnector {
    private Connection connection;

    public MySQLConnector() {
        String user = "tim_patient_dossier";
        String password = "4ihS#g881";
        String connectionString = "jdbc:mysql://adainforma.tk/bp2_tim_patient_dossier";

        try {
            System.out.println(connectionString);
            connection = DriverManager.getConnection(connectionString, user, password);
            System.out.println("Db connected");
        }
        catch (SQLException ex) {
            System.out.println(ex);
        }
    }

    public ArrayList<Ziekenhuis> getZiekenhuizen() {
        String query = "Select * FROM Ziekenhuis";

        ArrayList<Ziekenhuis> ziekenhuisList = new ArrayList<>();

        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);

            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String naam = resultSet.getString("naam");
                String locatie = resultSet.getString("locatie");

                Ziekenhuis ziekenhuis = new Ziekenhuis(id, naam, locatie);
                ziekenhuisList.add(ziekenhuis);

            }

            resultSet.close();
            statement.close();
        }
        catch (SQLException ex) {
            ex.printStackTrace();
        }
        return ziekenhuisList;
    }

    public void addZiekenhuis(Ziekenhuis ziekenhuis) {
        String query = "INSERT INTO Ziekenhuis (naam, locatie) VALUES (?, ?)";

        if (ziekenhuis.getNaam().equals("") || ziekenhuis.getLocatie().equals("")) {
            System.out.println("Naam en locatie moeten ingevuld zijn");
            return;
        }

        try (PreparedStatement statement = connection.prepareStatement(query)){
            statement.setString(1, ziekenhuis.getNaam());
            statement.setString(2, ziekenhuis.getLocatie());

            statement.executeUpdate();

            System.out.println("Ziekenhuis toegevoegd aan database.");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public ArrayList<Zorgverlener> getZorgverleners(Ziekenhuis ziekenhuis) {
        String query = "SELECT * FROM Zorgverlener WHERE Ziekenhuisid = ?";

        ArrayList<Zorgverlener> zorgverlenerList = new ArrayList<>();

        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, ziekenhuis.getId());
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    int id = resultSet.getInt("id");
                    String voornaam = resultSet.getString("voornaam");
                    String achternaam = resultSet.getString("achternaam");
                    String functie = resultSet.getString("functie");

                    Zorgverlener zorgverlener = new Zorgverlener(id, voornaam, achternaam, functie);
                    zorgverlenerList.add(zorgverlener);
                }
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return zorgverlenerList;
    }

    public ArrayList<Patient> getPatienten(Ziekenhuis ziekenhuis) {
        String query = "SELECT * FROM Patiënt WHERE Ziekenhuisid = ?";

        ArrayList<Patient> patienten = new ArrayList<>();

        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, ziekenhuis.getId());
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    int id = resultSet.getInt("id");
                    String voornaam = resultSet.getString("voornaam");
                    String achternaam = resultSet.getString("achternaam");
                    String geslacht = resultSet.getString("Geslacht");
                    int bsn = resultSet.getInt("BSN");

                    Patient patient = new Patient(id, voornaam, achternaam, geslacht, bsn);
                    patienten.add(patient);
                }
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return patienten;
    }

    public void addZorgverlener(Zorgverlener zorgverlener, Ziekenhuis ziekenhuis) {
        String query = "INSERT INTO Zorgverlener (voornaam, achternaam, functie, Ziekenhuisid) VALUES (?, ?, ?, ?)";

        if (zorgverlener.getVoornaam().equals("") || zorgverlener.getAchternaam().equals("") || zorgverlener.getFunctie().equals("")) {
            System.out.println("Voornaam, Achternaam en Functie moeten ingevuld zijn");
            return;
        }

        try (PreparedStatement statement = connection.prepareStatement(query)){
            statement.setString(1, zorgverlener.getVoornaam());
            statement.setString(2, zorgverlener.getAchternaam());
            statement.setString(3, zorgverlener.getFunctie());
            statement.setInt(4, ziekenhuis.getId());

            statement.executeUpdate();

            System.out.println("Zorgverlener toegevoegd aan database.");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void addPatient(Patient patient, Ziekenhuis ziekenhuis) {
        String query = "INSERT INTO Patiënt (voornaam, achternaam, geslacht, BSN, Ziekenhuisid) VALUES (?, ?, ?, ?, ?)";

        if (patient.getVoornaam().equals("") || patient.getAchternaam().equals("") || patient.getGeslacht().equals("")) {
            System.out.println("Alle velden moeten ingevuld zijn");
            return;
        }

        try (PreparedStatement statement = connection.prepareStatement(query)){
            statement.setString(1, patient.getVoornaam());
            statement.setString(2, patient.getAchternaam());
            statement.setString(3, patient.getGeslacht());
            statement.setInt(4, patient.getBSN());
            statement.setInt(5, ziekenhuis.getId());

            statement.executeUpdate();

            System.out.println("Patiënt toegevoegd aan database.");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    public void editZorgverlener(Zorgverlener zorgverlener) {
        String query = "UPDATE Zorgverlener SET voornaam = ?, achternaam = ?, functie = ? WHERE id = ?";

        if (zorgverlener.getVoornaam().equals("") || zorgverlener.getAchternaam().equals("") || zorgverlener.getFunctie().equals("")) {
            System.out.println("Voornaam, Achternaam en Functie moeten ingevuld zijn");
            return;
        }

        try (PreparedStatement statement = connection.prepareStatement(query)){
            statement.setString(1, zorgverlener.getVoornaam());
            statement.setString(2, zorgverlener.getAchternaam());
            statement.setString(3, zorgverlener.getFunctie());
            statement.setInt(4, zorgverlener.getId());

            int rowsUpdated = statement.executeUpdate();

            if (rowsUpdated > 0) {
                System.out.println("Zorgverlener bijgewerkt in de database.");
            } else {
                System.out.println("Geen zorgverlener gevonden met ID: " + zorgverlener.getId());
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void editPatient(Patient patient) {
        String query = "UPDATE Patiënt SET voornaam = ?, achternaam = ?, geslacht = ?, BSN = ? WHERE id = ?";

        if (patient.getVoornaam().equals("") || patient.getAchternaam().equals("") || patient.getGeslacht().equals("")) {
            System.out.println("Alle velden moeten ingevuld zijn");
            return;
        }

        try (PreparedStatement statement = connection.prepareStatement(query)){
            statement.setString(1, patient.getVoornaam());
            statement.setString(2, patient.getAchternaam());
            statement.setString(3, patient.getGeslacht());
            statement.setInt(4, patient.getBSN());
            statement.setInt(5, patient.getId());

            int rowsUpdated = statement.executeUpdate();

            if (rowsUpdated > 0) {
                System.out.println("Zorgverlener bijgewerkt in de database.");
            } else {
                System.out.println("Geen zorgverlener gevonden met ID: " + patient.getId());
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void deleteZorgverlener(Zorgverlener zorgverlener) {
        String checkQuery = "SELECT COUNT(*) AS count FROM Patiënt_Zorgverlener WHERE Zorgverlenerid = ?";
        String deleteLinkQuery = "DELETE FROM Patiënt_Zorgverlener WHERE Zorgverlenerid = ?";
        String deleteZorgverlenerQuery = "DELETE FROM Zorgverlener WHERE id = ?";

        try (PreparedStatement checkStatement = connection.prepareStatement(checkQuery)) {
            checkStatement.setInt(1, zorgverlener.getId());
            ResultSet resultSet = checkStatement.executeQuery();
            if (resultSet.next()) {
                int count = resultSet.getInt("count");
                if (count > 0) {
                    // Verwijder koppelingen
                    try (PreparedStatement deleteLinkStatement = connection.prepareStatement(deleteLinkQuery)) {
                        deleteLinkStatement.setInt(1, zorgverlener.getId());
                        deleteLinkStatement.executeUpdate();
                    }
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Fout bij controleren van koppelingen", e);
        }

        // Verwijder zorgverlener
        try (PreparedStatement deleteZorgverlenerStatement = connection.prepareStatement(deleteZorgverlenerQuery)) {
            deleteZorgverlenerStatement.setInt(1, zorgverlener.getId());
            int rowsDeleted = deleteZorgverlenerStatement.executeUpdate();
            if (rowsDeleted > 0) {
                System.out.println("Zorgverlener verwijderd");
            } else {
                System.out.println("Zorgverlener niet gevonden");
            }
        } catch (SQLException e) {
            throw new RuntimeException("Fout bij verwijderen van zorgverlener", e);
        }
    }

    public void deletePatient(Patient patient) {
        String checkDossierQuery = "SELECT COUNT(*) AS count FROM Patiëntdossier WHERE PatiëntId = ?";
        String deleteDossierQuery = "DELETE FROM Patiëntdossier WHERE Patiëntid = ?";
        String checkLinkQuery = "SELECT COUNT(*) AS count FROM Patiënt_Zorgverlener WHERE Patiëntid = ?";
        String deleteLinkQuery = "DELETE FROM Patiënt_Zorgverlener WHERE Patiëntid = ?";
        String deletePatientQuery = "DELETE FROM Patiënt WHERE id = ?";

        System.out.println(checkDossierQuery);
        System.out.println(deleteDossierQuery);

        try (PreparedStatement checkDossierStatement = connection.prepareStatement(checkDossierQuery)) {
            checkDossierStatement.setInt(1, patient.getId());
            ResultSet dossierResultSet = checkDossierStatement.executeQuery();
            if (dossierResultSet.next()) {
                int dossierCount = dossierResultSet.getInt("count");
                if (dossierCount > 0) {
                    // Verwijder patiëntdossier
                    try (PreparedStatement deleteDossierStatement = connection.prepareStatement(deleteDossierQuery)) {
                        deleteDossierStatement.setInt(1, patient.getId());
                        deleteDossierStatement.executeUpdate();
                        System.out.println("Patiëntdossier verwijderd");
                    }
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Fout bij verwijderen van patiëntdossier", e);
        }

        try (PreparedStatement checkLinkStatement = connection.prepareStatement(checkLinkQuery)) {
            checkLinkStatement.setInt(1, patient.getId());
            ResultSet resultSet = checkLinkStatement.executeQuery();
            if (resultSet.next()) {
                int count = resultSet.getInt("count");
                if (count > 0) {
                    // Verwijder koppelingen
                    try (PreparedStatement deleteLinkStatement = connection.prepareStatement(deleteLinkQuery)) {
                        deleteLinkStatement.setInt(1, patient.getId());
                        deleteLinkStatement.executeUpdate();
                    }
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Fout bij controleren van koppelingen", e);
        }

        // Verwijder patiënt
        try (PreparedStatement deletePatientStatement = connection.prepareStatement(deletePatientQuery)) {
            deletePatientStatement.setInt(1, patient.getId());
            int rowsDeleted = deletePatientStatement.executeUpdate();
            if (rowsDeleted > 0) {
                System.out.println("Patiënt verwijderd");
            } else {
                System.out.println("Patiënt niet gevonden");
            }
        } catch (SQLException e) {
            throw new RuntimeException("Fout bij verwijderen van Patiënt", e);
        }
    }
}
