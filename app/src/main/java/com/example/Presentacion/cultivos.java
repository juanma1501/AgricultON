package com.example.Presentacion;

import android.app.AlertDialog;
import android.content.Intent;
import android.graphics.drawable.Icon;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

import Dominio.Usuario;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link cultivos#newInstance} factory method to
 * create an instance of this fragment.
 */
public class cultivos extends Fragment {

    private GridView gridView;
    ArrayList<String> nombreProductos = new ArrayList<String>() {{add("Manzana"); add("Naranja"); add("Fresa"); add("Sandía"); add("Kiwi"); add("Plátano"); add("Tomate");}};
    ArrayList<Integer> imagenesProductos = new ArrayList<Integer>() {{add(R.drawable.apple); add(R.drawable.oranges); add(R.drawable.strawberry); add(R.drawable.watermelon); add(R.drawable.kiwi); add(R.drawable.banana); add(R.drawable.tomate);}};
    ArrayList<String> descripciones = new ArrayList<String>();
    private FloatingActionButton btnAnadir;
    private AlertDialog.Builder dialogBuilder;
    private AlertDialog dialog;
    private EditText new_nombre, new_descripcion;
    private Button guardarNew, CancelarNew;
    private Usuario usuario;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            this.usuario = (Usuario) getArguments().getSerializable("usuario");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_cultivos, container, false);


        initViews(view);
        descripciones = setDescripciones();

        CustomAdapter customAdapter = new CustomAdapter();
        gridView.setAdapter(customAdapter);
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int i, long id) {
                Intent intent = new Intent(getActivity().getApplicationContext(), grid_item.class);
                intent.putExtra("nombre", nombreProductos.get(i));
                intent.putExtra("descripcion", descripciones.get(i));
                startActivity(intent);
            }
        });

        btnAnadir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                createNewCultivoDialog();
            }
        });
        return view;
    }

    private void initViews(View view) {
        gridView = view.findViewById(R.id.gridview);
        btnAnadir = view.findViewById(R.id.btnAnadir);
    }

    public void createNewCultivoDialog(){
        dialogBuilder = new AlertDialog.Builder(this.getActivity());
        final View newCultivoPopupView = getLayoutInflater().inflate(R.layout.new_cultivo_popup, null);

        new_nombre = newCultivoPopupView.findViewById(R.id.newNombre);
        new_descripcion = newCultivoPopupView.findViewById(R.id.newDescripcion);
        guardarNew = newCultivoPopupView.findViewById(R.id.btnGuardarNew);
        CancelarNew = newCultivoPopupView.findViewById(R.id.btnCancelarNew);

        dialogBuilder.setView(newCultivoPopupView);
        dialog = dialogBuilder.create();
        dialog.show();

        guardarNew.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                //Define save button
            }
        });

        CancelarNew.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
    }

    private class CustomAdapter extends BaseAdapter {
        @Override
        public int getCount() {
            return nombreProductos.size();
        }

        @Override
        public Object getItem(int position) {
            return null;
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public View getView(int i, View view, ViewGroup viewGroup) {
            View view1 = getLayoutInflater().inflate(R.layout.activity_row_data, null);

            TextView name = view1.findViewById(R.id.productos);
            ImageView image = view1.findViewById(R.id.fotoproductos);
            btnAnadir = view1.findViewById(R.id.btnAnadir);

            name.setText(nombreProductos.get(i));
            image.setImageResource(imagenesProductos.get(i));

            return view1;
        }
    }

    private ArrayList<String> setDescripciones (){
        ArrayList<String> descripciones = new ArrayList<String>();

        //MANZANA
        descripciones.add("La manzana es el fruto comestible de la especie Malus domestica, llamada comúnmente manzano. Es una fruta pomácea de forma redonda y sabor más o menos dulce, dependiendo de la variedad.\n" +
                "\n" +
                "Los manzanos se cultivan en todo el mundo y son las especies más cultivadas en el género Malus. El árbol se originó en Asia Central, donde su ancestro salvaje, Malus sieversii, todavía se encuentra hoy en día. Las manzanas se han cultivado durante miles de años en Asia y Europa y fueron traídas a América del Norte por colonos europeos. Las manzanas tienen un significado religioso y mitológico en muchas culturas, incluyendo la tradición nórdica, griega y cristiana europea.\n" +
                "\n" +
                "Los manzanos son grandes si se cultivan a partir de semillas. Generalmente, los cultivares de manzana se propagan injertando en portainjertos, que controlan el tamaño del árbol resultante. Hay más de 7500 cultivares conocidos de manzanas, lo que resulta en una gama de características deseadas. Diferentes cultivares se crían para diversos gustos y uso, incluyendo cocinar, comer crudo y la producción de sidra. Los árboles y los frutos son propensos a una serie de problemas de hongos, bacterias y plagas, que pueden ser controlados por una serie de medios orgánicos y no orgánicos. En 2010, el genoma del fruto fue secuenciado como parte de la investigación sobre el control de enfermedades y la reproducción selectiva en la producción de manzanas.\n" +
                "\n" +
                "La producción mundial de manzanas en 2018 fue de 86 millones de toneladas, y China representa casi la mitad del total.");

        //NARANJA
        descripciones.add("La naranja es una fruta cítrica obtenida del naranjo dulce (Citrus × sinensis), del naranjo amargo (Citrus × aurantium) y de naranjos de otras variedades o híbridos, de origen asiático.1\u200B Es un hesperidio carnoso de cáscara 2\u200Bmás o menos gruesa y endurecida, y su pulpa está formada típicamente por once gajos u hollejos llenos de jugo, el cual contiene mucha vitamina C, flavonoides y aceites esenciales. Se cultiva como un antiguo árbol ornamental y para obtener fragancias de sus frutos. Es más pequeña y dulce que el pomelo o toronja y más grande, aunque menos perfumada que la mandarina. Existen numerosas variedades de naranjas, siendo la mayoría híbridos producidos a partir de las especies Citrus maxima (pamplemusa), Citrus reticulata (mandarina) y Citrus medica (cidro).\n" +
                "\n" +
                "Según la FAO, en 2014 la fruticultura mundial produjo unos 71 millones de toneladas de este cítrico, una cuarta parte proveniente de Brasil y el resto de China, India, México, EE. UU., España, Egipto, Indonesia, Turquía y otros países.");


        //FRESA
        descripciones.add("Fragaria, llamado comúnmente fresa o frutilla, es un género de plantas rastreras estoloníferas de la familia Rosaceae. Agrupa unos 400 taxones descritos, de los cuales solo unos 20 están aceptados. Son cultivadas por su fruto comestible (eterio) llamado de la misma manera, fresa o frutilla. Las variedades cultivadas comercialmente son por lo general híbridos, en especial Fragaria × ananassa, que ha reemplazado casi universalmente a las especies silvestres locales, como la eurasiática Fragaria vesca, por el superior tamaño de sus frutos.\n La fresa tiene un alto contenido de fibra, vitamina C, antioxidantes, potasio, ácido fólico y minerales. Es una de las frutas con un número menor de calorías. El consumo de esta fruta ayuda a mantener la piel hidratada; combate el estreñimiento debido a la fibra, evita enfermedades oculares, debido también a la vitamina C y los pigmentos como la luteína y la zeaxantina; o reduce problemas cardiovasculares.\n" +
                "\n" +
                "Se debe evitar el consumo a menores de 18 meses, por ser una fruta alergénica. Debido a un alto nivel de ácido oxálico puede estar contraindicada a personas con predisposición a padecer litiasis renal.");

        //SANDIA
        descripciones.add("Es una planta herbácea de ciclo anual, trepadora o rastrera, de textura áspera, con tallos pilosos provistos de zarcillos y hojas de cinco lóbulos profundos. Las flores son amarillas, grandes y unisexuales, las femeninas tienen el gineceo con tres carpelos, y las masculinas con cinco estambres.\n" +
                "\n" +
                "El fruto de la planta es grande (normalmente más de 4 kilos), pepónide, carnoso y jugoso (más del 90% es agua), casi esférico, de textura lisa y sin porosidades, de color verde en dos o más tonos. La pulpa es de color rojo —por el antioxidante licopeno, también presente en los tomates— y de carne de sabor generalmente dulce (más raramente amarilla y amarga).\n" +
                "\n" +
                "Las numerosas semillas pueden llegar a medir 1 cm de longitud, son de color negro, marrón o blanco y ricas en vitamina E, se han utilizado en medicina popular y también se consumen tostadas como alimento.");


        //KIWI
        descripciones.add("Es una baya oval de unos 6 cm de largo, con piel delgada de color verde parduzco y densamente cubierta de unos pelillos rígidos y cortos de color marrón. La pulpa, firme hasta que madura completamente, es de color verde brillante jugosa y con diminutas semillas negras dispuestas en torno a un corazón blanquecino. Tiene un sabor subácido a bastante ácido, similar al de la grosella o la fresa.\n Para la conservación de los frutos en estado fresco, se requiere someterlos a una temperatura de 0 °C, con humedad relativa de 90-95 %, suplementadas con atmósferas controladas u otras tecnologías. El metilciclopropeno favorece la conservación de la firmeza del fruto, ya sea que el tratamiento se realice antes de la introducción en cámara fría, ya sea que se efectúe durante el período de conservación. Esto se debe a que el kiwi es muy sensible a la presencia del etileno, y el 1-metilciclopropeno inhibe la percepción del etileno por parte de fruto.");


        //PLATANO
        descripciones.add("Es un fruto con cualidades variables en tamaño, color y firmeza, alargado, generalmente curvado y carnoso, rico en almidón cubierto con una cáscara, que puede ser verde, amarilla, roja, púrpura o marrón cuando está madura. Los frutos crecen en piñas que cuelgan de la parte superior de la planta. Casi todos los plátanos en la actualidad son frutos estériles que no producen semillas fructificantes y provienen de dos especies silvestres: Musa acuminata y Musa balbisiana. El nombre científico de la mayoría de los plátanos cultivados es Musa × paradisiaca, el híbrido Musa acuminata × M. balbisiana, con distintas denominaciones var. o cultivares, dependiendo de su constitución genómica.\n" +
                "\n" +
                "Los plátanos, de los que se conocen más de 1000 variedades, proporcionan alimento a grandes poblaciones humanas en dos formas principales:\n" +
                "\n" +
                "Plátanos de postre o dulces, para comer principalmente crudos, con gran parte de su fécula convertida en azúcar, destacando la variedad cavendish, que representa aproximadamente el 47% de la producción mundial.\n" +
                "Plátanos de cocinar o de guisar, más grandes, se comen cocinados de formas diversas, con diferentes variedades como el plátano macho o el pisang awak en Asia. Se suelen consumir hervidos, asados o fritos, independientemente de si están maduros o no.");


        //TOMATE
        descripciones.add("Es una planta herbácea anual o perenne. El tallo es erguido y cilíndrico en la planta joven; a medida que ésta crece, el tallo cae y se vuelve anguloso, presenta vellosidades en la mayor parte de sus órganos y glándulas que segregan una sustancia de color verde aromática, puede llegar a medir hasta 2,50 m, ramifica de forma abundante y tiene yemas axilares. Si al final del crecimiento todas las ramificaciones exhiben yemas reproductivas, estas se clasifican como de crecimiento determinado y si terminan con yemas vegetativas, son clasificadas como de crecimiento indeterminado\n Es una planta de clima relativamente cálido, para el tomate, las temperaturas óptimas según el ciclo de vida son: temperaturas nocturnas entre 15 y 18 ºC, temperaturas diurnas 24 a 25 ºC, con temperatura ideal en la floración de 21 ºC. El tomate es clasificado dentro de las hortalizas tolerantes al calor, temperaturas menores de 8 ºC detienen su crecimiento. La planta de tomate se desarrolla mejor con alta intensidad luminosa. La exigencia del tomate en cuanto a la humedad del suelo es media, el exceso de humedad provoca la el ataque de diferentes patógenos, además influye en el crecimiento de los tejidos, transpiración, fecundación de las flores y desarrollo de las enfermedades criptogámicas. Por otro lado, humedad relativa inferiores al 60–65 % causa la desecación del polen");

        return descripciones;
    }
}