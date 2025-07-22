package com.cognizant.api_gateway.filter;

import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

@Component
public class LogFilter implements GlobalFilter, Ordered {

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        System.out.println("📥 Incoming Request URI: " + exchange.getRequest().getURI());
        return chain.filter(exchange).then(Mono.fromRunnable(() -> {
            System.out.println("📤 Outgoing Response Status: " + exchange.getResponse().getStatusCode());
        }));
    }

    @Override
    public int getOrder() {
        return 1; // Lower values have higher priority
    }
}
