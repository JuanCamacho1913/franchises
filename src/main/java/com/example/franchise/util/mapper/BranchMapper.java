package com.example.franchise.util.mapper;

import com.example.franchise.persistence.entities.Branch;
import com.example.franchise.persistence.entities.Product;
import com.example.franchise.presentation.dtos.BranchDto;
import com.example.franchise.presentation.dtos.ProductDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;

@Component
@RequiredArgsConstructor
public class BranchMapper {

    public Branch toBranch(BranchDto branchDto){
        if (branchDto.products() == null || branchDto.products().isEmpty()){
            Branch branch = Branch.builder()
                    .id(branchDto.id())
                    .name(branchDto.name())
                    .products(Collections.emptyList())
                    .build();
            return branch;
        }else {
            Branch branch = Branch.builder()
                    .id(branchDto.id())
                    .name(branchDto.name())
                    .products(branchDto.products())
                    .build();
            return branch;
        }
    }

    public BranchDto toBranchDto(Branch branch) {
        return new BranchDto(
                branch.getId(),
                branch.getName(),
                branch.getProducts()
        );
    }
}
