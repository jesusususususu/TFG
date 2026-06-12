package com.example.tfg;
import org.junit.Test;
import static org.junit.Assert.*;
import java.util.HashMap;
import java.util.Map;
public class AlgoritmoUnitTest {
    @Test
    public void verificacion_algoritmo_viabilidad_isCorrect() {
        // 1. Simular la despensa del usuario (miAlmacenMap)
        // El usuario tiene: 3 huevos y 500 gramos de harina
        Map<String, Double> miAlmacenMap = new HashMap<>();
        miAlmacenMap.put("huevos", 3.0);
        miAlmacenMap.put("harina", 500.0);
        // 2. Simular los requisitos de una receta concreta en este caso
        bizcocho
        // La receta pide: 2 huevos y 250 gramos de harina
        Map<String, Double> requisitosReceta = new HashMap<>();
        requisitosReceta.put("huevos", 2.0);
        requisitosReceta.put("harina", 250.0);
        // 3. Ejecutar la misma lógica matemática que la de nuestra aplicacion
        int coincidencias = 0;
        double puntos = 0;
        int totalIngredientesReq = requisitosReceta.size(); // Son 2
        ingredientes
        for (Map.Entry<String, Double> req : requisitosReceta.entrySet()) {
            String nombreReq = req.getKey();
            double cantidadNecesaria = req.getValue();
            if (miAlmacenMap.containsKey(nombreReq)) {
                coincidencias++;
                double cantidadTengo = miAlmacenMap.get(nombreReq);
                if (cantidadTengo >= cantidadNecesaria) {
                    puntos += 1.0; // Tiene suficiente de este ingrediente
                } else {
                    puntos += cantidadTengo / cantidadNecesaria;
                }
            }
        }
        double porcentajeViabilidad = puntos / totalIngredientesReq;
        // 4. Comprobaciones
        assertEquals(2, coincidencias);
        // Como tenemos suficiente cantidad de ambos ingredientes, los
        puntos acumulados deben ser 2.0
        assertEquals(2.0, puntos, 0.001);
        // El porcentaje de viabilidad debe ser del 100% (1.0)
        assertEquals(1.0, porcentajeViabilidad, 0.001);
    }
    @Test
    public void verificacion_exclusion_cero_coincidencias() {
        // Simular almacén con ingredientes que no sirven para una receta de
        repostería
        Map<String, Double> miAlmacenMap = new HashMap<>();
        miAlmacenMap.put("patatas", 4.0);
        miAlmacenMap.put("cebolla", 1.0);
        // Requisitos de la receta
        Map<String, Double> requisitosReceta = new HashMap<>();
        requisitosReceta.put("harina", 200.0);
        int coincidencias = 0;
        for (String req : requisitosReceta.keySet()) {
            if (miAlmacenMap.containsKey(req)) {
                coincidencias++;
            }
        }
        // Comprobamos que el contador de coincidencias es exactamente 0
        // Esto es lo que activa el "continue" en tu app para ocultar la receta
        assertEquals(0, coincidencias);
    }
}
