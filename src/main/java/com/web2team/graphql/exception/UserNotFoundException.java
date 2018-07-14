package com.web2team.graphql.exception;

import graphql.ErrorType;
import graphql.GraphQLError;
import graphql.language.SourceLocation;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class UserNotFoundException extends RuntimeException implements GraphQLError {
  private Map<String, Object> extensions = new HashMap<>();

  public UserNotFoundException(String message, Long invalidUserId) {
    super(message);
    extensions.put("invalid User Id", invalidUserId);
  }

  public UserNotFoundException(String message, String invalidUsername) {
    super(message);
    extensions.put("invalid User Name", invalidUsername);
  }

  @Override
  public List<SourceLocation> getLocations() {
    return null;
  }

  @Override
  public Map<String, Object> getExtensions() {
    return extensions;
  }

  @Override
  public ErrorType getErrorType() {
    return ErrorType.DataFetchingException;
  }
}
