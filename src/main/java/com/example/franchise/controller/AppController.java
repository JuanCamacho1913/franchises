package com.example.franchise.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RequestPredicates;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

@Configuration
@RequiredArgsConstructor
public class AppController {

    private final FranchiseHandler franchiseHandler;
    private final BranchHandler branchHandler;
    private final ProductHandler productHandler;

    @Bean
    public RouterFunction<ServerResponse> routes() {
        return RouterFunctions
                //Franchise
                .route(RequestPredicates.POST("/v1/franchise/create"), franchiseHandler::createFranchise)
                .andRoute(RequestPredicates.POST("/v1/franchise/branch/add/{idFranchise}"), franchiseHandler::addBranchToFranchise)
                .andRoute(RequestPredicates.PUT("/v1/franchise/name/update"), franchiseHandler::updateNameFranchise)
                //Braches
                .andRoute(RequestPredicates.POST("/v1/branch/product/add/{idBranch}"), branchHandler::addProductToBranch)
                .andRoute(RequestPredicates.POST("/v1/branch/product/remove"), branchHandler::removeProductFromBranch)
                .andRoute(RequestPredicates.PUT("/v1/branch/name/update"), branchHandler::updateNameBranch)
                //Product
                .andRoute(RequestPredicates.PUT("/v1/product/stock"), productHandler::changeProductStock)
                .andRoute(RequestPredicates.GET("/v1/product/stock/max"), productHandler::getLargestStockForFranquice)
                .andRoute(RequestPredicates.PUT("/v1/product/name/update"), productHandler::updateNameProduct);
    }
}
