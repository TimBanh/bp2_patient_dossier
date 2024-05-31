package com.example.bp2_patient_dossier.controllers;

import java.sql.*;

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
}
