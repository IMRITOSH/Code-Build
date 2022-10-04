package com.spring.codebuild.Validators;

public class ValidationURL {

    public static String checkMethod(String methodType) {
        if (methodType.equals("GET") || methodType.equals("POST")) {
            return "/errors/getError";
        } else if (methodType.equals("PUT")) {
            return "/errors/putError";
        } else if (methodType.equals("DELETE")) {
            return "/errors/deleteError";
        }

        return "";
    }
}
