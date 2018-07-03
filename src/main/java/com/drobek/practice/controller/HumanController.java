package com.drobek.practice.controller;


import com.drobek.practice.dao.model.Human;
import com.drobek.practice.dao.model.Toys;
import com.drobek.practice.service.HumanService;
import com.drobek.practice.service.ToysService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class HumanController {
    private Logger LOG = LoggerFactory.getLogger(HumanController.class);

    @Autowired
    private HumanService humanService;

    @Autowired
    private ToysService toysService;


    @GetMapping("/human")
    protected List<Human> getAllHumans() {

        return humanService.getAllHumans();
    }

    @GetMapping("/customers")
    protected List<String> getAllCustomers() {
        List<String> onlyName = new ArrayList<>();
        List<Human> allHuman = humanService.getAllHumans();

        for (final Human eachHuman : allHuman) {
            LOG.info(eachHuman.getName());
            onlyName.add(eachHuman.getName());
        }

        return onlyName;
    }

    @PutMapping("/human")
    private void updateAHuman(@RequestBody Human human,
                              @RequestParam(name = "name", required = false) String name) {
        humanService.updateAHuman(human, name);
    }

    @PostMapping("/human")
    private void createAHuman(@RequestBody Human human) {
        humanService.createAHuman(human);
    }


    //add 2 objects through post human with toy

    //    @PostMapping("/human/toys")
//    private void creatHumanWithToy(@RequestBody Human human,
//                                   @RequestBody Toys toy) {
//        humanService.createAHuman(human);
//        toysService.createAToy(toy);
//        toysService.updatetoyWithHuman(toy.getId(), human);
//
//
//    }
    @GetMapping("/toysforhuman")
    private List<String> getToysRelatedToHuman() {
        toysService.getAllToys();
        Human newHuman = humanService.findHumanByName("greg");
        List<Toys> theToys = newHuman.getToys();
        List<String> onlyName = new ArrayList<>();

        for (final Toys eachToy : theToys) {
            onlyName.add(eachToy.getName());
        }
        return onlyName;
    }

}
