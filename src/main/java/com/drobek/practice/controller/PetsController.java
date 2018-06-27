package com.drobek.practice.controller;


import com.drobek.practice.model.Pets;
import com.drobek.practice.repository.PetsRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class PetsController {
    private Logger LOG = LoggerFactory.getLogger(PetsController.class);

    @Autowired
    private PetsRepository petsRepository;

    @GetMapping("/pets")
    public List<Pets> getAllPets() {

        return petsRepository.findAll();
    }

    @PutMapping("/pets")
    private void updateAPet(@RequestBody Pets pet,
                            @RequestParam(name = "name", required = false) String name) {
        Pets pets = petsRepository.findByName(name);
        pets.setName(pet.getName());
        pets.setBreed(pet.getBreed());
        pets.setColor(pet.getColor());
        petsRepository.save(pets);
    }

    @PostMapping("/pets")
    private void createAPet(@RequestBody Pets pet) {
        Pets pets = new Pets();
        pets.setBreed(pet.getBreed());
        pets.setColor(pet.getColor());
        pets.setName(pet.getName());
        petsRepository.save(pets);
    }


}