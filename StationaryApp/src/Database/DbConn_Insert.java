/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Database;

import static com.sun.org.apache.xalan.internal.lib.ExsltDatetime.dateTime;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author chris
 */
public class DbConn_Insert {
    String dbUrl = "";
    String username = "root";
    String password = "";
    public DbConn_Insert(){
            
    }
    
    public boolean RegisterUser(String Fname, String LName,Date DOB , String Cell, String Loc, String Username, String Password, int DepartmentID){
        String Query = "INSERT INTO `person`(`FName`, `LName`, `DOB`, `Cellnr`, `DepartmentID`, `Location`, `Username`, `Password`) VALUES ('"+Fname+"','"+LName+"','"+DOB+"','"+Cell+"','"+DepartmentID+"','"+Loc+"','"+Username+"','"+Password+"')";
        try(Connection conn = DriverManager.getConnection(dbUrl,username,password)) {
             
              try(PreparedStatement stmt = conn.prepareStatement(dbUrl)) {
               
                stmt.setString(2, Fname);
                stmt.setString(3, LName);
                stmt.setDate(4, DOB);
                stmt.setString(5, Cell);
                stmt.setString(6, Loc);
                stmt.setString(7, Password);
                stmt.setString(8, Username);
                stmt.setInt(9, DepartmentID);
                stmt.executeUpdate();
              }
              return true;
        } catch (SQLException ex) {
            Logger.getLogger(DbConn_Insert.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    
    }
    
    
    public void InsertProduct(String ProductName, String CategoryName, String Model, double Quantity, double Price, Date DOE){
         String Query = "INSERT INTO `product`(`ProductName`, `CategoryName`, `Model`, `Quantity`, `Price`, `DateofEntry`) VALUES ('"+ProductName+"','"+CategoryName+"','"+Model+"','"+Quantity+"','"+Price+"','"+DOE+"')";
        try(Connection conn = DriverManager.getConnection(dbUrl,username,password)) {
             
              try(PreparedStatement stmt = conn.prepareStatement(dbUrl)) {
               
                stmt.setString(2, ProductName);
                stmt.setString(3, CategoryName);
                stmt.setDouble(4, Quantity);
                stmt.setDate(6, DOE);
                stmt.setDouble(5, Price);
                stmt.executeUpdate();
              }
              
        } catch (SQLException ex) {
            Logger.getLogger(DbConn_Insert.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
}
