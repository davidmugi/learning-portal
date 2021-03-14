package com.learning.portal.api.facade;

import com.learning.portal.api.FacadeInterface;
import com.learning.portal.api.data.ResponseModel;
import com.learning.portal.api.service.UserService;
import com.learning.portal.core.template.AppConstants;
import com.learning.portal.web.usermanager.entity.Permissions;
import com.learning.portal.web.usermanager.entity.Users;
import com.learning.portal.web.usermanager.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
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
    String password = new BCryptPasswordEncoder().encode(users.getPassword());
    String validationMessage = validationCheck(users);

    if (validationMessage != null) {
      return responseModel(null, validationMessage);
    }

    users.setEnabled(true);
    users.setPassword(password);
    users.createBy(userService.getUserId());
    users.createDate();
    users.setFlag(AppConstants.ACTIVE_RECORD);
    record = userService.create(users);

    String message =
        (record == null) ? AppConstants.FAIL_CREATE_MESSAGE : AppConstants.SUCCESS_CREATE_MESSAGE;

    return responseModel(record, message);
  }

  @Override
  public ResponseModel<Users> update(Users users) {

    users.updateDate();
    users.updatedBy(userService.getUserId());
    users.setFlag(AppConstants.ACTIVE_RECORD);
    var record = userService.update(users);

    String message =
        (record == null) ? AppConstants.FAIL_UPDATE_MESSAGE : AppConstants.SUCCESS_UPDATE_MESSAGE;

    return responseModel(record, message);
  }

  @Override
  public ResponseModel<Users> readId(Long id) {
    Optional<Users> record = userService.fetchOne(id);

    String message =
        (record == null) ? AppConstants.FAIL_FETCH_MESSAGE : AppConstants.SUCCESS_FETCH_MESSAGE;

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
    var record = userService.delete(id);

    String message =
        (record == null) ? AppConstants.FAIL_DELETE_MESSAGE : AppConstants.SUCCESS_DELETE_MESSAGE;

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

  public ResponseModel getProfile() {
    var users = userService.getLoginUSer().get();
    List<Permissions> permissions = new ArrayList<>();
    List<String> permissionsId = new ArrayList<>();

    permissions = users.getUserGroupsLink().getPermissions();

    permissions.stream()
        .forEach(
            p -> {
              permissionsId.add(p.getName());
            });


    users.setPermissions(permissionsId);
    users.setUserType(users.getUserTypesLink().getCode());
    ResponseModel responseModel = new ResponseModel();
    responseModel.setStatus("00");
    responseModel.setMessage("Profile fetched successful");
    responseModel.setData(users);

    return responseModel;
  }
}
