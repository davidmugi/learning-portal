package com.learning.portal.core.sms.infobip.data;

import java.util.ArrayList;
import java.util.List;

public class InfobipMessageRequest {

  private String from;
  private List<String> to = new ArrayList<>();
  private String text;
  private String campaignId;
  private String operatorClientId;
  private String transliteration;

  public String getFrom() {
    return from;
  }

  public InfobipMessageRequest setFrom(String from) {
    this.from = from;
    return this;
  }

  public List<String> getTo() {
    return to;
  }

  public InfobipMessageRequest setTo(List<String> to) {
    this.to = to;
    return this;
  }

  public String getText() {
    return text;
  }

  public InfobipMessageRequest setText(String text) {
    this.text = text;
    return this;
  }

  public String getCampaignId() {
    return campaignId;
  }

  public void setCampaignId(String campaignId) {
    this.campaignId = campaignId;
  }

  public String getOperatorClientId() {
    return operatorClientId;
  }

  public void setOperatorClientId(String operatorClientId) {
    this.operatorClientId = operatorClientId;
  }

  public String getTransliteration() {
    return transliteration;
  }

  public void setTransliteration(String transliteration) {
    this.transliteration = transliteration;
  }

  @Override
  public String toString() {
    return "SMSTextualRequest{"
        + "from='"
        + from
        + "'"
        + ", to='"
        + to
        + "'"
        + ", text='"
        + text
        + "'"
        + ", campaignId='"
        + campaignId
        + "'"
        + ", operatorClientId='"
        + operatorClientId
        + "'"
        + ", transliteration='"
        + transliteration
        + "'"
        + '}';
  }
}
