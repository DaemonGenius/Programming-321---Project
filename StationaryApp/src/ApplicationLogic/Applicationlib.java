/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ApplicationLogic;

import DatabaseControllers.Person;
import java.io.FileWriter;
/**
 *
 * @author chris
 */
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import static com.oracle.jrockit.jfr.ContentType.Class;
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
    public static Set<Person> Users = new HashSet();

    public boolean WriteTOfile() throws IOException {

        try (Writer writer = new FileWriter("Output.json")) {
            Gson gson = new GsonBuilder().create();
            Users = p.getApplicationStatus();
            Iterator<Person> it = Users.iterator();
            while (it.hasNext()) {
                //gson.toJson(it, writer);
                System.out.println(it.next());

                for (Person User : Users) {
                    System.out.println(User.getDepartment());
                    String json = gson.toJson(User);
                    writer.write(json);
                }
            }

        }
        return true;
    }

    public ArrayList ReadFromfile() throws IOException {
        ArrayList<Person> list = new ArrayList<>();
        try {
            Gson gson = new Gson();
            Reader br = new FileReader("Output.json");

            //convert the json string back to object
            p = gson.fromJson(br, Person.class);
            list.add(p);
            for (Person person : list) {
                System.out.println("Username: " + person.getUsername());
                System.out.println("Department: " + p.getDepartment());
            }

        } catch (Exception e) {
        }
        return list;
    }
}
