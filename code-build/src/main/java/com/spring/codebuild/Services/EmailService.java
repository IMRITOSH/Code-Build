package com.spring.codebuild.Services;

import com.spring.codebuild.models.EmailDetails;

public interface EmailService {
    String sendSimpleMail(String toAdress, String subject, String message);

    String sendMailWithAttachment(EmailDetails details);
}
