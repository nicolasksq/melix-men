package com.melix.men.service;

import com.melix.men.model.MutantCounter;
import com.melix.men.repository.MutantCounterRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class MutantCounterService {

    private static final Logger logger = LoggerFactory.getLogger(MutantCounterService.class);
    private static final String KEY = MutantCounter.class.toString();

    private final MutantCounterRepository mutantCounterRepository;

    public MutantCounterService(MutantCounterRepository mutantCounterRepository) {
        this.mutantCounterRepository = mutantCounterRepository;
    }

    public MutantCounter getMutantCounter() {
        if(mutantCounterRepository.findById(KEY).isPresent())
            return mutantCounterRepository.findById(KEY).get();

        logger.error("Counter is not inicialiced");
        logger.info("creating a new one..");

        //saving new counter
        return mutantCounterRepository.save(new MutantCounter(KEY,0,0, 0.0f));
    }

    public void updateMutantCounter(MutantCounter mutantCounter) {
        mutantCounterRepository.save(mutantCounter);
    }
}
