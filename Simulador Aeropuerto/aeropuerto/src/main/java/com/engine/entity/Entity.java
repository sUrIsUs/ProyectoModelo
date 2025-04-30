package com.engine.entity;

import com.engine.CodeGenerator;

/**
 * Clase de la cual debera extenderse para especificar el tipo de entidad/es particular de cada simulación
 */
public abstract class Entity {

    private int code;
    private int serverId;
    private EntityHistory entityHistory;
    
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
