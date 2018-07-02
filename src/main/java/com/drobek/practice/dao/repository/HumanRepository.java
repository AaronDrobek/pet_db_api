package com.drobek.practice.dao.repository;

import com.drobek.practice.dao.model.Human;
import org.springframework.data.repository.RepositoryDefinition;

import java.util.List;

@RepositoryDefinition(domainClass = Human.class, idClass = int.class)
public interface HumanRepository {

    List<Human> findAll();

    Human findByName(String name);

    void save(Human human);

    Human findById(int humanId);

}
