package com.engine.entity;

import com.engine.CodeGenerator;

/**
 * <p> Clase de la cual debera extenderse para especificar el tipo de entidad/es particular de cada simulación. Para luego implementar la clase EntityFactory. <\p>
 */
public abstract class Entity {

    private final int code;
    private int serverId;
    private final EntityHistory entityHistory;
    
    /**
     * Al crear una entidad se deberá especificar un generador de código
    */
    public Entity(CodeGenerator codeGenerator) {
        this.code = codeGenerator.nextCode();
        this.serverId = 0;
        this.entityHistory = new EntityHistory();
    }
    
    public int getCode() {
        return this.code;
    }
    
    public int getServerId() {
        return serverId;
    }
    
    public void setServerId(int serverId) {
        this.serverId = serverId;
    }
    
    public EntityHistory getEntityHistory() {
        return entityHistory;
    }
    
}
