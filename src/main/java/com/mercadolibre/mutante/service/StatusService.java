package com.mercadolibre.mutante.service;

import com.mercadolibre.mutante.domain.Status;
import com.mercadolibre.mutante.repository.ResultRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StatusService {

    @Autowired
    private ResultRepository resultRepository;

    public Status getStatistics() {
        Status status = new Status();
        Long mutantCount = this.resultRepository.countByMutantTrue();
        status.setMutantCount(mutantCount);
        Long humanCount = this.resultRepository.countByMutantFalse();
        status.setHumanCount(humanCount);
        status.setRatio(calcRatio(mutantCount, humanCount));
        return status;
    }

    private Double calcRatio(Long mutantCount, Long humanCount) {
        if (mutantCount != 0) {
            if (humanCount == 0) {
                return 1d;
            } else {
                return mutantCount.doubleValue() / humanCount.doubleValue();
            }
        } else {
            return 0d;
        }
    }

}
