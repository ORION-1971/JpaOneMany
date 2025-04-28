package com.jpa;

import com.jpa.entity.Passport;
import com.jpa.entity.Student;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;

public class Find_ {

    public static void main(String[] args) {

        EntityManagerFactory factory = Persistence.createEntityManagerFactory("jpa-course");
        EntityManager manager = factory.createEntityManager();
        EntityTransaction transaction = manager.getTransaction();

        try {
            transaction.begin();

            Student student = manager.find(Student.class, 3);  // найти студента с id 3
            System.out.println(student);
            System.out.println(student.getPassport());                  // получить его паспорт

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
