package com.jpa.ManyToOne.oneToManyBi;

import com.jpa.ManyToOne.entity.Student;
import com.jpa.ManyToOne.entity.University;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;

public class PersistStudent {

    public static void main(String[] args) {

        EntityManagerFactory factory = Persistence.createEntityManagerFactory("jpa-course");
        EntityManager manager = factory.createEntityManager();
        EntityTransaction transaction = manager.getTransaction();

        try {
            transaction.begin();

            University university = manager.find(University.class, 2);  // id университета

            Student student = new Student("Zaur", "Trigulov", 4.5);

            university.addStudent(student);                 // добавление студента в университет

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
