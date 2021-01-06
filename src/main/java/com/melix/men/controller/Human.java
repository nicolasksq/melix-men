package com.melix.men.controller;

import com.melix.men.Utils.Utils;
import com.melix.men.model.Dna;
import com.melix.men.model.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;

@RestController
public class Human {

    //max value to find in the DNA
    private static final int MAX_NITRO_BASE = 4;

    //Human adn to verify
    //@todo throw an exception if dna does not have any letter in array.
    private static final String[] HUMAN_ADN = {"A","T","C","G"};

    @RequestMapping(value = "/mutant", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> isMutant(@RequestBody Dna dna) {
        try {
            this.validateMutant(Utils.convertToMatrix(dna.getDna()));
            return ResponseEntity.status(HttpStatus.OK).build();
        } catch (Exception exception) {
            //Todo - create a specific exception to handle.
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }
    }

    /**
     * @param dna
     * @throws Exception
     */
    private void validateMutant(char[][] dna) throws Exception {

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
                    if (horizontalCoincidence == MAX_NITRO_BASE) return;
                }

                if (nitroBaseIndex > 0) {
                    if (dna[nitroBaseIndex][letterIndex] == dna[nitroBaseIndex - 1][letterIndex]) verticalCoincidence[letterIndex]++;
                    else verticalCoincidence[letterIndex] = 1;

                    //validate vertically
                    if (verticalCoincidence[letterIndex] == MAX_NITRO_BASE) return;
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
                    //As the matrix has all values in 0, i rest -1 to max_nitro_base
                    if (obliqueRightCoincidence[nitroBaseIndex][letterIndex] == MAX_NITRO_BASE-1) return;
                    if (obliqueLeftCoincidence[nitroBaseIndex][letterIndex] == MAX_NITRO_BASE-1) return;
                }
            }
        }

        throw new Exception("The DNA is human");
    }
}
