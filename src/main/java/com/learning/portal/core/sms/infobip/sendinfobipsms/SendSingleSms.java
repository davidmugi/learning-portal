package com.learning.portal.core.sms.infobip.sendinfobipsms;

import com.google.gson.GsonBuilder;

import com.learning.portal.core.sms.infobip.configurations.Configuration;
import com.learning.portal.core.sms.infobip.configurations.TimeoutClientProvider;
import com.learning.portal.core.sms.infobip.data.InfobipMessageRequest;
import com.learning.portal.core.sms.infobip.data.SMSResponse;
import retrofit.RequestInterceptor;
import retrofit.RestAdapter;
import retrofit.converter.GsonConverter;

public class SendSingleSms implements SendSingleSmsServiceInterface {

  private final Configuration configuration;

  public SendSingleSms(Configuration configuration) {
    this.configuration = configuration;
  }

  @Override
  public SMSResponse execute(InfobipMessageRequest request) {

    RestAdapter restAdapter =
        new RestAdapter.Builder()
            .setEndpoint(configuration.getBaseUrl())
            .setRequestInterceptor(getRequestInterceptor())
            .setConverter(
                new GsonConverter(
                    new GsonBuilder().setDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSZ").create()))
            .setClient(new TimeoutClientProvider(configuration))
            .build();

    SendSingleSmsServiceInterface sendSingleSmsServiceInterface =
        restAdapter.create(SendSingleSmsServiceInterface.class);

    return sendSingleSmsServiceInterface.execute(request);
  }

  private RequestInterceptor getRequestInterceptor() {
    return new RequestInterceptor() {
      @Override
      public void intercept(RequestFacade request) {
        if (configuration != null && configuration.getAuthorizationHeader() != null) {
          request.addHeader("Authorization", configuration.getAuthorizationHeader());
          request.addHeader("User-Agent", "Java-Client-Library");
        }
      }
    };
  }
}
