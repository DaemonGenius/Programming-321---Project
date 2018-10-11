/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author chris
 */
public class DbConn {

    String dbUrl = "";
    String username = "root";
    String password = "";

  
    public void Connect() throws SQLException {
      Connection conn = DriverManager.getConnection(dbUrl, username, password);

        
    }
}
