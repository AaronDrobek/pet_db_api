package com.drobek.practice.dao.repository;




//@RepositoryDefinition(domainClass = RMSEntity.class, idClass = long.class)
//extends CrudRepository<Customer, Long>
//   https://docs.spring.io/spring-data/data-commons/docs/1.6.1.RELEASE/reference/html/repositories.html


import com.drobek.practice.dao.model.Pets;
import com.drobek.practice.dao.model.Toys;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.RepositoryDefinition;

import java.util.List;

@RepositoryDefinition(domainClass = Pets.class, idClass = int.class)
public interface PetsRepository
//        extends JpaRepository<Pets,Long>
{
    //select * from pets
    List<Pets> findAll();

    Pets findById(int id);

    Pets findByName(String name);

    void save(Pets pets);

}