package com.example.franchise.domain.services.implementation;

import com.example.franchise.domain.exception.ElementNotFoundException;
import com.example.franchise.persistence.entities.Branch;
import com.example.franchise.persistence.entities.Franchise;
import com.example.franchise.persistence.repositories.IBranchRepository;
import com.example.franchise.persistence.repositories.IFranchiseRepository;
import com.example.franchise.presentation.dtos.BranchDto;
import com.example.franchise.presentation.dtos.FranchiseDto;
import com.example.franchise.domain.services.interfaces.IFranchiseService;
import com.example.franchise.util.mapper.BranchMapper;
import com.example.franchise.util.mapper.FranchiseMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.util.List;

@Service
@RequiredArgsConstructor
public class FranchiseServiceImpl implements IFranchiseService {

    private final IBranchRepository branchRepository;
    private final IFranchiseRepository franchiseRepository;
    private final FranchiseMapper franchiseMapper;
    private final BranchMapper branchMapper;

    @Override
    public Mono<FranchiseDto> createFranchise(Mono<FranchiseDto> franchiseDtoMono) {
        return franchiseDtoMono
                .map(franchiseDto -> this.franchiseMapper.toFranchise(franchiseDto))
                .flatMap(franchiseDoc -> this.franchiseRepository.save(franchiseDoc))
                .map(franchiseSaved -> this.franchiseMapper.toFranchiseDto(franchiseSaved));
    }

    @Override
    public Mono<FranchiseDto> addBranchToFranchise(String idFranchise, Mono<BranchDto> branchDtoMono) {
        Mono<Franchise> franchiseMono = this.franchiseRepository.findById(idFranchise)
                .switchIfEmpty(Mono.error(new ElementNotFoundException("Franchise don't exist")));

        Mono<Branch> branchMono = branchDtoMono
                .map(branchDto -> this.branchMapper.toBranch(branchDto))
                .flatMap(branchDoc -> this.branchRepository.save(branchDoc));

        Mono<FranchiseDto> franchiseDtoMono = Mono.zip(franchiseMono, branchMono, (franchise, branch) -> {
                    franchise.setBranches(List.of(branch));

                    return franchise;
                }).flatMap(franchise -> this.franchiseRepository.save(franchise))
                .map(franchise -> this.franchiseMapper.toFranchiseDto(franchise));

        return franchiseDtoMono;
    }
}









