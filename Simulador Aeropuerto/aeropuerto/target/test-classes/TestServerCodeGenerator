import static org.junit.Assert.assertEquals;

import org.junit.Test;

import com.engine.server.ServerCodeGenerator;

public class TestServerCodeGenerator {
    //Caso unico: debe retornar codigos en secuencia
    @Test
    public void codeGenerator(){
        ServerCodeGenerator serverCodeGenerator = new ServerCodeGenerator();
        assertEquals(1, serverCodeGenerator.nextCode());
        assertEquals(2, serverCodeGenerator.nextCode());
    }
}
