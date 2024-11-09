package com.example.franchise.presentation.dtos;

import com.example.franchise.persistence.entities.Product;

import java.util.List;

public record BranchDto(
        String id,
        String name,
        List<ProductDto> products) {
}
