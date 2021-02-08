package com.learning.portal.api.auth;

import com.learning.portal.core.template.AppConstants;
import com.learning.portal.web.usermanager.entity.Permissions;
import com.learning.portal.web.usermanager.entity.UserGroups;
import com.learning.portal.web.usermanager.entity.UserTypes;
import com.learning.portal.web.usermanager.entity.Users;
import com.learning.portal.web.usermanager.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service("appUserDetailsService")
@Transactional
public class AppUserDetailService implements UserDetailsService {

  @Autowired private UserRepository userRepository;

  @Override
  public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

    Optional<Users> oUser = userRepository.findByEmail(email);

    if (!oUser.isPresent()) {
      throw new UsernameNotFoundException("Not user assocaited with the provided email");
    }
    Users users = oUser.get();

    return build(users);
  }

  private User build(Users users) {
    List<SimpleGrantedAuthority> simpleGrantedAuthorities = new ArrayList<>();

    UserGroups userGroups = users.getUserGroupsLink();

    UserTypes userTypes = users.getUserTypesLink();

    List<Permissions> permissionsSet = userGroups.getPermissions();

    for (Permissions permissions : permissionsSet) {
      simpleGrantedAuthorities.add(new SimpleGrantedAuthority(permissions.getName()));
    }

    switch (userTypes.getName()) {
      case AppConstants.AGENT:
        simpleGrantedAuthorities.add(new SimpleGrantedAuthority("AGENT"));
        break;

      case AppConstants.STUDENT:
        simpleGrantedAuthorities.add(new SimpleGrantedAuthority("STUDENT"));
        break;
      case AppConstants.SUPER_ADMIN:
        simpleGrantedAuthorities.add(new SimpleGrantedAuthority("SUPER_ADMIN"));
        break;
    }

    return new User(
        users.getEmail(),
        users.getPassword(),
        users.isEnabled(),
        true,
        true,
        true,
        simpleGrantedAuthorities);
  }
}
