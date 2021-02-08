package com.learning.portal.web.usermanager.repository;

import com.learning.portal.web.usermanager.entity.UserTypes;
import org.springframework.data.repository.CrudRepository;

public interface UsertypeRepository extends CrudRepository<UserTypes,Long> {
}
