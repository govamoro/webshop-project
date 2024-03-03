package com.radnoti.webshop.repository;

import com.radnoti.webshop.model.entity.Art;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ArtRepository extends CrudRepository<Art, Integer> {

    @Query("select a from Art a where a.user.id = :userId")
    List<Art> findProductsByUserId(Integer userId);

    @Query("delete from Art a where a.id = :productId and a.user = :userId")
    void findProductsByUserId(Integer userId, Integer productId);
}
