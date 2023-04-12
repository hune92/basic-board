package com.toffee.nut.board.interceptor;

import com.toffee.nut.board.util.SessionManager;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.hibernate.Session;
import org.springframework.http.HttpStatus;
import org.springframework.web.servlet.HandlerInterceptor;

public class BasicLoginInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        System.out.println("이곳은 인터셉터");
        HttpSession session = request.getSession(false);
        System.out.println(session);
        if(session == null || session.getAttribute("loginUser") == null){
            System.out.println("여기");
            response.sendRedirect("/error/sessionNotFound");
            return false;
        }else{
            HttpSession resetSession = SessionManager.resetSession(session);
            System.out.println(resetSession);
        }
        return true;
    }
}
