package com.jpa.ManyToMany;

import com.jpa.ManyToMany.entity.Teacher;
import com.jpa.ManyToMany.entity.University;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;

import java.sql.Date;

public class InsertTeacher {

    public static void main(String[] args) {

        EntityManagerFactory factory = Persistence.createEntityManagerFactory("jpa-course");
        EntityManager manager = factory.createEntityManager();
        EntityTransaction transaction = manager.getTransaction();

        try {
            transaction.begin();

            University university = manager.find(University.class, 5);
            Teacher teacher = manager.find(Teacher.class, 2);

            university.addTeacher(teacher);                // добавить университету учителя

            manager.persist(university);                   // добавление университета в базу

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
