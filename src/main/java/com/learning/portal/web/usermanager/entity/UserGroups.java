package com.learning.portal.web.usermanager.entity;

import com.learning.portal.core.audit.CreatedByAndUpdatedBy;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.List;

@Entity
@Table(name = "user_group")
public class UserGroups extends CreatedByAndUpdatedBy {

  @NotBlank(message = "User group name is required")
  @Column(name = "name")
  private String name;

  @NotBlank(message = "Description is reqiured")
  @Column(name = "description")
  private String description;

  @NotBlank(message = "Base type is required")
  @Column(name = "base_type")
  private String baseType;

  @Transient private List<Long> permissionIds;

  @ManyToMany(fetch = FetchType.EAGER)
  @JoinTable(
      name = "user_group_permissions",
      joinColumns = {@JoinColumn(name = "user_group_id")},
      inverseJoinColumns = {@JoinColumn(name = "permission_id")})
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

  public String getBaseType() {
    return baseType;
  }

  public void setBaseType(String baseType) {
    this.baseType = baseType;
  }

  public List<Long> getPermissionIds() {
    return permissionIds;
  }

  public void setPermissionIds(List<Long> permissionIds) {
    this.permissionIds = permissionIds;
  }

  public List<Permissions> getPermissions() {
    return permissions;
  }

  public void setPermissions(List<Permissions> permissions) {
    this.permissions = permissions;
  }


}
