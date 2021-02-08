package com.learning.portal.web.usermanager.entity;

import com.learning.portal.core.audit.AuditData;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "user_types")
public class UserTypes implements Serializable {

  private static final Long serialVersionUID = 1L;

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id")
  @Basic(optional = false)
  private Long id;


  @Column(name = "name")
  private String name;

  @Column(name = "code")
  private String code;

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getCode() {
    return code;
  }

  public void setCode(String code) {
    this.code = code;
  }
}
