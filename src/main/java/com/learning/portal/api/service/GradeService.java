package com.learning.portal.api.service;

import com.learning.portal.core.template.AppConstants;
import com.learning.portal.core.template.BaseServiceInterface;
import com.learning.portal.web.classes.entity.Grade;
import com.learning.portal.web.classes.repository.GradeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class GradeService implements BaseServiceInterface<Grade> {

  private final GradeRepository gradeRepository;

  @Override
  public Grade create(Grade grade) {
    Grade record = gradeRepository.save(grade);

    if (record != null) {
      return record;
    }
    return null;
  }

  @Override
  public Object update(Grade grade) {
    Optional<Grade> record = gradeRepository.findById(grade.getId());

    if (record.isPresent()) {
      gradeRepository.save(grade);
      return true;
    }
    return null;
  }

  @Override
  public Object delete(Long id) {
    Optional<Grade> record = gradeRepository.findById(id);

    if (record.isPresent()) {
      Grade grade = record.get();
      grade.setFlag(AppConstants.DELETE_RECORD);
      return true;
    }
    return null;
  }

  @Override
  public Optional<Grade> fetchOne(Long id) {
    Optional<Grade> record = gradeRepository.findById(id);
    if (record.isPresent()){
      return record;
    }
    return null;
  }

  @Override
  public List<Grade> fetchAll() {
    return (List<Grade>) gradeRepository.findAllByFlag(AppConstants.ACTIVE_RECORD);
  }

  public String validationRules(Grade grade) {
    Optional<Grade> oName = gradeRepository.findByName(grade.getName());

    if (oName.isPresent()) {
      return "Class already exist";
    }

    return null;
  }
}
