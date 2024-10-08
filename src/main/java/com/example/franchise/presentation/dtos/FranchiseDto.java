package com.example.franchise.presentation.dtos;

import com.example.franchise.persistence.entities.Branch;

import java.util.List;

public record FranchiseDto(
        String id,
        String name,
        List<Branch> branches) {
}
