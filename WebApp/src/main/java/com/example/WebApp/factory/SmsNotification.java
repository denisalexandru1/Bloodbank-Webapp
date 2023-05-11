package com.example.WebApp.factory;

import com.example.WebApp.dto.AppointmentDTO;
import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;

import java.time.LocalDate;
import java.time.ZoneOffset;

public class SmsNotification implements AppointmentMessage {

    public static final String ACCOUNT_SID = "to-be-inserted-manually";
    public static final String AUTH_TOKEN = "to-be-inserted-manually";
    @Override
    public void send(AppointmentDTO appointmentDTO) {
        Twilio.init(ACCOUNT_SID, AUTH_TOKEN);

        // Calculate the sendAt timestamp (one day before the appointment date)
        LocalDate reminderDate = appointmentDTO.date.minusDays(1);
        long sendAt = reminderDate.atStartOfDay().toEpochSecond(ZoneOffset.UTC);

        Message message = Message.creator(
                        new com.twilio.type.PhoneNumber("+4076804628"),
                        new com.twilio.type.PhoneNumber("twilio-number"),
                        "Hello, this is a reminder for your appointment on " + appointmentDTO.date +
                                ". Please be prepared and arrive on time."
                )
                //.setSendAt(sendAt) // Schedule the message to be sent at the specified timestamp
                .create();

        System.out.println(message.getSid());
    }
}
