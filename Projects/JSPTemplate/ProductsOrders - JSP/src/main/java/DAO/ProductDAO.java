package DAO;

import Model.Product;
import Utility.ConnectionManager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ProductDAO {
    public void insertProduct(Product p) throws SQLException {
        Connection con = ConnectionManager.getConnection();
        PreparedStatement ps = con.prepareStatement("insert into products values(?,?,?)");
        ps.setInt(1, 0);
        ps.setString(2, p.getName());
        ps.setString(3, p.getDescription());
        ps.executeUpdate();
    }

    public void updateProduct(Product p) throws SQLException {
        Connection con = ConnectionManager.getConnection();
        PreparedStatement ps = con.prepareStatement("update products set name=?,description=? where id=?");
        ps.setString(1, p.getName());
        ps.setString(2, p.getDescription());
        ps.setInt(3, p.getId());
        ps.executeUpdate();
    }

    public void deleteProduct(Product p) throws SQLException {
        Connection con = ConnectionManager.getConnection();
        PreparedStatement ps = con.prepareStatement("delete from products where id=?");
        ps.setInt(1, p.getId());
        ps.executeUpdate();
    }

    public Product getProductById(int id) throws SQLException {
        Connection con = ConnectionManager.getConnection();
        PreparedStatement ps = con.prepareStatement("select * from products where id=?");
        ps.setInt(1, id);
        ResultSet rs = ps.executeQuery();
        if (rs.next()) {
            Product p = new Product();
            p.setId(rs.getInt("id"));
            p.setName(rs.getString("name"));
            p.setDescription(rs.getString("description"));
            return p;
        }
        return null;
    }

    public ArrayList<Product> getAllProducts() throws SQLException {
        Connection con = ConnectionManager.getConnection();
        PreparedStatement ps = con.prepareStatement("select * from products");
        ResultSet rs = ps.executeQuery();
        ArrayList<Product> products = new ArrayList<>();
        while (rs.next()) {
            Product p = new Product();
            p.setId(rs.getInt("id"));
            p.setName(rs.getString("name"));
            p.setDescription(rs.getString("description"));
            products.add(p);
        }
        return products;
    }

    public Product getProductByName(String name) throws SQLException {
        Connection con = ConnectionManager.getConnection();
        PreparedStatement ps = con.prepareStatement("select * from products where name=?");
        ps.setString(1, name);
        ResultSet rs = ps.executeQuery();
        if (rs.next()) {
            Product p = new Product();
            p.setId(rs.getInt("id"));
            p.setName(rs.getString("name"));
            p.setDescription(rs.getString("description"));
            return p;
        }
        return null;
    }

    public ArrayList<Product> filterProducts(String name) throws SQLException {
        Connection con = ConnectionManager.getConnection();
        PreparedStatement ps = con.prepareStatement("select * from products where name like ?");
        ps.setString(1, name + "%");
        ResultSet rs = ps.executeQuery();
        ArrayList<Product> products = new ArrayList<>();
        while (rs.next()) {
            Product p = new Product();
            p.setId(rs.getInt("id"));
            p.setName(rs.getString("name"));
            p.setDescription(rs.getString("description"));
            products.add(p);
        }
        return products;
    }
}
