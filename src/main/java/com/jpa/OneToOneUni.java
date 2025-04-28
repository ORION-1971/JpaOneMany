package com.jpa;

import com.jpa.entity.Passport;
import com.jpa.entity.Student;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;

import java.util.Scanner;

public class OneToOneUni {

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
            transaction.begin();                             //запуск транзакции
            Student student = new Student(name, surname, avgGrade);
            Passport passport = new Passport(email, height, eyeColor);
                                                            /// @Очередность обязательна
            student.setPassport(passport);                  // 1 - передача паспорта студенту
            manager.persist(passport);                      // 2 - добавление паспорта в базу
            manager.persist(student);                       // 3 - добавление студента в базу

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
