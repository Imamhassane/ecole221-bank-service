package com.ecole221.api.gateway;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@EnableAutoConfiguration
@Configuration
public class ApiGatewayConfiguration {

    @Bean
    public RouteLocator gatewayRouter(RouteLocatorBuilder builder) {
        return builder.routes()
                .route(p -> p.path("/api/clients/**")
                        .uri("lb://client-paiement"))
                .route(p -> p.path("/api/paiements/**")
                        .uri("lb://client-paiement"))
                .route(p -> p.path("/api/comptes/**")
                        .uri("lb://compte-transaction"))
                .route(p -> p.path("/api/transactions/**")
                        .uri("lb://compte-transaction"))
                .route(p -> p.path("/api/services/**")
                        .uri("lb://service-application"))
                //.route(p -> p.path("/login")
                //        .uri("lb://api-gateway"))
                .build();
    }

}
