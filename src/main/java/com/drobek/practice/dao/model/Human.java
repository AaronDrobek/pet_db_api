package com.drobek.practice.dao.model;


import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;


@Entity
public class Human {

    @Id
//    @GeneratedValue(strategy = GenerationType.AUTO)
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private int id;

    @Column(name = "name")
    private String name;

    @Column(name = "age")
    private int age;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "human", targetEntity = Toys.class, cascade = CascadeType.ALL)
//    @JsonIgnore
    private List<Toys> toys;

    @OneToOne(mappedBy = "human", targetEntity = Pets.class)
    @JsonIgnore
    private Pets pets;

    public Pets getPets() {
        return pets;
    }

    public void setPets(Pets pets) {
        this.pets = pets;
    }

    public List<Toys> getToys() {
        if(toys==null){
            toys = new ArrayList<>();
        }
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
