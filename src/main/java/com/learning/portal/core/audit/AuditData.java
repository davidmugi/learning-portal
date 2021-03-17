package com.learning.portal.core.audit;


import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public class AuditData implements Serializable {

  private static final Long serialVersionUID = 1L;

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id")
  @Basic(optional = false)
  private Long id;

  @CreatedDate
  @Column(name = "created_date")
  @Temporal(TemporalType.TIMESTAMP)
  private Date createdDate;

  @LastModifiedDate
  @Column(name = "last_modified_date")
  @Temporal(TemporalType.TIMESTAMP)
  private Date lastModifiedDate;

  @Column(name = "flag")
  private String flag;

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public Date getCreatedDate() {
    return createdDate;
  }

  public void setCreatedDate(Date createdDate) {
    this.createdDate = createdDate;
  }

  public Date getLastModifiedDate() {
    return lastModifiedDate;
  }

  public void setLastModifiedDate(Date lastModifiedDate) {
    this.lastModifiedDate = lastModifiedDate;
  }

  public String getFlag() {
    return flag;
  }

  public void setFlag(String flag) {
    this.flag = flag;
  }

  public AuditData createDate(){
    this.createdDate = new Date(System.currentTimeMillis());
    this.lastModifiedDate = new Date(System.currentTimeMillis());
    return this;
  }

  public AuditData updateDate(){
    this.lastModifiedDate = new Date(System.currentTimeMillis());
    return this;
  }
}
