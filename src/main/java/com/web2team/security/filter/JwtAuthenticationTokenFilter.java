package com.web2team.security.filter;

import com.web2team.security.exception.JwtTokenMissingException;
import com.web2team.security.model.JwtAuthenticationToken;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class JwtAuthenticationTokenFilter extends AbstractAuthenticationProcessingFilter {

  @Value("${security.jwt.header}")
  private String tokenHeader;

  public JwtAuthenticationTokenFilter() {
    super("/api/**");
  }

  @Override
  public Authentication attemptAuthentication(
      HttpServletRequest request, HttpServletResponse response) {
    String header = request.getHeader(this.tokenHeader);

    if (header == null || !header.startsWith("Bearer ")) {
      throw new JwtTokenMissingException("No JWT token found in request headers");
    }

    String authToken = header.substring(7);

    JwtAuthenticationToken authRequest = new JwtAuthenticationToken(authToken);

    return getAuthenticationManager().authenticate(authRequest);
  }

  @Override
  protected void successfulAuthentication(
      HttpServletRequest request,
      HttpServletResponse response,
      FilterChain chain,
      Authentication authResult)
      throws IOException, ServletException {
    super.successfulAuthentication(request, response, chain, authResult);

    chain.doFilter(request, response);
  }
}
