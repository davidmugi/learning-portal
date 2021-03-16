package com.learning.portal.core.sms.infobip.data;

import java.util.ArrayList;
import java.util.List;

public class SMSResponse {

  private String bulkId;
  private String trackingProcessKey;
  private List<SMSResponseDetails> messages = new ArrayList<>();

  public SMSResponse() {}

  public List<SMSResponseDetails> getMessages() {
    return messages;
  }

  public void setMessages(List<SMSResponseDetails> messages) {
    this.messages = messages;
  }

  public String getBulkId() {
    return bulkId;
  }

  public void setBulkId(String bulkId) {
    this.bulkId = bulkId;
  }

  public String getTrackingProcessKey() {
    return trackingProcessKey;
  }

  public void setTrackingProcessKey(String trackingProcessKey) {
    this.trackingProcessKey = trackingProcessKey;
  }

  @Override
  public boolean equals(Object obj) {
    if (obj == null) {
      return false;
    }
    if (getClass() != obj.getClass()) {
      return false;
    }

    SMSResponse o = (SMSResponse) obj;
    if (this.bulkId == null) {
      if (o.bulkId != null) {
        return false;
      }
    } else if (!this.bulkId.equals(o.bulkId)) {
      return false;
    }
    if (this.trackingProcessKey == null) {
      if (o.trackingProcessKey != null) {
        return false;
      }
    } else if (!this.trackingProcessKey.equals(o.trackingProcessKey)) {
      return false;
    }
    if (this.messages == null) {
      if (o.messages != null) {
        return false;
      }
    } else if (!this.messages.equals(o.messages)) {
      return false;
    }

    return true;
  }

  @Override
  public String toString() {
    return "SMSResponse{"
        + "bulkId='"
        + bulkId
        + "'"
        + ", trackingProcessKey='"
        + trackingProcessKey
        + "'"
        + ", messages='"
        + messages
        + "'"
        + '}';
  }
}
