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
import java.util.HashSet;
import java.util.Set;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

/**
 *
 * @author chris
 */
public class Person {

    private int _Id;
    private String _FName;
    private String _LName;
    private String _DOB;
    private String _CellNr;
    private String _Department;
    private String _Location;
    private String _Username;
    private String _Password;

    public int getId() {
        return _Id;
    }

    public void setId(int _Id) {
        this._Id = _Id;
    }

    public String getFName() {
        return _FName;
    }

    public void setFName(String _FName) {
        this._FName = _FName;
    }

    public String getLName() {
        return _LName;
    }

    public void setLName(String _LName) {
        this._LName = _LName;
    }

    public String getDOB() {
        return _DOB;
    }

    public void setDOB(String _DOB) {
        this._DOB = _DOB;
    }

    public String getCellNr() {
        return _CellNr;
    }

    public void setCellNr(String _CellNr) {
        this._CellNr = _CellNr;
    }

    public String getDepartment() {
        return _Department;
    }

    public void setDepartment(String _Department) {
        this._Department = _Department;
    }

    public String getLocation() {
        return _Location;
    }

    public void setLocation(String _Location) {
        this._Location = _Location;
    }

    public String getUsername() {
        return _Username;
    }

    public void setUsername(String _Username) {
        this._Username = _Username;
    }

    public String getPassword() {
        return _Password;
    }

    public void setPassword(String _Password) {
        this._Password = _Password;
    }

    public Person() {
    }

    public Person(int Id, String FName, String LName, String DOB, String CellNr, String Department, String Location, String Username, String Password) {
        this._Id = Id;
        this._FName = FName;
        this._LName = LName;
        this._DOB = DOB;
        this._CellNr = CellNr;
        this._Department = Department;
        this._Location = Location;
        this._Username = Username;
        this._Password = Password;
    }
    public static Person person = new Person();
    public static ResultSet rs;

    public Person getPerson(String username) throws SQLException {
        Connection connection = DbConn.getConnection();

        try {
            Statement stmt = connection.createStatement();
            rs = stmt.executeQuery("SELECT * FROM `person` WHERE `Username` = '" + username + "'");
            if (rs.next()) {

                return extractUserFromResultSet(rs);
            } else {
                JFrame frame = new JFrame();
                JOptionPane.showMessageDialog(frame, "Account doesnt exist!?!?");
            }
        } catch (SQLException ex) {

            ex.printStackTrace();
        }

        return null;
    }

    private Person extractUserFromResultSet(ResultSet rs) throws SQLException {

        person.setId(rs.getInt("ID"));
        person.setFName(rs.getString("FName"));
        person.setLName(rs.getString("LName"));
        person.setDOB(rs.getString("DOB"));
        person.setDepartment(rs.getString("Department"));
        person.setLocation(rs.getString("Location"));
        person.setCellNr(rs.getString("Cellnr"));
        person.setUsername(rs.getString("Username"));
        person.setPassword(rs.getString("Password"));

        return person;
    }

    public Person getLoginCredentials(String user, String pass) throws SQLException {
        DbConn connector = new DbConn();
        String Admin = "Admin";
        String Staff = "Staff";
        Connection connection = connector.getConnection();
        try {
            PreparedStatement ps = connection.prepareStatement("SELECT * FROM person WHERE Username=? AND Password=? AND Department = '" + Admin + "' OR Department = '" + Staff + "'");
            ps.setString(1, user);
            ps.setString(2, pass);
            rs = ps.executeQuery();
            if (rs.next()) {
                return extractUserFromResultSet(rs);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return null;
    }
    public Person users;

    public Person GetUser(String user) throws SQLException {
        users = getPerson(user);
        return users;
    }

    public Set getApplicationStatus() {
        DbConn connector = new DbConn();
        String Pending = "Pending";
        Connection connection = connector.getConnection();
        try {
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM `person` WHERE `Department` = '"+Pending+"'");
            Set persons = new HashSet();
            while (rs.next()) {
                Person person = extractUserFromResultSet(rs);
                persons.add(person);               
            }
            return persons;
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return null;
    }
    
    
    
    public String state;

    public String getLoginStatus(String user) throws SQLException {
        Connection connection = DbConn.getConnection();
        person = getPerson(user);
        String pending = "Pending";
        String Admin = "Admin";
        String Staff = "Staff";

        if (person.getDepartment().equals(pending)) {
            state = pending;
        } else if (person.getDepartment().equals(Admin)) {
            state = Admin;
        } else if (person.getDepartment().equals(Staff)) {
            state = Staff;
        }

        return state;
    }

    public Set getAllUsers() {
        DbConn connector = new DbConn();
        Connection connection = connector.getConnection();
        try {
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM user");
            Set persons = new HashSet();
            while (rs.next()) {
                Person person = extractUserFromResultSet(rs);
                persons.add(person);
            }
            return persons;
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return null;
    }

    public boolean insertUser(Person person) {
        DbConn connector = new DbConn();
        Connection connection = connector.getConnection();
        try {
            PreparedStatement ps = connection.prepareStatement("INSERT INTO person VALUES (NULL, ?, ?, ?, ?, ?, ?, ?, ?)");
            ps.setString(1, person.getFName());
            ps.setString(2, person.getLName());
            ps.setString(3, person.getDOB());
            ps.setString(4, person.getCellNr());
            ps.setString(5, person.getLocation());
            ps.setString(6, person.getDepartment());
            ps.setString(7, person.getUsername());
            ps.setString(8, person.getPassword());

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
