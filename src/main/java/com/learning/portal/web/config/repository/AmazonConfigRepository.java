package com.learning.portal.web.config.repository;

import com.learning.portal.web.config.entity.AmazonConfig;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface AmazonConfigRepository extends CrudRepository<AmazonConfig,Long> {

    Optional<AmazonConfig> findByName(String name);
}
