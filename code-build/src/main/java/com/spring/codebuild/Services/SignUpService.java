package com.spring.codebuild.Services;

import com.spring.codebuild.DAO.CandidateDAO;
import com.spring.codebuild.DAO.UserDAO;
import com.spring.codebuild.Helpers.JWT;
import com.spring.codebuild.Validators.UserValidator;
import com.spring.codebuild.models.User;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import org.slf4j.Logger;

@Service
public class SignUpService extends UserValidator {
    private static final Logger LOG = LoggerFactory.getLogger(SignUpService.class);

    private PasswordEncoder passwordEncoder;
    private final EmailServiceImpl emailService;
    private final UserDAO userDAO;
    private final CandidateDAO candidateDAO;

    @Autowired
    public SignUpService(UserDAO userDAO, EmailServiceImpl emailService, CandidateDAO candidateDAO) {
        this.userDAO = userDAO;
        this.emailService = emailService;
        this.candidateDAO = candidateDAO;
    }

    public String sign(User user) {
        String checkingEmail = user.getEmail();
        String checkingName = user.getName();
        String password = PasswordGenerator.generate(10);
        String message = "This is a new password: " + password;
        User dataBaseUser = userDAO.checkUser(checkingEmail);

        String emailError = checkSignEmail(checkingEmail, dataBaseUser);
        if (emailError != "") {
            return emailError;
        }

        String nameError = checkName(checkingName);
        if (nameError != "") {
            return nameError;
        }

        try {
            emailService.sendSimpleMail(checkingEmail, "Welcome in Code-Build", message);
        } catch (MailException mailException) {
            LOG.error("Error while sending out email..{}", mailException.getStackTrace());
            return "Unable to send email";
        }

        user.setPassword(password);
        user.setVersion(1);
        candidateDAO.insertInCandidates(user);

        return "Letter sent to your mail";
    }

    public String confirm(User user) {
        candidateDAO.checkConfirm();
        String checkingEmail = user.getEmail();
        String checkingPassword = user.getPassword();
        User dataBaseUser = userDAO.checkUser(checkingEmail);
        User candidate = candidateDAO.checkCandidate(checkingEmail);

        String emailError = checkSignEmail(checkingEmail, dataBaseUser);
        if (emailError != "") {
            return emailError;
        }

        String candidateError = checkCandidateEmail(checkingEmail, candidate);
        if (candidateError != "") {
            return candidateError;
        }

        String passwordError = checkPassword(checkingPassword, candidate);
        if (passwordError != "") {
            return passwordError;
        }

        JWT jwt = new JWT();
        Map<String, Object> claims = new HashMap<>();
        claims.put("Email", checkingEmail);
        claims.put("Password", passwordEncoder.encode(candidate.getPassword()));
        claims.put("Name", candidate.getName());
        claims.put("Version", candidate.getVersion());

        userDAO.regUser(claims);
        claims.put("Id", userDAO.checkUser(candidate.getEmail()).getId());
        claims.remove("Password");
        claims.remove("Name");

        String token = jwt.generateToken(claims);

        return "token: " +token;
    }
}
