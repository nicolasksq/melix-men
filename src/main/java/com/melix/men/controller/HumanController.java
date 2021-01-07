package com.melix.men.controller;

import com.melix.men.model.Dna;
import com.melix.men.service.DnaService;
import com.melix.men.service.MutantCounterService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.ResponseEntity;

@RestController
public class HumanController {

    private final DnaService dnaService;
    private final MutantCounterService mutantCounterService;

    public HumanController(DnaService dnaService, MutantCounterService mutantCounterService) {
        this.dnaService = dnaService;
        this.mutantCounterService = mutantCounterService;
    }

    @RequestMapping(value = "/mutant", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> isMutant(@RequestBody Dna dna) {
            if(dnaService.processDna(dna)) {
                return ResponseEntity.status(HttpStatus.OK).build();
            } else {
                return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
            }
    }

    @RequestMapping(value = "/stats", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> stats() {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(mutantCounterService.getMutantCounter());
        } catch (Exception exception) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }
    }
}