package com.learning.portal.web.usermanager.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.learning.portal.core.audit.AuditData;
import jdk.jfr.Description;
import org.w3c.dom.stylesheets.LinkStyle;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.List;

@Entity
@Table(name = "permissions")
public class Permissions extends AuditData {

  @Column(name = "name")
  private String name;

  @Column(name = "description")
  private String description;

  @JsonIgnoreProperties
  @JsonBackReference("permission-group")
  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "permission_group_id")
  private PermissionGroups permissionGroups;

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public PermissionGroups getPermissionGroups() {
    return permissionGroups;
  }

  public void setPermissionGroups(PermissionGroups permissionGroups) {
    this.permissionGroups = permissionGroups;
  }
}
