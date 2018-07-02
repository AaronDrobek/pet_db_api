package com.drobek.practice.dao.repository;


import com.drobek.practice.dao.model.Toys;
import org.springframework.data.repository.RepositoryDefinition;

import java.util.List;

@RepositoryDefinition(domainClass = Toys.class, idClass = int.class)
public interface ToysRepository {
    List<Toys> findAll();

    Toys findById(int id);

    Toys findByName(String name);

    void save(Toys toys);

}
