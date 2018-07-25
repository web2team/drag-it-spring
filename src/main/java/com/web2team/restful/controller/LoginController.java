package com.web2team.restful.controller;

import com.web2team.restful.model.LoginData;
import com.web2team.security.transfer.JwtUserDto;
import com.web2team.security.util.JwtTokenGenerator;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.atomic.AtomicLong;

class Data {
  public String id;
  public String pw;
}

@RestController
public class LoginController {

  private static final String template = "Hello, %s!";
  private final AtomicLong counter = new AtomicLong();

  @Value("${security.jwt.secret}")
  private String secret;

  @RequestMapping(value = "/login", method = RequestMethod.POST)
  public LoginData greeting(@RequestBody() Data body) {

    if (body.id.equals("abc") && body.pw.equals("1234")) {
      JwtUserDto userDto = new JwtUserDto();
      userDto.setUsername(body.id);
      userDto.setRole("user");

      final String token = JwtTokenGenerator.generateToken(userDto, secret);
      LoginData data = new LoginData();
      data.setTime(1234);
      data.setToken(token);
      data.setName(body.id + body.pw);

      return data;
    }

    return new LoginData();
  }
}
