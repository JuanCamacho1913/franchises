package com.example.franchise.persistence.repositories;

import com.example.franchise.persistence.entities.Franchise;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IFranchiseRepository extends ReactiveMongoRepository<Franchise, String> {
}
