package com.radnoti.webshop.repository;

import com.radnoti.webshop.model.entity.Material;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MaterialRepository extends CrudRepository<Material, Integer> {

}
