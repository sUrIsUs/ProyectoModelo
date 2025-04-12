package com.bootstrapping.entity;

import com.bootstrapping.CodeGenerator;

public abstract class Entity {

    private int code;
    private int serverId;
    private EntityHistory entityHistory;
    
    public Entity(CodeGenerator codeGenerator) {
        this.code = codeGenerator.nextCode();
        this.serverId = 0;
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
