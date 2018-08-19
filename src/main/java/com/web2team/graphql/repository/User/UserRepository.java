package com.web2team.graphql.repository.User;

import com.web2team.graphql.model.User.User;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface UserRepository extends CrudRepository<User, Long> {
  Optional<User> findUserByEmailEqualsAndPassword(String email, String password);

  Optional<User> findUserByEmailEquals(String email);

}
