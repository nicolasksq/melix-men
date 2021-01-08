package mockObjects;

import com.melix.men.model.Dna;

public class DnaHumanMock {
    public static String[] getDnaHuman() {
        return new String[]{"ATGCAA", "CAGAGC", "TTATAT", "AGACGG", "GCGTCA", "TCACTG"};
    }

    public static String[] getMutantVerticalyCoincidence() {
        return new String[]{"ATGCAA","AAGAGC","ATATAT","AGACGG","GCGTCA","TCACTG"};
    }

    public static String[] getMutantHorizontallyCoincidence() {
        return new String[]{"AAAAGA", "CAGAGC", "TTGTAT", "AGCCGG", "GCGTCA", "TCACTG"};
    }

    public static String[] getMutantLeftObliqueCoincidence() {
        return new String[]{"ATGCAA", "CAGAGC", "TTATAT", "AGTCGG", "GTGTCA", "TCACTG"};
    }

    public static String[] getMutantRightObliqueCoincidence() {
        return new String[]{"ATGCAA", "CAGAGC", "TTGTAT", "AGAGGG", "GCGTGA", "TCACTG"};
    }

    public static Dna getDnaMock() {
		return new Dna(
		        new String[]{"AAAAGA", "CAGAGC", "TTGTAT", "AGCCGG", "GCGTCA", "TCACTG"});
    }
}
