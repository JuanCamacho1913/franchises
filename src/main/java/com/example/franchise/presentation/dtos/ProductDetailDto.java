package com.example.franchise.presentation.dtos;

import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ProductDetailDto {

    private String idProduct;
    private String productName;
    private Integer stock;
    private String branchName;
    private String franchiseName;
}
