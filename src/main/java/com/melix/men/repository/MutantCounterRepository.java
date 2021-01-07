package com.melix.men.repository;

import com.melix.men.model.MutantCounter;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MutantCounterRepository extends CrudRepository<MutantCounter, String> {}

