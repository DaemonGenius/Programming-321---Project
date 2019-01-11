/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DatabaseControllers;

import Database.DbConn;
import static DatabaseControllers.Person.rs;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

/**
 *
 * @author <Christian Steinmann>
 */
public class Product {
    private int ID;
    private String ProductName;
    private String CategoryName;
    private String Model;
    private String DoE;
    private double Price;

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getProductName() {
        return ProductName;
    }

    public void setProductName(String ProductName) {
        this.ProductName = ProductName;
    }

    public String getCategoryName() {
        return CategoryName;
    }

    public void setCategoryName(String CategoryName) {
        this.CategoryName = CategoryName;
    }

    public String getModel() {
        return Model;
    }

    public void setModel(String Model) {
        this.Model = Model;
    }

    public String getDoE() {
        return DoE;
    }

    public void setDoE(String DoE) {
        this.DoE = DoE;
    }

    public double getPrice() {
        return Price;
    }

    public void setPrice(double Price) {
        this.Price = Price;
    }

    public Product() {
    }

    public Product(int ID, String ProductName, String CategoryName, String Model, String DoE, double Price) {
        this.ID = ID;
        this.ProductName = ProductName;
        this.CategoryName = CategoryName;
        this.Model = Model;
        this.DoE = DoE;
        this.Price = Price;
    }
    private Product extractProductRSet(ResultSet rs) throws SQLException {
        Product product = new Product();
        product.setID(rs.getInt("ID"));
        product.setProductName(rs.getString("ProductName"));
        product.setCategoryName(rs.getString("CategoryName"));
        product.setPrice(rs.getDouble("Price"));
        product.setDoE(rs.getString("Date of Entry"));
        product.setModel(rs.getString("Model"));      
       
        return product;
    }
    
    private String extractProductOrderNRSet(ResultSet rs) throws SQLException {
        Product product = new Product();
        product.setProductName(rs.getString("ProductName"));
       
        return product.ProductName;
    }
    
    private Product extractProductOrderPNRSet(ResultSet rs) throws SQLException {
        Product product = new Product();
        product.setProductName(rs.getString("ProductName"));
       
        return product;
    }
    
    private Product extractProductOrderInfoRSet(ResultSet rs) throws SQLException {
        Product product = new Product();
        product.setProductName(rs.getString("ProductName"));
        product.setPrice(rs.getDouble("Price"));
        product.setID(rs.getInt("ID"));
       
        return product;
    }
    
    public String getProductOrder(int ID) throws SQLException {
        Connection connection = DbConn.getConnection();
        System.out.println(ID);

        try {
            Statement stmt = connection.createStatement();
            rs = stmt.executeQuery("SELECT * FROM `product` WHERE `ID` = '" + ID + "'");
            if (rs.next()) {

                return extractProductOrderNRSet(rs);
            } else {
                JFrame frame = new JFrame();
                JOptionPane.showMessageDialog(frame, "Something went wrong!?!?");
            }
        } catch (SQLException ex) {

            ex.printStackTrace();
        }

        return null;
    }
    
    public Product getProductOrderInfo(String productName) throws SQLException {
        Connection connection = DbConn.getConnection();
        System.out.println(productName);
        try {
            Statement stmt = connection.createStatement();
            rs = stmt.executeQuery("SELECT * FROM `product` WHERE `ProductName` = '" + productName + "'");
            if (rs.next()) {

                return extractProductOrderInfoRSet(rs);
            } else {
                JFrame frame = new JFrame();
                JOptionPane.showMessageDialog(frame, "Something went wrong!?!?");
            }
        } catch (SQLException ex) {

            ex.printStackTrace();
        }

        return null;
    }
    
    public ArrayList<Product> getAllProductName() {
        DbConn connector = new DbConn();
        Connection connection = connector.getConnection();
        try {
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM `product` ");
            ArrayList products = new ArrayList();
            while (rs.next()) {
                Product product = extractProductOrderPNRSet(rs);
                products.add(product);
            }
            return products;
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return null;
    }
    
    
    
     public ArrayList<String> getCategoryNames() {
        DbConn connector = new DbConn();
        Connection connection = connector.getConnection();
        ArrayList<String> Category = new ArrayList<>();
        try {
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM `product` ");
            ArrayList<Product> products = new ArrayList();
            while (rs.next()) {
                Product product = extractProductRSet(rs);
                products.add(product);
                Category.add(product.getCategoryName());
            }
            return Category;
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        
        return null;
    }
    
    public ArrayList<String> getProductNameList(String CategoryName) throws SQLException {
        Connection connection = DbConn.getConnection();
        try {
            Statement stmt = connection.createStatement();
            rs = stmt.executeQuery("SELECT * FROM `product` WHERE `CategoryName` = '" + CategoryName + "'");
            ArrayList<String> ProductNList = new ArrayList<>();
            ArrayList<Product> products = new ArrayList();
             while (rs.next()) {
                Product product = extractProductRSet(rs);
                products.add(product);
                ProductNList.add(product.ProductName);
            }             
             return ProductNList;
        } catch (SQLException ex) {

            ex.printStackTrace();
        }

        return null;
    }
    
    
    public Product getProductInfo(String productName) throws SQLException {
        Connection connection = DbConn.getConnection();
        System.out.println(productName);
        try {
            Statement stmt = connection.createStatement();
            rs = stmt.executeQuery("SELECT * FROM `product` WHERE `ProductName` = '" + productName + "'");
            if (rs.next()) {
                return extractProductRSet(rs);
            } else {
                JFrame frame = new JFrame();
                JOptionPane.showMessageDialog(frame, "Something went wrong!?!?");
            }
        } catch (SQLException ex) {

            ex.printStackTrace();
        }
        return null;
    }
    
     public Product getProductInfo(int productID) throws SQLException {
        Connection connection = DbConn.getConnection();
        try {
            Statement stmt = connection.createStatement();
            rs = stmt.executeQuery("SELECT * FROM `product` WHERE `ID` = '" + productID + "'");
            if (rs.next()) {
                return extractProductRSet(rs);
            } else {
                JFrame frame = new JFrame();
                JOptionPane.showMessageDialog(frame, "Something went wrong!?!?");
            }
        } catch (SQLException ex) {

            ex.printStackTrace();
        }
        return null;
    }
    
    
}
