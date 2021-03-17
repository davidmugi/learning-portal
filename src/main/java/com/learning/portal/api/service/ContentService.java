package com.learning.portal.api.service;

import com.learning.portal.core.template.AppConstants;
import com.learning.portal.core.template.BaseServiceInterface;
import com.learning.portal.web.content.entity.Content;
import com.learning.portal.web.content.repository.ContentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class ContentService implements BaseServiceInterface<Content> {

  private final ContentRepository contentRepository;

  @Override
  public Content create(Content content) {
    var record = contentRepository.save(content);

    if (record == null) {
      return null;
    }
    record.setGradeName(record.getGradeLink().getName());
    return content;
  }

  @Override
  public Object update(Content content) {
    var record = contentRepository.findById(content.getId());

    if (record.isPresent()) {
      contentRepository.save(content);
      return true;
    }
    return null;
  }

  @Override
  public Object delete(Long id) {
    var record = contentRepository.findById(id);

    if (record.isPresent()) {
      Content content = record.get();
      content.setFlag(AppConstants.DELETE_RECORD);
      contentRepository.save(content);
      return true;
    }
    return null;
  }

  @Override
  public Optional<Content> fetchOne(Long id) {
    var record = contentRepository.findById(id);

    if (record.isPresent()){
      return record;
    }
   return null;
  }

  @Override
  public List<Content> fetchAll() {
    return (List<Content>) contentRepository.findAllByFlag(AppConstants.ACTIVE_RECORD);
  }

  public List<Content> fetchPerGrade(Long gradeId) {
    return contentRepository.findAllByGradeIdAndFlag(gradeId,AppConstants.ACTIVE_RECORD);
  }

  public List<Content> fetchPerCreator(Long userId) {
    return contentRepository.findAllByCreatedByAndFlag(userId,AppConstants.ACTIVE_RECORD);
  }
}
