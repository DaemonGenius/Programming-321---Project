/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ApplicationLogic;

import DatabaseControllers.Person;
import DatabaseControllers.PurchaseOrder;
import java.io.FileWriter;
/**
 *
 * @author chris
 */
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import static com.oracle.jrockit.jfr.ContentType.Class;
import java.awt.List;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.io.Writer;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

public class Applicationlib {

    public static Person p = new Person();
    public static ArrayList<Person> Users = new ArrayList<Person>();

    public static PurchaseOrder Po = new PurchaseOrder();
    public static ArrayList<PurchaseOrder> Orders = new ArrayList<>();
    
    public boolean WriteTOfile() throws IOException {

        try (Writer writer = new FileWriter("Output.json")) {
            Gson gson = new GsonBuilder().create();
            Users = p.getApplicationStatus();
            String json = gson.toJson(Users);
            writer.write(json);
        }
        return true;
    }

    public ArrayList<Person> ReadFromfile() throws IOException {
        ArrayList<Person> list = new ArrayList<>();
        try {
            Gson gson = new Gson();
            Reader br = new FileReader("Output.json");
            list = (ArrayList<Person>) gson.fromJson(br, new TypeToken<ArrayList<Person>>() {
            }.getType());
        } catch (Exception e) {
        }
        return list;
    }
    
    public boolean WriteTOfilePurchaseOrder() throws IOException {
        try (Writer writer = new FileWriter("OutputPurchaseOrder.json")) {
            Gson gson = new GsonBuilder().create();
            Orders = Po.getPurchaseOrderStatus();
            String json = gson.toJson(Orders);
            writer.write(json);
        }
        return true;
    }
    
    public ArrayList<PurchaseOrder> ReadFromfilePurchaseOrder() throws IOException {
        ArrayList<PurchaseOrder> list = new ArrayList<>();
        try {
            Gson gson = new Gson();
            Reader br = new FileReader("OutputPurchaseOrder.json");
            list = (ArrayList<PurchaseOrder>) gson.fromJson(br, new TypeToken<ArrayList<PurchaseOrder>>() {
            }.getType());
        } catch (Exception e) {
        }
        return list;
    }
}
