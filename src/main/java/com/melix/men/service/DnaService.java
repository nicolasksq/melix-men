package com.melix.men.service;

import com.melix.men.model.Dna;
import com.melix.men.model.MutantCounter;
import com.melix.men.repository.DnaRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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

    /**
     * @param dna
     * @return Dna
     */
    public Dna saveDna(Dna dna) {

        if(!dnaRepository.existsByDna(dna.getDna())){
            dnaRepository.save(dna);
        }

        return dna;
    }

    /**
     * @param dna
     * @return Dna
     */
    public Boolean existsByDna(Dna dna) {
        return dnaRepository.existsByDna(dna.getDna());
    }

    /**
     * @param isMutant
     * @return ResponseEntity
     */
    public ResponseEntity<?> getResponse(Boolean isMutant){
            return isMutant ?
                    ResponseEntity.status(HttpStatus.OK).build() :
                    ResponseEntity.status(HttpStatus.FORBIDDEN).build();
    }

    /**
     *
     * @param isMutant
     * @return MutantCounter
     */
    public MutantCounter updateStats(Boolean isMutant) {

        MutantCounter mutantCounter = mutantCounterService.getMutantCounter();
        if(isMutant) {
            mutantCounterService.increaseHumanAndMutant(mutantCounter);
        } else {
            mutantCounterService.increaseHuman(mutantCounter);
        }
        return mutantCounter;
    }

    /**
     *
     * @param dna
     * @return Boolean
     */
    public Boolean isMutant(char[][] dna) {

        int[] verticalCoincidence       = new int[dna[0].length];
        Arrays.fill(verticalCoincidence,1);

        int[][] obliqueLeftCoincidence  = new int[dna.length][dna[0].length];
        int[][] obliqueRightCoincidence = new int[dna.length][dna[0].length];

        boolean isMutant = false;

        for (int nitroBaseIndex = 0; nitroBaseIndex < dna.length; nitroBaseIndex++) {
            int horizontalCoincidence = 1;
            for (int letterIndex = 0; letterIndex < dna[nitroBaseIndex].length; letterIndex++) {

                if (letterIndex > 0) {
                    //increment + 1 to horizontal coincidence
                    horizontalCoincidence =
                            this.validateAndIncreaseCounterCoincidences(
                                    dna[nitroBaseIndex][letterIndex],
                                    dna[nitroBaseIndex][letterIndex - 1],
                                    horizontalCoincidence);
                }

                if (nitroBaseIndex > 0) {

                    //increment + 1 to vertical coincidence
                    verticalCoincidence[letterIndex] = this.validateAndIncreaseCounterCoincidences(
                            dna[nitroBaseIndex][letterIndex],
                            dna[nitroBaseIndex - 1][letterIndex],
                            verticalCoincidence[letterIndex]);

                    if (letterIndex > 0) {
                        //increment + 1 to oblique right coincidence
                        obliqueRightCoincidence[nitroBaseIndex][letterIndex] += this.validateAndIncreaseCounterCoincidences(
                                dna[nitroBaseIndex][letterIndex] ,
                                dna[nitroBaseIndex - 1][letterIndex - 1],
                                obliqueRightCoincidence[nitroBaseIndex - 1][letterIndex - 1]);
                    }

                    if (letterIndex < dna[nitroBaseIndex].length - 1) {
                        //increment + 1 to oblique left coincidence
                        obliqueLeftCoincidence[nitroBaseIndex][letterIndex] += this.validateAndIncreaseCounterCoincidences(
                                dna[nitroBaseIndex][letterIndex] ,
                                dna[nitroBaseIndex - 1][letterIndex + 1],
                                obliqueLeftCoincidence[nitroBaseIndex - 1][letterIndex + 1]);
                    }
                }

                //check if the counter has reached the wished number
                if (
                        (obliqueRightCoincidence[nitroBaseIndex][letterIndex] == MAX_NITRO_BASE-1) ||
                        (obliqueLeftCoincidence[nitroBaseIndex][letterIndex] == MAX_NITRO_BASE-1) ||
                        (horizontalCoincidence == MAX_NITRO_BASE) ||
                        (verticalCoincidence[letterIndex] == MAX_NITRO_BASE)
                ) isMutant = true;
            }
        }

        return isMutant;
    }

    /**
     * validate and increse the counter given
     * @param currentLetter
     * @param previusLetter
     * @param counterCoincidences
     * @return int
     */
    private int validateAndIncreaseCounterCoincidences(char currentLetter,
                                                       char previusLetter,
                                                       int counterCoincidences) {
        if (currentLetter == previusLetter)
            counterCoincidences++;
        else counterCoincidences = 1;

        return counterCoincidences;
    }

}
