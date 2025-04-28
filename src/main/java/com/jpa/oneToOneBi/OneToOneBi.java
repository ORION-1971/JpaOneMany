package com.jpa.oneToOneBi;

import com.jpa.entity.Passport;
import com.jpa.entity.Student;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;

import java.util.Scanner;

public class OneToOneBi {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Scanner scan = new Scanner(System.in);

        System.out.println("Введи имя студента");
        String name = sc.nextLine();
        System.out.println("Введи фамилию студента");
        String surname = sc.nextLine();
        System.out.println("Средняя оценка (5,5)");  // Вводить через запятую
        double avgGrade = sc.nextDouble();

        System.out.println("Введи email");
        String email = scan.nextLine();
        System.out.println("Цвет глаз");
        String eyeColor = scan.nextLine();
        System.out.println("Введи рост");
        Integer height = scan.nextInt();

        EntityManagerFactory factory = Persistence.createEntityManagerFactory("jpa-course");
        EntityManager manager = factory.createEntityManager();
        EntityTransaction transaction = manager.getTransaction();

        try {
            transaction.begin();
            Student student = new Student(name, surname, avgGrade);
            Passport passport = new Passport(email, height, eyeColor);

            passport.setStudent(student);                   // 1 - передача студента паспорту
            student.setPassport(passport);                  // 2 - передача паспорта студенту
            // manager.persist(student);
            manager.persist(passport);

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
