package com.learning.portal.api.service;

import com.learning.portal.core.template.AppConstants;
import com.learning.portal.core.template.BaseServiceInterface;
import com.learning.portal.web.usermanager.entity.UserGroups;
import com.learning.portal.web.usermanager.repository.UserGroupRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserGroupService implements BaseServiceInterface<UserGroups> {

  private final UserGroupRepository userGroupRepository;

  @Override
  public UserGroups create(UserGroups userGroups) {
    UserGroups record = userGroupRepository.save(userGroups);

    if (record == null) {
      return null;
    }
    return record;
  }

  @Override
  public Object update(UserGroups userGroups) {
    var record = userGroupRepository.findById(userGroups.getId());

    if (record.isPresent()) {
      userGroupRepository.save(userGroups);
      return true;
    }
    return false;
  }

  @Override
  public Object delete(Long id) {
    Optional<UserGroups> record = userGroupRepository.findById(id);

    if (record.isPresent()) {
      UserGroups userGroups = record.get();
      userGroups.setFlag(AppConstants.DELETE_RECORD);
      userGroupRepository.save(userGroups);
      return true;
    }
    return null;
  }

  @Override
  public Optional<UserGroups> fetchOne(Long id) {
    Optional<UserGroups> record = userGroupRepository.findById(id);
    return record;
  }

  @Override
  public List<UserGroups> fetchAll() {
    List<UserGroups> record = userGroupRepository.findAllByFlag(AppConstants.ACTIVE_RECORD);
    return (List<UserGroups>) record;
  }

  public String validationRules(UserGroups userGroups) {

    Optional<UserGroups> oName = userGroupRepository.findByName(userGroups.getName());

    if (oName.isPresent()) {
      return "User group with the same name already exist";
    }

    return null;
  }


}
