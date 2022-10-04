package com.spring.codebuild.Inceraptors;

import com.spring.codebuild.DAO.UserDAO;
import com.spring.codebuild.Helpers.JWT;
import com.spring.codebuild.Validators.ValidationURL;
import com.spring.codebuild.models.User;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

public class LogInceraptor extends HandlerInterceptorAdapter {
    private UserDAO userDAO;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {
        String token = request.getHeader("Authourization");
        JWT jwt = new JWT();
        String[] ListURI = new String[]{"/user", "/user/change"};

        for(String URI : ListURI){
            if (URI.equals(request.getRequestURI())){
                Map<String, Object> claims = jwt.getAllClaimsFromToken(token);
                String version = claims.get("Version").toString();
                String email = claims.get("Email").toString();
                User dataBaseUser = userDAO.checkUser(email);

                boolean correctVersion = jwt.validateVersion(version, dataBaseUser);
                if (correctVersion == false) {
                    String errorMethod = ValidationURL.checkMethod(request.getMethod());
                    response.sendRedirect(errorMethod);
                    return false;
                }

            }
        }
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, //
                           Object handler, ModelAndView modelAndView) throws Exception {
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, //
                                Object handler, Exception ex) throws Exception {
    }
}
