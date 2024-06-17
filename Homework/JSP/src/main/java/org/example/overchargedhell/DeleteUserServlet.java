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

@WebServlet(name = "DeleteUserServlet", value = "/DeleteUserServlet")
public class DeleteUserServlet extends HttpServlet {

    List<Profile> profiles = new ArrayList<>();
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String id = request.getParameter("id");
        deleteProfile(Integer.parseInt(id));
        response.sendRedirect("UserServlet");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    }

    private void deleteProfile(int id) {
        String url = "jdbc:sqlite:C:\\Program Files\\Apache Software Foundation\\Tomcat 10.1_TomcatServer\\webapps\\overchargedhell\\src\\main\\java\\org\\example\\overchargedhell\\help.db";
        Connection conn = null;
        try {
            Class.forName("org.sqlite.JDBC");
            conn = DriverManager.getConnection(url);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        String sql = "DELETE FROM profile WHERE id = ?";

        int modifiedRows = 0;
        try {
            conn = DriverManager.getConnection(url);
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, id);
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