package com.learning.portal.api;

import com.learning.portal.api.data.ResponseModel;

public interface FacadeInterface<T> {

    ResponseModel<T>  create(T t);

    ResponseModel<T>  update(T t);

    ResponseModel<T> readId(Long id);

    ResponseModel<T> readAll();

    ResponseModel<T> delete(Long id);
}
