package com.drobek.practice.service;


import com.drobek.practice.dao.model.Human;
import com.drobek.practice.dao.model.Pets;
import com.drobek.practice.dao.repository.HumanRepository;
import com.drobek.practice.dao.repository.PetsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PetsService {

    @Autowired
    private PetsRepository petsRepository;

    @Autowired
    private HumanRepository humanRepository;


    public List<Pets> getAllPets() {

        return petsRepository.findAll();
    }


    public void updateAPet(Pets pet, String name) {
        Pets pets = petsRepository.findByName(name);
        pets.setName(pet.getName());
        pets.setBreed(pet.getBreed());
        pets.setColor(pet.getColor());
        petsRepository.save(pets);
    }



    public void createAPet(Pets pet) {
        Pets pets = new Pets();
        pets.setBreed(pet.getBreed());
        pets.setColor(pet.getColor());
        pets.setName(pet.getName());
        petsRepository.save(pets);
    }


    public void updatePetWithHuman(int petId, Human human) {
        Pets pet = petsRepository.findById(petId);
        pet.setHuman(human);
        petsRepository.save(pet);
        }
}
