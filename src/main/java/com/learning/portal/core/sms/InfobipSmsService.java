package com.learning.portal.core.sms;


import com.learning.portal.core.sms.infobip.configurations.BasicInfobipConfiguration;
import com.learning.portal.core.sms.infobip.data.InfobipMessageRequest;
import com.learning.portal.core.sms.infobip.data.SMSResponse;
import com.learning.portal.core.sms.infobip.data.SMSResponseDetails;
import com.learning.portal.core.sms.infobip.sendinfobipsms.SendSingleSms;
import com.learning.portal.web.config.entity.InfobipSmsConfig;
import com.learning.portal.web.config.repository.InfobipSmsConfigRepository;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class InfobipSmsService {

  private final InfobipSmsConfigRepository infobipSmsConfigRepository;

  private final Logger LOGGER = LoggerFactory.getLogger(InfobipSmsService.class);

  public void infobipSendSms(InfobipMessageRequest infobipMessageRequest) {

    InfobipSmsConfig infobipSmsConfig = infobipSmsConfigRepository.findByName("Portal").get();

    SendSingleSms sendSingleSms =
        new SendSingleSms(
            new BasicInfobipConfiguration(
                infobipSmsConfig.getUserName(),
                infobipSmsConfig.getPassword(),
                infobipSmsConfig.getBaseUrl()));

    infobipMessageRequest.setFrom(infobipSmsConfig.getSenderName());

    SMSResponse response = sendSingleSms.execute(infobipMessageRequest);

    for (SMSResponseDetails sentMessageInfo : response.getMessages()) {
      LOGGER.info("Message ID: " + sentMessageInfo.getMessageId());
      LOGGER.info("Receiver: " + sentMessageInfo.getTo());
      LOGGER.info("Message status: " + sentMessageInfo.getStatus().getName());
      LOGGER.info("------------------------------------------------");
    }
  }
}
