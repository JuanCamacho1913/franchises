package com.example.franchise.services.implementation;

import com.example.franchise.persistence.repositories.IFranchiseRepository;
import com.example.franchise.presentation.dtos.BranchDto;
import com.example.franchise.presentation.dtos.FranchiseDto;
import com.example.franchise.services.interfaces.IFranchiseService;
import com.example.franchise.util.mapper.FranchiseMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class FranchiseServiceImpl implements IFranchiseService {

    private final IFranchiseRepository franchiseRepository;
    private final FranchiseMapper franchiseMapper;

    @Override
    public Mono<FranchiseDto> createFranchise(Mono<FranchiseDto> franchiseDtoMono) {
        return franchiseDtoMono
                .map(franchiseDto -> this.franchiseMapper.toFranchise(franchiseDto))
                .flatMap(franchiseDoc -> this.franchiseRepository.save(franchiseDoc))
                .map(franchiseSaved -> this.franchiseMapper.toFranchiseDto(franchiseSaved));
    }

    @Override
    public Mono<FranchiseDto> addBranch(Mono<BranchDto> branchDto) {
        return null;
    }
}









