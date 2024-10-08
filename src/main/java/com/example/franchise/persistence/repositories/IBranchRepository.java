package com.example.franchise.persistence.repositories;

import com.example.franchise.persistence.entities.Branch;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IBranchRepository extends ReactiveMongoRepository<Branch, String> {
}
