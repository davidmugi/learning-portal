package com.learning.portal.core.sms.infobip.configurations;

import javax.xml.bind.DatatypeConverter;

public class BasicInfobipConfiguration extends Configuration {

  private String userName;

  private String password;

  public BasicInfobipConfiguration() {}

  public BasicInfobipConfiguration(String userName, String password, String baseUrl) {
    this.baseUrl = baseUrl;
    this.userName = userName;
    this.password = password;
  }

  public String getUserName() {
    return userName;
  }

  public void setUserName(String userName) {
    this.userName = userName;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  @Override
  public String getAuthorizationHeader() {
    return "Basic " + encodeBase64();
  }

  private String encodeBase64() {
    String userPass = userName + ":" + password;
    return DatatypeConverter.printBase64Binary(userPass.getBytes());
  }
}
