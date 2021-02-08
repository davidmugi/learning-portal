package com.learning.portal.api.controller;

import com.learning.portal.api.facade.UserFacade;
import com.learning.portal.web.controller.ControllerGenerator;
import com.learning.portal.web.usermanager.entity.Users;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/users")
public class UsersApiController extends ControllerGenerator<Users, UserFacade> {
  public UsersApiController(UserFacade userFacade) {
    super(userFacade);
  }
}
