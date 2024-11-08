package com.example.franchise.util.mapper;

import com.example.franchise.persistence.entities.Branch;
import com.example.franchise.persistence.entities.Franchise;
import com.example.franchise.presentation.dtos.BranchDto;
import com.example.franchise.presentation.dtos.FranchiseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;

@Component
@RequiredArgsConstructor
public class FranchiseMapper {

    private final BranchMapper branchMapper;

    public Franchise toFranchise(FranchiseDto franchiseDto) {
        if (franchiseDto.branches() == null || franchiseDto.branches().isEmpty()) {
            Franchise franchise = Franchise.builder()
                    .id(franchiseDto.id())
                    .name(franchiseDto.name())
                    .branches(Collections.emptyList())
                    .build();
            return franchise;
        }else {
            List<Branch> branches = franchiseDto.branches()
                    .stream()
                    .map(branchDto -> this.branchMapper.toBranch(branchDto))
                    .toList();

            Franchise franchise = Franchise.builder()
                    .id(franchiseDto.id())
                    .name(franchiseDto.name())
                    .branches(branches)
                    .build();
            return franchise;
        }
    }

    public FranchiseDto toFranchiseDto(Franchise franchise){
        List<BranchDto> branchDtos = franchise.getBranches()
                .stream()
                .map(branch -> this.branchMapper.toBranchDto(branch))
                .toList();

        return new FranchiseDto(
                franchise.getId(),
                franchise.getName(),
                branchDtos
        );
    }
}
