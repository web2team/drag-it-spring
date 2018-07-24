package com.web2team.security;

import com.web2team.security.exception.JwtTokenMalformedException;
import com.web2team.security.model.AuthenticatedUser;
import com.web2team.security.model.JwtAuthenticationToken;
import com.web2team.security.transfer.JwtUserDto;
import com.web2team.security.util.JwtTokenValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.dao.AbstractUserDetailsAuthenticationProvider;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.util.List;

/** Used for checking the token from the request and supply the UserDetails if the token is valid */
@Component
public class JwtAuthenticationProvider extends AbstractUserDetailsAuthenticationProvider {

  private final JwtTokenValidator jwtTokenValidator;

  @Autowired
  public JwtAuthenticationProvider(JwtTokenValidator jwtTokenValidator) {
    this.jwtTokenValidator = jwtTokenValidator;
  }

  @Override
  public boolean supports(Class<?> authentication) {
    return (JwtAuthenticationToken.class.isAssignableFrom(authentication));
  }

  @Override
  protected void additionalAuthenticationChecks(
      UserDetails userDetails, UsernamePasswordAuthenticationToken authentication)
      throws AuthenticationException {}

  @Override
  protected UserDetails retrieveUser(
      String username, UsernamePasswordAuthenticationToken authentication)
      throws AuthenticationException {
    JwtAuthenticationToken jwtAuthenticationToken = (JwtAuthenticationToken) authentication;
    String token = jwtAuthenticationToken.getToken();

    JwtUserDto parsedUser = jwtTokenValidator.parseToken(token);

    if (parsedUser == null) {
      throw new JwtTokenMalformedException("JWT token is not valid");
    }

    List<GrantedAuthority> authorityList =
        AuthorityUtils.commaSeparatedStringToAuthorityList(parsedUser.getRole());

    return new AuthenticatedUser(parsedUser.getUsername(), token, authorityList);
  }
}
