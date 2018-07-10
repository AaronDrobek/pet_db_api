package com.drobek.practice.controller;


import com.drobek.practice.dao.dto.HumanPet;
import com.drobek.practice.dao.model.Human;
import com.drobek.practice.dao.model.Toys;
import com.drobek.practice.dao.repository.HumanRepository;
import com.drobek.practice.service.HumanService;
import com.drobek.practice.service.ToysService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class FormController {
    private Logger LOG = LoggerFactory.getLogger(HumanController.class);

    @Autowired
    HumanService humanService;
    @Autowired
    ToysService toysService;
    @Autowired
    HumanRepository humanRepository;


    @GetMapping("/addcustomer")
    public String greetingForm(Model model) {
        HumanPet humanPet = new HumanPet();
        model.addAttribute("humanPet", humanPet);
        return "newcustomer";
    }

    @RequestMapping(value = "addcustomer", method = RequestMethod.POST)
    public String takeTwoObjectsCreateNewHumanAndToy(@ModelAttribute(value = "humanPet") HumanPet humanPet) {
        LOG.info(humanPet.getHuman().getName() + " ------------------------------------");
        LOG.info(humanPet.getName() + " ======================");
        toysService.createAToyFromTwoObjects(humanPet);
        return "redirect:/customerlist";
    }


    @RequestMapping("/customerlist")
    public String listOfAllCustomers(Model model) {
        Human human = new Human();
        model.addAttribute("human", humanService.getAllHumans());
        LOG.info(human.getToys() + " this is human toys");

        return "/customerlist";
    }

    @RequestMapping("/toyslist")
    public String listOfAllToys(Model model) {
        Toys toys = new Toys();
        model.addAttribute("toys", toysService.getAllToys());
        return "/toyslist";
    }

    @RequestMapping("/home")
    public String showHomeView() {
        return "homeview";
    }

    @RequestMapping("/addtoy")
    public String showFormToAddNewToy(Model model) {
        return null;
    }

    @RequestMapping(value = "toyslist", method = RequestMethod.POST)
//    public String deleteACustomerAndToy(@PathVariable) {
    public String deleteACustomerAndToy(@ModelAttribute(value = "toys") Toys toys) {
        LOG.info(toys.getName() + " ------------------------------------");
        LOG.info(toys.getHuman().getName() + " ------------------------------------");
        LOG.info(toys.getId() + " ::::::::::::::::::::::::::::::::::::");

        return "redirect:/toyslist";
    }
}

