package com.nva.apigateway.filters;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.Objects;

@Component
@Slf4j
public class AuthenticationFilter extends AbstractGatewayFilterFactory<AuthenticationFilter.Config> {
    @Autowired
    private RouteValidator routeValidator;
    @Autowired
    private WebClient.Builder webClientBuilder;

    public AuthenticationFilter() {
        super(Config.class);
    }

    @Override
    public GatewayFilter apply(Config config) {
        return ((exchange, chain) -> {
            log.info("Path >>>>: " + exchange.getRequest().getPath());
            log.info("Method >>>>: " + exchange.getRequest().getMethod());
            if (!routeValidator.isByPassToken(exchange.getRequest())) {
                String authHeader = Objects.requireNonNull(exchange.getRequest().getHeaders().get(HttpHeaders.AUTHORIZATION)).get(0);
                if (authHeader != null && authHeader.startsWith("Bearer ")) {
                    authHeader = authHeader.substring(7);
                }

                // Authenticate request
                return webClientBuilder.build().post()
                        .uri("http://auth-service/api/v1/auth/validateToken?token={token}", authHeader)
                        .retrieve()
                        .bodyToMono(String.class)
                        .flatMap(response -> chain.filter(exchange))
                        .onErrorResume(ex -> {
                            throw new RuntimeException("Unauthorized access to application!");
                        });
            } else {
                return chain.filter(exchange);
            }
        });
    }

    public static class Config {
    }
}
