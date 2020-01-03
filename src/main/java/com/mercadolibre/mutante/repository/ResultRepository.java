package com.mercadolibre.mutante.repository;

import com.mercadolibre.mutante.domain.Result;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ResultRepository extends MongoRepository<Result, String> {

    List<Result> findByMutantTrue();

    Long countByMutantTrue();

    List<Result> findByMutantFalse();

    Long countByMutantFalse();

}
