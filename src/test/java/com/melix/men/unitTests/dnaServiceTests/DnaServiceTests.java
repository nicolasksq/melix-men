package com.melix.men.unitTests.dnaServiceTests;

import com.melix.men.Utils.Utils;
import com.melix.men.model.Dna;
import com.melix.men.repository.DnaRepository;
import mockObjects.DnaHumanMock;
import com.melix.men.service.DnaService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

@SpringBootTest
class DnaServiceTests {

	@InjectMocks
	private DnaService dnaService;

	@Mock
	private DnaRepository dnaRepository;

	@Test
	void whenIsHumanThenFalseTest() {
		Boolean isMutant = dnaService.isMutant(
				Utils.convertToMatrix(DnaHumanMock.getDnaHuman()));
		Assertions.assertFalse(isMutant);
	}

	@Test
	void whenIsMutantVerticallyCoincidenceThenTrueTest() {
		Boolean isMutant = dnaService.isMutant(
				Utils.convertToMatrix(DnaHumanMock.getMutantVerticalyCoincidence()));
		Assertions.assertTrue(isMutant);
	}

	@Test
	void whenIsMutantHorizontallyCoincidenceThenTrueTest() {
		Boolean isMutant = dnaService.isMutant(
				Utils.convertToMatrix(DnaHumanMock.getMutantHorizontallyCoincidence()));
		Assertions.assertTrue(isMutant);
	}

	@Test
	void whenIsMutantObliqueCoincidenceThenTrueTest() {
		Boolean isMutantLeft = dnaService.isMutant(
				Utils.convertToMatrix(DnaHumanMock.getMutantLeftObliqueCoincidence())
		);

		Assertions.assertTrue(isMutantLeft);

		Boolean isMutantRight = dnaService.isMutant(
				Utils.convertToMatrix(DnaHumanMock.getMutantRightObliqueCoincidence()));

		Assertions.assertTrue(isMutantRight);
	}

	@Test
	void whenExistsByDnaIsTrueThenSameDna() {

		when(dnaRepository.existsByDna(any())).thenReturn(true);

		Dna dnaHumanMock = DnaHumanMock.getDnaMock();

		when(dnaRepository.save(any())).thenReturn(dnaHumanMock);

		Dna dna = dnaService.saveDna(dnaHumanMock);
		Assertions.assertEquals(dna,dnaHumanMock);
	}

	@Test
	void whenExistsByDnaIsFalseThenSameDna() {

		when(dnaRepository.existsByDna(any())).thenReturn(false);
		Dna dnaHumanMock = DnaHumanMock.getDnaMock();

		when(dnaRepository.save(any())).thenReturn(dnaHumanMock);
		Dna dna = dnaService.saveDna(dnaHumanMock);

		Assertions.assertEquals(dna,dnaHumanMock);
	}

	@Test
	void whenIsMutantTrueThenGetResponseOk() {
		Assertions.assertEquals(
				ResponseEntity.status(HttpStatus.OK).build(), dnaService.getResponse(true));
	}

	@Test
	void whenIsMutantFalseThenGetResponseOk() {
		Assertions.assertEquals(
				ResponseEntity.status(HttpStatus.FORBIDDEN).build(), dnaService.getResponse(false));
	}

}