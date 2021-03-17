package com.learning.portal.api.facade;

import com.learning.portal.api.FacadeInterface;
import com.learning.portal.api.data.ResponseModel;
import com.learning.portal.api.service.GradeService;
import com.learning.portal.api.service.MeetingService;
import com.learning.portal.api.service.UserService;
import com.learning.portal.core.template.AppConstants;
import com.learning.portal.web.classes.entity.Grade;
import com.learning.portal.web.meetings.entity.Meetings;
import com.learning.portal.web.usermanager.entity.Users;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class MeetingFacade implements FacadeInterface<Meetings> {

  private final UserService userService;

  private final MeetingService meetingService;

  private final GradeService gradeService;

  @Override
  public ResponseModel<Meetings> create(Meetings meetings) {

    Grade grade = gradeService.fetchOne(meetings.getGradeId()).get();

    if (meetingService.validationCheck(meetings) != null) {
      return responseModel(null, meetingService.validationCheck(meetings));
    }

    meetings.createBy(userService.getUserId());
    meetings.createDate();
    meetings.setFlag(AppConstants.ACTIVE_RECORD);
    Meetings meeting = meetingService.create(meetings);

    meeting.setGradeName(grade.getName());

    String message =
        (meeting == null) ? AppConstants.FAIL_CREATE_MESSAGE : AppConstants.SUCCESS_CREATE_MESSAGE;

    return responseModel(meeting, message);
  }

  @Override
  public ResponseModel<Meetings> update(Meetings meetings) {
    Users users = userService.getLoginUSer().get();
    meetings.setLastModifiedBy(users.getId());

    meetings.updatedBy(userService.getUserId());
    meetings.updateDate();
    meetings.setFlag(AppConstants.ACTIVE_RECORD);
    Object meeting = meetingService.update(meetings);

    String message =
        (meeting == null) ? AppConstants.FAIL_UPDATE_MESSAGE : AppConstants.SUCCESS_UPDATE_MESSAGE;

    return responseModel(meeting, message);
  }

  @Override
  public ResponseModel<Meetings> readId(Long id) {
    Optional<Meetings> meeting = meetingService.fetchOne(id);

    String message =
        (meeting == null) ? AppConstants.FAIL_FETCH_MESSAGE : AppConstants.SUCCESS_FETCH_MESSAGE;

    return responseModel(meeting, message);
  }

  @Override
  public ResponseModel<Meetings> readAll() {
    List<Meetings> meeting = meetingService.fetchAll();

    String message =
        (meeting == null) ? AppConstants.FAIL_FETCH_MESSAGE : AppConstants.SUCCESS_FETCH_MESSAGE;

    return responseModel(meeting, message);
  }

  @Override
  public ResponseModel<Meetings> delete(Long id) {
    Object meeting = meetingService.delete(id);

    String message =
        (meeting == null) ? AppConstants.FAIL_DELETE_MESSAGE : AppConstants.SUCCESS_DELETE_MESSAGE;

    return responseModel(meeting, message);
  }

  public ResponseModel<Meetings> fetchPerGrade(Long gradeId) {
    List<Meetings> meeting = meetingService.fetchPerGrade(gradeId);

    String message =
        (meeting == null) ? AppConstants.FAIL_FETCH_MESSAGE : AppConstants.SUCCESS_FETCH_MESSAGE;

    return responseModel(meeting, message);
  }

  public ResponseModel<Meetings> fetchPerCreator(Long userId) {
    List<Meetings> meeting = meetingService.fetchPerCreator(userId);

    String message =
        (meeting == null) ? AppConstants.FAIL_FETCH_MESSAGE : AppConstants.SUCCESS_FETCH_MESSAGE;

    return responseModel(meeting, message);
  }

  private ResponseModel responseModel(Object record, String message) {
    String status = (record == null) ? "01" : "00";
    Object data = (record == null) ? null : record;

    ResponseModel responseModel = new ResponseModel();
    responseModel.setData(data);
    responseModel.setMessage(message);
    responseModel.setStatus(status);
    return responseModel;
  }
}
