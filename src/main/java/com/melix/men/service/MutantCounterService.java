package com.melix.men.service;

import com.melix.men.model.MutantCounter;
import com.melix.men.repository.MutantCounterRepository;
import org.springframework.stereotype.Service;

@Service
public class MutantCounterService {

    private static final String KEY = MutantCounter.class.toString();
    private final MutantCounterRepository mutantCounterRepository;

    public MutantCounterService(MutantCounterRepository mutantCounterRepository) {
        this.mutantCounterRepository = mutantCounterRepository;
    }

    /**
     * @return MutantCounter
     */
    public MutantCounter getMutantCounter() {
            return mutantCounterRepository.findById(KEY).orElse(
                    mutantCounterRepository.save(new MutantCounter(KEY,0,0, 0.0f))
            );
    }

    /**
     *
     * @param mutantCounter
     * @return MutantCounter
     */
    public MutantCounter increaseHuman(MutantCounter mutantCounter) {
        mutantCounter.setCount_human_dna(mutantCounter.getCount_human_dna() + 1);
        mutantCounter.setRatio((float) mutantCounter.getCount_mutant_dna()/mutantCounter.getCount_human_dna());
        return mutantCounterRepository.save(mutantCounter);
    }

    /**
     *
     * @param mutantCounter
     * @return MutantCounter
     */
    public MutantCounter increaseHumanAndMutant(MutantCounter mutantCounter){
        mutantCounter.setCount_mutant_dna(mutantCounter.getCount_mutant_dna() + 1);
        mutantCounter.setCount_human_dna(mutantCounter.getCount_human_dna() + 1);
        mutantCounter.setRatio((float) mutantCounter.getCount_mutant_dna()/mutantCounter.getCount_human_dna());
        return mutantCounterRepository.save(mutantCounter);
    }
}
