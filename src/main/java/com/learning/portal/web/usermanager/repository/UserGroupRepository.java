package com.learning.portal.web.usermanager.repository;

import com.learning.portal.web.usermanager.entity.UserGroups;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface UserGroupRepository extends CrudRepository<UserGroups,Long> {

    Optional<UserGroups> findByName(String name);
}
