package com.learning.portal.core.sms.infobip.configurations;

import com.squareup.okhttp.OkHttpClient;
import retrofit.client.Client;
import retrofit.client.OkClient;

import java.util.concurrent.TimeUnit;

public class TimeoutClientProvider implements Client.Provider {
  private final Configuration configuration;

  public TimeoutClientProvider(Configuration configuration) {
    this.configuration = configuration;
  }

  @Override
  public Client get() {
    final OkHttpClient okHttpClient = new OkHttpClient();
    okHttpClient.setReadTimeout(configuration.getReadTimeOut(), TimeUnit.MILLISECONDS);
    okHttpClient.setConnectTimeout(configuration.getConnectionTimeOut(), TimeUnit.MILLISECONDS);
    return new OkClient(okHttpClient);
  }
}
