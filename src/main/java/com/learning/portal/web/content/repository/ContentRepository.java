package com.learning.portal.web.content.repository;

import com.learning.portal.web.content.entity.Content;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ContentRepository extends CrudRepository<Content,Long> {

    public List<Content> findAllByGradeIdAndFlag(Long gradeId,String flag);

    public List<Content> findAllByCreatedByAndFlag(Long userId,String flag);

    public List<Content> findAllByFlag(String flag);

}
