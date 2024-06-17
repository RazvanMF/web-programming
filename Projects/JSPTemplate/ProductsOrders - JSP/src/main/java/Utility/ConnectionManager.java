package Utility;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionManager {
    static String user="root";
    static String password="swaggaws1";
    static String database="productsordersjsp";
    static String url="jdbc:mysql://localhost:3306/";
    static String driver="com.mysql.jdbc.Driver";

    static public Connection getConnection() throws SQLException {
        return DriverManager.getConnection(url+database, user, password);
    }
}
