package com.mercadolibre.mutante.controller;

import com.mercadolibre.mutante.domain.Status;
import com.mercadolibre.mutante.service.StatusService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/stats")
public class StatusController {

    private static final Logger log = LoggerFactory.getLogger(StatusController.class);

    @Autowired
    private StatusService statusService;

    @GetMapping("/")
    public ResponseEntity<Status> statisticsDNAMutant() {
        Status status = statusService.getStatistics();
        log.info("Status: {}", status);
        return ResponseEntity.ok(status);
    }

}
