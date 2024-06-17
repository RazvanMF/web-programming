package org.example.jsptemplate.crudoperationsex;

import java.sql.*;

public class CRUDTemplate {
    // THIS USES SQLITE
    // THESE ARE RAW FUNCTIONS NOT MEANT TO BE RUN, TAKE WHAT YOU NEED AND MAKE YOUR OWN FUNCTIONS
    // CAN'T CONFIRM 100% RELIABILITY, SINCE THESE WERE NEVER RUN, BUT IT *SHOULD* BE FINE

    String url = "jdbc:sqlite:C:\\Program Files\\Apache Software Foundation\\Tomcat 10.1_TomcatServer\\webapps\\JSPTemplate\\src\\main\\webexamdb.db";
    String driver = "org.sqlite.JDBC";
    private void SelectExample() throws SQLException {
        Connection connection = null;
        try {
            Class.forName(driver);
            connection = DriverManager.getConnection(url);
        }
        catch (Exception e) {
            throw new RuntimeException(e);
        }

        String sql = "SELECT id FROM user WHERE username = ? AND age = ?";
        PreparedStatement stmt = connection.prepareStatement(sql);
        stmt.setString(1, "username");
        stmt.setInt(2, 20);
        ResultSet rs = stmt.executeQuery();

        // push cursor to the 1st result, and retrieve that, in case you expect 1 row
        if (rs.next() == false) {
            return;
        }
        // OR
        // go through all rows, get all info, and depending on case, add to whatever list you have in there, eventually create a model etc. etc.
        while (rs.next()) {
            int intcol = rs.getInt("id");
            String stringcol = rs.getString("name");
            Date datecol = rs.getDate("date");
        }

        try {
            connection.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private void InsertExample() throws SQLException {
        Connection connection = null;
        try {
            Class.forName(driver);
            connection = DriverManager.getConnection(url);
        }
        catch (Exception e) {
            throw new RuntimeException(e);
        }

        String sql = "INSERT INTO users(username, password) VALUES(?, ?)";
        PreparedStatement stmt = connection.prepareStatement(sql);
        stmt.setString(1, "admin");
        stmt.setString(2, "admin");
        int ROWCOUNT = stmt.executeUpdate();

        try {
            connection.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private void UpdateExample() throws SQLException {
        Connection connection = null;
        try {
            Class.forName(driver);
            connection = DriverManager.getConnection(url);
        }
        catch (Exception e) {
            throw new RuntimeException(e);
        }

        String sql = "UPDATE users SET username = ?, password = ? WHERE id = ?";
        PreparedStatement stmt = connection.prepareStatement(sql);
        stmt.setString(1, "admin");
        stmt.setString(2, "admin");
        stmt.setInt(3, 1);
        int ROWCOUNT = stmt.executeUpdate();

        try {
            connection.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private void DeleteExample() throws SQLException {
        Connection connection = null;
        try {
            Class.forName(driver);
            connection = DriverManager.getConnection(url);
        }
        catch (Exception e) {
            throw new RuntimeException(e);
        }

        String sql = "DELETE FROM users WHERE id = ?";
        PreparedStatement stmt = connection.prepareStatement(sql);
        stmt.setInt(1, 1);
        int ROWCOUNT = stmt.executeUpdate();

        try {
            connection.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
