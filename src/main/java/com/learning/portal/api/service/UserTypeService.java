package com.learning.portal.api.service;

import com.learning.portal.core.template.BaseServiceInterface;
import com.learning.portal.web.usermanager.entity.UserTypes;
import com.learning.portal.web.usermanager.repository.UsertypeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class UserTypeService implements BaseServiceInterface<UserTypes> {


    private final UsertypeRepository usertypeRepository;

    @Override
    public UserTypes create(UserTypes userTypes) {
        return null;
    }

    @Override
    public boolean update(UserTypes userTypes) {
        return false;
    }

    @Override
    public boolean delete(Long id) {
        return false;
    }

    @Override
    public Optional<UserTypes> fetchOne(Long id) {
        return Optional.empty();
    }

    @Override
    public List<UserTypes> fetchAll() {
        return (List<UserTypes>) usertypeRepository.findAll();
    }
}
