package com.drobek.practice.service;

import com.drobek.practice.dao.model.Human;
import com.drobek.practice.dao.model.Toys;
import com.drobek.practice.dao.repository.HumanRepository;
import junitparams.JUnitParamsRunner;
import org.hamcrest.CoreMatchers;
import org.hamcrest.core.Is;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.beans.HasPropertyWithValue.hasProperty;
import static org.hamcrest.core.AllOf.allOf;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;


@RunWith(JUnitParamsRunner.class)
public class HumanServiceTest {


    @InjectMocks
    private HumanService humanService;

    @Mock
    private HumanRepository humanRepository;

    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);
    }


    @Test
    public void givenANameAndHumanObjectVerifyHumanObjectIsFoundAndNewHumanIsSaved() {
        Human humanFromDb = new Human();
        humanFromDb.setName("tom");
        humanFromDb.setAge(22);

        when(humanRepository.findByName("tom")).thenReturn(humanFromDb);

        Human newHuman = new Human();
        newHuman.setName("tommy");
        newHuman.setAge(23);

        humanService.updateAHuman(newHuman, "tom");

        ArgumentCaptor<Human> argCaptor = ArgumentCaptor.forClass(Human.class);
        verify(humanRepository).save(argCaptor.capture());

        Human captured = argCaptor.getValue();
        assertThat(captured, allOf(
                hasProperty("name", Is.is("tommy")),
                hasProperty("age", Is.is(23))
        ));
    }


    @Test
    public void givenHumanObjectVerifyTheObjectInputMatchesTheObjectSaved() {
        Human humanToCreate = new Human();
        humanToCreate.setName("paul");
        humanToCreate.setAge(41);

        humanService.createAHuman(humanToCreate);

        ArgumentCaptor<Human> argCaptor = ArgumentCaptor.forClass(Human.class);
        verify(humanRepository).save(argCaptor.capture());

        Human captured = argCaptor.getValue();
        assertThat(captured, allOf(
                hasProperty("name", Is.is("paul")),
                hasProperty("age", Is.is(41))
        ));
    }

    @Test
    public void givenACallToReturnListOfAllHumansWhenMethodIsCalledReturnCompleteListOfHumans() {
        List<Human> testList = new ArrayList<>();
        Human human1 = new Human();
        human1.setName("tom");
        human1.setAge(33);
        testList.add(human1);
        Human human2 = new Human();
        human2.setName("lucy");
        human2.setAge(55);
        testList.add(human2);

        when(humanRepository.findAll()).thenReturn(testList);

        List<Human> listToEvaluate = humanService.getAllHumans();

        Assert.assertThat(listToEvaluate.get(0), CoreMatchers.allOf(
                hasProperty("name", Is.is("tom")),
                hasProperty("age", Is.is(33))

        ));
        Assert.assertThat(listToEvaluate.get(1), CoreMatchers.allOf(
                hasProperty("name", Is.is("lucy")),
                hasProperty("age", Is.is(55))

        ));

    }


}