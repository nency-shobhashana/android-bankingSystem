package com.lambton.androidFinalProject;

import com.mailjet.client.ClientOptions;
import com.mailjet.client.MailjetClient;
import com.mailjet.client.errors.MailjetException;
import com.mailjet.client.transactional.SendContact;
import com.mailjet.client.transactional.SendEmailsRequest;
import com.mailjet.client.transactional.TransactionalEmail;
import com.mailjet.client.transactional.response.SendEmailsResponse;

public class MailService {
    private static final String TAG = "MailService";

    public static void sendEmail(String subject, String to, String message) {

        ClientOptions options = ClientOptions.builder()
                .apiKey(BuildConfig.MJ_APIKEY_PUBLIC)
                .apiSecretKey(BuildConfig.MJ_APIKEY_PRIVATE)
                .build();

        MailjetClient client = new MailjetClient(options);

        TransactionalEmail message1 = TransactionalEmail
                .builder()
                .to(new SendContact(to))
                .from(new SendContact("emailtonency@gmail.com", "Notification Eco Earth Bank"))
                .htmlPart(message)
                .subject(subject)
                .build();

        SendEmailsRequest request = SendEmailsRequest
                .builder()
                .message(message1) // you can add up to 50 messages per request
                .build();

        // act

        new Thread(() -> {
            try {
                SendEmailsResponse response = request.sendWith(client);
            } catch (MailjetException e) {
                e.printStackTrace();
            }
        }).start();

    }
}


