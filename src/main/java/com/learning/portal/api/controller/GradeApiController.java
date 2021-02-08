package com.learning.portal.api.controller;

import com.learning.portal.api.facade.GradeFacade;
import com.learning.portal.web.classes.entity.Grade;
import com.learning.portal.web.controller.ControllerGenerator;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/grade")
public class GradeApiController extends ControllerGenerator<Grade, GradeFacade> {
  public GradeApiController(GradeFacade gradeFacade) {
    super(gradeFacade);
  }
}
