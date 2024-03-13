package com.nva.productservice.configs;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import java.util.List;

@Component
public class JwtInterceptor implements HandlerInterceptor {
    private final String secretKey = "5367586B59700373367639792F423F4528482B4D6251655128576D5A71347437";
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String token = request.getHeader("Authorization");
        if (token != null && token.startsWith("Bearer ")) {
            token = token.substring(7); // Remove "Bearer " prefix
            Claims claims = Jwts.parser().setSigningKey(secretKey).build().parseClaimsJws(token).getBody();
            List<String> roles = claims.get("role", List.class);
            // Perform authorization checks based on roles
            if (roles.contains("ROLE_ADMIN")) {
                // Authorized, continue with the request
                return true;
            } else {
                // Not authorized, send 403 Forbidden response
                response.sendError(HttpServletResponse.SC_FORBIDDEN, "Tu choi");
                return false;
            }
        } else {
            // No token provided, send 401 Unauthorized response
            response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Khong co quyen");
            return false;
        }
    }
}
