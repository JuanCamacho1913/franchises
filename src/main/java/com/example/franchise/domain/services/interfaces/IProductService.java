package com.example.franchise.domain.services.interfaces;

import com.example.franchise.presentation.dtos.BranchDto;
import com.example.franchise.presentation.dtos.ProductDto;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface IProductService {

    public Mono<ProductDto> changeProductStock(String productId, Integer newStock);

    //public Flux<BranchDto> getMaxStockProduct(Flux<ProductDto> productDto);
}
