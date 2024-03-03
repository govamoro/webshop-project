package com.radnoti.webshop.repository;

import com.radnoti.webshop.model.entity.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends CrudRepository<User, Integer> {

    @Query("Select u from User u where u.userName = :username")
    Optional<User> findByUsername(String username);

    @Query("Select u from User u where u.email = :email")
    Optional<User> findByEmail(String email);
}
