package com.learning.portal.api.facade;

import com.learning.portal.api.FacadeInterface;
import com.learning.portal.api.data.ResponseModel;
import com.learning.portal.api.service.GradeService;
import com.learning.portal.api.service.UserService;
import com.learning.portal.core.template.AppConstants;
import com.learning.portal.web.classes.entity.Grade;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class GradeFacade implements FacadeInterface<Grade> {

  private final GradeService gradeService;

  private final UserService userService;

  @Override
  public ResponseModel<Grade> create(Grade grade) {
    Grade record;
    String validationRules = gradeService.validationRules(grade);
    if (validationRules != null) {
      return responseModel(null, validationRules);
    }

    grade.setFlag(AppConstants.ACTIVE_RECORD);
    grade.createDate();
    grade.createBy(userService.getUserId());
    record = gradeService.create(grade);

    String message =
        (record == null) ? AppConstants.FAIL_CREATE_MESSAGE : AppConstants.SUCCESS_CREATE_MESSAGE;

    return responseModel(record, message);
  }

  @Override
  public ResponseModel<Grade> update(Grade grade) {

    grade.updateDate();
    grade.updatedBy(userService.getUserId());
    Object record = gradeService.update(grade);

    String message =
        (record == null) ? AppConstants.FAIL_UPDATE_MESSAGE : AppConstants.SUCCESS_UPDATE_MESSAGE;
    return responseModel(record, message);
  }

  @Override
  public ResponseModel<Grade> readId(Long id) {
    Optional<Grade> record = gradeService.fetchOne(id);

    String message =
        (record == null) ? AppConstants.FAIL_FETCH_MESSAGE : AppConstants.SUCCESS_FETCH_MESSAGE;

    return responseModel(record, message);
  }

  @Override
  public ResponseModel<Grade> readAll() {
    List<Grade> record = gradeService.fetchAll();

    String message =
        (record == null) ? AppConstants.FAIL_FETCH_MESSAGE : AppConstants.SUCCESS_FETCH_MESSAGE;

    return responseModel(record, message);
  }

  @Override
  public ResponseModel<Grade> delete(Long id) {
    Object record = gradeService.delete(id);

    String message =
        (record == null) ? AppConstants.FAIL_DELETE_MESSAGE : AppConstants.SUCCESS_DELETE_MESSAGE;

    return responseModel(record, message);
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
