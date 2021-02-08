package com.learning.portal.api.controller;

import com.learning.portal.api.facade.UserTypeFacade;
import com.learning.portal.web.controller.ControllerGenerator;
import com.learning.portal.web.usermanager.entity.UserTypes;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/user-type")
public class UserTypeController extends ControllerGenerator<UserTypes,UserTypeFacade> {
    public UserTypeController(UserTypeFacade userTypeFacade) {
        super(userTypeFacade);
    }
}
