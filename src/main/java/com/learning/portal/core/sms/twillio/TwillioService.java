package com.learning.portal.core.sms.twillio;

import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TwillioService implements TwillioServiceInterface {

  private static final String ACCOUNT_SID = "ACa36cf8e321eafe8931c35beb3b9dc660";
  private static final String TOKEN = "f4404a92b12c3741cef31838241c4442";
  private static final String number = "+17653798486";

  @Override
  public void sendSms(String message, List<String> phoneNumber) {
    Twilio.init(ACCOUNT_SID, TOKEN);

    for (String s : phoneNumber) {
      Message.creator(new PhoneNumber(s), new PhoneNumber(number), message).create();
    }
  }
}
