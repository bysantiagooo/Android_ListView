package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class DetailActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        TextView titulo = findViewById(R.id.tituloDetalle);
        TextView contenido = findViewById(R.id.contenidoDetalle);
        ImageView imagen = findViewById(R.id.imagenDetalle);
        Button btnVolver = findViewById(R.id.btnVolver);

        Intent i = getIntent();
        titulo.setText(i.getStringExtra("titulo"));
        contenido.setText(i.getStringExtra("contenido"));
        imagen.setImageResource(i.getIntExtra("imagen", 0));

        btnVolver.setOnClickListener(v -> finish());
    }
}
