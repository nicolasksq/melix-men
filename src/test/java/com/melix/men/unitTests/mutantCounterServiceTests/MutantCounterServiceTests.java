package com.melix.men.unitTests.mutantCounterServiceTests;

import com.melix.men.model.MutantCounter;
import com.melix.men.repository.MutantCounterRepository;
import com.melix.men.service.MutantCounterService;
import mockObjects.MutantCounterMock;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import static org.mockito.AdditionalAnswers.returnsFirstArg;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@SpringBootTest
class MutantCounterServiceTests {

	@InjectMocks
	private MutantCounterService mutantCounterService;

	@Mock
	private MutantCounterRepository mutantCounterRepository;

	@Test
	void whenIsHumanThenIncreaseHumanTest() {

		MutantCounter mutantCounter = MutantCounterMock.getMutantCounterMock();
		MutantCounter mutantCounterIncremented =
				MutantCounterMock.getMutantCounterHumanIncrementedMock();

		when(mutantCounterRepository.save(any())).then(returnsFirstArg());

		MutantCounter mutantCounterResult =
				mutantCounterService.increaseHuman(mutantCounter);

		Assertions.assertEquals(
				mutantCounterIncremented.getCount_mutant_dna(),
				mutantCounterResult.getCount_mutant_dna()
		);

		Assertions.assertEquals(
				mutantCounterIncremented.getCount_human_dna(),
				mutantCounterResult.getCount_human_dna()
		);

		Assertions.assertEquals(
				mutantCounterIncremented.getRatio(),
				mutantCounterResult.getRatio()
		);
	}

	@Test
	void whenIsMutantThenIncreaseHumanAndMutantTest() {
		MutantCounter mutantCounter =
				MutantCounterMock.getMutantCounterMock();
		MutantCounter mutantCounterIncremented =
				MutantCounterMock.getMutantCounterHumanAndMutantIncrementedMock();

		when(mutantCounterRepository.save(any())).then(returnsFirstArg());

		MutantCounter mutantCounterResult =
				mutantCounterService.increaseHumanAndMutant(mutantCounter);

		Assertions.assertEquals(
				mutantCounterIncremented.getCount_mutant_dna(),
				mutantCounterResult.getCount_mutant_dna());

		Assertions.assertEquals(
				mutantCounterIncremented.getCount_human_dna(),
				mutantCounterResult.getCount_human_dna());

		Assertions.assertEquals(
				mutantCounterIncremented.getRatio(),
				mutantCounterResult.getRatio());
	}
}