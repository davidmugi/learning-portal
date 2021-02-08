package com.learning.portal.web.usermanager.repository;

import com.learning.portal.web.usermanager.entity.Permissions;
import org.springframework.data.repository.CrudRepository;

public interface PermissionRepository extends CrudRepository<Permissions,Long> {
}
