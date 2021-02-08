package com.learning.portal.api.controller;

import com.learning.portal.api.facade.UserGroupFacade;
import com.learning.portal.web.controller.ControllerGenerator;
import com.learning.portal.web.usermanager.entity.UserGroups;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/vl/user-group")
public class UserGroupApiController extends ControllerGenerator<UserGroups, UserGroupFacade> {
  public UserGroupApiController(UserGroupFacade userGroupFacade) {
    super(userGroupFacade);
  }
}
