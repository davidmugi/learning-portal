package com.learning.portal.web.usermanager.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.learning.portal.core.audit.AuditData;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "permission_groups")
public class PermissionGroups extends AuditData {

  @Column(name = "name")
  private String name;

  @Column(name = "description")
  private String description;

  @JsonManagedReference("permission-group")
  @OneToMany(mappedBy = "permissionGroups", fetch = FetchType.EAGER)
  private List<Permissions> permissions;

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

  public List<Permissions> getPermissions() {
    return permissions;
  }
}
