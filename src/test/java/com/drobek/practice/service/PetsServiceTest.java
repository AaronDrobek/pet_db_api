package com.drobek.practice.service;

import com.drobek.practice.dao.model.Human;
import com.drobek.practice.dao.model.Pets;
import com.drobek.practice.dao.repository.PetsRepository;
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
import java.util.List;

import static org.hamcrest.CoreMatchers.allOf;
import static org.hamcrest.beans.HasPropertyWithValue.hasProperty;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(JUnitParamsRunner.class)
public class PetsServiceTest {

    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);
    }

    @Mock
    private PetsRepository petsRepository;

    @InjectMocks
    private PetsService petsService;

    @Test
    public void givenAPetNameAndPetObjectVarifyPetObjectIsFoundAndNewPetIsSaved() {
        Pets petInDb = new Pets();
        petInDb.setName("charlie");
        petInDb.setBreed("corgi");
        petInDb.setColor("blonde");

        when(petsRepository.findByName("charlie")).thenReturn(petInDb);

        Pets petToUpdate = new Pets();
        petToUpdate.setName("charlie");
        petToUpdate.setBreed("corgi");
        petToUpdate.setColor("grey");

        petsService.updateAPet(petToUpdate, "charlie");

        ArgumentCaptor<Pets> argCaptor = ArgumentCaptor.forClass(Pets.class);
        verify(petsRepository).save(argCaptor.capture());

        Pets capturedPet = argCaptor.getValue();
        assertThat(capturedPet, allOf(
                hasProperty("name", Is.is("charlie")),
                hasProperty("breed", Is.is("corgi")),
                hasProperty("color", Is.is("grey"))
        ));
    }

    @Test
    public void givenPetObjectVerifyTheObjectInputMatchesTheObjectSaved() {
        Pets petToCreate = new Pets();
        petToCreate.setName("trixie");
        petToCreate.setBreed("poodle");
        petToCreate.setColor("black");

        petsService.createAPet(petToCreate);

        ArgumentCaptor<Pets> argCaptor = ArgumentCaptor.forClass(Pets.class);
        verify(petsRepository).save(argCaptor.capture());

        Pets capturedPet = argCaptor.getValue();
        assertThat(capturedPet, allOf(
                hasProperty("name", Is.is("trixie")),
                hasProperty("breed", Is.is("poodle")),
                hasProperty("color", Is.is("black"))
        ));
    }

    @Test
    public void givenObjectHumanAndPetByIdAssignHumanObjectToHumanFieldInPetEntity() {
        Human passedHuman = new Human();
        passedHuman.setName("joe");
        passedHuman.setAge(33);

        Pets petToAppend = new Pets();
        petToAppend.setId(4);
        petToAppend.setName("charlie");
        petToAppend.setBreed("corgi");
        petToAppend.setColor("grey");

        when(petsRepository.findById(4)).thenReturn(petToAppend);

        petsService.updatePetWithHuman(4, passedHuman);

        ArgumentCaptor<Pets> argCaptor = ArgumentCaptor.forClass(Pets.class);
        verify(petsRepository).save(argCaptor.capture());

        Pets capturedPet = argCaptor.getValue();
        assertThat(capturedPet, allOf(
                hasProperty("id", Is.is(4)),
                hasProperty("name", Is.is("charlie")),
                hasProperty("breed", Is.is("corgi")),
                hasProperty("color", Is.is("grey")),
                hasProperty("human", (hasProperty("name", Is.is("joe")))),
                hasProperty("human", (hasProperty("age", Is.is(33))))
        ));
    }

    @Test
    public void givenACallToReturnListOfAllPetsWhenMethodIsCalledReturnCompleteListOfPets() {
        List<Pets> testListPets = new ArrayList<>();
        Pets pet1 = new Pets();
        pet1.setName("charlie");
        pet1.setBreed("corgi");
        pet1.setColor("grey");
        testListPets.add(pet1);
        Pets pet2 = new Pets();
        pet2.setName("missy");
        pet2.setBreed("deerhead");
        pet2.setColor("black");
        testListPets.add(pet2);

        when(petsRepository.findAll()).thenReturn(testListPets);

        List<Pets> listToEvaluate = petsService.getAllPets();

        assertThat(listToEvaluate.get(0),allOf(
                hasProperty("name",Is.is("charlie")),
                hasProperty("breed",Is.is("corgi")),
                hasProperty("color",Is.is("grey"))
        ));
assertThat(listToEvaluate.get(1),allOf(
        hasProperty("name",Is.is("missy")),
        hasProperty("breed",Is.is("deerhead")),
        hasProperty("color",Is.is("black"))
));
    }

}