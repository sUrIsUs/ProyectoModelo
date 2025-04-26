package com.bootstrapping.distribution;

import com.bootstrapping.Randomizer;

/**
 * Utiliza la técnia de la tranformada inversa para generar un tiempo de distribución
 * Ej. Tiempo entre arribo, duración de servicio.
 */
public interface Distribution {
    public double generateTime(Randomizer randomizer);
}
