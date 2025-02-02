package com.study.restcontroller.repository;


import com.study.restcontroller.domain.User;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface UserJdbcRepository extends CrudRepository<User, Integer> {
    Optional<User> findByName(String name);
    List<User> findAllByGenderAndDeletedYn(String gender, boolean deleted);
}
