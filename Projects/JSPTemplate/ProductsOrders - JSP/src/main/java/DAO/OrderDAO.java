package DAO;

import Model.Order;
import Utility.ConnectionManager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class OrderDAO {
    public void insertOrder(Order order) throws SQLException {
        Connection con = ConnectionManager.getConnection();
        PreparedStatement ps = con.prepareStatement("insert into Orders value (?,?,?,?)");
    }
}
