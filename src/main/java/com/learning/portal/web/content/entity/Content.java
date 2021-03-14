package com.learning.portal.web.content.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.learning.portal.core.audit.CreatedByAndUpdatedBy;
import com.learning.portal.web.classes.entity.Grade;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.io.Serializable;

@Entity
@Table(name = "content")
public class Content extends CreatedByAndUpdatedBy {

  @Column(name = "name")
  @Size(max = 100, min = 5)
  @NotBlank(message = "Name is required")
  private String name;

  @Column(name = "grade_id")
  @NotBlank(message = "Garde is required")
  private Long gradeId;

  @Column(name = "description")
  @NotBlank(message = "Description is required")
  private String description;

  @Column(name = "file_server_name")
  private String fileServeName;

  @Column(name = "content_link")
  @NotBlank(message = "Content link is required")
  private String contentLink;

  @JoinColumn(name = "grade_id", insertable = false, updatable = false, nullable = false)
  @ManyToOne(fetch = FetchType.LAZY)
  private Grade gradeLink;

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public Long getGradeId() {
    return gradeId;
  }

  public void setGradeId(Long gradeId) {
    this.gradeId = gradeId;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public String getContentLink() {
    return contentLink;
  }

  public void setContentLink(String contentLink) {
    this.contentLink = contentLink;
  }

  @JsonIgnore
  public Grade getGradeLink() {
    return gradeLink;
  }


  public String getFileServeName() {
    return fileServeName;
  }

  public void setFileServeName(String fileServeName) {
    this.fileServeName = fileServeName;
  }
}
