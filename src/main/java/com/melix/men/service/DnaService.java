package com.melix.men.service;

import com.melix.men.Utils.Utils;
import com.melix.men.model.Dna;
import com.melix.men.model.MutantCounter;
import com.melix.men.repository.DnaRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.Arrays;

@Service
public class DnaService {

    private static final Logger logger = LoggerFactory.getLogger(DnaService.class);

    //max value to find in the DNA
    private static final int MAX_NITRO_BASE = 4;

    private final DnaRepository dnaRepository;
    private final MutantCounterService mutantCounterService;

    public DnaService(DnaRepository dnaRepository, MutantCounterService mutantCounterService) {
        this.dnaRepository = dnaRepository;
        this.mutantCounterService = mutantCounterService;
    }

    public Boolean processDna(Dna dna) {

        logger.info("checking if dna mutant...");
        Boolean isMutant = isMutant(Utils.convertToMatrix(dna.getDna()));
        MutantCounter mutantCounter = mutantCounterService.getMutantCounter();

        if(!existDna(dna)){
            dnaRepository.save(dna);

            if(isMutant) {
                mutantCounterService.updateMutantCounter(mutantCounter.increaseHuman());
                mutantCounterService.updateMutantCounter(mutantCounter.increaseMutant());
            } else {
                mutantCounterService.updateMutantCounter(mutantCounter.increaseHuman());
            }
        }

        return isMutant;
    }

    private Boolean existDna(Dna dna) {
        logger.info("find dna...");
        return dnaRepository.existDna(dna.getDna());
    }

    private Boolean isMutant(char[][] dna) {

        int horizontalCoincidence       = 1;
        int[] verticalCoincidence       = new int[dna[0].length];
        int[][] obliqueLeftCoincidence  = new int[dna.length][dna[0].length];
        int[][] obliqueRightCoincidence = new int[dna.length][dna[0].length];

        Arrays.fill(verticalCoincidence,1);

        for (int nitroBaseIndex = 0; nitroBaseIndex < dna.length; nitroBaseIndex++) {
            for (int letterIndex = 0; letterIndex < dna[nitroBaseIndex].length; letterIndex++) {

                if (letterIndex > 0) {
                    if (dna[nitroBaseIndex][letterIndex] == dna[nitroBaseIndex][letterIndex - 1]) horizontalCoincidence++;
                    else horizontalCoincidence = 1;

                    //validate horizontally
                    if (horizontalCoincidence == MAX_NITRO_BASE) return true;
                }

                if (nitroBaseIndex > 0) {
                    if (dna[nitroBaseIndex][letterIndex] == dna[nitroBaseIndex - 1][letterIndex]) verticalCoincidence[letterIndex]++;
                    else verticalCoincidence[letterIndex] = 1;

                    //validate vertically
                    if (verticalCoincidence[letterIndex] == MAX_NITRO_BASE) return true;
                }

                if(letterIndex > 0 && nitroBaseIndex > 0) {

                    if (dna[nitroBaseIndex][letterIndex] == dna[nitroBaseIndex - 1][letterIndex - 1]) {
                        obliqueRightCoincidence[nitroBaseIndex][letterIndex] += obliqueRightCoincidence[nitroBaseIndex - 1][letterIndex - 1]+1;
                    }

                    if(letterIndex < dna[nitroBaseIndex].length - 1) {
                        if (dna[nitroBaseIndex][letterIndex] == dna[nitroBaseIndex - 1][letterIndex + 1]) {
                            obliqueLeftCoincidence[nitroBaseIndex][letterIndex] += obliqueLeftCoincidence[nitroBaseIndex - 1][letterIndex + 1]+1;
                        }
                    }

                    //validate obliquely
                    //As the matrix has all values in 0, max_nitro_base will be decrese 1.
                    if (obliqueRightCoincidence[nitroBaseIndex][letterIndex] == MAX_NITRO_BASE-1) return true;
                    if (obliqueLeftCoincidence[nitroBaseIndex][letterIndex] == MAX_NITRO_BASE-1) return true;
                }
            }
        }

        return false;
    }

}
