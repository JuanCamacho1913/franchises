package com.example.franchise.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RequestPredicates;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

@Configuration
public class ConfigurationRouterConfig {

    @Bean
    public RouterFunction<ServerResponse> routes() {
        return RouterFunctions
                .route(RequestPredicates.POST("v1/franquicia/create"), request -> ServerResponse
                        .ok()
                        .body(Mono.just("Nike"), String.class))

                .andRoute(RequestPredicates.POST("v1/sucursal/create"), request -> ServerResponse
                        .ok()
                        .body(Mono.just("Bello"), String.class))

                .andRoute(RequestPredicates.POST("v1/producto/create"), request -> ServerResponse
                        .ok()
                        .body(Mono.just("Tenis"), String.class))

                .andRoute(RequestPredicates.DELETE("v1/producto/delete"), request -> ServerResponse
                        .ok()
                        .body(Mono.just("Tenis"), String.class))

                .andRoute(RequestPredicates.PUT("v1/stock/producto"), request -> ServerResponse
                        .ok()
                        .body(Mono.just(25), Integer.class));
    }
}
