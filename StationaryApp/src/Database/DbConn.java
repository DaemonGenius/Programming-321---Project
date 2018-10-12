/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Database;

import com.mysql.jdbc.Driver;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author chris
 */
public class DbConn {

    public static final String dbUrl = "jdbc:mysql://localhost:3306/bcmanagementsystemdb";
    public static final String username = "root";
    public static final String password = "";

    public static Connection getConnection() {
        try {
            DriverManager.registerDriver(new Driver() {
            });
            return DriverManager.getConnection(dbUrl, username, password);
        } catch (SQLException ex) {
            throw new RuntimeException("Error connecting to the database", ex);
        }

    }
    
    
    public static void main(String[] args) {
        Connection connection = DbConn.getConnection();
    }
}
