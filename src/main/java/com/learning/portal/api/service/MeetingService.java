package com.learning.portal.api.service;

import com.learning.portal.core.template.AppConstants;
import com.learning.portal.core.template.BaseServiceInterface;
import com.learning.portal.web.meetings.entity.Meetings;
import com.learning.portal.web.meetings.repository.MeetingRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class MeetingService implements BaseServiceInterface<Meetings> {

  private final MeetingRepository meetingRepository;

  @Override
  public Meetings create(Meetings meetings) {
    var record = meetingRepository.save(meetings);

    if (record != null) {
      return record;
    }
    return null;
  }

  @Override
  public Object update(Meetings meetings) {
    var oldRecord = meetingRepository.findById(meetings.getId());

    if (oldRecord.isPresent()) {
      meetingRepository.save(meetings);
      return true;
    }
    return null;
  }

  @Override
  public Object delete(Long id) {
    var record = meetingRepository.findById(id);

    if (record.isPresent()) {
      Meetings meetings = record.get();
      meetings.setFlag(AppConstants.DELETE_RECORD);
      meetingRepository.save(meetings);
      return true;
    }
    return null;
  }

  @Override
  public Optional<Meetings> fetchOne(Long id) {
    var record = meetingRepository.findById(id);
    if (record.isPresent()) {
      return record;
    }
    return null;
  }

  @Override
  public List<Meetings> fetchAll() {
    return (List<Meetings>) meetingRepository.findAll();
  }

  public List<Meetings> fetchPerGrade(Long gradeId){
    return meetingRepository.findAllByGradeId(gradeId);
  }

  public List<Meetings> fetchPerCreator(Long userId){
    return meetingRepository.findAllByCreatedBy(userId);
  }
}
