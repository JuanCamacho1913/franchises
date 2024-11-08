package com.example.franchise.services.interfaces;

import com.example.franchise.presentation.dtos.BranchDto;
import com.example.franchise.presentation.dtos.ProductDto;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface IProductService {

    public Mono<ProductDto> updateProductStock(Mono<String> id);

    public Flux<BranchDto> getMaxStockProduct(Flux<ProductDto> productDto);
}
