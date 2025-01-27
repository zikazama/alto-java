package com.example.ewallet.middleware;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import java.util.List;

@Component
public class LoggingMiddleware implements HandlerInterceptor {

    private static final List<String> SUSPICIOUS_PATTERNS = List.of(
            "' OR '1'='1",
            "SELECT * FROM",
            "DROP TABLE",
            "UNION SELECT",
            "--", // SQL comment
            ";",  // End of SQL statement
            "INSERT INTO",
            "DELETE FROM",
            "UPDATE"
    );

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String queryString = request.getQueryString();
        String requestBody = extractRequestBody(request);

        // Log request details
        System.out.println("Request: " + request.getMethod() + " " + request.getRequestURI());

        // Detect suspicious patterns in query string
        if (queryString != null && containsSuspiciousPatterns(queryString)) {
            System.out.println("[SECURITY ALERT] SQL Injection Attempt Detected in Query: " + queryString);
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            response.getWriter().write("Bad Request: Suspicious query detected");
            return false;
        }

        // Detect suspicious patterns in body
        if (requestBody != null && containsSuspiciousPatterns(requestBody)) {
            System.out.println("[SECURITY ALERT] SQL Injection Attempt Detected in Body: " + requestBody);
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            response.getWriter().write("Bad Request: Suspicious input detected");
            return false;
        }

        return true;
    }

    private boolean containsSuspiciousPatterns(String input) {
        return SUSPICIOUS_PATTERNS.stream()
                .anyMatch(pattern -> input.toUpperCase().contains(pattern.toUpperCase()));
    }

    private String extractRequestBody(HttpServletRequest request) {
        try {
            StringBuilder stringBuilder = new StringBuilder();
            request.getReader().lines().forEach(stringBuilder::append);
            return stringBuilder.toString();
        } catch (Exception e) {
            return null;
        }
    }
}
