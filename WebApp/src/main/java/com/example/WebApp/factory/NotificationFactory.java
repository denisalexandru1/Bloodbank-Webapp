package com.example.WebApp.factory;

import org.springframework.stereotype.Component;

import javax.management.Notification;

@Component
public class NotificationFactory {
    public static AppointmentMessage createEmailNotification() {
        return new EmailNotification();
    }
    public static AppointmentMessage createSMSNotification() {
        return new SmsNotification();
    }

    public static AppointmentMessage createScheduledEmailNotification(){
        return new ScheduledEmailNotification();
    }
}
