package com.web2team.graphql.repository;

import com.web2team.graphql.model.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Long> {}
