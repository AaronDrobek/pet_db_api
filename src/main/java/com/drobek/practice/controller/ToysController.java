package com.drobek.practice.controller;


import com.drobek.practice.dao.model.Toys;
import com.drobek.practice.service.ToysService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ToysController {


    @Autowired
    private ToysService toysService;

    @GetMapping("/toys")
    private List<Toys> getAllToys() {

        return toysService.getAllToys();
    }

    @PutMapping("/toys")
    private void updateAToy(@RequestBody Toys toy,
                            @RequestParam(name = "name", required = false) String name) {
        toysService.updateAToy(toy,name);
    }

    @PostMapping("/toys")
    private void createAToy(@RequestBody Toys toys) {
        toysService.createAToy(toys);
    }

}
