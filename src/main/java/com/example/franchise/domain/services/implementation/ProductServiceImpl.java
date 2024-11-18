package com.example.franchise.domain.services.implementation;

import com.example.franchise.domain.exception.ElementNotFoundException;
import com.example.franchise.domain.services.interfaces.IProductService;
import com.example.franchise.persistence.repositories.IProductRepository;
import com.example.franchise.presentation.dtos.ProductDto;
import com.example.franchise.util.mapper.ProductMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements IProductService{

    private final IProductRepository productRepository;
    private final ProductMapper productMapper;

    @Override
    public Mono<ProductDto> changeProductStock(String productId, Integer newStock) {
        Mono<ProductDto> productDtoMono = this.productRepository.findById(productId)
                .switchIfEmpty(Mono.error(new ElementNotFoundException("Product don't exist")))
                .map(product -> {
                    product.setStock(String.valueOf(newStock));
                    return product;
                })
                .flatMap(product -> this.productRepository.save(product))
                .map(product -> this.productMapper.toProductoDto(product));

        return productDtoMono;
    }
}







