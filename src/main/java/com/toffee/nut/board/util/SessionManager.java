package com.toffee.nut.board.util;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

@Slf4j
@Component
public class SessionManager {

    public static final String SESSION_NAME = "LOGIN_SESSION_ID";

    private Map<String, Object> sessionMap = new ConcurrentHashMap<>();

    public void createSession(Object value, HttpServletResponse response){
        String sessionId = String.valueOf(UUID.randomUUID());
        sessionMap.put(sessionId, value);

        response.addCookie(new Cookie(SESSION_NAME, sessionId));
    }

    public static HttpSession resetSession(HttpSession session){
        session.setMaxInactiveInterval(3);
        return session;
    }
}
