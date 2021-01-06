package com.melix.men;

import com.melix.men.controller.Human;
import com.melix.men.model.Dna;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@SpringBootTest
class MenApplicationTests {

	@Autowired
	private Human humanController;

	@Test
	void isHumanTest() {
		String[] humanDna = {"ATGCAA","CAGAGC","TTATAT","AGACGG","GCGTCA","TCACTG"};
		Dna dna = new Dna(humanDna);
		ResponseEntity<?> response = humanController.isMutant(dna);
		Assertions.assertEquals(response.getStatusCode(), HttpStatus.FORBIDDEN);
	}

	@Test
	void isMutantVerticallyCoincidenceTest() {
		String[] mutantVerticalyCoincidence = {"ATGCAA","AAGAGC","ATATAT","AGACGG","GCGTCA","TCACTG"};
		Dna dna = new Dna(mutantVerticalyCoincidence);
		ResponseEntity<?> response = humanController.isMutant(dna);
		Assertions.assertEquals(response.getStatusCode(), HttpStatus.OK);
	}

	@Test
	void isMutantHorizontallyCoincidenceTest() {
		String[] mutantHorizontallyCoincidence = {"AAAAGA", "CAGAGC", "TTGTAT", "AGCCGG", "GCGTCA", "TCACTG"};
		Dna dna = new Dna(mutantHorizontallyCoincidence);
		ResponseEntity<?> response = humanController.isMutant(dna);
		Assertions.assertEquals(response.getStatusCode(), HttpStatus.OK);
	}

	@Test
	void isMutantObliqueCoincidenceTest() {
		String[] mutantLeftObliqueCoincidence = {"ATGCAA", "CAGAGC", "TTATAT", "AGTCGG", "GTGTCA", "TCACTG"};
		String[] mutantRightObliqueCoincidence = {"ATGCAA", "CAGAGC", "TTGTAT", "AGAGGG", "GCGTGA", "TCACTG"};

		Dna dnaLeftCoincidence = new Dna(mutantLeftObliqueCoincidence);
		Dna dnaRightCoincidence = new Dna(mutantRightObliqueCoincidence);

		ResponseEntity<?> responseL = humanController.isMutant(dnaLeftCoincidence);
		ResponseEntity<?> responseR = humanController.isMutant(dnaRightCoincidence);
		Assertions.assertEquals(responseL.getStatusCode(), HttpStatus.OK);
		Assertions.assertEquals(responseR.getStatusCode(), HttpStatus.OK);
	}



}
