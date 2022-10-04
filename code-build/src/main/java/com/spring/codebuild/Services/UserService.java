package com.spring.codebuild.Services;

import com.spring.codebuild.DAO.UserDAO;
import com.spring.codebuild.Helpers.JWT;
import com.spring.codebuild.Validators.UserValidator;
import com.spring.codebuild.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

@Service
public class UserService extends UserValidator {
    private final UserDAO userDAO;
    private PasswordEncoder passwordEncoder;

    @Autowired
    public UserService(UserDAO userDAO, PasswordEncoder passwordEncoder) {
        this.userDAO = userDAO;
        this.passwordEncoder=passwordEncoder;
    }

    public String getUser(HttpServletRequest request) {
        JWT jwt = new JWT();
        String token = request.getHeader("Authourization");
        String email = jwt.getClaimFromToken(token, "Email");
        User dataBaseUser = userDAO.checkUser(email);

        return dataBaseUser.toString();
    }

    public String updateUser(Map<String, Object> values, HttpServletRequest request) {
        String name = values.get("Name").toString();
        boolean isChangePassword = (boolean) values.get("Change password");
        String oldPassword = values.get("Old password").toString();
        String newPassword = values.get("New password").toString();
        Map<String, Object> claims = new HashMap<>();

        JWT jwt = new JWT();
        String token = request.getHeader("Authourization");
        int id = Integer.valueOf(jwt.getClaimFromToken(token, "Id"));
        int version = Integer.valueOf(jwt.getClaimFromToken(token, "Version"));
        User dataBaseUser = userDAO.showUser(id);

        String updatedError = checkUpdated(name, isChangePassword, oldPassword, newPassword, dataBaseUser);
        if(!updatedError.equals("")){
            return updatedError;
        }

        if(isChangePassword==true){
            userDAO.update(id,name, passwordEncoder.encode(newPassword), version);
            claims.put("Id", dataBaseUser.getId());
            claims.put("Email", dataBaseUser.getEmail());
            claims.put("Version", version+1);
            String newToken = jwt.generateToken(claims);
            return "Name: " + name + " " + "Password: " + newPassword + " " +  "Version: " + version
                    + "\n" + "token: " + newToken;
        }

        userDAO.updateName(id, name, version);
        claims.put("Id", dataBaseUser.getId());
        claims.put("Email", dataBaseUser.getEmail());
        claims.put("Version", version+1);
        String newToken = jwt.generateToken(claims);
        return "Name: " + name + " " + "Password: " + newPassword + " " +  "Version: " + version
                + "\n" + "token: " + newToken;
    }
}
