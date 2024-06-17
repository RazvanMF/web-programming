package org.example.overchargedhell;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.example.overchargedhell.models.Profile;

import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "EditUserServlet", value = "/EditUserServlet")
public class EditUserServlet extends HttpServlet {

    List<Profile> profiles = new ArrayList<>();
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String name = request.getParameter("name");
        String id = request.getParameter("id");
        String userid = request.getParameter("userid");
        String email = request.getParameter("email");
        String picture = request.getParameter("picture");
        String age = request.getParameter("age");

        updateProfile(name, email, picture, age, id, userid);

        response.sendRedirect("UserServlet");
    }

    private void updateProfile(String name, String email, String picture, String age, String id, String userid) {
        String url = "jdbc:sqlite:C:\\Program Files\\Apache Software Foundation\\Tomcat 10.1_TomcatServer\\webapps\\overchargedhell\\src\\main\\java\\org\\example\\overchargedhell\\help.db";
        Connection conn = null;
        try {
            Class.forName("org.sqlite.JDBC");
            conn = DriverManager.getConnection(url);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        String sql = "UPDATE profile SET name = ?, email = ?, picture = ?, age = ? WHERE id = ?";

        int modifiedRows = 0;
        try {
            conn = DriverManager.getConnection(url);
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, name);
            stmt.setString(2, email);
            stmt.setString(3, picture);
            stmt.setInt(4, Integer.parseInt(age));
            stmt.setInt(5, Integer.parseInt(id));
            modifiedRows = stmt.executeUpdate();

        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        try {
            conn.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}