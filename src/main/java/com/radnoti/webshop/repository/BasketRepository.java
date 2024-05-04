package com.radnoti.webshop.repository;

import com.radnoti.webshop.model.entity.Art;
import com.radnoti.webshop.model.entity.Basket;
import com.radnoti.webshop.model.entity.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;


import java.util.List;
import java.util.Optional;

public interface BasketRepository extends CrudRepository<Basket, Integer> {

    @Query("select b from Basket b where b.user.id = :userId")
    List<Basket> getOwnedBaskets(Integer userId);

    /*@Query("select b from Basket b where b.user.id = :userId and b.art = :art")
    Optional<Basket> findByUserAndArt(User user, Art art);*/
}
