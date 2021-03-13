package com.learning.portal.core.config;

import com.learning.portal.api.auth.AppUserDetailService;
import com.learning.portal.api.auth.AuthenticationProvider;
import com.learning.portal.core.config.jwt.JwtFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class WebServerConfig extends WebSecurityConfigurerAdapter {

  @Autowired private AuthenticationProvider authenticationProvider;

  @Autowired private JwtFilter jwtFilter;


  @Override
  protected void configure(HttpSecurity http) throws Exception {
     http
          .cors()
          .and()
          .csrf()
          .disable()
          .authorizeRequests()
          .antMatchers("/api/v1/auth").permitAll()
          .anyRequest().authenticated()
          .and()
          .sessionManagement()
          .sessionCreationPolicy(SessionCreationPolicy.STATELESS);

     http.addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);
  }

  @Override
  protected void configure(AuthenticationManagerBuilder auth) throws Exception {
    auth.authenticationProvider(authenticationProvider);
  }

  @Bean
  public BCryptPasswordEncoder bCryptPasswordEncoder() {
    BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
    return bCryptPasswordEncoder;
  }
}
