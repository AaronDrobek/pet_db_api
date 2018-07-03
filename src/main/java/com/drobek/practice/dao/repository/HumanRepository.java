package com.drobek.practice.dao.repository;

import com.drobek.practice.dao.model.Human;
import com.drobek.practice.dao.model.Toys;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.RepositoryDefinition;

import java.util.List;

@RepositoryDefinition(domainClass = Human.class, idClass = int.class)
public interface HumanRepository
//        extends JpaRepository<Human,Long>
{

    List<Human> findAll();

    Human findByName(String name);

    void save(Human human);

    Human findById(int humanId);

}
