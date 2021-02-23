package com.learning.portal.web.meetings.repository;

import com.learning.portal.web.meetings.entity.Meetings;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface MeetingRepository extends CrudRepository<Meetings, Long> {

  public List<Meetings> findAllByGradeId(Long id);

  public List<Meetings> findAllByCreatedBy(Long userId);
}
