package com.example.tfg;
import org.junit.Test;
import static org.junit.Assert.*;
import java.util.HashMap;
import java.util.Map;
import com.example.tfg.Receta;
public class AlgoritmoUnitTest {
    @Test
   public void verificar_tiempo_positivo() {
        Receta nuevaReceta = new Receta("macarrones con queso","macarrones,queso","cocer los macarrones y meterles el queso",3,0);
    }

    @Test
    public void verificar_tiempo_negativo() {
        assertThrows(RuntimeException.class, () -> {
            new Receta(
                    "macarrones con queso",
                    "macarrones,queso",
                    "cocer los macarrones y meterles el queso",
                    -3,
                    0

            );
        });
    }

}
