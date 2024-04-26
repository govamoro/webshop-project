package com.radnoti.webshop.repository;

import com.radnoti.webshop.model.entity.Basket;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface BasketRepository extends CrudRepository<Basket, Integer> {

    @Query("select b from Basket b where b.user.id = :userId")
    List<Basket> getOwnedBaskets(Integer userId);
}
