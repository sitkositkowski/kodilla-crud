package com.crud.tasks.service;

import com.crud.tasks.domain.Mail;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class SimpleEmailServiceTest {

    @InjectMocks
    private SimpleEmailService simpleEmailService;

    @Mock
    private JavaMailSender javaMailSender;

    @Test
    public void shouldSendEmailWithCC() {
        //Given
        Mail mail = Mail.builder()
                .mailTo("test@test.com")
                .message("test")
                .subject("test")
                .toCC("test2@test.com")
                .build();

        //When
        simpleEmailService.send(mail, false);

        //Then
        verify(javaMailSender, times(1)).send(any(MimeMessagePreparator.class));
    }

    @Test
    public void shouldSendEmailWithoutCC() {
        //Given
        Mail mail = Mail.builder()
                .mailTo("test@test.com")
                .message("test")
                .subject("test")
                .build();

        //When
        simpleEmailService.send(mail, false);

        //Then
        verify(javaMailSender, times(1)).send(any(MimeMessagePreparator.class));
    }
}