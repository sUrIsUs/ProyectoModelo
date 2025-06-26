package com.engine;

/**
 * Especifica la manera en la que se generará un código para la clase que lo requiera
**/
public interface CodeGenerator {
  
    /**
     * Especifica la secuencia de generación 
    */
    public int nextCode();

}
 