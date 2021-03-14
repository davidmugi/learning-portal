package com.learning.portal.api.facade;

import com.learning.portal.api.FacadeInterface;
import com.learning.portal.api.data.ResponseModel;
import com.learning.portal.api.service.UserGroupService;
import com.learning.portal.api.service.UserService;
import com.learning.portal.core.template.AppConstants;
import com.learning.portal.web.usermanager.entity.PermissionGroups;
import com.learning.portal.web.usermanager.entity.Permissions;
import com.learning.portal.web.usermanager.entity.UserGroups;
import com.learning.portal.web.usermanager.repository.PermissionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
@Transactional
@RequiredArgsConstructor
public class UserGroupFacade implements FacadeInterface<UserGroups> {

  private final UserGroupService userGroupService;

  private final UserService userService;

  private final PermissionRepository permissionRepository;

  @Override
  public ResponseModel<UserGroups> create(UserGroups userGroups) {
    UserGroups record;
    String validationRule = userGroupService.validationRules(userGroups);

    if (validationRule != null) {
      return responseModel(null, validationRule);
    }

    userGroups.setFlag("1");
    userGroups.createDate();
    userGroups.createBy(userService.getUserId());
    userGroups.setFlag(AppConstants.ACTIVE_RECORD);
    record = userGroupService.create(userGroups);

    updatePermissions(userGroups);

    String message =
        (record == null) ? AppConstants.FAIL_CREATE_MESSAGE : AppConstants.SUCCESS_CREATE_MESSAGE;

    return responseModel(record, message);
  }

  @Override
  public ResponseModel<UserGroups> update(UserGroups userGroups) {

    userGroups.setFlag(AppConstants.ACTIVE_RECORD);
    userGroups.updatedBy(userService.getUserId());
    userGroups.updateDate();
    var record = userGroupService.update(userGroups);

    String message =
        (record == null) ? AppConstants.FAIL_UPDATE_MESSAGE : AppConstants.SUCCESS_UPDATE_MESSAGE;

    return responseModel(record, message);
  }

  @Override
  public ResponseModel<UserGroups> readId(Long id) {
    var record = userGroupService.fetchOne(id);

    String message =
        (!record.isPresent())
            ? AppConstants.FAIL_FETCH_MESSAGE
            : AppConstants.SUCCESS_FETCH_MESSAGE;

    return responseModel(record, message);
  }

  @Override
  public ResponseModel<UserGroups> readAll() {
    var record = userGroupService.fetchAll();

    String message =
        (record == null) ? AppConstants.FAIL_FETCH_MESSAGE : AppConstants.SUCCESS_FETCH_MESSAGE;

    return responseModel(record, message);
  }

  @Override
  public ResponseModel<UserGroups> delete(Long id) {
    var record = userGroupService.delete(id);

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

  private void updatePermissions(UserGroups userGroups) {
    var permissionList = permissionRepository.findAllById(userGroups.getPermissionIds());
    List<Permissions> permissions = new ArrayList<>();

    if (permissionList.iterator().hasNext()) {
      permissions =
          StreamSupport.stream(permissionList.spliterator(), false).collect(Collectors.toList());
    }
    userGroups.setPermissions(permissions);
    userGroupService.update(userGroups);
  }
}
