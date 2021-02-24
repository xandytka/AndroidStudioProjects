package com.example.mateapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class KmM extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_km_m);

        //Declaração dos componentes da Interface KmM
        final EditText edtKmprog = (EditText) findViewById(R.id.edtKm);
        final EditText edtMprog = (EditText) findViewById(R.id.edtM);
        Button btnConverterprog = (Button) findViewById(R.id.btnConverter);

        //Declaração do evento do Botão btnConverterprog
        btnConverterprog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                float km, m;
                km = Float.parseFloat(edtKmprog.getText().toString());
                m = km * 1000;
                edtMprog.setText(String.valueOf(m));
            }
        });

    }
}