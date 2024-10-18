package com.codemaster.io.filters;


import com.codemaster.io.litespring.annotation.Autowired;
import com.codemaster.io.litespring.annotation.Component;
import com.codemaster.io.litespring.context.UserContext;
import com.codemaster.io.models.SessionData;
import com.codemaster.io.service.CustomSessionService;
import jakarta.servlet.*;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;

import java.io.IOException;


@Component
public class AuthenticationFilter implements Filter {

    @Autowired
    private CustomSessionService customSessionService;

    public void init(FilterConfig filterConfig) throws ServletException {
        System.out.println("Init AuthenticationFilter");
    }

    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse,
                         FilterChain chain)
            throws IOException, ServletException {

        System.out.println("Do AuthenticationFilter");

        HttpServletRequest request = (HttpServletRequest) servletRequest;

        customSessionService.findSessionAndSetContext(request);

        chain.doFilter(servletRequest, servletResponse);

        UserContext.clear();
    }

    public void destroy() {
        System.out.println("Cleanup AuthenticationFilter");
    }
}