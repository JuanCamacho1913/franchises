package com.example.franchise.services.interfaces;

import com.example.franchise.presentation.dtos.BranchDto;
import com.example.franchise.presentation.dtos.ProductDto;
import reactor.core.publisher.Mono;

public interface IBranchService {

    public Mono<BranchDto> addProduct(Mono<ProductDto> addProductDto);

    public Mono<BranchDto> deleteProduct(Mono<ProductDto> deleteProductDto);
}
