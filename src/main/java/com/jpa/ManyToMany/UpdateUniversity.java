package com.jpa.ManyToMany;

import com.jpa.ManyToMany.entity.Teacher;
import com.jpa.ManyToMany.entity.University;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;

import java.sql.Date;

public class UpdateUniversity {

    public static void main(String[] args) {

        EntityManagerFactory factory = Persistence.createEntityManagerFactory("jpa-course");
        EntityManager manager = factory.createEntityManager();
        EntityTransaction transaction = manager.getTransaction();

        try {
            transaction.begin();

            Teacher teacher = manager.find(Teacher.class, 2);
            teacher.setName("Zuhra");
            manager.persist(teacher);                       // поменять имя у учителя id 2

            University university = manager.find(University.class, 5);
            university.setName("Pendos");
            manager.persist(university);                   // поменять название университета

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
