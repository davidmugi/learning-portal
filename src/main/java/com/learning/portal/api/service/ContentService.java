package com.learning.portal.api.service;

import com.learning.portal.core.template.BaseServiceInterface;
import com.learning.portal.web.classes.repository.GradeRepository;
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
    return record;
  }

  @Override
  public boolean update(Content content) {
    return false;
  }

  @Override
  public boolean delete(Long id) {
    return false;
  }

  @Override
  public Optional<Content> fetchOne(Long id) {
    var record = contentRepository.findById(id);
    return record;
  }

  @Override
  public List<Content> fetchAll() {
    return (List<Content>) contentRepository.findAll();
  }
}
