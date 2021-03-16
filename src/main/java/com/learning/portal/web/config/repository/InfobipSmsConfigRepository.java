package com.learning.portal.web.config.repository;

import com.learning.portal.web.config.entity.InfobipSmsConfig;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface InfobipSmsConfigRepository extends CrudRepository<InfobipSmsConfig,Long> {

    Optional<InfobipSmsConfig> findByName(String name);
}
