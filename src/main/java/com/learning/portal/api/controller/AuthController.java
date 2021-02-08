package com.learning.portal.api.controller;

import com.learning.portal.api.auth.AppUserDetailService;
import com.learning.portal.api.data.AuthenticationRequest;
import com.learning.portal.api.data.AuthenticationResponse;
import com.learning.portal.core.config.jwt.JwtConfig;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/")
public class AuthController {

  @Autowired private AppUserDetailService appUserDetailService;

  @Autowired private JwtConfig jwtConfig;

  @PostMapping("auth")
  public ResponseEntity auth(@RequestBody AuthenticationRequest request) {

    final UserDetails userDetails = appUserDetailService.loadUserByUsername(request.getUsername());

    final String token = jwtConfig.generateToken(userDetails);

    return ResponseEntity.ok(new AuthenticationResponse(token));
  }

  @GetMapping("let")
  public ResponseEntity let(){
    return ResponseEntity.ok("okay");
  }
}
