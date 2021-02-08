package com.learning.portal.core.template;

import com.learning.portal.web.usermanager.entity.Users;

import java.util.List;
import java.util.Optional;

public interface BaseServiceInterface<T> {

    public T create(T t);

    public boolean update(T t);

    public boolean delete(Long id);

    public Optional<T> fetchOne(Long id);

    public List<T> fetchAll();
}
