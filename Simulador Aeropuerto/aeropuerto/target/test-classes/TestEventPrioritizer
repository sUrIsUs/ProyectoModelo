import static org.junit.Assert.assertEquals;

import org.junit.Test;

import com.aeropuerto.scenario.airportEntity.Aircraft;
import com.aeropuerto.scenario.distribution.StaticDiscreteEmpiricLandingDuration;
import com.aeropuerto.scenario.distribution.StaticDiscreteEmpiricTimeBetweenArrival;
import com.engine.comparators.EventPrioritizer;
import com.engine.events.Arrival;
import com.engine.events.Departure;

public class TestEventPrioritizer {

    
    // Caso 1: Evento 1 tiene menor clock que el Evento 2
    @Test
    public void case1() {
        
        Arrival a1 = new Arrival(2, 0, new Aircraft(), new StaticDiscreteEmpiricLandingDuration(), new StaticDiscreteEmpiricTimeBetweenArrival());
        Arrival a2 = new Arrival(2, 1, new Aircraft(),new StaticDiscreteEmpiricLandingDuration(), new StaticDiscreteEmpiricTimeBetweenArrival());
        EventPrioritizer eventPrioritizer = new EventPrioritizer();
        assertEquals(-1, eventPrioritizer.compare(a1, a2));
    }

    // Caso 2: Evento 2 tiene menor clock que el Evento 1
    @Test
    public void case2() {
        EventPrioritizer eventPrioritizer = new EventPrioritizer();
        assertEquals(1,
            eventPrioritizer.compare(new Arrival(2, 1, new Aircraft(), new StaticDiscreteEmpiricLandingDuration(), new StaticDiscreteEmpiricTimeBetweenArrival()), new Arrival(2, 0, new Aircraft(),new StaticDiscreteEmpiricLandingDuration(), new StaticDiscreteEmpiricTimeBetweenArrival())));
    }

    // Caso 3: Ambos eventos tienen el mismo clock, y Evento 1 es del tipo
    // prioritario
    @Test
    public void case3() {
        EventPrioritizer eventPrioritizer = new EventPrioritizer();
        assertEquals(-1,
            eventPrioritizer.compare(new Departure(0, 0, new Aircraft(), new StaticDiscreteEmpiricLandingDuration()), new Arrival(2, 0, new Aircraft(),new StaticDiscreteEmpiricLandingDuration(), new StaticDiscreteEmpiricTimeBetweenArrival())));
    }

    // Caso 4: Ambos eventos tienen el mismo clock, y Evento 2 es del tipo
    // prioritario
    @Test
    public void case4() {
        EventPrioritizer eventPrioritizer = new EventPrioritizer();
        assertEquals(1,
            eventPrioritizer.compare(new Arrival(2, 0, new Aircraft(), new StaticDiscreteEmpiricLandingDuration(), new StaticDiscreteEmpiricTimeBetweenArrival()), new Departure(0, 0, new Aircraft(), new StaticDiscreteEmpiricLandingDuration())));
    }

}
