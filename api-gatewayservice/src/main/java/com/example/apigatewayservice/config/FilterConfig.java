package com.example.apigatewayservice.config;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * application.yml 에서 할 일을 자바 클래스로 옮겨놓았다.
 */
// @Configuration
public class FilterConfig {
    // @Bean기
    public RouteLocator gatewayRoutes(RouteLocatorBuilder builder){
        return builder.routes()
                .route(r -> r.path("/first-service/**")
                        .filters(f -> f.addRequestHeader("first-request", "first-request Header!")
                                        .addResponseHeader("first-response", "first-response Header!"))
                                .uri("http://localhost:8081"))
                .route(r -> r.path("/second-service/**")
                        .filters(f -> f.addRequestHeader("second-request", "second-request Header!")
                                .addResponseHeader("second-response", "second-response Header!"))
                        .uri("http://localhost:8082"))

                .build();

    }

}
