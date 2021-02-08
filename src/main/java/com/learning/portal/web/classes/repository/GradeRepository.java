package com.learning.portal.web.classes.repository;

import com.learning.portal.web.classes.entity.Grade;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface GradeRepository extends CrudRepository<Grade, Long> {

    Optional<Grade> findByName(String name);
}
