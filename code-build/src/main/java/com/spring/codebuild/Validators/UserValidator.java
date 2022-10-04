package com.spring.codebuild.Validators;

import com.spring.codebuild.models.User;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public abstract class UserValidator {

    private PasswordEncoder passwordEncoder;

    public String checkLoginEmail(String email, User dataBaseUser) {
        boolean isEmpty = email.isEmpty();
        int emailLenght = email.length();
        Pattern pattern = Pattern.compile(".+\\.(com|ru)");
        Matcher matcher = pattern.matcher(email);
        boolean emailPattern = matcher.matches();

        if (isEmpty == true) {
            return "Email should not be empty";
        } else if (emailLenght > 42) {
            return "Email must be less than 42";
        } else if (emailPattern == false) {
            return "Incorrect email format";
        } else if (dataBaseUser == null) {
            return "This email does not exist";
        }

        return "";
    }

    public String checkSignEmail(String email, User dataBaseUser) {
        boolean isEmpty = email.isEmpty();
        int emailLenght = email.length();
        Pattern pattern = Pattern.compile(".+\\.(com|ru)");
        Matcher matcher = pattern.matcher(email);
        boolean emailPattern = matcher.matches();

        if (isEmpty == true) {
            return "Email should not be empty";
        } else if (emailLenght > 42) {
            return "Email must be less than 42";
        } else if (emailPattern == false) {
            return "Incorrect email format";
        } else if (dataBaseUser != null) {
            return "This email already exists";
        }

        return "";
    }

    public String checkCandidateEmail(String email, User candidate) {
        if (candidate == null) {
            return "Время на потверждение регистрации истекло";
        }

        return "";
    }

    public String checkPassword(String password, User candidate) {
        boolean isEmpty = password.isEmpty();

        if (isEmpty == true) {
            return "Password should not be empty";
        } else if (passwordEncoder.matches(password, candidate.getPassword())==false) {
            return "Password invalid";
        }

        return "";
    }

    public String checkName(String name) {
        boolean isEmpty = name.isEmpty();
        int nameLength = name.length();
        Pattern pattern = Pattern.compile("[A-z]*[А-я]*[-]*");
        Matcher matcher = pattern.matcher(name);
        boolean namePattern = matcher.matches();

        if (isEmpty == true) {
            return "Name should not be empty";
        } else if (nameLength > 20) {
            return "Name must be less than 20";
        } else if (namePattern == false) {
            return "The name must contain only Cyrillic, Latin letters and a hyphen";
        }

        return "";
    }

    public String checkUpdated(String name, boolean isChangePassword, String oldPassword, String newPassword, User dataBaseUser) {

        if (isChangePassword == true) {
            String oldPasswordError = checkPassword(oldPassword, dataBaseUser);
            if (!oldPasswordError.equals("")) {
                return oldPasswordError;
            }

            String newPasswordError = checkNewPassword(newPassword, oldPassword);
            if (!newPasswordError.equals("")) {
                return newPasswordError;
            }
        }

        String nameError = checkName(name);
        if (!nameError.equals("")) {
            return nameError;
        }

        return "";
    }

    public String checkNewPassword(String newPassword, String oldPassword) {
        boolean isEmpty = newPassword.isEmpty();
        int newPasswordLength = newPassword.length();
        Pattern pattern = Pattern.compile("[A-z]*[А-я]*[!-+]*");
        Matcher matcher = pattern.matcher(newPassword);
        boolean newPasswordPattern = matcher.matches();

        if (isEmpty == true) {
            return "New password should not be empty";
        } else if (newPasswordLength <= 8 || newPasswordLength >= 20) {
            return "New password must be more than 8 characters and less than 20 characters ";
        } else if (newPasswordPattern == false) {
            return "New password must contain only Cyrillic, Latin letters and characters: -, _, !, @, #, $, %, ^, &, *, (, ), =, +, ~, `, ?";
        } else if(newPassword.equals(oldPassword)){
            return "New password must not match the old one";
        }

        return "";
    }
}
