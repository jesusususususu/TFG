package com.example.tfg;

import android.content.Context;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverter;
import androidx.room.TypeConverters;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Database(entities = {IngredienteAlmacen.class, Receta.class}, version = 7, exportSchema = false)
@TypeConverters({AppDatabase.Converters.class})
public abstract class AppDatabase extends RoomDatabase {

    private static volatile AppDatabase INSTANCE;

    public abstract IngredienteDao ingredienteDao();
    public abstract RecetaDao recetaDao();

    public static synchronized AppDatabase getInstance(Context context) {
        if (INSTANCE == null) {
            INSTANCE = Room.databaseBuilder(
                            context.getApplicationContext(),
                            AppDatabase.class,
                            "base-recetas"
                    )
                    .fallbackToDestructiveMigration()
                    .allowMainThreadQueries()
                    .build();

            INSTANCE.precargarRecetasSiEstaVacia();
        }
        return INSTANCE;
    }

    private void precargarRecetasSiEstaVacia() {
        RecetaDao dao = recetaDao();
        List<Receta> existentes = dao.obtenerTodas();
        if (existentes.isEmpty()) {

            // 1. TORTILLA FRANCESA
            Receta r1 = new Receta(
                    "Tortilla francesa",
                    "2 huevos\n5 g de sal\n5 ml de aceite de oliva",
                    "1. Batir los huevos.\n2. Añadir la sal.\n3. Calentar una sartén con aceite.\n4. Cocinar la tortilla.",
                    5,
                    R.drawable.tortilla
            );
            r1.agregarIngredienteRequisito("huevos", 2);
            r1.agregarIngredienteRequisito("sal", 5);
            r1.agregarIngredienteRequisito("aceite de oliva", 5);
            dao.insertar(r1);

            // 2. FILETE DE TERNERA
            Receta r2 = new Receta(
                    "Filete de ternera",
                    "300 g de carne de ternera\n5 g de sal\n10 ml de aceite de oliva",
                    "1. Salar la carne.\n2. Calentar una sartén.\n3. Cocinar 4 minutos por cada lado.\n4. Servir caliente.",
                    10,
                    R.drawable.tortilla
            );
            r2.agregarIngredienteRequisito("carne de ternera", 300);
            r2.agregarIngredienteRequisito("sal", 5);
            r2.agregarIngredienteRequisito("aceite de oliva", 10);
            dao.insertar(r2);

            // 3. PASTA CARBONARA
            Receta r3 = new Receta(
                    "Pasta carbonara",
                    "200 g de pasta\n100 g de bacon\n2 huevos\n30 g de queso rallado",
                    "1. Cocer la pasta.\n2. Freír el bacon.\n3. Mezclar huevo y queso.\n4. Mezclar todo y servir.",
                    20,
                    R.drawable.ensalada
            );
            r3.agregarIngredienteRequisito("pasta", 200);
            r3.agregarIngredienteRequisito("bacon", 100);
            r3.agregarIngredienteRequisito("huevos", 2);
            r3.agregarIngredienteRequisito("queso rallado", 30);
            dao.insertar(r3);

            // 4. HAMBURGUESA CASERA
            Receta r4 = new Receta(
                    "Hamburguesa casera",
                    "150 g de carne picada\n1 pan de hamburguesa\n20 g de queso",
                    "1. Formar la hamburguesa.\n2. Cocinar a la plancha.\n3. Montar en el pan.\n4. Añadir queso.",
                    15,
                    R.drawable.tortilla
            );
            r4.agregarIngredienteRequisito("carne picada", 150);
            r4.agregarIngredienteRequisito("pan hamburguesa", 1);
            r4.agregarIngredienteRequisito("queso", 20);
            dao.insertar(r4);

            // 5. ALBÓNDIGAS
            Receta r5 = new Receta(
                    "Albóndigas",
                    "500 g de carne picada\n2 huevos\n50 g de pan rallado\n5 g de sal",
                    "1. Mezclar todos los ingredientes.\n2. Formar bolas.\n3. Freír hasta dorar.",
                    30,
                    R.drawable.tortilla
            );
            r5.agregarIngredienteRequisito("carne picada", 500);
            r5.agregarIngredienteRequisito("huevos", 2);
            r5.agregarIngredienteRequisito("pan rallado", 50);
            r5.agregarIngredienteRequisito("sal", 5);
            dao.insertar(r5);

            Receta r6 = new Receta(
                    "Pechuga de pollo a la plancha",
                    "200 g de pechuga de pollo\n5 g de sal\n10 ml de aceite de oliva",
                    "1. Salar la pechuga.\n2. Calentar la plancha.\n3. Cocinar 5 minutos por cada lado.",
                    15,
                    R.drawable.tortilla
            );

            r6.agregarIngredienteRequisito("pechuga de pollo", 200);
            r6.agregarIngredienteRequisito("sal", 5);
            r6.agregarIngredienteRequisito("aceite de oliva", 10);

            dao.insertar(r6);

            Receta r7 = new Receta(
                    "Arroz blanco",
                    "200 g de arroz\n500 ml de agua\n5 g de sal",
                    "1. Hervir el agua.\n2. Añadir arroz y sal.\n3. Cocinar 18 minutos.",
                    20,
                    R.drawable.tortilla
            );

            r7.agregarIngredienteRequisito("arroz", 200);
            r7.agregarIngredienteRequisito("agua", 500);
            r7.agregarIngredienteRequisito("sal", 5);

            dao.insertar(r7);

            Receta r8 = new Receta(
                    "Ensalada mixta",
                    "100 g de lechuga\n2 tomates\n50 g de cebolla",
                    "1. Cortar los ingredientes.\n2. Mezclar y servir.",
                    10,
                    R.drawable.ensalada
            );

            r8.agregarIngredienteRequisito("lechuga", 100);
            r8.agregarIngredienteRequisito("tomates", 2);
            r8.agregarIngredienteRequisito("cebolla", 50);

            dao.insertar(r8);

            Receta r9 = new Receta(
                    "Huevos revueltos",
                    "3 huevos\n5 g de sal\n5 ml de aceite",
                    "1. Batir huevos.\n2. Cocinar removiendo constantemente.",
                    8,
                    R.drawable.tortilla
            );

            r9.agregarIngredienteRequisito("huevos", 3);
            r9.agregarIngredienteRequisito("sal", 5);
            r9.agregarIngredienteRequisito("aceite", 5);

            dao.insertar(r9);

            Receta r10 = new Receta(
                    "Sándwich mixto",
                    "2 rebanadas de pan\n2 lonchas de jamón\n2 lonchas de queso",
                    "1. Montar el sándwich.\n2. Tostar.",
                    5,
                    R.drawable.sandwich
            );

            r10.agregarIngredienteRequisito("pan", 2);
            r10.agregarIngredienteRequisito("jamon", 2);
            r10.agregarIngredienteRequisito("queso", 2);

            dao.insertar(r10);

            Receta r11 = new Receta(
                    "Macarrones con tomate",
                    "200 g de macarrones\n150 g de tomate frito",
                    "1. Cocer macarrones.\n2. Añadir tomate.",
                    20,
                    R.drawable.tortilla
            );

            r11.agregarIngredienteRequisito("macarrones", 200);
            r11.agregarIngredienteRequisito("tomate frito", 150);

            dao.insertar(r11);

            Receta r12 = new Receta(
                    "Tortilla de patatas",
                    "4 huevos\n300 g de patatas\n20 ml de aceite",
                    "1. Freír patatas.\n2. Mezclar con huevo.\n3. Cuajar.",
                    30,
                    R.drawable.tortilla
            );

            r12.agregarIngredienteRequisito("huevos", 4);
            r12.agregarIngredienteRequisito("patatas", 300);
            r12.agregarIngredienteRequisito("aceite", 20);

            dao.insertar(r12);

            Receta r13 = new Receta(
                    "Pizza casera",
                    "1 masa pizza\n100 g de queso\n100 g de tomate",
                    "1. Montar ingredientes.\n2. Hornear.",
                    25,
                    R.drawable.tortilla
            );

            r13.agregarIngredienteRequisito("masa pizza", 1);
            r13.agregarIngredienteRequisito("queso", 100);
            r13.agregarIngredienteRequisito("tomate", 100);

            dao.insertar(r13);

            Receta r14 = new Receta(
                    "Lentejas",
                    "250 g de lentejas\n100 g de chorizo",
                    "1. Cocer lentejas.\n2. Añadir chorizo.",
                    45,
                    R.drawable.tortilla
            );

            r14.agregarIngredienteRequisito("lentejas", 250);
            r14.agregarIngredienteRequisito("chorizo", 100);

            dao.insertar(r14);

            Receta r15 = new Receta(
                    "Salchichas al vino",
                    "300 g de salchichas\n100 ml de vino blanco",
                    "1. Dorar salchichas.\n2. Añadir vino y reducir.",
                    25,
                    R.drawable.tortilla
            );

            r15.agregarIngredienteRequisito("salchichas", 300);
            r15.agregarIngredienteRequisito("vino blanco", 100);

            dao.insertar(r15);

            Receta r16 = new Receta(
                    "Puré de patatas",
                    "500 g de patatas\n100 ml de leche",
                    "1. Cocer patatas.\n2. Triturar con leche.",
                    25,
                    R.drawable.tortilla
            );

            r16.agregarIngredienteRequisito("patatas", 500);
            r16.agregarIngredienteRequisito("leche", 100);

            dao.insertar(r16);

            Receta r17 = new Receta(
                    "Croquetas",
                    "200 g de jamon\n500 ml de leche\n100 g de harina",
                    "1. Preparar bechamel.\n2. Formar croquetas.\n3. Freír.",
                    60,
                    R.drawable.tortilla
            );

            r17.agregarIngredienteRequisito("jamon", 200);
            r17.agregarIngredienteRequisito("leche", 500);
            r17.agregarIngredienteRequisito("harina", 100);

            dao.insertar(r17);

            Receta r18 = new Receta(
                    "Filete empanado",
                    "200 g de filete\n1 huevo\n50 g de pan rallado",
                    "1. Empanar.\n2. Freír.",
                    20,
                    R.drawable.tortilla
            );

            r18.agregarIngredienteRequisito("filete", 200);
            r18.agregarIngredienteRequisito("huevo", 1);
            r18.agregarIngredienteRequisito("pan rallado", 50);

            dao.insertar(r18);

            Receta r19 = new Receta(
                    "Arroz a la cubana",
                    "200 g de arroz\n2 huevos\n100 g de tomate frito",
                    "1. Cocer arroz.\n2. Freír huevos.\n3. Servir con tomate.",
                    20,
                    R.drawable.tortilla
            );

            r19.agregarIngredienteRequisito("arroz", 200);
            r19.agregarIngredienteRequisito("huevos", 2);
            r19.agregarIngredienteRequisito("tomate frito", 100);

            dao.insertar(r19);

            Receta r20 = new Receta(
                    "Bocadillo de jamón",
                    "1 barra de pan\n100 g de jamon",
                    "1. Abrir el pan.\n2. Añadir el jamón.",
                    3,
                    R.drawable.tortilla
            );

            r20.agregarIngredienteRequisito("pan", 1);
            r20.agregarIngredienteRequisito("jamon", 100);

            dao.insertar(r20);


        }
    }

    public static class Converters {
        @TypeConverter
        public static Map<String, Double> fromString(String value) {
            Type listType = new TypeToken<HashMap<String, Double>>() {}.getType();
            return new Gson().fromJson(value, listType);
        }

        @TypeConverter
        public static String fromMap(Map<String, Double> map) {
            Gson gson = new Gson();
            return gson.toJson(map);
        }
    }
}