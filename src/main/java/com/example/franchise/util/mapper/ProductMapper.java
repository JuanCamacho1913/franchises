package com.example.franchise.util.mapper;

import com.example.franchise.persistence.entities.Product;
import com.example.franchise.presentation.dtos.ProductDto;
import org.springframework.stereotype.Component;

@Component
public class ProductMapper {

    public Product toProduct(ProductDto productDto){
        Product product = Product.builder()
                .id(productDto.id())
                .name(productDto.name())
                .stock(productDto.stock())
                .build();
        return product;
    }

    public ProductDto toProductoDto(Product product) {
        return new ProductDto(
                product.getId(),
                product.getName(),
                product.getStock()
        );
    }
}
