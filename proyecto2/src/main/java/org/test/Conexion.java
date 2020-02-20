package org.test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexion {
    String url = "jdbc:mysql://localhost:3306/factura_db?serverTimezone=UTC"; //
    String user = "root";
    String pass = "1234";
    Connection conn;


    public Connection conexionmysql (){

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection(url, user, pass);
            System.out.println("Connection Successful");

        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return conn;
    }
}
