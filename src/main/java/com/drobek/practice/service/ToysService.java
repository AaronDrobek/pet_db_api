package com.drobek.practice.service;


import com.drobek.practice.dao.model.Human;
import com.drobek.practice.dao.dto.HumanPet;
import com.drobek.practice.dao.model.Toys;
import com.drobek.practice.dao.repository.HumanRepository;
import com.drobek.practice.dao.repository.ToysRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ToysService {

    @Autowired
    private ToysRepository toysRepository;

    @Autowired
    private HumanRepository humanRepository;

    public List<Toys> getAllToys() {
        return toysRepository.findAll();
    }


    public void updateAToy(Toys toy, int toyId) {
        Toys toys = toysRepository.findById(toyId);
        toys.setName(toy.getName());
        toys.setColor(toy.getColor());
        toys.setDiscription(toy.getDiscription());
        toys.setHuman(toy.getHuman());
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

    public void updatetoyWithHuman(int toyId, Human human) {
        Toys toy = toysRepository.findById(toyId);
        toy.setHuman(human);
        toysRepository.save(toy);
    }

    public Toys findById(int toyId) {

        return toysRepository.findById(toyId);
    }

    public List<Toys> findToysRelatedToHuman(int humanId) {
        return toysRepository.findByHumanId(humanId);
    }

    public void createAToyFromTwoObjects(HumanPet humanPet) {
        Toys toy = new Toys();
        Human human = new Human();
        toy.setName(humanPet.getName());
        toy.setColor(humanPet.getColor());
        toy.setDiscription(humanPet.getDiscription());
        human.setName(humanPet.getHuman().getName());
        human.setAge(humanPet.getHuman().getAge());
        toy.setHuman(human);
        human.getToys().add(toy);
        humanRepository.save(human);

//        humanRepository.save(human);
//        toy.setHuman(human);
        Toys saved = toysRepository.save(toy);
//        updatetoyWithHuman(saved.getId(),human);

    }

//    public List<Toys> findByHuman() {
//        return toysRepository.findByHuman(human);
//    }

//    public List<Toys> findAllToysThatHaveHumans() {
//        return toysRepository.findAllToysThatHaveHuman();
//    }
}
