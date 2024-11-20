package com.example.franchise.controller;

import com.example.franchise.domain.exception.BadRequestException;
import com.example.franchise.domain.exception.ElementNotFoundException;
import com.example.franchise.domain.services.interfaces.IProductService;
import com.example.franchise.presentation.dtos.ProductDetailDto;
import com.example.franchise.presentation.dtos.ProductDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Component
@RequiredArgsConstructor
public class ProductHandler {

    private final IProductService productService;

    public Mono<ServerResponse> changeProductStock(ServerRequest serverRequest) {
        String productId = serverRequest.queryParam("productId")
                .orElseThrow(() -> new BadRequestException("The productId do not be empty nor null"));

        String newStock = serverRequest.queryParam("newStock")
                .orElseThrow(() -> new BadRequestException("The newStock do not be empty nor null"));

        Mono<ProductDto> productDtoMono = this.productService.changeProductStock(productId, Integer.parseInt(newStock));

        return ServerResponse
                .ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(productDtoMono, ProductDto.class);
    }

    public Mono<ServerResponse> getLargestStockForFranquice(ServerRequest serverRequest) {

        String franchiseId = serverRequest.queryParam("franchiseId")
                .orElseThrow(() -> new BadRequestException("The franchiseId do not be empty nor null"));

        Flux<ProductDetailDto> productDetailDtoFlux = this.productService.getLargestStockForFranchise(franchiseId);

        return ServerResponse
                .ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(productDetailDtoFlux, ProductDetailDto.class);

    }
}









