package com.example.meaningful_data.rest.api.security.jwt;

import org.springframework.security.core.AuthenticationException;

/**
 * @Authot: Albert Akimov
 * @Date: 22.03.2020
 * @Description:
 */
public class JwtAuthenticationException extends AuthenticationException {

    public JwtAuthenticationException(String msg, Throwable t) {
        super(msg, t);
    }

    public JwtAuthenticationException(String msg) {
        super(msg);
    }
}
