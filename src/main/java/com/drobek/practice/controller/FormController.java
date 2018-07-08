package com.drobek.practice.controller;


import com.drobek.practice.dao.model.Human;
import com.drobek.practice.dao.model.HumanPet;
import com.drobek.practice.dao.model.Toys;
import com.drobek.practice.dao.repository.HumanRepository;
import com.drobek.practice.dao.repository.ToysRepository;
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

@Controller
public class FormController {
    private Logger LOG = LoggerFactory.getLogger(HumanController.class);

    @Autowired
    HumanService humanService;
    @Autowired
    ToysService toysService;
//    @Autowired
//    HumanRepository humanRepository;
//    @Autowired
//    ToysRepository toysRepository;






    @GetMapping("/addcustomer")
    public String greetingForm(Model model) {
        HumanPet humanPet = new HumanPet();
model.addAttribute("humanPet", humanPet);
//        model.addAttribute("greeting", new Greeting());
//        humanPet.setName("name");
//        humanPet.getHuman().setName("humanPet.name");
        return "newcustomer";
    }

    @RequestMapping(value = "addcustomer", method = RequestMethod.POST)
    public String takeTwoObjectsCreateNewHumanAndToy(@ModelAttribute(value = "humanPet") HumanPet humanPet){
        LOG.info(humanPet.getHuman().getName() + " ------------------------------------");
        LOG.info(humanPet.getName()+" ======================");

        toysService.createAToyFromTwoObjects(humanPet);

        LOG.info(humanPet.getHuman().getName() + " ------------------------------------");
        LOG.info(humanPet.getName()+" ======================");


//        String response= humanPet.getHuman().getName() + " has been added successfully ";
        return "customerlist";

    }

//    @RequestMapping(value = "customerlist", method = RequestMethod.GET)
//    public List <String> customerList(@ModelAttribute(value ="human")Human human) {
//        List<String> onlyName = new ArrayList<>();
//        List<Human> allHuman = humanService.getAllHumans();
//        for (final Human eachHuman : allHuman) {
//            LOG.info(eachHuman.getName());
//            onlyName.add(eachHuman.getName());
//        }
//        return onlyName;
//
//    }

    @RequestMapping("/customerlist")
public String listOfAllCustomers(Model model){
        model.addAttribute("human",humanService.getAllHumans());
        LOG.info("human" + "this is list of humans???");
        return "/customerlist";
    }

}

//@ModelAttribute
//Toys toy = new Toys();
//    Human human = new Human();
//        toy.setName(humanPet.getName());
//                toy.setColor(humanPet.getColor());
//                toy.setDiscription(humanPet.getDiscription());
//                human.setName(humanPet.getHuman().getName());
//                human.setAge(humanPet.getHuman().getAge());
//                humanRepository.save(human);
//                toysRepository.save(toy);
//                toysService.updatetoyWithHuman(toy.getId(),human);