package org.test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexion {
    String url = "jdbc:postgresql://192.168.43.167:5432/ProyectoEmpresa?serverTimezone=UTC"; //
    String user = "postgres";
    String pass = "1234";
    Connection conn;


    public Connection conexionmysql (){

        try {
            Class.forName("org.postgresql.Driver");
            conn = DriverManager.getConnection(url, user, pass);
            System.out.println("Connection Successful");

        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return conn;
    }
}
