package com.web2team.restful.controller;

import com.web2team.graphql.event.RxBus;
import com.web2team.graphql.model.User;
import com.web2team.graphql.repository.UserRepository;
import com.web2team.restful.model.LoginRequestBody;
import com.web2team.restful.model.LoginResponseData;
import com.web2team.restful.model.RegisterReqeustBody;
import com.web2team.restful.model.RegisterResponseData;
import com.web2team.security.transfer.JwtUserDto;
import com.web2team.security.util.JwtTokenGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
public class LoginController {
  private final UserRepository userRepository;
  private final RxBus rxBus;

  @Value("${security.jwt.secret}")
  private String secret;

  @Autowired
  public LoginController(UserRepository userRepository, RxBus rxBus) {
    this.userRepository = userRepository;
    this.rxBus = rxBus;
  }

  @PostMapping("/login")
  public ResponseEntity<LoginResponseData> login(@RequestBody() LoginRequestBody body) {
    final String email = body.getEmail();
    final String password = body.getPassword();

    return userRepository
        .findUserByEmailEqualsAndPassword(email, password)
        .map(
            user -> {
              JwtUserDto userDto = new JwtUserDto();
              userDto.setUsername(user.getUsername());
              userDto.setRole("user");

              final String token = JwtTokenGenerator.generateToken(userDto, secret);
              LoginResponseData loginResponseData = new LoginResponseData();
              loginResponseData.setTime(0);
              loginResponseData.setToken(token);
              loginResponseData.setUserName(user.getUsername());

              return new ResponseEntity<>(loginResponseData, HttpStatus.OK);
            })
        .orElse(new ResponseEntity<>(HttpStatus.UNAUTHORIZED));
  }

  @PostMapping("/register")
  public ResponseEntity<RegisterResponseData> register(@RequestBody() RegisterReqeustBody body) {
    final String email = body.getEmail();
    final String password = body.getPassword();
    final String userName = body.getUserName();
    final String phone = body.getPhone();

    Optional<User> duplicatedUser = userRepository.findUserByEmailEquals(email);

    if (duplicatedUser.isPresent()) {
      return new ResponseEntity<>(new RegisterResponseData("중복된 이메일이 존재합니다."), HttpStatus.CONFLICT);
    }

    User newUser = new User();
    newUser.setEmail(email);
    newUser.setUsername(userName);
    newUser.setPassword(password);
    newUser.setPhone(phone);

    userRepository.save(newUser);
    rxBus.send(userRepository.findUserByEmailEquals(email).get());

    return new ResponseEntity<>(new RegisterResponseData(""), HttpStatus.OK);
  }
}
