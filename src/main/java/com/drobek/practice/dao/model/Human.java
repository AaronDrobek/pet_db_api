package com.drobek.practice.dao.model;


import javax.persistence.*;
import java.util.List;

@Entity
public class Human {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Column(name = "name")
    private String name;

    @Column(name = "age")
    private int age;

    @OneToMany
    private List<Toys> toys;


    public List<Toys> getToys() {
        return toys;
    }

    public void setToys(List<Toys> toys) {
        this.toys = toys;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}
