package com.example.franchise.services.interfaces;

import com.example.franchise.presentation.dtos.BranchDto;
import com.example.franchise.presentation.dtos.FranchiseDto;
import reactor.core.publisher.Mono;

public interface IFranchiseService {

    public Mono<FranchiseDto> createFranchise(Mono<FranchiseDto> franchiseDto);

    public Mono<FranchiseDto> addBranch(Mono<BranchDto> branchDto);






}
