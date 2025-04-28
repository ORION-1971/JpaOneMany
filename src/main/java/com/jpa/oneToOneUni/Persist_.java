package com.jpa.oneToOneUni;

import com.jpa.entity.Passport;
import com.jpa.entity.Student;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;

public class Persist_ {

    public static void main(String[] args) {

        EntityManagerFactory factory = Persistence.createEntityManagerFactory("jpa-course");
        EntityManager manager = factory.createEntityManager();
        EntityTransaction transaction = manager.getTransaction();

        try {
            transaction.begin();

            Student student = new Student("John", "Travolta", 7.8);
            Passport passport = new Passport("travol@gmail.com", 182, "black");

            student.setPassport(passport);
            //manager.persist(passport);         // добавление паспорта автоматом (CascadeType.ALL)
            manager.persist(student);

            transaction.commit();

        } catch (Exception e) {
            if (transaction != null && transaction.isActive()) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
        finally {
            manager.close();
            factory.close();
        }
    }
}
