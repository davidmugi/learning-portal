package com.learning.portal.api.facade;

import com.learning.portal.api.FacadeInterface;
import com.learning.portal.api.data.ResponseModel;
import com.learning.portal.api.service.ContentService;
import com.learning.portal.api.service.UserService;
import com.learning.portal.core.template.AppConstants;
import com.learning.portal.web.usermanager.entity.Users;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import com.learning.portal.web.content.entity.Content;

import javax.transaction.Transactional;
import java.util.Date;

@Service
@Transactional
@RequiredArgsConstructor
public class ContentFacade implements FacadeInterface<Content> {

    private final ContentService contentService;

    private final UserService userService;

    @Override
    public ResponseModel<Content> create(Content content) {


        Users users = userService.getLoginUSer().get();

        content.setCreatedDate(new Date());
        content.setLastModifiedDate(new Date());
        content.setCreatedBy(users.getId());

        var record = contentService.create(content);

        String message = (record == null) ?
                AppConstants.FAIL_CREATE_MESSAGE : AppConstants.SUCCESS_CREATE_MESSAGE;

        return responseModel(record,message);
    }

    @Override
    public ResponseModel<Content> update(Content content) {
        return null;
    }

    @Override
    public ResponseModel<Content> readId(Long id) {
        var record = contentService.fetchOne(id);

        String message = (record.isPresent()) ?
                AppConstants.SUCCESS_FETCH_MESSAGE : AppConstants.FAIL_FETCH_MESSAGE;


        return responseModel(record,message);
    }

    @Override
    public ResponseModel<Content> readAll() {
        var record = contentService.fetchAll();

        String message = (record == null) ?
                AppConstants.FAIL_FETCH_MESSAGE : AppConstants.SUCCESS_FETCH_MESSAGE;

        return responseModel(record,message);
    }

    @Override
    public ResponseModel<Content> delete(Long id) {
        return null;
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
}
