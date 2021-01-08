package mockObjects;

import com.melix.men.model.Dna;

public class DnaHumanMock {
    public final static String[] getDnaHuman() {
        return new String[]{"ATGCAA", "CAGAGC", "TTATAT", "AGACGG", "GCGTCA", "TCACTG"};
    }

    public final static String[] getMutantVerticalyCoincidence() {
        return new String[]{"ATGCAA","AAGAGC","ATATAT","AGACGG","GCGTCA","TCACTG"};
    }

    public final static String[] getMutantHorizontallyCoincidence() {
        return new String[]{"AAAAGA", "CAGAGC", "TTGTAT", "AGCCGG", "GCGTCA", "TCACTG"};
    }

    public final static String[] getMutantLeftObliqueCoincidence() {
        return new String[]{"ATGCAA", "CAGAGC", "TTATAT", "AGTCGG", "GTGTCA", "TCACTG"};
    }

    public final static String[] getMutantRightObliqueCoincidence() {
        return new String[]{"ATGCAA", "CAGAGC", "TTGTAT", "AGAGGG", "GCGTGA", "TCACTG"};
    }

    public final static Dna getDnaMock() {
        String[] mutantHorizontallyCoincidence = {"AAAAGA", "CAGAGC", "TTGTAT", "AGCCGG", "GCGTCA", "TCACTG"};
		return new Dna(mutantHorizontallyCoincidence);
    }
}
