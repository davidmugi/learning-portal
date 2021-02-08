package com.learning.portal.api.data;

public class ResponseModel<E> {

  private String status;

  private String message;

  private E data;

  public ResponseModel() {
  }

  public ResponseModel(String status, String message) {
    this.status = status;
    this.message = message;
  }

  public ResponseModel(String status, String message, E data) {
    this.status = status;
    this.message = message;
    this.data = data;
  }

  public String getStatus() {
    return status;
  }

  public void setStatus(String status) {
    this.status = status;
  }

  public String getMessage() {
    return message;
  }

  public void setMessage(String message) {
    this.message = message;
  }

  public E getData() {
    return data;
  }

  public void setData(E data) {
    this.data = data;
  }
}
