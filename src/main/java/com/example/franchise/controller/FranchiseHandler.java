package com.example.franchise.controller;

import com.example.franchise.persistence.entities.Franchise;
import com.example.franchise.presentation.dtos.FranchiseDto;
import com.example.franchise.services.interfaces.IFranchiseService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

@Component
@RequiredArgsConstructor
public class FranchiseHandler {

    public final IFranchiseService franchiseService;

    public Mono<ServerResponse> createFranchise(ServerRequest serverRequest){
        Mono<FranchiseDto> franchiseDtoMono = serverRequest.bodyToMono(FranchiseDto.class);
        Mono<FranchiseDto> franchiseDtoSaved = this.franchiseService.createFranchise(franchiseDtoMono);

        return ServerResponse
                .ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(franchiseDtoSaved, Franchise.class);
    }
}
