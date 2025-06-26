package com.engine.entity;

/**
 * La implementación de esta clase sera la que se envie a Bootstrapping al iniciar la simulación, y sera del tipo de la entidad de la simulación
 * Implemente la clase con su entidad retornando una nueva instancia de su entidad.
 * */
public interface EntityFactory {
    
    /**
     * Retorna una nueva instancia 
     **/
    public Entity create();
}
