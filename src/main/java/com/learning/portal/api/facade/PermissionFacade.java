package com.learning.portal.api.facade;

import com.learning.portal.api.FacadeInterface;
import com.learning.portal.api.data.ResponseModel;
import com.learning.portal.api.service.PermissionsService;
import com.learning.portal.core.template.AppConstants;
import com.learning.portal.web.usermanager.entity.Permissions;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class PermissionFacade implements FacadeInterface<Permissions> {

    private final PermissionsService permissionsService;

    @Override
    public ResponseModel<Permissions> create(Permissions permissions) {
        return null;
    }

    @Override
    public ResponseModel<Permissions> update(Permissions permissions) {
        return null;
    }

    @Override
    public ResponseModel<Permissions> readId(Long id) {
        return null;
    }

    @Override
    public ResponseModel<Permissions> readAll() {
        List<Permissions> record = permissionsService.fetchAll();
        String message = (record == null) ?
                AppConstants.SUCCESS_FETCH_MESSAGE : AppConstants.FAIL_FETCH_MESSAGE;

        return responseModel(record,message);
    }

    @Override
    public ResponseModel<Permissions> delete(Long id) {
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
