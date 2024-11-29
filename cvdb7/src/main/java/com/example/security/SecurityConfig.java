package com.example.security;

import org.springframework.context.annotation.Configuration;

import jakarta.servlet.http.HttpServletRequest;

@Configuration
public class SecurityConfig {
    public static final String LOGIN_PAGE = "/login";
    public static final String LOGOUT_PAGE = "/logout";
    public static final int SESSION_TIMEOUT = 30 * 60; // 30 minutes

    public static boolean isUserLoggedIn(HttpServletRequest request) {
        return request.getSession().getAttribute("user") != null;
    }

    public static void configureSessionTimeout(HttpServletRequest request) {
        request.getSession().setMaxInactiveInterval(SESSION_TIMEOUT);
    }
}