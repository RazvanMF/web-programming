package org.example.overchargedhell;

import com.mysql.cj.jdbc.MysqlDataSource;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import java.io.IOException;
import java.sql.*;


@WebServlet(name = "LoginServlet", value = "/LoginServlet")
public class LoginServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        //perform validation stuff, redirect to userpage if true else to error if false
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        String url = "jdbc:sqlite:C:\\Program Files\\Apache Software Foundation\\Tomcat 10.1_TomcatServer\\webapps\\overchargedhell\\src\\main\\java\\org\\example\\overchargedhell\\help.db";
        Connection conn = null;
        try {
            Class.forName("org.sqlite.JDBC");
            conn = DriverManager.getConnection(url);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
//        finally {
//            try {
//                assert conn != null;
//                conn.close();
//            } catch (Exception e) {
//                throw new RuntimeException(e);
//            }
//        }

        int id = selectUser(username, password);
        try {
            conn.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        if (id != 0) {
            response.sendRedirect("UserServlet" + "?id=" + id);
        }
        else {
            response.sendRedirect("error.jsp");
        }
    }

    private int selectUser(String username, String password) {
        String sql = "SELECT id FROM user WHERE username = ? AND password = ?";
        //        String sql = "SELECT COUNT(*) AS cnt FROM user WHERE username = '" + username + "' AND password = '" + password + "'";
        String url = "jdbc:sqlite:C:\\Program Files\\Apache Software Foundation\\Tomcat 10.1_TomcatServer\\webapps\\overchargedhell\\src\\main\\java\\org\\example\\overchargedhell\\help.db";

        try{
            Connection conn = DriverManager.getConnection(url);
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, username);
            stmt.setString(2, password);
            ResultSet rs = stmt.executeQuery();
            int value = rs.getInt("id");

            try {
                conn.close();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }

            return value;

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}