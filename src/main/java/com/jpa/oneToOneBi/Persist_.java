package com.jpa.oneToOneBi;

import com.jpa.entity.EyeColor;
import com.jpa.entity.Passport;
import com.jpa.entity.Student;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;

import java.util.Scanner;

public class Persist_ {

    public static void main(String[] args) {

        EntityManagerFactory factory = Persistence.createEntityManagerFactory("jpa-course");
        EntityManager manager = factory.createEntityManager();
        EntityTransaction transaction = manager.getTransaction();

        try {
            transaction.begin();

            Student student = new Student("John", "Travolta", 7.8);
            Passport passport = new Passport("travol@gmail.com", 182, EyeColor.BLACK);

            passport.setStudent(student);                   // 1 - передача студента паспорту
            student.setPassport(passport);                  // 2 - передача паспорта студенту
            // manager.persist(student);
            manager.persist(passport);

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
