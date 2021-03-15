package com.learning.portal.web.usermanager.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.learning.portal.core.audit.CreatedByAndUpdatedBy;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.List;

@Entity
@Table(name = "users")
public class Users extends CreatedByAndUpdatedBy {


  @NotBlank(message = "First name is required")
  @Size(
          max = 25,
          min = 1,
          message = "Provide a first name with more than 1 character and less than 25"
  )
  @Column(name = "first_name")
  private String firstName;

  @NotBlank(message = "Middle name is required")
  @Size(
          max = 25,
          min = 1,
          message = "Provide a middle name with more than 1 character and less than 25"
  )
  @Column(name = "middle_name")
  private String middleName;

  @NotBlank(message = "Surname is required")
  @Size(
          max = 25,
          min = 1,
          message = "Provide a surname with more than 1 character and less than 25"
  )
  @Column(name = "surname")
  private String surname;

  @Column(name = "enabled")
  private boolean enabled = false;

  @NotBlank(message = "Email address is required")
  @Size(
          max = 100,
          min = 1,
          message = "Provide a email with more than 1 character and less than 100"
  )
  @Column(name = "email")
  private String email;

  @NotBlank(message = "Password is required")
  @Column(name = "password")
  private String password;

  @Transient
  private List<String> permissions;

  @Transient
  private String userType;

  @Transient
  private String fullName;

  @NotBlank(message = "Phone number is required")
  @Size(
          max = 25,
          min = 10,
          message = "Provide a phone number with more than 10 character and less than 13"
  )
  @Column(name = "phone")
  private String phone;



  @Column(name = "user_type_id")
  private Long userTypeId;

  @Column(name = "user_group_id")
  private Long userGroupId;

  @JoinColumn(name = "user_type_id", insertable = false, updatable = false)
  @ManyToOne(fetch = FetchType.LAZY)
  private UserTypes userTypesLink;

  @JoinColumn(name = "user_group_id", insertable = false, updatable = false)
  @ManyToOne(fetch = FetchType.LAZY)
  private UserGroups userGroupsLink;

  public String getFirstName() {
    return firstName;
  }

  public void setFirstName(String firstName) {
    this.firstName = firstName;
  }

  public String getMiddleName() {
    return middleName;
  }

  public void setMiddleName(String middleName) {
    this.middleName = middleName;
  }

  public String getSurname() {
    return surname;
  }

  public void setSurname(String surname) {
    this.surname = surname;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public String getPhone() {
    return phone;
  }

  public void setPhone(String phone) {
    this.phone = phone;
  }

  public String getFullName() {
    return this.firstName +" "+ this.surname;
  }


  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public Long getUserTypeId() {
    return userTypeId;
  }

  public void setUserTypeId(Long userTypeId) {
    this.userTypeId = userTypeId;
  }

  public Long getUserGroupId() {
    return userGroupId;
  }

  public void setUserGroupId(Long userGroupId) {
    this.userGroupId = userGroupId;
  }

  public boolean isEnabled() {
    return enabled;
  }


  public void setEnabled(boolean enabled) {
    this.enabled = enabled;
  }

  public List<String> getPermissions() {
    return permissions;
  }

  public void setPermissions(List<String> permissions) {
    this.permissions = permissions;
  }

  public String getUserType() {
    return userType;
  }

  public void setUserType(String userType) {
    this.userType = userType;
  }

  @JsonIgnore
  public UserTypes getUserTypesLink() {
    return userTypesLink;
  }

  @JsonIgnore
  public UserGroups getUserGroupsLink() {
    return userGroupsLink;
  }
}
