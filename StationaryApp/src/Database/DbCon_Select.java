/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import static javax.swing.text.html.HTML.Tag.SELECT;


/**
 *
 * @author chris
 */
public class DbCon_Select {

    String dbUrl = "jdbc:mysql://localhost:3306/bcstatmanagementsystemdb";
    String username = "root";
    String password = "";
    

    

//    public DbCon_Select() {
//        try (Connection conn = DriverManager.getConnection(dbUrl, username, password)) {
//            try (Statement stmt = (Statement) conn.CreateStatement(query)) {
//                ResultSet rs;
//                rs = stmt.executeQuery("SQL STATEMENT");
//                while (rs.next()) {
//                    String fname = rs.getString(ColumName);
//                    String lname = rs.getString(ColumName);
//                }
//            }
//        } catch (SQLException ex) {
//            Logger.getLogger(DbCon_Select.class.getName()).log(Level.SEVERE, null, ex);
//        }
//
//    }

    public boolean Login(String Username) throws SQLException {
        String query = "SELECT `ID`, `FName`, `LName`, `DOB`, `Cellnr`, `DepartmentID`, `Location`, `Username`, `Password` FROM `person` WHERE person.Username = '" + Username + "'";
        try (Connection conn = DriverManager.getConnection(dbUrl, username, password)) {
            try (PreparedStatement stmt = (PreparedStatement) conn.prepareStatement(query)) {
                return true;

            } catch (SQLException ex) {
                Logger.getLogger(DbCon_Select.class.getName()).log(Level.SEVERE, null, ex);
            }

        }
        return false;

    }

}
