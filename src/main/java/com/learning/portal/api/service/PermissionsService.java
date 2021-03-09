package com.learning.portal.api.service;

import com.learning.portal.core.template.BaseServiceInterface;
import com.learning.portal.web.usermanager.entity.Permissions;
import com.learning.portal.web.usermanager.repository.PermissionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class PermissionsService implements BaseServiceInterface<Permissions> {

  private final PermissionRepository permissionRepository;

  @Override
  public Permissions create(Permissions permissions) {
    return null;
  }

  @Override
  public Object update(Permissions permissions) {
    return null;
  }

  @Override
  public Object delete(Long id) {
    return null;
  }

  @Override
  public Optional<Permissions> fetchOne(Long id) {
    return Optional.empty();
  }

  @Override
  public List<Permissions> fetchAll() {
    return (List<Permissions>) permissionRepository.findAll();
  }
}
