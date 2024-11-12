package com.example.franchise.controller;

import com.example.franchise.domain.exception.BadRequestException;
import com.example.franchise.domain.services.interfaces.IBranchService;
import com.example.franchise.persistence.entities.Branch;
import com.example.franchise.presentation.dtos.BranchDto;
import com.example.franchise.presentation.dtos.ProductDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

@Component
@RequiredArgsConstructor
public class BranchHandler {

    public final IBranchService branchService;

    public Mono<ServerResponse> addProductToBranch(ServerRequest serverRequest) {
        String branchId = serverRequest.pathVariable("idBranch");
        Mono<ProductDto> productDtoMono = serverRequest.bodyToMono(ProductDto.class);

        Mono<BranchDto> branchDtoResponse = this.branchService.addProductToBranch(branchId, productDtoMono);

        return ServerResponse
                .ok()
                .body(branchDtoResponse, BranchDto.class);
    }

    public Mono<ServerResponse> removeProductFromBranch(ServerRequest serverRequest) {
        String branchId = serverRequest.queryParam("branchId")
                .orElseThrow(() -> new BadRequestException("the branchId do not be empty nor null"));

        String productId = serverRequest.queryParam("productId")
                .orElseThrow(() -> new BadRequestException("The productId do not be empty nor null"));

        Mono<BranchDto> branchDtoResponse = this.branchService.removeProductToBranch(branchId, productId);

        return ServerResponse
                .ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(branchDtoResponse, BranchDto.class);
    }
}







