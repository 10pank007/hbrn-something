package org.example;

import models.GENDER;
import models.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

import java.util.Arrays;
import java.util.List;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        StandardServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder().configure("hibernate.cfg.xml").build();
        Metadata metadata = new MetadataSources(serviceRegistry)
                .addAnnotatedClass(User.class)
                .getMetadataBuilder()
                .build();

        SessionFactory sessionFactory = metadata.getSessionFactoryBuilder().build();
        Session session = sessionFactory.openSession();
        session.beginTransaction();

        //crud
        User vasil = new User("vasil", GENDER.MALE, List.of("java", "js", "html"));
        User alexandr = new User("alexandr", GENDER.FEMALE, List.of("mongo", "sql"));
        User volodimir = new User("volodimir", GENDER.MALE, List.of("docker"));
        User vitaliy = new User("vitaliy", GENDER.FEMALE);
        session.save(vasil);
        session.save(alexandr);
        session.save(volodimir);
        session.save(vitaliy);



        session.getTransaction().commit();
        session.close();
        sessionFactory.close();
        serviceRegistry.close();
    }
}