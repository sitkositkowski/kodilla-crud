package com.crud.tasks.service;

import com.crud.tasks.domain.Mail;
import lombok.Builder;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class SimpleEmailService {

    private final JavaMailSender javaMailSender;

    @Autowired
    private MailCreatorService mailCreatorService;

    public void send(final Mail mail, boolean isDaily) {
        log.info("Starting email preparation...");
        try {
            //SimpleMailMessage mailMessage = createMailMessage(mail);
            javaMailSender.send(createMimeMessage(mail,isDaily));
            log.info("Email has been sent.");
        } catch (MailException e) {
            log.error("Failed to process email sending: " + e.getMessage(), e);
        }
    }

    private MimeMessagePreparator createMimeMessage(final Mail mail, boolean isDaily) {
        return mimeMessage -> {
            MimeMessageHelper messageHelper = new MimeMessageHelper(mimeMessage);
            messageHelper.setTo(mail.getMailTo());
            messageHelper.setSubject(mail.getSubject());
            if (isDaily) {
                messageHelper.setText(mailCreatorService.buildDailyEmail(mail.getMessage()), true);
            } else {
                messageHelper.setText(mailCreatorService.buildTrelloCardEmail(mail.getMessage()), true);
            }
            String toCC = mail.getToCC();
            if (toCC != null) messageHelper.setCc(toCC);
        };
    }



    private SimpleMailMessage createMailMessage(final Mail mail) {
        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setTo(mail.getMailTo());
        mailMessage.setSubject(mail.getSubject());
        mailMessage.setText(mailCreatorService.buildTrelloCardEmail(mail.getMessage()));
        Optional.ofNullable(mail.getToCC()).ifPresent(mailMessage::setCc);
        return mailMessage;
    }

}