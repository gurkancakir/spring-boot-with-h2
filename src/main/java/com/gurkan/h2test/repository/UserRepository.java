package com.gurkan.h2test.repository;

import com.gurkan.h2test.domain.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Long> {

    User findByUsername(String username);
}