package com.web2team.graphql.repository;

import com.web2team.graphql.model.User;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface UserRepository extends CrudRepository<User, Long> {
  Optional<User> findUserByEmailEqualsAndPassword(String Email, String password);

  Optional<User> findUserByEmailEquals(String Email);
}
