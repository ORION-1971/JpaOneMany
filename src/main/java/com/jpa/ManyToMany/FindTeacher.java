package com.jpa.ManyToMany;

import com.jpa.ManyToMany.entity.Teacher;
import com.jpa.ManyToMany.entity.University;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;

public class FindTeacher {

    public static void main(String[] args) {

        EntityManagerFactory factory = Persistence.createEntityManagerFactory("jpa-course");
        EntityManager manager = factory.createEntityManager();
        EntityTransaction transaction = manager.getTransaction();

        try {
            University university = manager.find(University.class, 5);
            System.out.println(university.getTeachers());     // найти всех учителей университета id 5

            Teacher teacher = manager.find(Teacher.class, 2);
            System.out.println(teacher.getUniversities());   // найти все университеты учителя id 2

        } catch (Exception e) {
            e.printStackTrace();
        }
        finally {
            manager.close();
            factory.close();
        }
    }
}
