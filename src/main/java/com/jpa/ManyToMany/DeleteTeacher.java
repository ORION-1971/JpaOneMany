package com.jpa.ManyToMany;

import com.jpa.ManyToMany.entity.Teacher;
import com.jpa.ManyToMany.entity.University;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;

public class DeleteTeacher {

    public static void main(String[] args) {

        EntityManagerFactory factory = Persistence.createEntityManagerFactory("jpa-course");
        EntityManager manager = factory.createEntityManager();
        EntityTransaction transaction = manager.getTransaction();

        try {
            transaction.begin();

            Teacher teacher = manager.find(Teacher.class, 2);

            manager.remove(teacher);                   // удалить учителя id 2

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
