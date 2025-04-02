package com.bootstrapping;

public abstract class Entity {

    private int code;

    public Entity(CodeGenerator codeGenerator) {
        this.code = codeGenerator.nextCode();
    }

    public int getCode() {
        return this.code;
    }

}
