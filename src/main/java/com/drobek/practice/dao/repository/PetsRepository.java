package com.drobek.practice.dao.repository;


import com.drobek.practice.dao.model.Pets;
import org.springframework.data.repository.RepositoryDefinition;

import java.util.List;

@RepositoryDefinition(domainClass = Pets.class, idClass = int.class)
public interface PetsRepository
{
    List<Pets> findAll();

    Pets findById(int id);

    Pets findByName(String name);

    void save(Pets pets);

}