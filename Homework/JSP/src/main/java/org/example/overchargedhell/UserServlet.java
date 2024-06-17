package org.example.overchargedhell;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.example.overchargedhell.models.Profile;

@WebServlet(name = "UserServlet", value = "/UserServlet")
public class UserServlet extends HttpServlet {

    List<Profile> profiles = new ArrayList<>();
    static int id;
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            id = Integer.parseInt(request.getParameter("id"));
        }
        catch (Exception ignored) {}
        profiles = selectProfiles(id);

        // SEARCH BY NAME
        try {
            String nameparam = request.getParameter("searchername");
            if (nameparam != "")
                profiles = profiles.stream().filter(profile -> profile.name.contains(nameparam)).collect(Collectors.toList());
        }
        catch (Exception ignored) {}

        // SEARCH BY EMAIL
        try {
            String emailparam = request.getParameter("searcheremail");
            if (emailparam != "")
                profiles = profiles.stream().filter(profile -> profile.email.contains(emailparam)).collect(Collectors.toList());
        }
        catch (Exception ignored) {}


        response.setContentType("text/html");
        // Hello
        java.io.PrintWriter out = response.getWriter();
        out.println("<html><body>");
        out.println("<form action=\"index.jsp\"><input type=\"submit\" value=\"GO BACK\"></form>");
        out.println("<form action=\"add.jsp\" method=\"post\"><input type=\"submit\" value=\"ADD A PROFILE\"></form>");
        out.println("<form action=\"UserServlet\" method=\"get\">" +
                "<input type=\"hidden\" name=\"id\" value=\"" + id + "\">" +
                "<input type=\"text\" placeholder=\"SEARCH BY NAME\" name=\"searchername\"/>" +
                "<input type=\"text\" placeholder=\"SEARCH BY EMAIL\" name=\"searcheremail\"/>" +
                "<input type=\"submit\" value=\"SEARCH!\">" +
                "</form>");
        profiles.forEach(profile -> {
            out.println("<hr/>");
            out.println("<div>");
                out.println("<img src=\"" + profile.picture + "\" width=100 height=100/>");
                out.println("<h3>" + profile.name + "</h3>");
                out.println("<p>EMAIL: " + profile.email + "</h3>");
                out.println("<p>AGE: " + profile.age + "</p>");
                out.println("<form action=\"edit.jsp\">" +
                        "<input type=\"hidden\" name=\"userid\" value=\"" + id + "\">" +
                        "<input type=\"hidden\" name=\"id\" value=\"" + profile.id + "\">" +
                        "<input type=\"submit\" value=\"EDIT THIS\"></form>");
                out.println("<form action=\"DeleteUserServlet\">" +
                        "<input type=\"hidden\" name=\"id\" value=\"" + id + "\">" +
                        "<input type=\"submit\" value=\"DELETE THIS\"></form>");
            out.println("</div>");
        });
        out.println("</body></html>");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String name = request.getParameter("name");
        String email = request.getParameter("email");
        String picture = request.getParameter("picture");
        String age = request.getParameter("age");

        insertProfile(name, email, picture, age);

        response.sendRedirect("UserServlet");
    }

    protected void goBack(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.sendRedirect("index.jsp");
    }

    private void insertProfile(String name, String email, String picture, String age) {
        //perform validation stuff, redirect to userpage if true else to error if false

        String url = "jdbc:sqlite:C:\\Program Files\\Apache Software Foundation\\Tomcat 10.1_TomcatServer\\webapps\\overchargedhell\\src\\main\\java\\org\\example\\overchargedhell\\help.db";
        Connection conn = null;
        try {
            Class.forName("org.sqlite.JDBC");
            conn = DriverManager.getConnection(url);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        String sql = "INSERT INTO profile(name, email, picture, age, userid) VALUES(?, ?, ?, ?, ?)";

        int modifiedRows = 0;
        try {
            conn = DriverManager.getConnection(url);
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, name);
            stmt.setString(2, email);
            stmt.setString(3, picture);
            stmt.setInt(4, Integer.parseInt(age));
            stmt.setInt(5, id);
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

    private List<Profile> selectProfiles(int id) {
        String sql = "SELECT * FROM profile WHERE userid = ?";
        //        String sql = "SELECT COUNT(*) AS cnt FROM user WHERE username = '" + username + "' AND password = '" + password + "'";
        String url = "jdbc:sqlite:C:\\Program Files\\Apache Software Foundation\\Tomcat 10.1_TomcatServer\\webapps\\overchargedhell\\src\\main\\java\\org\\example\\overchargedhell\\help.db";

        try{
            Connection conn = DriverManager.getConnection(url);
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();

            List<Profile> localProfiles = new ArrayList<>();
            while (rs.next()) {
                localProfiles.add(new Profile(rs.getInt("id"),
                        rs.getString("name"),
                        rs.getString("email"),
                        rs.getString("picture"),
                        rs.getInt("age"),
                        rs.getInt("userid")));
            }

            try {
                conn.close();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }

            return localProfiles;

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}