package com.bootstrapping;

public abstract class Entity {

    public static double totalWaitingTime;

    private int code;

    public Entity(CodeGenerator codeGenerator) {
        this.code = codeGenerator.nextCode();
    }

    public int getCode() {
        return this.code;
    }

}
