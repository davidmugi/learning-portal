package com.learning.portal.api.controller;

import com.learning.portal.api.data.ResponseModel;
import com.learning.portal.api.facade.ContentFacade;
import com.learning.portal.web.content.entity.Content;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
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

  @CrossOrigin(origins = {"http://localhost:3000"})
  @GetMapping("/fetch-per-grade/{id}")
  public ResponseEntity<ResponseModel> fetchPerGrade(@PathVariable("id") Long id) {
    var response = contentFacade.fetchContentPerGrade(id);
    Boolean isSuccess = response.getStatus().equals("00");

    return ResponseEntity.status(
            (isSuccess ? HttpStatus.OK.value() : HttpStatus.BAD_REQUEST.value()))
        .body(response);
  }

  @CrossOrigin(origins = {"http://localhost:3000"})
  @GetMapping("fetch-per-creator/{id}")
  public ResponseEntity<ResponseModel> fetchPerCreator(@PathVariable("id") Long id) {
    var record = contentFacade.fetchContentPerCreator(id);
    boolean isSuccess = record.getStatus().equals("00");

    return ResponseEntity.status(
            (isSuccess ? HttpStatus.OK.value() : HttpStatus.BAD_REQUEST.value()))
        .body(record);
  }

  @CrossOrigin(origins = {"http://localhost:3000"})
  @PostMapping("/create")
  public ResponseEntity<ResponseModel> create(
      @RequestParam("file") MultipartFile file, Content entity) throws IOException {
    ResponseModel responseModel = contentFacade.create(entity, file);
    Boolean isSuccess = responseModel.getStatus().equals("00");

    return ResponseEntity.status(
            (isSuccess ? HttpStatus.OK.value() : HttpStatus.BAD_REQUEST.value()))
        .body(responseModel);
  }

  @CrossOrigin(origins = {"http://localhost:3000"})
  @DeleteMapping("/delete/{id}")
  public ResponseEntity<ResponseModel> delete(@PathVariable("id") Long id) {
    var response = contentFacade.delete(id);
    Boolean isSuccess = response.getStatus().equals("00");

    return ResponseEntity.status(
            (isSuccess ? HttpStatus.OK.value() : HttpStatus.BAD_REQUEST.value()))
        .body(response);
  }

  @CrossOrigin(origins = {"http://localhost:3000"})
  @GetMapping("/fetch-one/{id}")
  public ResponseEntity<ResponseModel> fetchOne(@PathVariable("id") Long id) {
    var response = contentFacade.readId(id);
    Boolean isSuccess = response.getStatus().equals("00");

    return ResponseEntity.status(
            (isSuccess ? HttpStatus.OK.value() : HttpStatus.BAD_REQUEST.value()))
        .body(response);
  }

  @CrossOrigin(origins = {"http://localhost:3000"})
  @GetMapping("/fetch-all")
  public ResponseEntity<ResponseModel> fetchAll() {
    var response = contentFacade.readAll();
    Boolean isSuccess = response.getStatus().equals("00");

    return ResponseEntity.status(
            (isSuccess ? HttpStatus.OK.value() : HttpStatus.BAD_REQUEST.value()))
        .body(response);
  }

  @CrossOrigin(origins = {"http://localhost:3000"})
  @PostMapping("/update/{id}")
  public ResponseEntity<ResponseModel> update(
      @PathVariable("id") Long id, @RequestParam("file") MultipartFile file, Content content)
      throws IOException {
    var response = contentFacade.update(content, file, id);
    Boolean isSuccess = response.getStatus().equals("00");

    return ResponseEntity.status(
            (isSuccess ? HttpStatus.OK.value() : HttpStatus.BAD_REQUEST.value()))
        .body(response);
  }
}
