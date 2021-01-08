package com.melix.men.repository;

import com.melix.men.model.Dna;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface DnaRepository extends MongoRepository<Dna, String> {
    public Boolean existsByDna(String[] dna);
}