package com.learning.portal.web.controller;

import com.learning.portal.api.FacadeInterface;
import com.learning.portal.api.data.ResponseModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

public class ControllerGenerator<T extends Object, F extends FacadeInterface> {
  FacadeInterface facade;

  public ControllerGenerator(F f) {
    this.facade = f;
  }

  @CrossOrigin(origins ={ "http://localhost:3000"})
  @PostMapping("/create")
  public ResponseEntity<ResponseModel> create(@RequestBody @Valid T entity){
    return _create(entity);
  }

  @CrossOrigin(origins ={ "http://localhost:3000"})
  @PostMapping("/update")
  public ResponseEntity<ResponseModel> update(@RequestBody @Valid T entity){
    return _update(entity);
  }

  @CrossOrigin(origins ={ "http://localhost:3000"})
  @PostMapping("/delete/{id}")
  public ResponseEntity<ResponseModel> delete(@PathVariable("id") Long id){
    return _delete(id);
  }

  @CrossOrigin(origins ={ "http://localhost:3000"})
  @GetMapping("/read-all")
  public ResponseEntity<ResponseModel> readAll(){
    return _readAll();
  }

  @CrossOrigin(origins ={ "http://localhost:3000"})
  @GetMapping("/read-one/{id}")
  public ResponseEntity<ResponseModel> readOne(@PathVariable("id") Long id){
    return _readOne(id);
  }

  public ResponseEntity<ResponseModel> _create(T entity) {
    ResponseModel responseModel = facade.create(entity);
    Boolean isSuccess = responseModel.getStatus().equals("00");

    return ResponseEntity.status(
            (isSuccess ? HttpStatus.OK.value() : HttpStatus.BAD_REQUEST.value()))
        .body(responseModel);
  }

  public ResponseEntity<ResponseModel> _update(T entity) {
    ResponseModel responseModel = facade.update(entity);
    Boolean isSuccess = responseModel.getStatus().equals("00");

    return ResponseEntity.status(
            (isSuccess ? HttpStatus.OK.value() : HttpStatus.BAD_REQUEST.value()))
        .body(responseModel);
  }

  public ResponseEntity<ResponseModel> _delete(@PathVariable("id") Long id) {
    ResponseModel responseModel = facade.delete(id);
    Boolean isSuccess = responseModel.getStatus().equals("00");

    return ResponseEntity.status(
            (isSuccess ? HttpStatus.OK.value() : HttpStatus.BAD_REQUEST.value()))
        .body(responseModel);
  }

  public ResponseEntity<ResponseModel> _readAll() {
    ResponseModel responseModel = facade.readAll();
    Boolean isSuccess = responseModel.getStatus().equals("00");

    return ResponseEntity.status(
            (isSuccess ? HttpStatus.OK.value() : HttpStatus.BAD_REQUEST.value()))
        .body(responseModel);
  }

  public ResponseEntity<ResponseModel> _readOne(@PathVariable("id") Long id) {
    ResponseModel responseModel = facade.readId(id);
    Boolean isSuccess = responseModel.getStatus().equals("00");

    return ResponseEntity.status(
            (isSuccess ? HttpStatus.OK.value() : HttpStatus.BAD_REQUEST.value()))
        .body(responseModel);
  }
}
