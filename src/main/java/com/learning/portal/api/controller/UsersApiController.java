package com.learning.portal.api.controller;

import com.learning.portal.api.data.ResponseModel;
import com.learning.portal.api.facade.UserFacade;
import com.learning.portal.web.controller.ControllerGenerator;
import com.learning.portal.web.usermanager.entity.Users;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/v1/users")
public class UsersApiController extends ControllerGenerator<Users, UserFacade> {

  @Autowired
  private UserFacade userFacade;

  public UsersApiController(UserFacade userFacade) {
    super(userFacade);
  }

  @CrossOrigin(origins ={ "http://localhost:3000","https://eimishajamii.herokuapp.com"})
  @PostMapping("/sign-up")
  public ResponseEntity<ResponseModel> signUp(@RequestBody @Valid Users users){
    ResponseModel<Users> response = userFacade.signUp(users);
    boolean isSuccess = response.getStatus().equals("00");

    return ResponseEntity.status(
            (isSuccess ? HttpStatus.OK : HttpStatus.BAD_REQUEST))
            .body(response);
  }
}
