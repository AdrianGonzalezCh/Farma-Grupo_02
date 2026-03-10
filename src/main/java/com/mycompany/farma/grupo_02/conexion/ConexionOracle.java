package com.mycompany.farma.grupo_02.conexion;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexionOracle {

    private static final String URL = "jdbc:oracle:thin:@localhost:1521/orcl";
    private static final String USER = "FARMA";
    private static final String PASSWORD = "Farma123";

    private ConexionOracle() {
    }

    public static Connection conectar() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }
}
