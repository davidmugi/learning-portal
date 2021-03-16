package com.learning.portal.core.sms.infobip.sendinfobipsms;


import com.learning.portal.core.sms.infobip.data.InfobipMessageRequest;
import com.learning.portal.core.sms.infobip.data.SMSResponse;
import retrofit.http.Body;
import retrofit.http.POST;

public interface SendSingleSmsServiceInterface {

  @POST("/sms/2/text/single")
  public SMSResponse execute(@Body InfobipMessageRequest request);
}
