package com.example.WebApp.factory;

import com.example.WebApp.dto.AppointmentDTO;
import com.sendgrid.Method;
import com.sendgrid.Request;
import com.sendgrid.Response;
import com.sendgrid.SendGrid;
import com.sendgrid.helpers.mail.Mail;
import com.sendgrid.helpers.mail.objects.Content;
import com.sendgrid.helpers.mail.objects.Email;

import java.io.IOException;
import java.sql.SQLOutput;
import java.time.LocalDate;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;

public class ScheduledEmailNotification implements AppointmentMessage{

    private static final String API_KEY = "to-be-inserted-manually";
    @Override
    public void send(AppointmentDTO appointmentDTO) {
        Email from = new Email("denisalexandru1009@gmail.com");
        Email to = new Email(appointmentDTO.donor.email);

        String subject = "Appointment Reminder";
        Content content = new Content("text/plain",
                "Hello, this is a reminder for your appointment on " + appointmentDTO.date +
                        ". Please be prepared and arrive on time.");

        LocalDate reminderDate = appointmentDTO.date.minusDays(1);
        System.out.println(reminderDate.toString());
        long sendAt = reminderDate.atStartOfDay().toEpochSecond(ZoneOffset.UTC);
        System.out.println(sendAt);

        Mail mail = new Mail(from, subject, to, content);
        mail.setSendAt(sendAt);

        SendGrid sendGrid = new SendGrid(API_KEY);
        Request request = new Request();

        request.setMethod(Method.POST);
        request.setEndpoint("mail/send");
        try {
            request.setBody(mail.build());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        Response response = null;
        try {
            response = sendGrid.api(request);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        System.out.println(response.getStatusCode());
        System.out.println(response.getHeaders());
        System.out.println(response.getBody());
    }
}
