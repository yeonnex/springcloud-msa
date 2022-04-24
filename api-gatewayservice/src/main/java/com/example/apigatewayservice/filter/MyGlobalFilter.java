package com.example.apigatewayservice.filter;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

@Slf4j
@Component
public class MyGlobalFilter extends AbstractGatewayFilterFactory<MyGlobalFilter.Config> {
    public MyGlobalFilter(){
        super(Config.class);
    }
    @Override
    public GatewayFilter apply(Config config) {

        // Custom Global pre Filter
        return ((exchange, chain) -> {
            ServerHttpRequest request = exchange.getRequest();
            ServerHttpResponse response = exchange.getResponse();

            log.info("My Global pre Filter message ===>" + config.getBaseMessage());
            if (config.isPreLogger()){ // preLogger 가 작동중이라면!
                log.info("Global Filter Start !: request id ===>" + request.getId());
            }

            // Custom Global post Filter
            return chain.filter(exchange).then(Mono.fromRunnable(()->{
                if(config.isPostLogger()) {
                    log.info("Global Filter End ! : response code ===>" + response.getStatusCode());
                }
            }));

        });
    }

    @Data
    public static class Config {
        public String baseMessage;
        private boolean preLogger;
        private boolean postLogger;
    }

}
