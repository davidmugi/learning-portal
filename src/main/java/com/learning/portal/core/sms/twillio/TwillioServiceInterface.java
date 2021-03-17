package com.learning.portal.core.sms.twillio;


import com.twilio.rest.api.v2010.account.Message;

import java.util.List;

public interface TwillioServiceInterface {

    public void sendSms(String messsage, List<String> phoneNumber);
}
