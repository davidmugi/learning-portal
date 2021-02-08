package com.learning.portal.api.service;

import com.learning.portal.core.template.BaseServiceInterface;
import com.learning.portal.web.usermanager.entity.Users;
import com.learning.portal.web.usermanager.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class UserService implements BaseServiceInterface<Users> {

  private final UserRepository userRepository;

  @Override
  public Users create(Users users) {
    var record = userRepository.save(users);

    if (record == null) {
      return null;
    }
    return record;
  }

  @Override
  public boolean update(Users users) {
    var record = userRepository.findById(users.getId());
    if (record.isPresent()) {
      userRepository.save(users);
      return true;
    }
    return false;
  }

  @Override
  public boolean delete(Long id) {
    var record = userRepository.findById(id);

    if (record.isPresent()) {
      Users users = record.get();
      users.setEnabled(false);
      userRepository.save(users);
      return true;
    }
    return false;
  }

  @Override
  public Optional<Users> fetchOne(Long id) {
    var record = userRepository.findById(id);
    return record;
  }

  @Override
  public List<Users> fetchAll() {
    return (List<Users>) userRepository.findAll();
  }


  public Optional<Users> getLoginUSer() {
    var user = SecurityContextHolder.getContext().getAuthentication();

    if (user.isAuthenticated()){
      return userRepository.findByEmail(user.getName());
    }

    return Optional.empty();
  }
}
