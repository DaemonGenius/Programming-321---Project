/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ApplicationLogic;

import DatabaseControllers.Person;
import DatabaseControllers.Product;
import java.util.ArrayList;

/**
 *
 * @author <Christian Steinmann>
 */
public class InterfaceLib {

    public static void main(String[] args) {
        String sampleString = "Apple Banana Carrot";
        String[] animals = sampleString.split(" ");
        int animalIndex = 1;
        for (String animal : animals) {
            System.out.println(animalIndex + ". " + animal);
            animalIndex++;
        }
    }

    public String FindUser(String SelectedUser) {
        String[] user = SelectedUser.split(" ");
        String retrieveUser = null;

        Person p = new Person();
        ArrayList<Person> list = new ArrayList<Person>();
        list = p.getAllUserName();

        for (String name : user) {
            for (Person person : list) {
                if (name.equals(person.getUsername())) {
                    retrieveUser = name;
                    System.out.println(retrieveUser);
                }
            }
        }
        
        return retrieveUser;
    }
    
    
    public String FindProduct(String SelectedProduct) {
        String[] product = SelectedProduct.split(" ");
        String retrieveProduct = null;

        Product p = new Product();
        ArrayList<Product> list = new ArrayList<Product>();
        list = p.getAllProductName();

        for (String name : product) {
            for (Product products : list) {
                if (name.equals(products.getProductName())) {
                    retrieveProduct = name;
                }
            }
        }
        
        return retrieveProduct;
    }
}
