package com.melix.men.repository;

import com.melix.men.model.Dna;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

public class DnaRepositoryImpl implements DnaRepositoryCustom {

    @Autowired
    MongoTemplate mongoTemplate;

    @Override
    public Boolean existDna(String[] dna) {
            return mongoTemplate.exists(
                Query.query(Criteria.where("dna").all(dna)), Dna.class, "dna");
    }
}
