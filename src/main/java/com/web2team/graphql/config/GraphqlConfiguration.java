package com.web2team.graphql.config;

import com.web2team.graphql.exception.GraphQLErrorAdapter;
import com.web2team.graphql.model.Book;
import com.web2team.graphql.model.User;
import com.web2team.graphql.repository.BookRepository;
import com.web2team.graphql.repository.UserRepository;
import com.web2team.graphql.resolver.Mutation;
import com.web2team.graphql.resolver.Query;
import com.web2team.graphql.resolver.UserResolver;
import graphql.ExceptionWhileDataFetching;
import graphql.GraphQLError;
import graphql.servlet.GraphQLErrorHandler;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Configuration
public class GraphqlConfiguration {

  @Bean
  public UserResolver userResolver(UserRepository userRepository) {
    return new UserResolver(userRepository);
  }

  @Bean
  public Query query(UserRepository userRepository, BookRepository bookRepository) {
    return new Query(userRepository, bookRepository);
  }

  @Bean
  public Mutation mutation(UserRepository userRepository, BookRepository bookRepository) {
    return new Mutation(userRepository, bookRepository);
  }

  @Bean
  public CommandLineRunner demo(UserRepository userRepository, BookRepository bookRepository) {
    return (args) -> {
      User user = new User("username123", "password123", "nicknameHI", "emails");
      userRepository.save(user);

//      bookRepository.save(
//          new Book("Java: A Beginner's Guide, Sixth Edition", "0071809252", 728, user));
    };
  }

  @Bean
  public GraphQLErrorHandler errorHandler() {
    return new GraphQLErrorHandler() {
      @Override
      public List<GraphQLError> processErrors(List<GraphQLError> errors) {
        List<GraphQLError> clientErrors =
            errors.stream().filter(this::isClientError).collect(Collectors.toList());

        List<GraphQLError> serverErrors =
            errors
                .stream()
                .filter(e -> !isClientError(e))
                .map(GraphQLErrorAdapter::new)
                .collect(Collectors.toList());

        List<GraphQLError> e = new ArrayList<>();
        e.addAll(clientErrors);
        e.addAll(serverErrors);
        return e;
      }

      boolean isClientError(GraphQLError error) {
        return !(error instanceof ExceptionWhileDataFetching || error instanceof Throwable);
      }
    };
  }
}
