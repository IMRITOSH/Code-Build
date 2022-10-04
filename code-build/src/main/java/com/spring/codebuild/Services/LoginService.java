package com.spring.codebuild.Services;

import com.spring.codebuild.DAO.UserDAO;
import com.spring.codebuild.Helpers.JWT;
import com.spring.codebuild.Validators.UserValidator;
import com.spring.codebuild.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class LoginService extends UserValidator {
    private final UserDAO userDAO;

    @Autowired
    public LoginService(UserDAO userDAO) {
        this.userDAO = userDAO;
    }

    public String authorization(Map <String,Object> values) {
        String checkingEmail = values.get("Email").toString();
        String checkingPassword = values.get("Password").toString();
        User dataBaseUser = userDAO.checkUser(checkingEmail);

        String emailError = checkLoginEmail(checkingEmail, dataBaseUser);
        if (emailError != "") {
            return emailError;
        }

        String passwordError = checkPassword(checkingPassword, dataBaseUser);
        if (passwordError != "") {
            return passwordError;
        }

        JWT jwt = new JWT();
        Map<String, Object> claims = new HashMap <>();
        claims.put("Id", dataBaseUser.getId());
        claims.put("Email", checkingEmail);
        claims.put("Version", dataBaseUser.getVersion());
        String  token = jwt.generateToken(claims);

        return checkingEmail + " succesfully logged in" + " "
                + "Token: " + " " + token;
    }
}
