package com.example.licenseservice.utils;


import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;

@Slf4j
public class UserContextFilter implements Filter {
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {

        HttpServletRequest httpServletRequest = (HttpServletRequest) servletRequest;
        UserContextHolder.getUserContext().setUserId(httpServletRequest.getHeader(UserContext.USER_ID));
        UserContextHolder.getUserContext().setAuthToken(httpServletRequest.getHeader(UserContext.AUTH_TOKEN));
        UserContextHolder.getUserContext().setCorrelationId(httpServletRequest.getHeader(UserContext.CORRELATION_ID));
        UserContextHolder.getUserContext().setOrganizationId(httpServletRequest.getHeader(UserContext.ORGANIZATION_ID));

        log.info("UserContextFilter Correlation id: {}", UserContextHolder.getUserContext().getCorrelationId());

        filterChain.doFilter(httpServletRequest, servletResponse);

    }
}
