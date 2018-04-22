/*
 * Copyright sablintolya@gmail.com
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package io.github.ma1uta.identity.dropwizard.service;

import io.github.ma1uta.identity.service.NotificationService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Implementation of the {@link NotificationService} based on the java mail.
 */
public class MailService implements NotificationService {

    private static final Logger LOGGER = LoggerFactory.getLogger(MailService.class);

    private final MailConfiguration mailConfiguration;

    public MailService(MailConfiguration mailConfiguration) {
        this.mailConfiguration = mailConfiguration;
    }

    public MailConfiguration getMailConfiguration() {
        return mailConfiguration;
    }

    /*@Override
    public void send(String addressee, String subject, String message) {
        Properties props = new Properties();
        props.putAll(getMail().getProps());
        //props.put("mail.smtp.auth", "true");
        //props.put("mail.smtp.starttls.enable", "true");
        //props.put("mail.smtp.host", "smtp.gmail.com");
        //props.put("mail.smtp.port", "587");

        Session session = Session.getInstance(props, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(getMail().getUsername(), getMail().getPassword());
            }
        });
        MimeMessage mimeMessage = new MimeMessage(session);

        try {
            mimeMessage.setFrom(getMail().getFrom());
            mimeMessage.setSubject(subject);
            mimeMessage.setText(message, "UTF-8");
            mimeMessage.setRecipient(Message.RecipientType.TO, new InternetAddress(addressee));

            Transport.send(mimeMessage);

        } catch (MessagingException e) {
            String msg = "Failed send email";
            LOGGER.error(msg, e);
            throw new MatrixException(MatrixException.M_INTERNAL, msg);
        }
    }*/

    @Override
    public void send(String medium, String address, String clientSecret, String token, String sid) {

    }
}
