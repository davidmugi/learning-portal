package com.learning.portal.core.config.jwt;

import com.fasterxml.jackson.core.JsonParseException;
import com.learning.portal.api.auth.AppUserDetailService;
import com.learning.portal.core.template.AppConstants;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureException;
import io.jsonwebtoken.UnsupportedJwtException;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
@AllArgsConstructor
public class JwtFilter extends OncePerRequestFilter {

  @Autowired private AppUserDetailService userDetailService;

  @Autowired private JwtConfig jwtConfig;

  @Override
  protected void doFilterInternal(
      HttpServletRequest request, HttpServletResponse response, FilterChain chain)
      throws ServletException, IOException {

    final String requestToken = request.getHeader("Authorization");

    String username = null;
    String jwtToken = null;

    if (requestToken != null && requestToken.startsWith(AppConstants.BEARER)) {
      jwtToken = requestToken.substring(7);

      try {
        username = jwtConfig.extraUserName(jwtToken);
      } catch (SignatureException e) {
        throw new IOException("Invalid authentication token", e);
      } catch (UnsupportedJwtException | IllegalArgumentException e) {
        throw new IOException("Unable to get token", e);
      } catch (ExpiredJwtException e) {
        throw new IOException(
            "Provided token is expired. Kindly log in again to acquire a new token", e);
      } catch (MalformedJwtException e) {
        throw new IOException("Provided token is malformed", e);
      }
    } else {

    }

    if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
      UserDetails userDetails = this.userDetailService.loadUserByUsername(username);

      if (jwtConfig.validateToken(jwtToken, userDetails)) {

        UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken =
            new UsernamePasswordAuthenticationToken(
                userDetails, null, userDetails.getAuthorities());

        usernamePasswordAuthenticationToken.setDetails(
            new WebAuthenticationDetailsSource().buildDetails(request));

        SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
      }
    }
    chain.doFilter(request, response);
  }
}
