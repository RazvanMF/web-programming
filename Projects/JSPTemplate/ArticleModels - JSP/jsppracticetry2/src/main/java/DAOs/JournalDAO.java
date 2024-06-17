package DAOs;

import Models.Article;
import Utils.DBConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class JournalDAO {
    public int getIdByName(String name){
        String sql = "SELECT id FROM journals WHERE name = ?";
        Connection conn;
        try {
            conn = DBConnection.getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, name);
            ResultSet rs = stmt.executeQuery();
            if(rs.next() == false)
                return 0;
            int value = rs.getInt("id");
            return value;
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public void insertJournal(String name){
        String sql = "INSERT INTO journals VALUES(0, ?)";
        Connection conn;
        try {
            conn = DBConnection.getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, name);
            int modifiedrows = stmt.executeUpdate();
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}
