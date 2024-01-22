package org.example;

import models.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

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
//        User vasil = new User("vasil");
//        User alexandr = new User("alexandr");
//        User volodimir = new User("volodimir");
//        User vitaliy = new User("vitaliy");
//        session.save(vasil);
//        session.save(alexandr);
//        session.save(volodimir);
//        session.save(vitaliy);
//        User user = session.find(User.class, 1);
//        System.out.println(user);
//        List<User> list = session.createNativeQuery("select * from user", User.class).list();
        List<User> list = session.createQuery("select u from User u", User.class).list();
        System.out.println(list);

        session.getTransaction().commit();
        session.close();
        sessionFactory.close();
        serviceRegistry.close();
    }
}