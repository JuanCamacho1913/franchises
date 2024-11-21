package com.example.franchise.domain.services.implementation;

import com.example.franchise.domain.exception.ElementNotFoundException;
import com.example.franchise.domain.services.interfaces.IProductService;
import com.example.franchise.persistence.repositories.IBranchRepository;
import com.example.franchise.persistence.repositories.IFranchiseRepository;
import com.example.franchise.persistence.repositories.IProductRepository;
import com.example.franchise.presentation.dtos.ProductDetailDto;
import com.example.franchise.presentation.dtos.ProductDto;
import com.example.franchise.util.mapper.ProductMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements IProductService {

    private final IBranchRepository branchRepository;
    private final IFranchiseRepository franchiseRepository;
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

    @Override
    public Flux<ProductDetailDto> getLargestStockForFranchise(String franchiseId) {
        return this.franchiseRepository.findById(franchiseId)
                .switchIfEmpty(Mono.error(new ElementNotFoundException("Franchise don't exist")))
                .flatMapMany(franchise -> Flux.fromIterable(franchise.getBranches())
                        .flatMap(branchId -> this.branchRepository.findById(branchId)
                                .flatMapMany(branch -> Flux.fromIterable(branch.getProducts())
                                        .flatMap(product -> this.productRepository.findById(product))
                                        .reduce((product1, product2) -> Integer.parseInt(product1.getStock()) > Integer.parseInt(product2.getStock()) ? product1 : product2)
                                        .map(product ->
                                                ProductDetailDto.builder()
                                                        .idProduct(product.getId())
                                                        .productName(product.getName())
                                                        .stock(Integer.parseInt(product.getStock()))
                                                        .branchName(branch.getName())
                                                        .franchiseName(franchise.getName())
                                                        .build()
                                        )))
                ).onErrorResume(errorAtributes -> Flux.empty());
    }

    @Override
    public Mono<ProductDto> updateNameProduct(String productId, String newName) {
        Mono<ProductDto> productDtoMono = this.productRepository.findById(productId)
                .switchIfEmpty(Mono.error(new ElementNotFoundException("The product don't exist")))
                .map(product -> {
                    product.setName(newName);
                    return product;
                })
                .flatMap(product -> this.productRepository.save(product))
                .map(product -> this.productMapper.toProductoDto(product));

        return productDtoMono;
    }
}







