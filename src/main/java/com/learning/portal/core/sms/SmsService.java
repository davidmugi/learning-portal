package com.learning.portal.core.sms;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.i18n.phonenumbers.NumberParseException;
import com.google.i18n.phonenumbers.PhoneNumberUtil;
import com.google.i18n.phonenumbers.Phonenumber;
import com.learning.portal.core.sms.infobip.data.InfobipMessageRequest;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * @category SMS
 * @package Dev
 * @since Nov 05, 2018
 * @author Ignatius
 * @version 1.0.0
 */
@Service("smsService")
@RequiredArgsConstructor
public class SmsService implements SmsServiceInterface {

  private static final Logger LOGGER = LoggerFactory.getLogger(SmsService.class);
  private final String USERNAME = "neshoj";
  private final String APIKEY = "1abe36dbc31688dd0c4372ff866fd90605dedc8b0d8e58967c3e89d27be59408";

  private final InfobipSmsService infobipSmsService;

  //    private final String APIKEY =
  // "5f62026be912e87930a675b30dd08a21b7a1393b652ae36108ecb05f45dac6eb";
  //    private final String USERNAME = "kilosahihi";

  /**
   * Get the object that will be used to send SMS via the Teke SMS Gateway API
   *
   * @return SMS
   */
  @Override
  public SmsOptions smsInit() {
    return new SmsOptions();
  }

  /**
   * Validate a client phone number
   *
   * @param mobileNumber
   * @param country
   * @return True- if its valid, else false
   */
  @Override
  public boolean validatePhoneNumber(String mobileNumber, String country) {
    try {
      PhoneNumberUtil phoneUtil = PhoneNumberUtil.getInstance();
      Phonenumber.PhoneNumber numberProto = instance(mobileNumber, country, phoneUtil);

      return phoneUtil.isValidNumber(numberProto);
    } catch (Exception e) {
      e.printStackTrace();
      return false;
    }
  }

  /**
   * Generate an international phone number
   *
   * @param mobileNumber
   * @param country
   * @return Mobile Phone Number in E64 format
   */
  @Override
  public String getInternationalPhoneNumber(String mobileNumber, String country) {
    try {

      PhoneNumberUtil phoneUtil = PhoneNumberUtil.getInstance();
      Phonenumber.PhoneNumber numberProto = instance(mobileNumber, country, phoneUtil);

      return phoneUtil.format(numberProto, PhoneNumberUtil.PhoneNumberFormat.E164);

    } catch (NumberParseException e) {
      e.printStackTrace();
      return mobileNumber;
    } catch (Exception e) {
      e.printStackTrace();
      return mobileNumber;
    }
  }

  private Phonenumber.PhoneNumber instance(
      String mobileNumber, String country, PhoneNumberUtil phoneUtil) throws Exception {
    Phonenumber.PhoneNumber numberProto = null;
    // when the country is defined
    if (null != country || "".equals(country)) {
      Map<String, String> countries = new HashMap<>();
      for (String isoCountry : Locale.getISOCountries()) {
        Locale l = new Locale("", isoCountry);
        countries.put(l.getDisplayCountry(), isoCountry);
      }
      String countryCode = countries.get(country);
      numberProto = phoneUtil.parse(mobileNumber, countryCode);
    }
    // when country is unknown
    else {
      numberProto = phoneUtil.parse(mobileNumber, "");
    }

    return numberProto;
  }

  @Override
  public void sendSMS(String message, String phoneNumber) {

    InfobipMessageRequest infobipMessageRequest =
        new InfobipMessageRequest().setTo(Arrays.asList(phoneNumber)).setText(message);
    infobipSmsService.infobipSendSms(infobipMessageRequest);
  }

  @Override
  public void sendMultipleUsers(String message, List<String> phoneNUmbers) {
    InfobipMessageRequest infobipMessageRequest =
        new InfobipMessageRequest().setTo(phoneNUmbers).setText(message);
    infobipSmsService.infobipSendSms(infobipMessageRequest);
  }
}
