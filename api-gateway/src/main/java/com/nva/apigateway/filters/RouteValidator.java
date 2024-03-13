package com.nva.apigateway.filters;

import org.apache.commons.lang3.tuple.Pair;
import org.springframework.http.HttpHeaders;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Objects;
import java.util.function.Predicate;

@Component
public class RouteValidator {
    public boolean isByPassToken(ServerHttpRequest request) {
        final List<String> authHeader = request.getHeaders().get(HttpHeaders.AUTHORIZATION);
        if (authHeader == null || !authHeader.get(0).startsWith("Bearer "))
            return true;

        final List<Pair<String, String>> byPassToken = List.of(
//                Pair.of("/api/v1/auth/register", "POST"),
//                Pair.of("/api/v1/auth/generateToken", "POST"),
//                Pair.of("/api/v1/auth/validateToken", "POST"),
                Pair.of("/api/v1/products", "GET"),
                Pair.of("/api/v1/products", "POST")
        );

        String requestPath = String.valueOf(request.getPath());
        String requestMethod = String.valueOf(request.getMethod());

        for (Pair<String, String> entry : byPassToken) {
            if (requestPath.equals(entry.getLeft()) && requestMethod.equals(entry.getRight()))
                return true;
        }

        return false;
    }
}
