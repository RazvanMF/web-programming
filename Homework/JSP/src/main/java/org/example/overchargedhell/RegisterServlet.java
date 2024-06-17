package org.example.overchargedhell;

import com.mysql.cj.jdbc.MysqlDataSource;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;

@WebServlet(name = "RegisterServlet", value = "/RegisterServlet")
public class RegisterServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //perform validation stuff, redirect to userpage if true else to error if false
        String username = request.getParameter("regusername");
        String password = request.getParameter("regpassword");

        String url = "jdbc:sqlite:C:\\Program Files\\Apache Software Foundation\\Tomcat 10.1_TomcatServer\\webapps\\overchargedhell\\src\\main\\java\\org\\example\\overchargedhell\\help.db";
        Connection conn = null;
        try {
            Class.forName("org.sqlite.JDBC");
            conn = DriverManager.getConnection(url);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        String sql = "INSERT INTO user(username, password) VALUES(?, ?)";
        //        String sql = "SELECT COUNT(*) AS cnt FROM user WHERE username = '" + username + "' AND password = '" + password + "'";

        int modifiedRows = 0;
        try {
            conn = DriverManager.getConnection(url);
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, username);
            stmt.setString(2, password);
            modifiedRows = stmt.executeUpdate();

        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        try {
            conn.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        if (modifiedRows == 1) {
            response.sendRedirect("index.jsp");
        }
        else {
            response.sendRedirect("error.jsp");
        }
    }
}