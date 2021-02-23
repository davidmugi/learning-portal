package com.learning.portal.core.template;

import java.util.List;
import java.util.Optional;

public interface BaseServiceInterface<T> {

    public T create(T t);

    public Object update(T t);

    public Object delete(Long id);

    public Optional<T> fetchOne(Long id);

    public List<T> fetchAll();
}
