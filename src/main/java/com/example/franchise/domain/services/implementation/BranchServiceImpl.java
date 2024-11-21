package com.example.franchise.domain.services.implementation;

import com.example.franchise.domain.exception.ElementNotFoundException;
import com.example.franchise.domain.services.interfaces.IBranchService;
import com.example.franchise.persistence.entities.Branch;
import com.example.franchise.persistence.entities.Product;
import com.example.franchise.persistence.repositories.IBranchRepository;
import com.example.franchise.persistence.repositories.IProductRepository;
import com.example.franchise.presentation.dtos.BranchDto;
import com.example.franchise.presentation.dtos.ProductDto;
import com.example.franchise.util.mapper.BranchMapper;
import com.example.franchise.util.mapper.ProductMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BranchServiceImpl implements IBranchService {

    private final IBranchRepository branchRepository;
    private final IProductRepository productRepository;
    private final BranchMapper branchMapper;
    private final ProductMapper productMapper;

    @Override
    public Mono<BranchDto> addProductToBranch(String branchId, Mono<ProductDto> productDtoMono) {
        Mono<Branch> branchMono = this.branchRepository.findById(branchId)
                .switchIfEmpty(Mono.error(new ElementNotFoundException("Branch don't exist")));

        Mono<Product> productMono = productDtoMono
                .map(productDto -> this.productMapper.toProduct(productDto))
                .flatMap(productSaved -> this.productRepository.save(productSaved));

        Mono<BranchDto> branchDtoMono = Mono.zip(branchMono, productMono, (branch, product) -> {
                    branch.getProducts().add(product.getId());
                    return branch;
                }).flatMap(branch -> this.branchRepository.save(branch))
                .map(branch -> this.branchMapper.toBranchDto(branch));

        return branchDtoMono;
    }

    @Override
    public Mono<BranchDto> removeProductToBranch(String branchId, String productId) {
        Mono<Branch> branchMono = this.branchRepository.findById(branchId)
                .switchIfEmpty(Mono.error(new ElementNotFoundException("Branch don't exist")));

        Mono<BranchDto> branchDtoMono = branchMono
                .map(branch -> {
                    List<String> product = branch.getProducts();

                    if (product.contains(productId)) {
                        product.remove(productId);
                        branch.setProducts(product);
                    }
                    return branch;
                }).flatMap(branch -> this.branchRepository.save(branch))
                .map(branch -> this.branchMapper.toBranchDto(branch));

        return branchDtoMono;
    }

    @Override
    public Mono<BranchDto> updateNameBranch(String idBranch, String newName) {
        Mono<BranchDto> branchDtoMono = this.branchRepository.findById(idBranch)
                .switchIfEmpty(Mono.error(new ElementNotFoundException("Branch don't exist")))
                .map(branch -> {
                    branch.setName(newName);
                    return branch;
                })
                 .flatMap(branch -> this.branchRepository.save(branch))
                 .map(branch -> this.branchMapper.toBranchDto(branch));

        return branchDtoMono;
    }
}









