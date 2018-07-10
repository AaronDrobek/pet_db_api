package com.drobek.practice.service;

import com.drobek.practice.dao.model.Human;
import com.drobek.practice.dao.model.Toys;
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

import static org.hamcrest.CoreMatchers.allOf;
import static org.hamcrest.beans.HasPropertyWithValue.hasProperty;
import static org.junit.Assert.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;


@RunWith(JUnitParamsRunner.class)
public class ToysServiceTest {

    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);
    }

    @InjectMocks
    private ToysService toysService;

    @Mock
    private ToysRepository toysRepository;

    @Test
    public void givenAnIdAndToyObjectVarifyToyObjectIsFoundAndNewToyIsSaved() {
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

}