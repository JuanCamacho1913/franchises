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

    public Franchise toFranchise(FranchiseDto franchiseDto) {
        if (franchiseDto.branches() == null || franchiseDto.branches().isEmpty()) {
            Franchise franchise = Franchise.builder()
                    .id(franchiseDto.id())
                    .name(franchiseDto.name())
                    .branches(Collections.emptyList())
                    .build();
            return franchise;
        }else {
            Franchise franchise = Franchise.builder()
                    .id(franchiseDto.id())
                    .name(franchiseDto.name())
                    .branches(franchiseDto.branches())
                    .build();
            return franchise;
        }
    }

    public FranchiseDto toFranchiseDto(Franchise franchise){
        return new FranchiseDto(
                franchise.getId(),
                franchise.getName(),
                franchise.getBranches()
        );
    }
}
