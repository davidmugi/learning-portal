package com.learning.portal.core.audit;

import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import java.util.Date;

@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public class CreatedByAndUpdatedBy extends AuditData {

  @CreatedBy
  @Column(name = "created_by")
  private Long createdBy;

  @LastModifiedBy
  @Column(name = "last_modified_by")
  private Long lastModifiedBy;

  public CreatedByAndUpdatedBy() {}

  public Long getCreatedBy() {
    return createdBy;
  }

  public void setCreatedBy(Long createdBy) {
    this.createdBy = createdBy;
  }

  public Long getLastModifiedBy() {
    return lastModifiedBy;
  }

  public void setLastModifiedBy(Long lastModifiedBy) {
    this.lastModifiedBy = lastModifiedBy;
  }

  public CreatedByAndUpdatedBy createBy(Long userId){
    this.createdBy = userId;
    this.lastModifiedBy = userId;
    return this;
  }

  public CreatedByAndUpdatedBy updatedBy(Long userId){
    this.lastModifiedBy = userId;
    return this;
  }
}
