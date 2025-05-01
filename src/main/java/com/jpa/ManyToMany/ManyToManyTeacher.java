package com.jpa.ManyToMany;

import com.jpa.ManyToMany.entity.Teacher;
import com.jpa.ManyToMany.entity.University;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;

import java.sql.Date;

public class ManyToManyTeacher {

    public static void main(String[] args) {

        EntityManagerFactory factory = Persistence.createEntityManagerFactory("jpa-course");
        EntityManager manager = factory.createEntityManager();
        EntityTransaction transaction = manager.getTransaction();

        try {
            transaction.begin();

            Teacher teacher = new Teacher("Donald", "McDuck");
            University university1 = new University("Oxford", Date.valueOf("1900-09-15"));
            University university2 = new University("OldSchool", Date.valueOf("1235-11-11"));

            teacher.addUniversity(university1);            // 1 - добавление университетов для учителя
            teacher.addUniversity(university2);

            manager.persist(teacher);                      // 2 - добавление учителя в базу

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
