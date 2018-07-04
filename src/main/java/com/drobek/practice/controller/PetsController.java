package com.drobek.practice.controller;


import com.drobek.practice.dao.model.Human;
import com.drobek.practice.dao.model.Pets;
import com.drobek.practice.dao.model.Toys;
import com.drobek.practice.service.HumanService;
import com.drobek.practice.service.PetsService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class PetsController {
    private Logger LOG = LoggerFactory.getLogger(PetsController.class);

    @Autowired
    private PetsService petsService;

    @Autowired
    private HumanService humanService;

    @GetMapping("/pets")
    private List<Pets> getAllPets() {
        return petsService.getAllPets();
    }

    @PutMapping("/pets")
    private void updateAPet(@RequestBody Pets pet,
                            @RequestParam(name = "name", required = false) String name) {
        petsService.updateAPet(pet, name);
    }

    @PostMapping("/pets")
    private void createAPet(@RequestBody Pets pet) {
        petsService.createAPet(pet);
    }


    @PutMapping("/pets/{petId}/owner")
    private void assignPetToOwner(@PathVariable("petId") int petId,
                                  @RequestParam(name = "ownerName", required = false) String ownerName) {

        Human human = humanService.findHumanByName(ownerName);
        petsService.updatePetWithHuman(petId, human);

    }


//    @GetMapping("petstohuman")
//    private Pets getTheDogThatBelongsToHuman(@RequestParam(name = "name", required = false) String name) {
//        petsService.getAllPets();
//        Human newHuman = humanService.findHumanByName(name);
//        Pets pet = newHuman.getPets();
//        return pet;
//    }
    @GetMapping("petstohuman")
    private List <String> getTheDogThatBelongsToHuman(@RequestParam(name = "name", required = false) String name) {
        List <String> petsName = new ArrayList<>();
        petsService.getAllPets();
        Human newHuman = humanService.findHumanByName(name);
        Pets pet = newHuman.getPets();
        petsName.add(pet.getName());


        return petsName;
    }

}