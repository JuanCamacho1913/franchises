package com.example.franchise.controller;

import com.example.franchise.domain.exception.BadRequestException;
import com.example.franchise.domain.exception.ElementNotFoundException;
import com.example.franchise.domain.services.interfaces.IProductService;
import com.example.franchise.presentation.dtos.ProductDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

@Component
@RequiredArgsConstructor
public class ProductHandler {

    private final IProductService productService;

    public Mono<ServerResponse> changeProductStock(ServerRequest serverRequest) {
        String productId = serverRequest.queryParam("productId")
                .orElseThrow(() -> new BadRequestException("the productId do not be empty nor null"));

        String newStock = serverRequest.queryParam("newStock")
                .orElseThrow(() -> new BadRequestException("the newStock do not be empty nor null"));

        Mono<ProductDto> productDtoMono = this.productService.changeProductStock(productId, Integer.parseInt(newStock));

        return ServerResponse
                .ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(productDtoMono, ProductDto.class);
    }
}









