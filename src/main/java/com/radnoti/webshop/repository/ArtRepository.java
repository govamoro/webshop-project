package com.radnoti.webshop.repository;

import com.radnoti.webshop.model.entity.Art;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ArtRepository extends CrudRepository<Art, Integer> {

}
