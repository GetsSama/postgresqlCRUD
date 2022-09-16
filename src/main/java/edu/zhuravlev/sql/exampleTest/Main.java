package edu.zhuravlev.sql.exampleTest;

import edu.zhuravlev.sql.example.ConnectionManager;
import edu.zhuravlev.sql.example.EntityKeeper;
import edu.zhuravlev.sql.example.KeeperFactory;

import java.sql.*;
import java.util.Arrays;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Connection connection = ConnectionManager.getConnection();


        Person person = new Person(1, "Nikolay", "zurik.n", "RU", "secret");
        Person person2 = new Person(2, "Sveta", "mathandmath", "RU", "secret");
        Person person3 = new Person(3, "John", "john.mail.eu", "EU", "secret");
        Person person1_new = new Person(1, "Nikolay", "mail", "EU", "secret");
        Person readPerson;
        EntityKeeper userKeeper = KeeperFactory.createEntityKeeper(Person.class, connection);
        System.out.println(userKeeper);

        try (Scanner scn = new Scanner(System.in)){
            while (scn.hasNext()) {
                String input = scn.next();
                if (input.equals("List"))
                    userKeeper.deleteAll(Arrays.asList(person, person2, person3));
                else if (input.equals("Drop"))
                    userKeeper.dropTable();
                else if (input.equals("SaveAll"))
                    userKeeper.saveAll(Arrays.asList(person, person2, person3));
                else if (input.equals("Read")) {
                    readPerson = (Person)userKeeper.read("1");
                    System.out.println(person.equals(readPerson));
                }
                else if (input.equals("Update"))
                    userKeeper.update(person1_new);
                else if (input.equals("1"))
                    break;

            }
        }

        ConnectionManager.close();
    }
}
