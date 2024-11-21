package com.example.franchise.domain.services.interfaces;

import com.example.franchise.presentation.dtos.BranchDto;
import com.example.franchise.presentation.dtos.ProductDto;
import reactor.core.publisher.Mono;

public interface IBranchService {

    public Mono<BranchDto> addProductToBranch(String branchId, Mono<ProductDto> productDtoMono);

    public Mono<BranchDto> removeProductToBranch(String branchId, String productId);

    public Mono<BranchDto> updateNameBranch(String idBranch, String newName);
}
