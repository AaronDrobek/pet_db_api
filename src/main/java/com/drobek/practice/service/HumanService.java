package com.drobek.practice.service;

import com.drobek.practice.dao.model.Human;
import com.drobek.practice.dao.model.Toys;
import com.drobek.practice.dao.repository.HumanRepository;
import com.drobek.practice.dao.repository.ToysRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HumanService {

    @Autowired
    private HumanRepository humanRepository;

    @Autowired
    private ToysRepository toysRepository;

    public List<Human> getAllHumans() {
        return humanRepository.findAll();
    }

    public void updateAHuman(Human newHuman, String name) {
        Human human = humanRepository.findByName(name);
        human.setName(newHuman.getName());
        human.setAge(newHuman.getAge());
        humanRepository.save(human);
    }

    public void createAHuman(Human human) {
        Human humans = new Human();
        humans.setName(human.getName());
        humans.setAge(human.getAge());
        humanRepository.save(human);
    }


    public Human findHumanByName(String ownerName) {
      return  humanRepository.findByName(ownerName);
      }

    public void addHumanAndToy(Human human, Toys toy) {
        humanRepository.save(human);
        toysRepository.save(toy);

    }

}
