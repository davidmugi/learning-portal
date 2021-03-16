package com.learning.portal.core.sms.infobip.configurations;

public abstract class Configuration {

  protected String baseUrl;

  protected int connectionTimeOut = 10000;

  protected int readTimeOut = 10000;

  public abstract String getAuthorizationHeader();

  public String getBaseUrl() {
    return baseUrl;
  }

  public int getConnectionTimeOut() {
    return connectionTimeOut;
  }

  public void setConnectionTimeOut(int connectionTimeOut) {
    this.connectionTimeOut = connectionTimeOut;
  }

  public int getReadTimeOut() {
    return readTimeOut;
  }

  public void setReadTimeOut(int readTimeOut) {
    this.readTimeOut = readTimeOut;
  }
}
