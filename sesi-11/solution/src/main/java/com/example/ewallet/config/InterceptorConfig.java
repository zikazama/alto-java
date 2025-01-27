package com.example.ewallet.config;

import com.example.ewallet.middleware.AuthMiddleware;
import com.example.ewallet.middleware.LoggingMiddleware;
import com.example.ewallet.middleware.RateLimitingMiddleware;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class InterceptorConfig implements WebMvcConfigurer {

    @Autowired
    private AuthMiddleware authMiddleware;

    @Autowired
    private RateLimitingMiddleware rateLimitingMiddleware;

    @Autowired
    private LoggingMiddleware loggingMiddleware;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(loggingMiddleware);
        registry.addInterceptor(rateLimitingMiddleware);
        registry.addInterceptor(authMiddleware).addPathPatterns("/api/**");
    }
}
