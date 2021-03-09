package com.learning.portal.web.usermanager.repository;

import com.learning.portal.web.usermanager.entity.Users;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
@Repository
public interface UserRepository extends CrudRepository<Users,Long> {

    Optional<Users> findByEmail(String email);

    Optional<Users> findByPhone(String phone);

    List<Users> findAllByFlag(String flag);
}
