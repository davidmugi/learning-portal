package com.learning.portal.api.controller;

import com.learning.portal.api.data.ResponseModel;
import com.learning.portal.api.facade.ContentFacade;
import com.learning.portal.web.content.entity.Content;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.io.IOException;

@RestController
@RequestMapping("/api/v1/content")
public class ContentController {

  private ContentFacade contentFacade;

  public ContentController(ContentFacade contentFacade) {
    this.contentFacade = contentFacade;
  }

  @GetMapping("/fetch-per-grade/{id}")
  public ResponseEntity<ResponseModel> fetchPerGrade(@RequestParam("id") Long id) {
    var response = contentFacade.fetchContentPerGrade(id);
    Boolean isSuccess = response.getStatus().equals("00");

    return ResponseEntity.status(
            (isSuccess ? HttpStatus.OK.value() : HttpStatus.BAD_REQUEST.value()))
        .body(response);
  }

  @GetMapping("fetch-per-creator/{id}")
  public ResponseEntity<ResponseModel> fetchPerCreator(@RequestParam("id") Long id) {
    var record = contentFacade.fetchContentPerCreator(id);
    boolean isSuccess = record.getStatus().equals("00");

    return ResponseEntity.status(
            (isSuccess ? HttpStatus.OK.value() : HttpStatus.BAD_REQUEST.value()))
        .body(record);
  }

  @PostMapping("/create")
  public ResponseEntity<ResponseModel> create(
      @RequestBody @Valid Content entity, @RequestParam("file") MultipartFile file)
      throws IOException {
    ResponseModel responseModel = contentFacade.create(entity, file);
    Boolean isSuccess = responseModel.getStatus().equals("00");

    return ResponseEntity.status(
            (isSuccess ? HttpStatus.OK.value() : HttpStatus.BAD_REQUEST.value()))
        .body(responseModel);
  }

  @DeleteMapping("/delete/{id}")
  public ResponseEntity<ResponseModel> delete(@RequestParam("id") Long id) {
    var response = contentFacade.delete(id);
    Boolean isSuccess = response.getStatus().equals("00");

    return ResponseEntity.status(
            (isSuccess ? HttpStatus.OK.value() : HttpStatus.BAD_REQUEST.value()))
        .body(response);
  }

  @GetMapping("/fetch-one/{id}")
  public ResponseEntity<ResponseModel> fetchOne(@RequestParam("id") Long id) {
    var response = contentFacade.readId(id);
    Boolean isSuccess = response.getStatus().equals("00");

    return ResponseEntity.status(
            (isSuccess ? HttpStatus.OK.value() : HttpStatus.BAD_REQUEST.value()))
        .body(response);
  }

  @GetMapping("/fetch-all")
  public ResponseEntity<ResponseModel> fetchAll() {
    var response = contentFacade.readAll();
    Boolean isSuccess = response.getStatus().equals("00");

    return ResponseEntity.status(
            (isSuccess ? HttpStatus.OK.value() : HttpStatus.BAD_REQUEST.value()))
        .body(response);
  }
}
