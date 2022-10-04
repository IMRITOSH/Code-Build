package com.spring.codebuild.Services;

import com.spring.codebuild.DAO.UserDAO;
import com.spring.codebuild.Validators.UserValidator;
import com.spring.codebuild.models.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class PasswordRecoveryService extends UserValidator {
    private static final Logger LOG = LoggerFactory.getLogger(SignUpService.class);

    private PasswordEncoder passwordEncoder;
    private final EmailServiceImpl emailService;
    private final UserDAO userDAO;

    @Autowired
    public PasswordRecoveryService(UserDAO userDAO, EmailServiceImpl emailService) {
        this.userDAO = userDAO;
        this.emailService=emailService;
    }

    public String passwordRecovery(String email){
        User dataBaseUser = userDAO.checkUser(email);
        String newPassword = PasswordGenerator.generate(10);
        String message = "Your password: " + newPassword;

        String emailError = checkLoginEmail(email, dataBaseUser);
        if (emailError != "") {
            return emailError;
        }

        try {
            emailService.sendSimpleMail(email, "Recovery password", message);
        } catch (MailException mailException) {
            LOG.error("Error while sending out email..{}", mailException.getStackTrace());
            return "Unable to send email";
        }

        userDAO.updatePassword(dataBaseUser.getId(),passwordEncoder.encode(newPassword), dataBaseUser.getVersion());
        return "An email with a password has been sent to your email address";
    }
}
