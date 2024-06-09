package com.myweb.storeapplication.repository;

import com.myweb.storeapplication.entities.User;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface UserRepository extends CrudRepository<User, Integer> {

    Optional<User> findByUserName(String login);
}
