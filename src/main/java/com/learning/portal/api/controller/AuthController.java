package com.learning.portal.api.controller;

import com.learning.portal.api.auth.AppUserDetailService;
import com.learning.portal.api.auth.AuthenticationProvider;
import com.learning.portal.api.data.AuthenticationRequest;
import com.learning.portal.api.data.AuthenticationResponse;
import com.learning.portal.api.data.ResponseModel;
import com.learning.portal.api.facade.UserFacade;
import com.learning.portal.api.service.UserService;
import com.learning.portal.core.config.jwt.JwtConfig;
import com.learning.portal.web.usermanager.entity.Users;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.Objects;

@RestController
@AllArgsConstructor
@RequestMapping("api/v1/")
public class AuthController {

  @Autowired private AppUserDetailService appUserDetailService;

  @Autowired  private AuthenticationProvider authenticationProvider;

  @Autowired private UserService userService;

  @Autowired private UserFacade userFacade;

  @Autowired private JwtConfig jwtConfig;

  @CrossOrigin(origins ={ "http://localhost:3000","https://eimishajamii.herokuapp.com"})
  @PostMapping("auth")
  public ResponseEntity auth(@RequestBody AuthenticationRequest request) throws BadCredentialsException{

    authenticationProvider.authenticate(new UsernamePasswordAuthenticationToken(request.getUsername(),request.getPassword()));
    final UserDetails userDetails = appUserDetailService.loadUserByUsername(request.getUsername());
    final String token = jwtConfig.generateToken(userDetails);

    return ResponseEntity.ok(new AuthenticationResponse(token));
  }

  @CrossOrigin(origins ={ "http://localhost:3000","https://eimishajamii.herokuapp.com"})
  @GetMapping("profile")
  public ResponseEntity<ResponseModel> auth(){
    return ResponseEntity.ok(userFacade.getProfile());
  }

  @CrossOrigin(origins ={ "http://localhost:3000","https://eimishajamii.herokuapp.com"})
  @GetMapping("let")
  public ResponseEntity let(){
    return ResponseEntity.ok("okay");
  }

  private void authenticate(String username,String password) throws BadCredentialsException{
    Objects.requireNonNull(username);
    Objects.requireNonNull(password);
    try {
      authenticationProvider.authenticate(
              new UsernamePasswordAuthenticationToken(username, password));
    }
    catch (BadCredentialsException e) {
      throw new BadCredentialsException("Invalid Credentials",e);
    }
  }

}
