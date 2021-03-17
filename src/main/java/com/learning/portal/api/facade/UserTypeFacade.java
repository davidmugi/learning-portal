package com.learning.portal.api.facade;

import com.learning.portal.api.FacadeInterface;
import com.learning.portal.api.data.ResponseModel;
import com.learning.portal.api.service.UserTypeService;
import com.learning.portal.core.template.AppConstants;
import com.learning.portal.web.usermanager.entity.UserTypes;
import com.learning.portal.web.usermanager.repository.UsertypeRepository;
import lombok.RequiredArgsConstructor;
import org.hibernate.usertype.UserType;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserTypeFacade implements FacadeInterface<UserType> {

    private final UserTypeService userTypeService;

    @Override
    public ResponseModel<UserType> create(UserType userType) {
        return null;
    }

    @Override
    public ResponseModel<UserType> update(UserType userType) {
        return null;
    }

    @Override
    public ResponseModel<UserType> readId(Long id) {
        return null;
    }

    @Override
    public ResponseModel readAll() {
      List<UserTypes> usertype =  userTypeService.fetchAll();

      String message = (usertype == null) ?
              AppConstants.FAIL_FETCH_MESSAGE : AppConstants.SUCCESS_FETCH_MESSAGE;

      return responseModel(usertype,message);
    }

    @Override
    public ResponseModel<UserType> delete(Long id) {
        return null;
    }

    private ResponseModel responseModel(Object record, String message) {
        String status = (record == null) ? "01" : "00";
        Object data = (record == null) ? null : record;

        ResponseModel responseModel = new ResponseModel();
        responseModel.setStatus(status);
        responseModel.setMessage(message);
        responseModel.setData(data);

        return responseModel;
    }
}
