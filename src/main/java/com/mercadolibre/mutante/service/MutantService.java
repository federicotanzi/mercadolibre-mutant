package com.mercadolibre.mutante.service;

import com.mercadolibre.mutante.detector.*;
import com.mercadolibre.mutante.domain.Result;
import com.mercadolibre.mutante.domain.Sequence;
import com.mercadolibre.mutante.repository.ResultRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.regex.Pattern;

@Service
public class MutantService {

    private static final Logger log = LoggerFactory.getLogger(MutantService.class);

    @Autowired
    private ResultRepository resultRepository;

    private static final Pattern NITROGENOUS_PATTERN = Pattern.compile("[atcg]+", Pattern.CASE_INSENSITIVE);

    private static final int SEQUENCE_SIZE_MUTANT = 4;

    private static final int SEQUENCES_MATCH_MUTANT = 2;

    public boolean isMutantDNA(Sequence sequence) {
        char[][] dna = loadStructure(sequence);
        MutantContext mutantContext = new MutantContext(dna, SEQUENCE_SIZE_MUTANT, SEQUENCES_MATCH_MUTANT);
        boolean mutant = isMutant(mutantContext);
        Result result = new Result();
        result.setSequence(sequence);
        result.setMutant(mutant);
        this.resultRepository.save(result);
        return mutant;
    }

    private char[][] loadStructure(Sequence sequence) {
        int vectorLength = sequence.getDna().size();
        if (vectorLength <= SEQUENCE_SIZE_MUTANT) {
            log.debug("Minimum length must be greater than {} to belong to mutant.", SEQUENCE_SIZE_MUTANT);
        }
        char[][] dna = new char[vectorLength][vectorLength];
        for (int i = 0; i < vectorLength; i++) {
            String dnaRow = sequence.getDna().get(i);
            if (dnaRow.length() != vectorLength) {
                log.error("The length of the DNA sequences must be the same size");
            } else if (!NITROGENOUS_PATTERN.matcher(dnaRow).matches()) {
                log.error("The only valid characters are A, T, C and G");
            }
            dna[i] = dnaRow.toUpperCase().toCharArray();
        }
        return dna;
    }

    private boolean isMutant(MutantContext context) {
        MutantProcessor horizontal = new HorizontalProcessor(context);
        horizontal.searchSequences();
        if (context.hasMatchQuantitySequencesMutant()){
           return true;
        }
        MutantProcessor vertical = new VerticalProcessor(context);
        vertical.searchSequences();
        if (context.hasMatchQuantitySequencesMutant()){
            return true;
        }
        MutantProcessor diagonalUp = new DiagonalUpProcessor(context);
        diagonalUp.searchSequences();
        if (context.hasMatchQuantitySequencesMutant()){
            return true;
        }
        MutantProcessor diagonalDown = new DiagonalDownProcessor(context);
        diagonalDown.searchSequences();
        return context.hasMatchQuantitySequencesMutant();
    }

}
