import static org.junit.Assert.assertEquals;

import org.junit.Test;

import com.aeropuerto.scenario.codeGenerator.AircraftCodeGenerator;

public class TestAircraftCodeGenerator {
    
    //Caso unico: debe retornar codigos en secuencia
    @Test
    public void codeGenerator(){
        AircraftCodeGenerator aircraftCodeGenerator = new AircraftCodeGenerator();
        assertEquals(1, aircraftCodeGenerator.nextCode());
        assertEquals(2, aircraftCodeGenerator.nextCode());
    }
}
