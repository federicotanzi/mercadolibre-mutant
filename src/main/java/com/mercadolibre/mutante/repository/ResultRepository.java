package com.mercadolibre.mutante.repository;

import com.mercadolibre.mutante.domain.Result;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ResultRepository extends JpaRepository<Result, Long> {

    Long countByMutantTrue();

    Long countByMutantFalse();

}
