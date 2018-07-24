package com.web2team.security.exception;

import org.springframework.security.core.AuthenticationException;

public class JwtTokenMalformedException extends AuthenticationException {

  public JwtTokenMalformedException(String msg) {
    super(msg);
  }
}
