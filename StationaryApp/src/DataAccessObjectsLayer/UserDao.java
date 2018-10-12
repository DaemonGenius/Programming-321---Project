/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DataAccessObjectsLayer;

import DatabaseControllers.Person;
import java.util.Set;

/**
 *
 * @author chris
 */
public interface UserDao {
    Person getPerson();
    Set<Person> getAllUsers();
    Person getLoginCredentials();
    boolean getLoginStatus();
    boolean insertPerson();
    boolean updatePerson();
    boolean deletePerson();
}
