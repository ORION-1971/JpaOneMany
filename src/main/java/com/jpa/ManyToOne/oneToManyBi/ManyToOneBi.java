package com.jpa.ManyToOne.oneToManyBi;

import com.jpa.ManyToOne.entity.Student;
import com.jpa.ManyToOne.entity.University;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;

import java.sql.Date;

public class ManyToOneBi {

    public static void main(String[] args) {

        EntityManagerFactory factory = Persistence.createEntityManagerFactory("jpa-course");
        EntityManager manager = factory.createEntityManager();
        EntityTransaction transaction = manager.getTransaction();

        try {
            transaction.begin();                             //запуск транзакции

            University university = new University("Harvard", Date.valueOf("1812-01-20"));
            Student student1 = new Student("Zaur", "Trigulov", 4.4);
            Student student2 = new Student("Rick", "Shnider", 5.5);


            university.addStudent(student1);                // 1 - добавление студентов в университет
            university.addStudent(student2);

            manager.persist(university);                    // 2 - добавление университета в базу

            transaction.commit();                           // совершить транзакцию

        } catch (Exception e) {
            if (transaction != null && transaction.isActive()) {
                transaction.rollback();                     // откат транзакции
            }
            e.printStackTrace();
        }
        finally {
            manager.close();
            factory.close();
        }
    }
}
