package com.drobek.practice.service;

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


}