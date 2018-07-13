package com.drobek.practice.service;

import com.drobek.practice.dao.dto.HumanPet;
import com.drobek.practice.dao.model.Human;
import com.drobek.practice.dao.model.Toys;
import com.drobek.practice.dao.repository.HumanRepository;
import com.drobek.practice.dao.repository.ToysRepository;
import junitparams.JUnitParamsRunner;
import org.hamcrest.core.Is;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.hamcrest.CoreMatchers.allOf;
import static org.hamcrest.Matchers.contains;
import static org.hamcrest.Matchers.hasItem;
import static org.hamcrest.Matchers.hasItemInArray;
import static org.hamcrest.beans.HasPropertyWithValue.hasProperty;
import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.internal.verification.VerificationModeFactory.times;


@RunWith(JUnitParamsRunner.class)
public class ToysServiceTest {

    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);
    }

    @InjectMocks
    private ToysService toysService;
    @InjectMocks
    private HumanPet humanPet;

    @Mock
    private ToysRepository toysRepository;
    @Mock
    private HumanRepository humanRepository;

    //updateAToy
    @Test
    public void givenAnIdAndToyObjectVerifyToyObjectIsFoundAndNewToyIsSaved() {
        Toys toysFromDb = new Toys();
        toysFromDb.setId(3);
        toysFromDb.setName("yoyo");
        toysFromDb.setColor("yellow");
        toysFromDb.setDiscription("toy on a string");

        when(toysRepository.findById(3)).thenReturn(toysFromDb);

        Toys toyToUpdate = new Toys();
        toyToUpdate.setId(3);
        toyToUpdate.setName("yoyo");
        toyToUpdate.setColor("green");
        toyToUpdate.setDiscription("toy on a string");

        toysService.updateAToy(toyToUpdate, 3);

        ArgumentCaptor<Toys> argCaptor = ArgumentCaptor.forClass(Toys.class);
        verify(toysRepository).save(argCaptor.capture());

        Toys capturedToy = argCaptor.getValue();
        assertThat(capturedToy, allOf(
                hasProperty("id", Is.is(3)),
                hasProperty("name", Is.is("yoyo")),
                hasProperty("color", Is.is("green")),
                hasProperty("discription", Is.is("toy on a string"))
        ));
    }

    //createAToy
    @Test
    public void givenToyObjectVerifyTheObjectInputMatchesTheObjectSaved() {
        Toys toyToCreate = new Toys();
        toyToCreate.setName("plastic bone");
        toyToCreate.setColor("white");
        toyToCreate.setDiscription("squeaky toy");

        toysService.createAToy(toyToCreate);

        ArgumentCaptor<Toys> argCaptor = ArgumentCaptor.forClass(Toys.class);
        verify(toysRepository).save(argCaptor.capture());

        Toys capturedToy = argCaptor.getValue();
        assertThat(capturedToy, allOf(
                hasProperty("name", Is.is("plastic bone")),
                hasProperty("color", Is.is("white")),
                hasProperty("discription", Is.is("squeaky toy"))
        ));
    }

    //findById
    @Test
    public void givenTheIdOfAToyReturnToyObjectBelongingToThatId() {
        Toys testToy = new Toys();
        testToy.setId(6);
        testToy.setName("bone");
        testToy.setColor("white");
        testToy.setDiscription("rubber chew toy");

        when(toysRepository.findById(6)).thenReturn(testToy);

        Toys toyToEvaluate = toysService.findById(6);

        assertThat(toyToEvaluate, allOf(
                hasProperty("name", Is.is("bone")),
                hasProperty("color", Is.is("white")),
                hasProperty("discription", Is.is("rubber chew toy"))
        ));
    }

    //getAllToys
    @Test
    public void givenACallToReturnListOfAllToysWhenMethodIsCalledReturnCompleteListOfToys() {
        List<Toys> testList = new ArrayList<>();
        Toys toy1 = new Toys();
        toy1.setName("yoyo");
        toy1.setColor("red");
        toy1.setDiscription("string toy");
        testList.add(toy1);
        Toys toy2 = new Toys();
        toy2.setName("frisbee");
        toy2.setColor("blue");
        toy2.setDiscription("flying disc");
        testList.add(toy2);

        when(toysRepository.findAll()).thenReturn(testList);

        List<Toys> listToysToEvaluate = toysService.getAllToys();

        assertThat(listToysToEvaluate.get(0), allOf(
                hasProperty("name", Is.is("yoyo")),
                hasProperty("color", Is.is("red")),
                hasProperty("discription", Is.is("string toy"))
        ));
        assertThat(listToysToEvaluate.get(1), allOf(
                hasProperty("name", Is.is("frisbee")),
                hasProperty("color", Is.is("blue")),
                hasProperty("discription", Is.is("flying disc"))
        ));

    }

    //updatetoyWithHuman
    @Test
    public void givenAToyIdAndHumanObjectAsParamsWhenMethodIsCalledAsignToyObjectToHuman() {
        Human passedHuman = new Human();
        passedHuman.setName("rickey");
        passedHuman.setAge(27);

        Toys passedToy = new Toys();
        passedToy.setId(2);
        passedToy.setName("jack in the box");
        passedToy.setColor("red");
        passedToy.setDiscription("wind up toy");

        when(toysRepository.findById(2)).thenReturn(passedToy);

        toysService.updatetoyWithHuman(2, passedHuman);

        ArgumentCaptor<Toys> argCaptor = ArgumentCaptor.forClass(Toys.class);
        verify(toysRepository).save(argCaptor.capture());

        Toys toysCaptured = argCaptor.getValue();
        assertThat(toysCaptured, allOf(
                hasProperty("id", Is.is(2)),
                hasProperty("name", Is.is("jack in the box")),
                hasProperty("color", Is.is("red")),
                hasProperty("discription", Is.is("wind up toy")),
                hasProperty("human", hasProperty("name", Is.is("rickey"))),
                hasProperty("human", hasProperty("age", Is.is(27)))

        ));

    }

    //findToyByName
    @Test
    public void givenAToyNameAsParameterWhenMethodIsCalledReturnToyObjectWithParameterName() {
        Toys toyToTest = new Toys();
        toyToTest.setName("barney");
        toyToTest.setColor("purple");
        toyToTest.setDiscription("dinosaur");

        when(toysRepository.findByName(anyString())).thenReturn(toyToTest);

        Toys toyToEvaluate = toysService.findToyByName("barney");

        assertThat(toyToEvaluate, allOf(
                hasProperty("name", Is.is("barney")),
                hasProperty("color", Is.is("purple")),
                hasProperty("discription", Is.is("dinosaur"))
        ));
    }

    //findToysRelatedToHuman
    @Test
    public void givenAHumanIdAsParamWhenMethodExecutesReturnListOfToysRelatedToThatHuman() {
        Human testHuman = new Human();
        testHuman.setId(1);
        testHuman.setName("joey");
        testHuman.setAge(45);
        List<Toys> testList = new ArrayList<>();
        Toys testToy1 = new Toys();
        testToy1.setName("yoyo");
        testToy1.setColor("yellow");
        testToy1.setDiscription("string toy");
        testToy1.setHuman(testHuman);
        testList.add(testToy1);
        Toys testToy2 = new Toys();
        testToy2.setName("ball");
        testToy2.setColor("green");
        testToy2.setDiscription("tennis ball");
        testToy2.setHuman(testHuman);
        testList.add(testToy2);

        when(toysRepository.findByHumanId(1)).thenReturn(testList);

        List<Toys> listToEvaluate = toysService.findToysRelatedToHuman(1);

        assertThat(listToEvaluate.get(0), allOf(
                hasProperty("name", Is.is("yoyo")),
                hasProperty("color", Is.is("yellow")),
                hasProperty("discription", Is.is("string toy"))
        ));
        assertThat(listToEvaluate.get(1), allOf(
                hasProperty("name", Is.is("ball")),
                hasProperty("color", Is.is("green")),
                hasProperty("discription", Is.is("tennis ball"))
        ));

    }

    //createAToyFromTwoObjects
    @Test
    public void givenAnObjectWithANestedObjectWhenTheMethodIsCalledCreateAToyObjectAndAHumanObjectRelatingThatToyWithThatHuman() {
        List<Toys> testList = new ArrayList<>();
        Toys testToy = new Toys();
        testToy.setName("pogo stick");
        testToy.setColor("red");
        testToy.setDiscription("spring loaded pole");
        testList.add(testToy);

        Human testHuman = new Human();
        testHuman.setName("tony");
        testHuman.setAge(23);
        testHuman.setToys(testList);


        HumanPet testHumanPet = new HumanPet();
        testHumanPet.setName("pogo stick");
        testHumanPet.setColor("red");
        testHumanPet.setDiscription("spring loaded pole");
        testHumanPet.setHuman(testHuman);


        when(toysRepository.findById(anyInt())).thenReturn(new Toys());


        toysService.createAToyFromTwoObjects(testHumanPet);


        ArgumentCaptor<Human> argCaptor = ArgumentCaptor.forClass(Human.class);
        verify(humanRepository).save(argCaptor.capture());

        Human humanCaptured = argCaptor.getValue();
        assertThat(humanCaptured, allOf(
                hasProperty("name", Is.is("tony")),
                hasProperty("age", Is.is(23)),
                hasProperty("toys", hasItem(allOf(
                        hasProperty("name", Is.is("pogo stick")),
                        hasProperty("color", Is.is("red")),
                        hasProperty("discription", Is.is("spring loaded pole"))

                )))));


    }


}