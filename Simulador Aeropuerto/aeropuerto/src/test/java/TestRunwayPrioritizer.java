import static org.junit.Assert.assertEquals;

import org.junit.Test;

import com.aeropuerto.scenario.RunwayPrioritizer;
import com.aeropuerto.scenario.airportEntity.Aircraft;
import com.aeropuerto.scenario.distribution.StaticDiscreteEmpiricLandingDuration;
import com.aeropuerto.scenario.distribution.StaticDiscreteEmpiricTimeBetweenArrival;
import com.engine.distribution.staticDistribution.StaticExponentialEventDuration;
import com.engine.distribution.staticDistribution.StaticNormalEventDuration;
import com.engine.events.Arrival;
import com.engine.server.Server;
import com.engine.server.ServerCodeGenerator;

public class TestRunwayPrioritizer {

    // Caso 1: Server 1 no está ocupado
    @Test
    public void case1(){
        ServerCodeGenerator serverCodeGenerator = new ServerCodeGenerator();
        StaticNormalEventDuration staticNormalEventDuration = new StaticNormalEventDuration(5, 1, new StaticExponentialEventDuration(5), 0.2, 0.04);
        Server s1 = new Server(serverCodeGenerator, null, staticNormalEventDuration, 3000);
        Server s2 = new Server(serverCodeGenerator, null, staticNormalEventDuration, 3000);
        
        RunwayPrioritizer runwayPrioritizer = new RunwayPrioritizer();
        assertEquals(-1, runwayPrioritizer.compare(s1, s2));
    }

    // Caso 2: Server 1 está ocupado y Server 2 no
    @Test
    public void case2(){
        ServerCodeGenerator serverCodeGenerator = new ServerCodeGenerator();
        StaticNormalEventDuration staticNormalEventDuration = new StaticNormalEventDuration(5, 1, new StaticExponentialEventDuration(5), 0.2, 0.04);
        Server s1 = new Server(serverCodeGenerator, null, staticNormalEventDuration, 3000);
        s1.setEntity(new Aircraft());
        Server s2 = new Server(serverCodeGenerator, null, staticNormalEventDuration, 3000);
        
        RunwayPrioritizer runwayPrioritizer = new RunwayPrioritizer();
        assertEquals(1, runwayPrioritizer.compare(s1, s2));
    }

    // Caso 3: Server 1 y 2 están ocupados, pero Server 1 tiene menos fila
    @Test
    public void case3(){
        ServerCodeGenerator serverCodeGenerator = new ServerCodeGenerator();
        StaticNormalEventDuration staticNormalEventDuration = new StaticNormalEventDuration(5, 1, new StaticExponentialEventDuration(5), 0.2, 0.04);
        Server s1 = new Server(serverCodeGenerator, null, staticNormalEventDuration, 3000);
        s1.setEntity(new Aircraft());
        Server s2 = new Server(serverCodeGenerator, null, staticNormalEventDuration, 3000);
        s2.setEntity(new Aircraft());
        s2.getQueue().add(new Arrival(2, 0, new Aircraft(),new StaticDiscreteEmpiricLandingDuration(), new StaticDiscreteEmpiricTimeBetweenArrival()));
        
        RunwayPrioritizer runwayPrioritizer = new RunwayPrioritizer();
        assertEquals(-1, runwayPrioritizer.compare(s1, s2));
    }

    // Caso 4: Server 1 y 2 están ocupados, pero Server 2 tiene menos fila
    @Test
    public void case4(){
        ServerCodeGenerator serverCodeGenerator = new ServerCodeGenerator();
        StaticNormalEventDuration staticNormalEventDuration = new StaticNormalEventDuration(5, 1, new StaticExponentialEventDuration(5), 0.2, 0.04);
        Server s1 = new Server(serverCodeGenerator, null, staticNormalEventDuration, 3000);
        s1.setEntity(new Aircraft());
        s1.getQueue().add(new Arrival(2, 0, new Aircraft(),new StaticDiscreteEmpiricLandingDuration(), new StaticDiscreteEmpiricTimeBetweenArrival()));
        Server s2 = new Server(serverCodeGenerator, null, staticNormalEventDuration, 3000);
        s2.setEntity(new Aircraft());
        
        RunwayPrioritizer runwayPrioritizer = new RunwayPrioritizer();
        assertEquals(1, runwayPrioritizer.compare(s1, s2));
    }

    // Caso 5: Server 1 y 2 tiene le mismo tamaño de fila, y los ordeno por su ID (Server 1 tiene menor ID que Server 2)
    @Test
    public void case5(){
        ServerCodeGenerator serverCodeGenerator = new ServerCodeGenerator();
        StaticNormalEventDuration staticNormalEventDuration = new StaticNormalEventDuration(5, 1, new StaticExponentialEventDuration(5), 0.2, 0.04);
        Server s1 = new Server(serverCodeGenerator, null, staticNormalEventDuration, 3000);
        s1.setEntity(new Aircraft());
        Server s2 = new Server(serverCodeGenerator, null, staticNormalEventDuration, 3000);
        s2.setEntity(new Aircraft());
        
        RunwayPrioritizer runwayPrioritizer = new RunwayPrioritizer();
        assertEquals(-1, runwayPrioritizer.compare(s1, s2));
    }

    // Caso 6: Server 1 y 2 tiene le mismo tamaño de fila, y los ordeno por su ID (Server 2 tiene menor ID que Server 1)
    @Test
    public void case6(){
        ServerCodeGenerator serverCodeGenerator = new ServerCodeGenerator();
        StaticNormalEventDuration staticNormalEventDuration = new StaticNormalEventDuration(5, 1, new StaticExponentialEventDuration(5), 0.2, 0.04);
        Server s2 = new Server(serverCodeGenerator, null, staticNormalEventDuration, 3000);
        s2.setEntity(new Aircraft());
        Server s1 = new Server(serverCodeGenerator, null, staticNormalEventDuration, 3000);
        s1.setEntity(new Aircraft());
        
        RunwayPrioritizer runwayPrioritizer = new RunwayPrioritizer();
        assertEquals(1, runwayPrioritizer.compare(s1, s2));
    }
}
