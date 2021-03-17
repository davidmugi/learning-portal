package com.learning.portal.api.facade;

import com.learning.portal.api.FacadeInterface;
import com.learning.portal.api.data.ResponseModel;
import com.learning.portal.api.service.ContentService;
import com.learning.portal.api.service.GradeService;
import com.learning.portal.api.service.UserService;
import com.learning.portal.core.aws.AmazonServiceInterface;
import com.learning.portal.core.template.AppConstants;
import com.learning.portal.web.classes.entity.Grade;
import com.learning.portal.web.usermanager.entity.Users;
import lombok.RequiredArgsConstructor;
import org.apache.commons.io.FilenameUtils;
import org.springframework.stereotype.Service;
import com.learning.portal.web.content.entity.Content;
import org.springframework.web.multipart.MultipartFile;

import javax.transaction.Transactional;
import java.io.IOException;
import java.util.Date;
import java.util.Optional;
import java.util.UUID;

@Service
@Transactional
@RequiredArgsConstructor
public class ContentFacade implements FacadeInterface<Content> {

  private final ContentService contentService;

  private final UserService userService;

  private final GradeService gradeService;

  private final AmazonServiceInterface amazonServiceInterface;

  @Override
  public ResponseModel<Content> create(Content content) {
    return null;
  }

  public ResponseModel<Content> create(Content content, MultipartFile file) throws IOException {
    String originalFileName = file.getOriginalFilename();
    String ext = FilenameUtils.getExtension(originalFileName);
    String filename = String.format("file-%s.%s", UUID.randomUUID().toString(), ext);
    String url = amazonServiceInterface.uploadMultipartFile(file, filename);

    Grade grade = gradeService.fetchOne(content.getGradeId()).get();
    Users users = userService.getLoginUSer().get();

    content.setCreatedDate(new Date());
    content.setLastModifiedDate(new Date());
    content.setCreatedBy(users.getId());
    content.setContentLink(url);
    content.setFlag(AppConstants.ACTIVE_RECORD);
    content.setFileServeName(filename);


    var record = contentService.create(content);

    content.setGradeName(grade.getName());

    String message =
        (record == null) ? AppConstants.FAIL_CREATE_MESSAGE : AppConstants.SUCCESS_CREATE_MESSAGE;

    return responseModel(record, message);
  }

  @Override
  public ResponseModel<Content> update(Content content) {
    return null;
  }

  public ResponseModel<Content> update(Content content, MultipartFile file,Long id) throws IOException {
    Optional<Content> oOldContent = contentService.fetchOne(id);
    Users users = userService.getLoginUSer().get();

    String originalFileName = file.getOriginalFilename();
    String ext = FilenameUtils.getExtension(originalFileName);
    String filename = String.format("file-%s.%s", UUID.randomUUID().toString(), ext);

    if (oOldContent.isEmpty()){
      return responseModel(null,AppConstants.FAIL_UPDATE_MESSAGE);
    }
    Content oldContent = oOldContent.get();


    amazonServiceInterface.deleteFile(oldContent.getFileServeName());
    String url = amazonServiceInterface.uploadMultipartFile(file,filename);


    content.setContentLink(url);
    content.setFileServeName(filename);
    content.updateDate();
    content.updatedBy(users.getId());
    content.setFlag(AppConstants.ACTIVE_RECORD);
    var record = contentService.update(content);

    String message =
        (record == null) ? AppConstants.FAIL_UPDATE_MESSAGE : AppConstants.SUCCESS_UPDATE_MESSAGE;

    return responseModel(record, message);
  }

  @Override
  public ResponseModel<Content> readId(Long id) {
    var record = contentService.fetchOne(id);

    String message =
        (record == null) ? AppConstants.FAIL_FETCH_MESSAGE: AppConstants.SUCCESS_FETCH_MESSAGE ;

    return responseModel(record, message);
  }

  @Override
  public ResponseModel<Content> readAll() {
    var record = contentService.fetchAll();

    String message =
        (record == null) ? AppConstants.FAIL_FETCH_MESSAGE : AppConstants.SUCCESS_FETCH_MESSAGE;

    return responseModel(record, message);
  }

  @Override
  public ResponseModel<Content> delete(Long id) {
    var content = contentService.fetchOne(id);


    boolean success = amazonServiceInterface.deleteFile(content.get().getFileServeName());

    if (success) {
      var record = contentService.delete(id);
      content.get().setContentLink(null);
      content.get().setFlag(AppConstants.DELETE_RECORD);

      String message =
          (record == null && !success) ? AppConstants.FAIL_DELETE_MESSAGE : AppConstants.SUCCESS_DELETE_MESSAGE;
      return responseModel(record, message);
    }
    return responseModel(null,AppConstants.FAIL_DELETE_MESSAGE);
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

  public ResponseModel fetchContentPerGrade(Long gradeId) {
    var record = contentService.fetchPerGrade(gradeId);

    String message =
        (record == null) ? AppConstants.FAIL_FETCH_MESSAGE : AppConstants.SUCCESS_FETCH_MESSAGE;

    return responseModel(record, message);
  }

  public ResponseModel fetchContentPerCreator(Long userId) {
    var record = contentService.fetchPerCreator(userId);

    String message =
        (record == null) ? AppConstants.FAIL_FETCH_MESSAGE : AppConstants.SUCCESS_FETCH_MESSAGE;

    return responseModel(record, message);
  }
}
