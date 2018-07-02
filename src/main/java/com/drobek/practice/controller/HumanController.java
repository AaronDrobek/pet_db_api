package com.drobek.practice.controller;


import com.drobek.practice.dao.model.Human;
import com.drobek.practice.dao.model.Toys;
import com.drobek.practice.service.HumanService;
import com.drobek.practice.service.ToysService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class HumanController {
    private Logger LOG = LoggerFactory.getLogger(HumanController.class);

    @Autowired
    private HumanService humanService;

    @Autowired
    private ToysService toysService;


    @GetMapping("/human")
    protected List<Human> getAllHumans(){

        return humanService.getAllHumans();
    }

    @PutMapping("/human")
    private void updateAHuman(@RequestBody Human human,
                            @RequestParam(name = "name", required = false) String name) {
       humanService.updateAHuman(human,name);
    }

    @PostMapping("/human")
    private void createAHuman(@RequestBody Human human) {
        humanService.createAHuman(human);
    }

    @PutMapping("/human/{humanId}/nametoy")
    private void asignToyToHuman(@PathVariable ("humanId")int humanId,
                                 @RequestParam(name = "toyname",required = false)String toyname){

        Toys toy = toysService.findToyByName(toyname);
        humanService.updateHumanWithToy(humanId,toy);


    }

}
