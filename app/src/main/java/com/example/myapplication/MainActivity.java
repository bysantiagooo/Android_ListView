package com.example.myapplication;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private ListView lista;
    private TextView texto;
    private RadioButton radioButton_pulsado = null;

    private ArrayList<Encapsulador> datos = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        lista = findViewById(R.id.lista);
        texto = findViewById(R.id.texto);

        datos.add(new Encapsulador(R.drawable.gen1, "Primera Generación", "Kanto", false));
        datos.add(new Encapsulador(R.drawable.gen2, "Segunda Generación", "Johto", false));
        datos.add(new Encapsulador(R.drawable.gen3, "Tercera Generación", "Hoenn", false));
        datos.add(new Encapsulador(R.drawable.gen4, "Cuarta Generación", "Sinnoh", false));
        datos.add(new Encapsulador(R.drawable.gen5, "Quinta Generación", "Teselia", false));
        datos.add(new Encapsulador(R.drawable.gen6, "Sexta Generación", "Kalos", false));
        datos.add(new Encapsulador(R.drawable.gen7, "Séptima Generación", "Alola", false));
        datos.add(new Encapsulador(R.drawable.gen8, "Octava Generación", "Galar", false));
        datos.add(new Encapsulador(R.drawable.gen9, "Novena Generación", "Paldea", false));

        lista.setAdapter(new Adaptador(this, R.layout.entrada, datos) {

            @Override
            public void onEntrada(Object entrada, View view) {
                if (entrada != null) {

                    Encapsulador item = (Encapsulador) entrada;

                    TextView texto_superior_entrada = (TextView) view.findViewById(R.id.textotitulo);
                    TextView texto_inferior_entrada = (TextView) view.findViewById(R.id.textodatos);
                    ImageView imagen_entrada = (ImageView) view.findViewById(R.id.imagen);
                    RadioButton miRadio = (RadioButton) view.findViewById(R.id.boton);

                    texto_superior_entrada.setText(item.get_textoTitulo());
                    texto_inferior_entrada.setText(item.get_textoContenido());
                    imagen_entrada.setImageResource(item.get_idImagen());
                    miRadio.setChecked(item.get_checkBox1());

                    miRadio.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            if (radioButton_pulsado != null)
                                radioButton_pulsado.setChecked(false);

                            radioButton_pulsado = miRadio;

                            texto.setText("MARCADA UNA OPCIÓN");

                        }
                    });
                }
            }
        });
    }

    public static class Encapsulador {

        private int imagen;
        private String titulo;
        private String texto;
        private boolean dato1;

        public Encapsulador(int idImagen, String textoTitulo, String textoContenido, boolean favorito) {
            this.imagen = idImagen;
            this.titulo = textoTitulo;
            this.texto = textoContenido;
            this.dato1 = favorito;
        }

        public String get_textoTitulo() {
            return titulo;
        }

        public String get_textoContenido() {
            return texto;
        }

        public int get_idImagen() {
            return imagen;
        }

        public boolean get_checkBox1() {
            return dato1;
        }

        public void set_checkBox1(boolean favorito) {
            this.dato1 = favorito;
        }
    }

    public abstract static class Adaptador extends BaseAdapter {

        private ArrayList<?> entradas;
        private int R_layout_IdView;
        private Context contexto;

        public Adaptador(Context contexto, int R_layout_IdView, ArrayList<?> entradas) {
            super();
            this.contexto = contexto;
            this.entradas = entradas;
            this.R_layout_IdView = R_layout_IdView;
        }

        public int getCount() {
            return entradas.size();
        }

        public Object getItem(int posicion) {
            return entradas.get(posicion);
        }

        public long getItemId(int posicion) {
            return posicion;
        }

        public View getView(int posicion, View view, ViewGroup pariente) {
            if (view == null) {
                LayoutInflater vi = (LayoutInflater) contexto.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                view = vi.inflate(R_layout_IdView, null);
            }
            onEntrada(entradas.get(posicion), view);
            return view;
        }

        public abstract void onEntrada(Object entrada, View view);
    }
}