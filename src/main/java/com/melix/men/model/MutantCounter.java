package com.melix.men.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;

import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@RedisHash("MutantCounter")
public class MutantCounter implements Serializable {

    @Id
    @JsonIgnore
    private String id;

    private int count_mutant_dna;

    private int count_human_dna;

    private float ratio;

    public MutantCounter increaseMutant() {
        this.setCount_mutant_dna(this.getCount_mutant_dna() + 1);
        this.setRatio((float) this.getCount_mutant_dna()/this.getCount_human_dna());
        return this;
    }

    public MutantCounter increaseHuman() {
        this.setCount_human_dna(this.getCount_human_dna() + 1);
        this.setRatio((float) this.getCount_mutant_dna()/this.getCount_human_dna());
        return this;
    }

}