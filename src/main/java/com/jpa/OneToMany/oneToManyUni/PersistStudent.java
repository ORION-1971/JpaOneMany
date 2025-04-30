package com.jpa.OneToMany.oneToManyUni;

import com.jpa.OneToMany.entity.Student;
import com.jpa.OneToMany.entity.University;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;

import java.sql.Date;

public class PersistStudent {

    public static void main(String[] args) {

        EntityManagerFactory factory = Persistence.createEntityManagerFactory("jpa-course");
        EntityManager manager = factory.createEntityManager();
        EntityTransaction transaction = manager.getTransaction();

        try {
            transaction.begin();

            University university = manager.find(University.class, 1);  // id университета

            Student student1 = new Student("Zaur", "Trigulov", 4.4);

            university.addStudent(student1);                // 1 - добавление студентов в университет

            manager.persist(university);                    // 2 - добавление университета в базу

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
