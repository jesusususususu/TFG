package com.example.tfg;

import java.util.ArrayList;
import java.util.List;

public class RecetasData {

    public static List<Receta> obtenerRecetas() {

        List<Receta> listaRecetas = new ArrayList<>();

        // TORTILLA FRANCESA
        Receta r1 = new Receta(
                "Tortilla francesa",
                "2 huevos\n5 g de sal\n5 ml de aceite de oliva",
                "1. Batir los huevos.\n" +
                        "2. Añadir la sal.\n" +
                        "3. Calentar una sartén con aceite.\n" +
                        "4. Cocinar la tortilla.",
                5,
                R.drawable.tortilla
        );

        r1.agregarIngredienteRequisito("huevos", 2);
        r1.agregarIngredienteRequisito("sal", 5);
        r1.agregarIngredienteRequisito("aceite de oliva", 5);

        listaRecetas.add(r1);

        // FILETE DE TERNERA
        Receta r2 = new Receta(
                "Filete de ternera",
                "300 g de carne de ternera\n5 g de sal\n10 ml de aceite de oliva",
                "1. Salar la carne.\n" +
                        "2. Calentar una sartén.\n" +
                        "3. Cocinar 4 minutos por cada lado.\n" +
                        "4. Servir caliente.",
                10,
                R.drawable.tortilla
        );

        r2.agregarIngredienteRequisito("carne de ternera", 300);
        r2.agregarIngredienteRequisito("sal", 5);
        r2.agregarIngredienteRequisito("aceite de oliva", 10);

        listaRecetas.add(r2);

        // PASTA CARBONARA
        Receta r3 = new Receta(
                "Pasta carbonara",
                "200 g de pasta\n100 g de bacon\n2 huevos\n30 g de queso rallado",
                "1. Cocer la pasta.\n" +
                        "2. Freír el bacon.\n" +
                        "3. Mezclar huevo y queso.\n" +
                        "4. Mezclar todo y servir.",
                20,
                R.drawable.tortilla
        );

        r3.agregarIngredienteRequisito("pasta", 200);
        r3.agregarIngredienteRequisito("bacon", 100);
        r3.agregarIngredienteRequisito("huevos", 2);
        r3.agregarIngredienteRequisito("queso rallado", 30);

        listaRecetas.add(r3);

        // HAMBURGUESA
        Receta r4 = new Receta(
                "Hamburguesa casera",
                "150 g de carne picada\n1 pan de hamburguesa\n20 g de queso",
                "1. Formar la hamburguesa.\n" +
                        "2. Cocinar a la plancha.\n" +
                        "3. Montar en el pan.\n" +
                        "4. Añadir queso.",
                15,
                R.drawable.tortilla
        );

        r4.agregarIngredienteRequisito("carne picada", 150);
        r4.agregarIngredienteRequisito("pan hamburguesa", 1);
        r4.agregarIngredienteRequisito("queso", 20);

        listaRecetas.add(r4);

        // ALBÓNDIGAS
        Receta r5 = new Receta(
                "Albóndigas",
                "500 g de carne picada\n2 huevos\n50 g de pan rallado\n5 g de sal",
                "1. Mezclar todos los ingredientes.\n" +
                        "2. Formar bolas.\n" +
                        "3. Freír hasta dorar.",
                30,
                R.drawable.tortilla
        );

        r5.agregarIngredienteRequisito("carne picada", 500);
        r5.agregarIngredienteRequisito("huevos", 2);
        r5.agregarIngredienteRequisito("pan rallado", 50);
        r5.agregarIngredienteRequisito("sal", 5);

        listaRecetas.add(r5);

        return listaRecetas;
    }
}