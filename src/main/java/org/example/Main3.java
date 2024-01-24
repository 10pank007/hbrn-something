package org.example;

import models.*;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class Main3 {
    public static void main(String[] args) {
        StandardServiceRegistry build = new StandardServiceRegistryBuilder().configure("hibernate.cfg.xml").build();
        Metadata metadata = new MetadataSources(build)
                .addAnnotatedClass(User.class)
                .addAnnotatedClass(Passport.class)
                .addAnnotatedClass(Card1.class)
                .addAnnotatedClass(Sungluses.class)
                .getMetadataBuilder()
                .build();
        SessionFactory factory = metadata.getSessionFactoryBuilder().build();
        Session session = factory.openSession();
        session.beginTransaction();

        User vasil = new User("vasil",
                GENDER.MALE,
                List.of("java", "js", "html"),
                new Passport("kjkdkjkdj"),
                Arrays.asList(new Card1("23433"), new Card1("34343")),
                Arrays.asList(new Sungluses("eye"), new Sungluses("brand"))
                );

        session.save(vasil);

        session.getTransaction().commit();

        session.createQuery("select u from User u", User.class).getResultList()
                        .forEach(user -> System.out.println(user.getCard1s().get(0).getNumber()));
        Set<User> collect = new HashSet<>(session.createQuery("select c.user from Card1 c", User.class).getResultList());
        System.out.println(collect);


        session.close();
        factory.close();
        build.close();
    }
}
