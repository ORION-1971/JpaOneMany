package com.jpa.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "passports", schema = "test_db")
public class Passport {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "email")
    private String email;

    @Column(name = "height")
    private Integer height;

    @Enumerated(EnumType.STRING)
    @Column(name = "eye_color")
    private EyeColor eyeColor;

    @OneToOne(mappedBy = "passport", cascade = CascadeType.ALL)
    private Student student;

    public Passport() {
    }

    public Passport(String email, Integer height, EyeColor eyeColor) {
        this.email = email;
        this.height = height;
        this.eyeColor = eyeColor;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Integer getHeight() {
        return height;
    }

    public void setHeight(Integer height) {
        this.height = height;
    }

    public EyeColor getEyeColor() {
        return eyeColor;
    }

    public void setEyeColor(EyeColor eyeColor) {
        this.eyeColor = eyeColor;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    @Override
    public String toString() {
        return "Passport{" +
                "id=" + id +
                ", email='" + email + '\'' +
                ", height=" + height +
                ", eyeColor='" + eyeColor + '\'' +
                '}';
    }
}
