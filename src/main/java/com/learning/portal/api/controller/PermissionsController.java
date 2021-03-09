package com.learning.portal.api.controller;

import com.learning.portal.api.facade.PermissionFacade;
import com.learning.portal.web.controller.ControllerGenerator;
import com.learning.portal.web.usermanager.entity.Permissions;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/permissions")
public class PermissionsController extends ControllerGenerator<Permissions, PermissionFacade> {
    public PermissionsController(PermissionFacade permissionFacade) {
        super(permissionFacade);
    }
}
