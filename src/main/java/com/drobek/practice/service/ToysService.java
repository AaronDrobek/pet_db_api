package com.drobek.practice.service;


import com.drobek.practice.dao.model.Pets;
import com.drobek.practice.dao.model.Toys;
import com.drobek.practice.dao.repository.ToysRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ToysService {


   @Autowired
   private ToysRepository toysRepository;

    public List<Toys> getAllToys() {
       return toysRepository.findAll();
    }


    public void updateAToy(Toys toy, String name) {
        Toys toys = toysRepository.findByName(name);
        toys.setName(toy.getName());
        toys.setColor(toy.getColor());
        toys.setDiscription(toy.getDiscription());
        toysRepository.save(toys);

    }

    public void createAToy(Toys toy) {
        Toys toys = new Toys();
        toys.setName(toy.getName());
        toys.setColor(toy.getColor());
        toys.setDiscription(toy.getDiscription());
        toysRepository.save(toys);

    }

    public Toys findToyByName(String toyname) {
        return toysRepository.findByName(toyname);

    }
}
