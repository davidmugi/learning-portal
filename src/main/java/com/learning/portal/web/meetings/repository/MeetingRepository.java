package com.learning.portal.web.meetings.repository;

import com.learning.portal.web.meetings.entity.Meetings;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.Date;
import java.util.List;
import java.util.Optional;

public interface MeetingRepository extends CrudRepository<Meetings, Long> {

  public List<Meetings> findAllByGradeIdAndFlag(Long id,String flag);

  public List<Meetings> findAllByCreatedByAndFlag(Long userId,String flag);

  @Query(value = "SELECT l FROM Meetings l WHERE (l.endTime BETWEEN ?3 AND ?2) AND (l.gradeId = ?1) AND (l.flag = ?4)")
  public List<Meetings> findAllByGradeIdAndStartTimeAndEndTimeAndFlag(Long gradeId, String startTime,String endTime,String flag);

  public List<Meetings> findAllByFlag(String flag);
}
