package com.construcaosw.keycloak.utils;

import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

public class AuthToken {

    public static String retrieveToken() {
        return ((ServletRequestAttributes) RequestContextHolder
                .currentRequestAttributes())
                .getRequest()
                .getHeader("Authorization")
                .split(" ") [1];
    }
}
