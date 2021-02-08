package com.learning.portal.api.auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationProvider extends DaoAuthenticationProvider {

  @Autowired
  @Qualifier("appUserDetailsService")
  @Override
  public void setUserDetailsService(UserDetailsService userDetailsService) {
    super.setUserDetailsService(userDetailsService);

    BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
    super.setPasswordEncoder(bCryptPasswordEncoder);
  }

  @Override
  public Authentication authenticate(Authentication authentication) throws AuthenticationException {

    try {
      return super.authenticate(authentication);
    } catch (BadCredentialsException e) {
      e.printStackTrace();
      throw new BadCredentialsException("Invalid Credentials");
    }
  }
}
