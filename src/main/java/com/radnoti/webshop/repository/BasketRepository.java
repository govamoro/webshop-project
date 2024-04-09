package com.radnoti.webshop.repository;

import com.radnoti.webshop.model.entity.Basket;
import org.springframework.data.repository.CrudRepository;

public interface BasketRepository extends CrudRepository<Basket, Integer> {
}
