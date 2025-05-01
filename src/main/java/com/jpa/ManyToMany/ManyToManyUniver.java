package com.jpa.ManyToMany;

import com.jpa.ManyToMany.entity.Teacher;
import com.jpa.ManyToMany.entity.University;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;

import java.sql.Date;

public class ManyToManyUniver {

    public static void main(String[] args) {

        EntityManagerFactory factory = Persistence.createEntityManagerFactory("jpa-course");
        EntityManager manager = factory.createEntityManager();
        EntityTransaction transaction = manager.getTransaction();

        try {
            transaction.begin();

            University university = new University("Harvard", Date.valueOf("1812-01-20"));
            Teacher teacher1 = new Teacher("Zaur", "Trigulov");
            Teacher teacher2 = new Teacher("Gunay", "Hashimova");

            university.addTeacher(teacher1);                // 1 - добавление учителей в университет
            university.addTeacher(teacher2);

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
