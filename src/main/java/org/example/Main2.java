package org.example;

import models.Card;
import models.GENDER;
import models.Passport;
import models.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

import java.util.List;

public class Main2 {
    public static void main(String[] args) {
        StandardServiceRegistry build = new StandardServiceRegistryBuilder().configure("hibernate.cfg.xml").build();
        Metadata metadata = new MetadataSources(build)
                .addAnnotatedClass(User.class)
                .addAnnotatedClass(Passport.class)
                .addAnnotatedClass(Card.class)
                .getMetadataBuilder()
                .build();
        SessionFactory factory = metadata.getSessionFactoryBuilder().build();
        Session session = factory.openSession();
        session.beginTransaction();

        User vasil = new User("vasil", GENDER.MALE, List.of("java", "js", "html"), new Passport("kjkdkjkdj"));
//        User alexandr = new User("alexandr", GENDER.FEMALE, List.of("mongo", "sql"), new Passport("sjjskdjks"));
//        User volodimir = new User("volodimir", GENDER.MALE, List.of("docker"), new Passport("djkfjkdkjd"));
//        User vitaliy = new User("vitaliy", GENDER.FEMALE);
        System.out.println(vasil);
        session.save(vasil);
        System.out.println(vasil);
//        session.save(alexandr);
//        session.save(volodimir);
//        session.save(vitaliy);

        Card card = new Card("2343232432", vasil);
        session.save(card);

        session.getTransaction().commit();

        session.createQuery("select c from Card c", Card.class).getResultList().forEach(System.out::println);


        session.close();
        factory.close();
        build.close();
    }
}
