package com.example.bp2_patient_dossier.controllers;

import com.example.bp2_patient_dossier.models.Ziekenhuis;
import com.example.bp2_patient_dossier.models.Zorgverlener;

import java.sql.*;
import java.util.ArrayList;

public class MySQLConnector {
    private Connection connection;

    public MySQLConnector() {
//        Class.forName("com.mysql.cj.jdbc.Driver");

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
}
