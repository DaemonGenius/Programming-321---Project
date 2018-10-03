/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Database;

import java.sql.Connection;
import java.sql.DriverManager;

/**
 *
 * @author chris
 */
public class DbConn {
     String dbUrl = "";
     String username = "root";
     String password = "";
    
    public void main(String[] args) {
        try (Connection conn = DriverManager.getConnection(dbUrl,username,password)){
            
        } catch (Exception e) {
        }
    }
}
