import org.junit.Test;

import com.engine.distribution.dinamicDistribution.DinamicExponentialEventDuration;
import com.engine.exceptions.ArraysNull;
import com.engine.exceptions.NegativeNumberException;
import com.engine.exceptions.OverlappingException;

public class TestDinamicExponentialEventDuration {
    //Case 1: Se ingresan intervalos con números negativos 

    @Test (expected = ArraysNull.class)
    public void negativeIntervals() throws ArraysNull, NegativeNumberException, OverlappingException{
        double [] intervals = null;
        double []peak = {2,3}; 
        DinamicExponentialEventDuration dinamicExponentialEventDuration = new DinamicExponentialEventDuration(intervals, peak, 0);
    }
}
