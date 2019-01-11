/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DatabaseControllers;

import Database.DbConn;
import static DatabaseControllers.Person.rs;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

/**
 *
 * @author <Christian Steinmann>
 */
public class Stock {
    private int ID;
    private int ProductID;
    private int Quantity;

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public int getProductID() {
        return ProductID;
    }

    public void setProductID(int ProductID) {
        this.ProductID = ProductID;
    }

    public int getQuantity() {
        return Quantity;
    }

    public void setQuantity(int Quantity) {
        this.Quantity = Quantity;
    }

    public Stock() {
    }

    public Stock(int ID, int ProductID, int Quantity) {
        this.ID = ID;
        this.ProductID = ProductID;
        this.Quantity = Quantity;
    }
    
    
    private Stock extractStockRSet(ResultSet rs) throws SQLException {
        Stock stock = new Stock();
        stock.setProductID(rs.getInt("ProductID"));
        stock.setQuantity(rs.getInt("Quantity"));
        return stock;
    }
    
    public Stock getStockID(int ID) throws SQLException {
        Connection connection = DbConn.getConnection();
        System.out.println(ID);

        try {
            Statement stmt = connection.createStatement();
            rs = stmt.executeQuery("SELECT * FROM `Stock` WHERE `ProductID` = '" + ID + "'");
            if (rs.next()) {

                return extractStockRSet(rs);
            } else {
                JFrame frame = new JFrame();
                JOptionPane.showMessageDialog(frame, "Something went wrong!?!?");
            }
        } catch (SQLException ex) {

            ex.printStackTrace();
        }

        return null;
    }
    
    public boolean updateStock(Stock stock) {
        DbConn connector = new DbConn();
        Connection connection = connector.getConnection();
        try {
            PreparedStatement ps = connection.prepareStatement("UPDATE Stock SET Quantity=? WHERE ProductID='" + stock.getProductID()+ "'");
            ps.setInt(1, stock.getQuantity());
            int i = ps.executeUpdate();
            if (i == 1) {
                return true;
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return false;
    }

    
}
