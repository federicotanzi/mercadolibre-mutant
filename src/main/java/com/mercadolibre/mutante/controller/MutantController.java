package com.mercadolibre.mutante.controller;

import com.mercadolibre.mutante.domain.Sequence;
import com.mercadolibre.mutante.service.MutantService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/mutant")
public class MutantController {

    private static final Logger log = LoggerFactory.getLogger(MutantController.class);

    @Autowired
    private MutantService mutantService;

    @PostMapping("/")
    public ResponseEntity<Void> isMutant(@RequestBody @Valid Sequence sequence) {
        log.debug("Sequence: {}", sequence);

        boolean isMutant = mutantService.isMutantDNA(sequence);
        if (isMutant) {
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }
    }

}
