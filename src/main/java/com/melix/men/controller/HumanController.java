package com.melix.men.controller;

import com.melix.men.Utils.Utils;
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
import org.springframework.web.servlet.function.EntityResponse;

@RestController
public class HumanController {


    private final DnaService dnaService;
    private final MutantCounterService mutantCounterService;

    public HumanController(DnaService dnaService, MutantCounterService mutantCounterService) {
        this.dnaService = dnaService;
        this.mutantCounterService = mutantCounterService;
    }

    /**
     * Definition for this method is ->
     * @link https://github.com/nicolasksq/melix-men/blob/master/README.md#mutants
     */
    @RequestMapping(value = "/mutant", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> isMutant(@RequestBody Dna dna) {
        try {
            Boolean isMutant = dnaService.isMutant(Utils.convertToMatrix(dna.getDna()));
            if(!dnaService.existsByDna(dna)) {
                dnaService.saveDna(dna);
                dnaService.updateStats(isMutant);
            }

            return dnaService.getResponse(isMutant);
        } catch (ArrayIndexOutOfBoundsException exception) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    /**
     * Definition for this method is ->
     * @link https://github.com/nicolasksq/melix-men/blob/master/README.md#stats
     */
    @RequestMapping(value = "/stats", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> stats() {
            return ResponseEntity.status(HttpStatus.OK).body(
                    mutantCounterService.getMutantCounter()
            );
    }
}