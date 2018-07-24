package com.web2team.restful.controller;

import com.web2team.restful.model.LoginData;
import com.web2team.security.transfer.JwtUserDto;
import com.web2team.security.util.JwtTokenGenerator;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.atomic.AtomicLong;

@RestController
public class LoginController {

  private static final String template = "Hello, %s!";
  private final AtomicLong counter = new AtomicLong();

  @Value("${security.jwt.secret}")
  private String secret;

  @RequestMapping(value = "/login", method = RequestMethod.POST)
  public LoginData greeting(
      @RequestParam(value = "name") String name,
      @RequestParam(value = "pw") String password) {

    if (name.equals("abc") && password.equals("1234")) {
      JwtUserDto userDto = new JwtUserDto();
      userDto.setUsername(name);
      userDto.setRole("user");

      final String token = JwtTokenGenerator.generateToken(userDto, secret);
      LoginData data = new LoginData();
      data.setTime(1234);
      data.setToken(token);
      data.setName(name + password);

      return data;
    }

    return new LoginData();
  }
}
