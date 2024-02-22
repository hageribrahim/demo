package com.example.demo.config;

import com.savoirtech.logging.slf4j.json.LoggerFactory;
import com.savoirtech.logging.slf4j.json.logger.Logger;
import jakarta.servlet.http.HttpServletRequest;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.util.Arrays;
import java.util.Collections;


@Aspect
@Component
public class LoggingServiceAdvisor {

    private final Logger LOGGER = LoggerFactory.getLogger(this.getClass());

    @Pointcut("execution(* com.example.demo.controller.*.*(..))")
    private void controllerMethods() {
    }

    @Before("controllerMethods()")
    public void logBefore(JoinPoint joinPoint) {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest();
        LOGGER.info()
                .message("Request Logging")
                .field("Request URI", request.getRequestURI())
                .field("HTTP Method", request.getMethod())
                .field("Request Headers", getRequestHeaders(request))
                .field("Request args", getRequestPayload(joinPoint.getArgs()))
                .log();
    }

    @AfterReturning(pointcut = "controllerMethods()", returning = "response")
    public void logAfterReturning(JoinPoint joinPoint, Object response) {

        LOGGER.info()
                .message("Response Logging")
                .field("response", response)
                .log();
    }

    private String getRequestHeaders(HttpServletRequest request) {
        return Arrays.toString(Collections.list(request.getHeaderNames()).stream()
                .map(headerName -> headerName + ":" + request.getHeader(headerName))
                .toArray());
    }

    private String getRequestPayload(Object[] args) {
        if (args != null && args.length > 0) {
            return Arrays.toString(args);
        }
        return "No request payload.";
    }
}
