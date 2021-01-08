package mockObjects;

import com.melix.men.model.MutantCounter;

public class MutantCounterMock {
    public static MutantCounter getMutantCounterMock() {
        return new MutantCounter("test",0,0, 0.0f);
    }

    public static MutantCounter getMutantCounterHumanIncrementedMock() {
        return new MutantCounter("test",0,1, 0.0f);
    }

    public static MutantCounter getMutantCounterHumanAndMutantIncrementedMock() {
        return new MutantCounter("test",1,1, 1.0f);
    }
}
