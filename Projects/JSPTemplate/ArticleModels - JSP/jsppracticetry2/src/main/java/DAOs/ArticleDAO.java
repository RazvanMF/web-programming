package DAOs;

import Models.Article;
import Utils.DBConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ArticleDAO {
    public List<Article> filteredSelect(String username, int journalId) {
        String sql = "SELECT * FROM articles WHERE user = ? AND journalid = ?";

        Connection conn;
        try {
            conn = DBConnection.getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, username);
            stmt.setInt(2, journalId);
            ResultSet rs = stmt.executeQuery();
            List<Article> values = new ArrayList<Article>();
            while(rs.next()){
                values.add(new Article(rs.getInt("id"), rs.getString("user"), rs.getInt("journalid"), rs.getString("summary"), rs.getDate("date")));
            }
            return values;
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public void insertArticle(String user, int journalId, String summary){
        String sql = "INSERT INTO articles VALUES(0, ?, ?, ?, ?)";
        Connection conn;
        try {
            conn = DBConnection.getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, user);
            stmt.setInt(2, journalId);
            stmt.setString(3, summary);
            stmt.setDate(4, Date.valueOf(java.time.LocalDate.now()));
            int modifiedrows = stmt.executeUpdate();
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}
