package com.learning.portal.api.facade;

import com.learning.portal.api.FacadeInterface;
import com.learning.portal.api.data.ResponseModel;
import com.learning.portal.api.service.UserService;
import com.learning.portal.core.template.AppConstants;
import com.learning.portal.web.usermanager.entity.Users;
import com.learning.portal.web.usermanager.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class UserFacade implements FacadeInterface<Users> {

  private final UserRepository userRepository;

  private final UserService userService;

  @Override
  public ResponseModel<Users> create(Users users) {
    Users record;
    String validationMessage = validationCheck(users);

    if (validationMessage != null) {
      return responseModel(null, validationMessage);
    }

    users.setEnabled(true);
    record = userService.create(users);

    String message =
        (record == null) ? AppConstants.FAIL_CREATE_MESSAGE : AppConstants.SUCCESS_CREATE_MESSAGE;

    return responseModel(record, message);
  }

  @Override
  public ResponseModel<Users> update(Users users) {
    boolean record = userService.update(users);

    String message =
        (record == false) ? AppConstants.FAIL_UPDATE_MESSAGE : AppConstants.SUCCESS_UPDATE_MESSAGE;

    return responseModel(record, message);
  }

  @Override
  public ResponseModel<Users> readId(Long id) {
    Optional<Users> record = userService.fetchOne(id);

    String message =
        (record.isPresent()) ? AppConstants.FAIL_FETCH_MESSAGE : AppConstants.SUCCESS_FETCH_MESSAGE;

    return responseModel(record, message);
  }

  @Override
  public ResponseModel<Users> readAll() {
    var record = userService.fetchAll();

    String message =
        (record == null) ? AppConstants.FAIL_FETCH_MESSAGE : AppConstants.SUCCESS_FETCH_MESSAGE;

    return responseModel(record, message);
  }

  @Override
  public ResponseModel<Users> delete(Long id) {
    boolean record = userService.delete(id);

    String message =
        (record == false) ? AppConstants.FAIL_DELETE_MESSAGE : AppConstants.SUCCESS_DELETE_MESSAGE;

    return responseModel(record, message);
  }

  private ResponseModel responseModel(Object record, String message) {
    String status = (record == null) ? "01" : "00";
    var data = (record == null) ? null : record;

    ResponseModel responseModel = new ResponseModel();
    responseModel.setStatus(status);
    responseModel.setMessage(message);
    responseModel.setData(data);

    return responseModel;
  }

  private String validationCheck(Users users) {
    String message = null;
    Optional<Users> oEmail = userRepository.findByEmail(users.getEmail());

    Optional<Users> oPhone = userRepository.findByPhone(users.getPhone());

    if (oEmail.isPresent()) {
      return "Sorry Email is already in use";
    }

    if (oPhone.isPresent()) {
      return "Sorry Phone number is already in use";
    }

    return message;
  }
}
