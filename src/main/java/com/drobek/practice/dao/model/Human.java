package com.drobek.practice.dao.model;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.util.List;

//@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})

@Entity
public class Human {
//    @Override
//    public String toString() {
//        return "Human{" +
//                "id=" + id +
//                ", name='" + name + '\'' +
//                ", age=" + age +
//                '}';
//    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Column(name = "name")
    private String name;

    @Column(name = "age")
    private int age;

    @OneToMany(fetch = FetchType.EAGER , mappedBy = "human", targetEntity=Toys.class)
//    @JsonIgnore
    private List <Toys> toys;

    @OneToOne(mappedBy = "human",targetEntity=Pets.class)
    @JsonIgnore
    private Pets pets;

    public Pets getPets() {
        return pets;
    }

    public void setPets(Pets pets) {
        this.pets = pets;
    }

    public List<Toys> getToys() {
        return toys;
    }

    public void setToys(List<Toys> toys) {
        this.toys = toys;
    }

//
//    @OneToMany(mappedBy = "human", cascade = CascadeType.ALL,fetch = FetchType.EAGER)
//    private List<Toys> toys;


//    public List<Toys> getToys() {
//        return toys;
//    }
//
//    public void setToys(List<Toys> toys) {
//        this.toys = toys;
//    }

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
