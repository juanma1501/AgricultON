package Persistencia;

import android.content.ContentValues;
import android.content.Context;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class AdminSQLiteOpenHelper extends SQLiteOpenHelper{

    public AdminSQLiteOpenHelper(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase BD) {
        try {
            BD.execSQL("create table Usuarios (idUser INTEGER PRIMARY KEY AUTOINCREMENT, email text unique, contrasena text, nombre text, apellido text, fecha_nacimiento text, ultimoAcceso text, foto text)");
            BD.execSQL("create table Cultivos (idCultivo INTEGER PRIMARY KEY AUTOINCREMENT, nombre text unique, descripcion text, foto text)");
            BD.execSQL("create table Pasos (idPaso INTEGER PRIMARY KEY AUTOINCREMENT, idUser integer, pasos integer, fecha text)");
            insertarDatosPorDefecto(BD);
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void onUpgrade(SQLiteDatabase BD, int oldVersion, int newVersion) {
        try {
            /*Se elimina la versión anterior de la table*/
            BD.execSQL("DROP TABLE IF EXISTS Usuarios");
            BD.execSQL("DROP TABLE IF EXISTS Cultivos");
            BD.execSQL("DROP TABLE IF EXISTS Pasos");

            /*Se crea la nueva versión de la table*/
            BD.execSQL("create table Usuarios (idUser INTEGER PRIMARY KEY AUTOINCREMENT, email text unique, contrasena text, nombre text, apellido text, fecha_nacimiento text, ultimoAcceso text, foto text)");
            BD.execSQL("create table Cultivos (idCultivo INTEGER PRIMARY KEY AUTOINCREMENT, nombre text unique, descripcion text, foto text)");
            BD.execSQL("create table Pasos (idPaso INTEGER PRIMARY KEY AUTOINCREMENT, idUser integer, pasos integer, fecha text)");
            insertarDatosPorDefecto(BD);
        } catch (SQLException e){
            e.printStackTrace();
        }
    }

    private void insertarDatosPorDefecto(SQLiteDatabase BD) {
        // INTRODUCIMOS EN LA BBDD LOS CULTIVOS QUE VIENEN POR DEFECTO CON LA APP
        ContentValues values = new ContentValues();
        values.put("nombre", "Manzana");
        values.put("descripcion", "La manzana es el fruto comestible de la especie Malus domestica, llamada comúnmente manzano. Es una fruta pomácea de forma redonda y sabor más o menos dulce, dependiendo de la variedad." +
                "\n" +
                "Los manzanos se cultivan en todo el mundo y son las especies más cultivadas en el género Malus. El árbol se originó en Asia Central, donde su ancestro salvaje, Malus sieversii, todavía se encuentra hoy en día. Las manzanas se han cultivado durante miles de años en Asia y Europa y fueron traídas a América del Norte por colonos europeos. Las manzanas tienen un significado religioso y mitológico en muchas culturas, incluyendo la tradición nórdica, griega y cristiana europea." +
                "\n" +
                "Los manzanos son grandes si se cultivan a partir de semillas. Generalmente, los cultivares de manzana se propagan injertando en portainjertos, que controlan el tamaño del árbol resultante. Hay más de 7500 cultivares conocidos de manzanas, lo que resulta en una gama de características deseadas. Diferentes cultivares se crían para diversos gustos y uso, incluyendo cocinar, comer crudo y la producción de sidra. Los árboles y los frutos son propensos a una serie de problemas de hongos, bacterias y plagas, que pueden ser controlados por una serie de medios orgánicos y no orgánicos. En 2010, el genoma del fruto fue secuenciado como parte de la investigación sobre el control de enfermedades y la reproducción selectiva en la producción de manzanas.");
        values.put("foto", "apple");
        BD.insert("Cultivos", null, values);

        values = new ContentValues();
        values.put("nombre", "Naranja");
        values.put("descripcion", "La naranja es una fruta cítrica obtenida del naranjo dulce (Citrus × sinensis), del naranjo amargo (Citrus × aurantium) y de naranjos de otras variedades o híbridos, de origen asiático. Es un hesperidio carnoso de cáscara más o menos gruesa y endurecida, y su pulpa está formada típicamente por once gajos u hollejos llenos de jugo, el cual contiene mucha vitamina C, flavonoides y aceites esenciales. Se cultiva como un antiguo árbol ornamental y para obtener fragancias de sus frutos. Es más pequeña y dulce que el pomelo o toronja y más grande, aunque menos perfumada que la mandarina. Existen numerosas variedades de naranjas, siendo la mayoría híbridos producidos a partir de las especies Citrus maxima (pamplemusa), Citrus reticulata (mandarina) y Citrus medica (cidro).\n" +
                "\n" +
                "Según la FAO, en 2014 la fruticultura mundial produjo unos 71 millones de toneladas de este cítrico, una cuarta parte proveniente de Brasil y el resto de China, India, México, EE. UU., España, Egipto, Indonesia, Turquía y otros países.");
        values.put("foto", "oranges");
        BD.insert("Cultivos", null, values);

        values = new ContentValues();
        values.put("nombre", "Sandía");
        values.put("descripcion", "Es una planta herbácea de ciclo anual, trepadora o rastrera, de textura áspera, con tallos pilosos provistos de zarcillos y hojas de cinco lóbulos profundos. Las flores son amarillas, grandes y unisexuales, las femeninas tienen el gineceo con tres carpelos, y las masculinas con cinco estambres.\n" +
                "\n" +
                "El fruto de la planta es grande (normalmente más de 4 kilos), pepónide, carnoso y jugoso (más del 90% es agua), casi esférico, de textura lisa y sin porosidades, de color verde en dos o más tonos. La pulpa es de color rojo —por el antioxidante licopeno, también presente en los tomates— y de carne de sabor generalmente dulce (más raramente amarilla y amarga).\n" +
                "\n" +
                "Las numerosas semillas pueden llegar a medir 1 cm de longitud, son de color negro, marrón o blanco y ricas en vitamina E, se han utilizado en medicina popular y también se consumen tostadas como alimento.");
        values.put("foto", "watermelon");
        BD.insert("Cultivos", null, values);

        values = new ContentValues();
        values.put("nombre", "Fresa");
        values.put("descripcion", "Son plantas herbáceas, perennifolias, con rizomas y estolones epigeos más o menos desarrollados, que enraízan en los nudos donde nacen hojas arrosetadas tri-partidas. Los tallos son generalmente simples, más o menos erectos y anuales. Las hojas se agrupan en falsas rosetas, con los segmentos ovalo-rómbicos, distalmente dentados. Las inflorescencias se organizan en cimas con brácteas. Las flores, hermafroditas o funcionalmente unisexuales, tienen un receptáculo con la zona axial algo cónica, acrescente y carnosa en la fructificación. Los 5 sépalos son lanceolados y en general enteros, más o menos acrescentes, erectos, patentes o reflejos en la fructificación. El calículo tiene 5 piezas más estrechas que los sépalos y son usualmente enteros. Los pétalos, en general en número de 5, son habitualmente mayores que los sépalos; son obovados, no escotados, con la uña corta, blancos, blanco-verdosos o de color crema. Hay unos 10-20 estambres y numerosos carpelos libres implantados en la zona axial del receptáculo. El fruto es un poliaquenio de aquenios ovoides incrustados en dicho receptáculo (eterio) que se vuelve carnoso al madurar.");
        values.put("foto", "strawberry");
        BD.insert("Cultivos", null, values);

        values = new ContentValues();
        values.put("nombre", "Kiwi");
        values.put("descripcion", "Es una baya oval de unos 6 cm de largo, con piel delgada de color verde parduzco y densamente cubierta de unos pelillos rígidos y cortos de color marrón. La pulpa, firme hasta que madura completamente, es de color verde brillante jugosa y con diminutas semillas negras dispuestas en torno a un corazón blanquecino. Tiene un sabor subácido a bastante ácido, similar al de la grosella o la fresa. Suele ser un alérgeno frecuente, sobre todo su piel.");
        values.put("foto", "kiwi");
        BD.insert("Cultivos", null, values);

        values = new ContentValues();
        values.put("nombre", "Platano");
        values.put("descripcion", "Es un fruto con cualidades variables en tamaño, color y firmeza, alargado, generalmente curvado y carnoso, rico en almidón cubierto con una cáscara, que puede ser verde, amarilla, roja, púrpura o marrón cuando está madura. Los frutos crecen en piñas que cuelgan de la parte superior de la planta. Casi todos los plátanos en la actualidad son frutos estériles que no producen semillas fructificantes y provienen de dos especies silvestres: Musa acuminata y Musa balbisiana. El nombre científico de la mayoría de los plátanos cultivados es Musa × paradisiaca, el híbrido Musa acuminata × M. balbisiana, con distintas denominaciones var. o cultivares, dependiendo de su constitución genómica.\n" +
                "\n" +
                "Los plátanos, de los que se conocen más de 1000 variedades.");
        values.put("foto", "banana");
        BD.insert("Cultivos", null, values);

        values = new ContentValues();
        values.put("nombre", "Tomate");
        values.put("descripcion", "Es una planta herbácea anual o perenne. El tallo es erguido y cilíndrico en la planta joven; a medida que ésta crece, el tallo cae y se vuelve anguloso, presenta vellosidades en la mayor parte de sus órganos y glándulas que segregan una sustancia de color verde aromática, puede llegar a medir hasta 2,50 m, ramifica de forma abundante y tiene yemas axilares. Si al final del crecimiento todas las ramificaciones exhiben yemas reproductivas, estas se clasifican como de crecimiento determinado y si terminan con yemas vegetativas, son clasificadas como de crecimiento indeterminado.");
        values.put("foto", "tomate");
        BD.insert("Cultivos", null, values);

        values = new ContentValues();
        values.put("email", "test@gmail.com");
        values.put("contrasena", "1234");
        values.put("nombre", "Antonio");
        values.put("apellido", "López");
        values.put("fecha_nacimiento", "09/03/1990");
        values.put("ultimoAcceso", "22/04/2021");
        values.put("foto", "juanma");
        BD.insert("Usuarios", null, values);

        values = new ContentValues();
        values.put("idUser", 1);
        values.put("pasos", 1864);
        values.put("fecha", "22/04/2021");
        BD.insert("Pasos", null, values);

        values = new ContentValues();
        values.put("idUser", 1);
        values.put("pasos", 5589);
        values.put("fecha", "23/04/2021");
        BD.insert("Pasos", null, values);

        values = new ContentValues();
        values.put("idUser", 1);
        values.put("pasos", 3000);
        values.put("fecha", "25/04/2021");
        BD.insert("Pasos", null, values);


    }
}
