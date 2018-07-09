package com.drobek.practice.controller;


import com.drobek.practice.dao.model.Human;
import com.drobek.practice.dao.model.HumanPet;
import com.drobek.practice.dao.model.Toys;
import com.drobek.practice.service.HumanService;
import com.drobek.practice.service.ToysService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
//@Controller
public class ToysController {
    private Logger LOG = LoggerFactory.getLogger(HumanController.class);


    @Autowired
    private ToysService toysService;

    @Autowired
    private HumanService humanService;

    @GetMapping("/toys")
    private List<Toys> getAllToys() {

        return toysService.getAllToys();
    }

    @PutMapping("/toys")
    private void updateAToy(@RequestBody Toys toy,
                            @RequestParam(name = "id", required = false) int toyId) {
        toysService.updateAToy(toy, toyId);
    }

    @PostMapping("/toys")
    private void createAToy(@RequestBody Toys toys) {

        toysService.createAToy(toys);
    }

    @PutMapping("/toys/{toyId}/owner")
    public void assignToyToOwner(@PathVariable("toyId") int toyId,
                                 @RequestParam(name = "ownerName", required = false) String ownerName) {

        Human human = humanService.findHumanByName(ownerName);
        LOG.info(human + "");
//        Toys toy = toysService.findById(toyId);
//        humanService.updateToyWithHuman(human.getId(),toy);
        toysService.updatetoyWithHuman(toyId, human);
    }

    @GetMapping("/toys/byHumanId")
    public List<Toys> byHumanId(@RequestParam(name = "humanId") int humanId) {
        return toysService.findToysRelatedToHuman(humanId);
    }

    @GetMapping("/allcustomertoys")
    public List<String> allToysPerCustomers() {
        List<String> ownerToyName = new ArrayList<>();
        List<Toys> toyList = toysService.getAllToys();

        for (final Toys eachtoy : toyList) {
            if (eachtoy.getHuman() != null) {
                ownerToyName.add(eachtoy.getHuman().getName());
                ownerToyName.add(eachtoy.getName());
            }
        }
        LOG.info(ownerToyName + " onwerToyName");

        return ownerToyName;
    }

    @PostMapping("newcustomer")
    public String takeTwoObjectsCreateNewHumanAndToy(@RequestBody HumanPet humanPet) {
//        LOG.info(humanPet.getHuman().getName());
//        humanService.createAHumanFromTwoObjects(humanPet);
        toysService.createAToyFromTwoObjects(humanPet);

        String response = humanPet.getHuman().getName() + " has been added successfully ";
        return response;

    }


}
