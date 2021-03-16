package com.learning.portal.api.service;

import com.learning.portal.core.sms.SmsServiceInterface;
import com.learning.portal.core.template.AppConstants;
import com.learning.portal.core.template.BaseServiceInterface;
import com.learning.portal.web.usermanager.entity.Users;
import com.learning.portal.web.usermanager.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class UserService implements BaseServiceInterface<Users> {

  private final UserRepository userRepository;
  private final SmsServiceInterface smsServiceInterface;

  @Autowired private MessageSource smsMessageSource;


  @Override
  public Users create(Users users) {
    users.setCreatedDate(new Date());
    users.setLastModifiedDate(new Date());
    var record = userRepository.save(users);

    if (record == null) {
      return null;
    }

//    Object[] object = new Object[]{record.getFullName()};
//    String message = smsMessageSource.getMessage("welcome.message",object, Locale.ENGLISH);
//    smsServiceInterface.sendSMS(message,record.getPhone());
    return record;
  }

  @Override
  public Object update(Users users) {
    var record = userRepository.findById(users.getId());
    if (record.isPresent()) {
      users.setLastModifiedDate(new Date());
      userRepository.save(users);
      return true;
    }
    return null;
  }

  @Override
  public Object delete(Long id) {
    var record = userRepository.findById(id);

    if (record.isPresent()) {
      Users users = record.get();
      users.setFlag(AppConstants.DELETE_RECORD);
      userRepository.save(users);
      return true;
    }
    return null;
  }

  @Override
  public Optional<Users> fetchOne(Long id) {
    var record = userRepository.findById(id);
    if (record.isPresent()){
      return record;
    }
    return null;
  }

  @Override
  public List<Users> fetchAll() {
    return (List<Users>) userRepository.findAllByFlag(AppConstants.ACTIVE_RECORD);
  }

  public Optional<Users> getLoginUSer() {
    var user = SecurityContextHolder.getContext().getAuthentication();
    if (user.isAuthenticated()) {
      return userRepository.findByEmail(user.getName());
    }
    return Optional.empty();
  }

  public Long getUserId() {
    var user = SecurityContextHolder.getContext().getAuthentication();
    if (user.isAuthenticated()) {
      return userRepository.findByEmail(user.getName()).get().getId();
    }
    return null;
  }
}
