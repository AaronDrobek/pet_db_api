package com.drobek.practice.dao.repository;


import com.drobek.practice.dao.model.Human;
import com.drobek.practice.dao.model.Toys;
import org.hibernate.annotations.NamedQuery;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.RepositoryDefinition;
import org.springframework.data.repository.query.Param;

import java.util.List;

@RepositoryDefinition(domainClass = Toys.class, idClass = int.class)
public interface ToysRepository
//        extends JpaRepository<Toys,Long>
{
    List<Toys> findAll();

    Toys findById(int id);

    Toys findByName(String name);

    List<Toys> findByHumanId(int humanId);

//    String get_all_toys_for_humans = "select t from Toys as t join Human where human.id=human_id";
//
//    @Query(get_all_toys_for_humans)
//            List <Toys> getAllToys();

//    @NamedQuery(
//            name = "Toys.findAllToys",
//            query =
//                    "SELECT FROM Toys" +
//                            "INNER JOIN Human WHERE human.id=toys.human_id"
//    )

//    String Q_GET_ALL_USERS = "from User u left join Role r on u.role_id=r.id";
//
//    @Query(Q_GET_ALL_USERS)
//    Collection<User> getAllUsers();

//    List <Toys> findAllToysThatHaveHuman(){
//     return findAll(where())
//    }
//
//    @Query("select * from Toys join Human where human.id=toys.human_id")
//    List <Toys> findByHuman(@Param("toys.human_id")Long toys.human_id);

//    @Query("select u.userName from User u inner join u.area ar where ar.idArea = :idArea")
//List<User> findByIdarea(@Param("idArea") Long idArea);
    void save(Toys toys);

}
