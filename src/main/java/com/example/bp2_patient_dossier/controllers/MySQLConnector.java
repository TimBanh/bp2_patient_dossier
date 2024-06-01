package com.example.bp2_patient_dossier.controllers;

import com.example.bp2_patient_dossier.models.Ziekenhuis;

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
}
