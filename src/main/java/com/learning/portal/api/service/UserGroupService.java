package com.learning.portal.api.service;

import com.learning.portal.core.template.BaseServiceInterface;
import com.learning.portal.web.usermanager.entity.UserGroups;
import com.learning.portal.web.usermanager.repository.UserGroupRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserGroupService implements BaseServiceInterface<UserGroups> {

  private final UserGroupRepository userGroupRepository;

  @Override
  public UserGroups create(UserGroups userGroups) {
    var record = userGroupRepository.save(userGroups);

    if (record == null) {
      return null;
    }
    return record;
  }

  @Override
  public boolean update(UserGroups userGroups) {
    var record = userGroupRepository.findById(userGroups.getId());

    if (record.isPresent()) {
      userGroupRepository.save(userGroups);
      return true;
    }
    return false;
  }

  @Override
  public boolean delete(Long id) {
    var record = userGroupRepository.findById(id);

    if (record.isPresent()) {
      UserGroups userGroups = record.get();
      userGroups.setFlag("3");
      userGroupRepository.save(userGroups);
      return true;
    }
    return false;
  }

  @Override
  public Optional<UserGroups> fetchOne(Long id) {
    var record = userGroupRepository.findById(id);
    return record;
  }

  @Override
  public List<UserGroups> fetchAll() {
    var record = userGroupRepository.findAll();
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
