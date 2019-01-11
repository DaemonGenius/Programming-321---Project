/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DatabaseControllers;

import Database.DbConn;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

/**
 *
 * @author <Christian Steinmann>
 */
public class PurchaseOrder {

    private int ID;
    private int ProductID;
    private int PersonID;
    private int Quantity;
    private double Price;
    private String Status;

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

    public double getPrice() {
        return Price;
    }

    public void setPrice(double Price) {
        this.Price = Price;
    }

    public String getStatus() {
        return Status;
    }

    public void setStatus(String Status) {
        this.Status = Status;
    }

    public int getPersonID() {
        return PersonID;
    }

    public void setPersonID(int PersonID) {
        this.PersonID = PersonID;
    }

    public PurchaseOrder() {
    }

    public PurchaseOrder(int ID, int ProductID, int PersonID, int Quantity, double Price, String Status) {
        this.ID = ID;
        this.ProductID = ProductID;
        this.PersonID = PersonID;
        this.Quantity = Quantity;
        this.Price = Price;
        this.Status = Status;
    }

    private PurchaseOrder extractPurchaseOrderFRSet(ResultSet rs) throws SQLException {
        PurchaseOrder order = new PurchaseOrder();
        order.setID(rs.getInt("ID"));
        order.setProductID(rs.getInt("ProductID"));
        order.setPersonID(rs.getInt("PersonID"));
        order.setPrice(rs.getDouble("TotalPrice"));
        order.setStatus(rs.getString("Status"));
        order.setQuantity(rs.getInt("Quantity"));

        return order;
    }

    private PurchaseOrder extractPurchaseOrderStatusFRSet(ResultSet rs) throws SQLException {
        PurchaseOrder order = new PurchaseOrder();
        order.setPersonID(rs.getInt("PersonID"));
        order.setStatus(rs.getString("Status"));
        order.setProductID(rs.getInt("ProductID"));
        order.setQuantity(rs.getInt("Quantity"));

        return order;
    }

    public ArrayList<PurchaseOrder> getPurchaseOrderStatus() {
        DbConn connector = new DbConn();
        String Pending = "Pending";
        Connection connection = connector.getConnection();
        try {
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM `PurchaseOrder` WHERE `Status` = '" + Pending + "'");
            ArrayList<PurchaseOrder> orders = new ArrayList<PurchaseOrder>();

            while (rs.next()) {
                PurchaseOrder order = extractPurchaseOrderStatusFRSet(rs);
                orders.add(order);
            }
            return orders;
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return null;
    }

    public boolean updatePurchaseOrder(PurchaseOrder purchaseOrder) {
        DbConn connector = new DbConn();
        String newStatus = "Approved";
        Connection connection = connector.getConnection();
        try {
            PreparedStatement ps = connection.prepareStatement("UPDATE purchaseOrder SET Status=?, TotalPrice=? WHERE ProductID='" + purchaseOrder.getProductID() + "'");
            ps.setString(1, newStatus);
            ps.setDouble(2, Price);
            int i = ps.executeUpdate();
            if (i == 1) {
                return true;
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return false;
    }

    public boolean InsertPurchaseOrder(PurchaseOrder purchaseOrder) {
        DbConn connector = new DbConn();
        String newStatus = "Pending";
        Connection connection = connector.getConnection();
        try {
            PreparedStatement ps = connection.prepareStatement("INSERT INTO `purchaseorder` (`ProductID`, `PersonID`, `Quantity`, `TotalPrice`, `Status`) VALUES (?,?,?,?,?)");
            ps.setInt(1, purchaseOrder.ProductID);
            ps.setInt(2, purchaseOrder.PersonID);
            ps.setInt(3, purchaseOrder.Quantity);
            ps.setDouble(4, purchaseOrder.Price);
            ps.setString(5, newStatus);
            int i = ps.executeUpdate();
            if (i == 1) {
                return true;
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return false;
    }
    public static int PQuantity;

    public ArrayList<String> getPurchaseOrderStatusInfos(int purchaseOrder) {
        DbConn connector = new DbConn();
        String status = "Pending";

        Connection connection = connector.getConnection();
        try {
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM `purchaseorder` WHERE `PersonID` = '" + purchaseOrder + "' AND 'status' = '"+status +"'");
            
            Product p = new Product();
            ArrayList<String> list = new ArrayList<>();   
            System.out.println("break 1");
            while (rs.next()) {
                System.out.println("break 2");
                PurchaseOrder order = extractPurchaseOrderFRSet(rs);
                System.out.println(order.ProductID);
                try {                    
                    p = p.getProductInfo(order.getProductID());
                    list.add(p.getProductName());
                    PQuantity = order.Quantity;
                    System.out.println("break 3");
                } catch (Exception e) {
                }
            }

            return list;
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return null;
    }
    
    public int getProductQuantity(){
        int quan = PQuantity;
        return quan;
    }
    
    
    
    
    
    
    

}
