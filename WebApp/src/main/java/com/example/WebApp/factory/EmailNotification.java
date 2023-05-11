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
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.Date;

public class EmailNotification implements AppointmentMessage{
    private static final String API_KEY = "to-be-inserted-manually";
    @Override
    public void send(AppointmentDTO appointment) {
        Email from =new Email("denisalexandru1009@gmail.com");
        Email to = new Email(appointment.donor.email);

        String subject = "Appointment for blood donation";
        Content content = new Content("text/html", "You have a scheduled appointment to donate blood at "
                + appointment.center.name + " on " + appointment.date.toString());

        Mail mail = new Mail(from, subject, to, content);
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
