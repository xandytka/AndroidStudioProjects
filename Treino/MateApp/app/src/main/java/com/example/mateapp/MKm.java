package com.example.mateapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MKm extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_m_km);

        //Declaração dos componentes da Interface
        final EditText edtMprog = (EditText) findViewById(R.id.edtM);
        final EditText edtKmprog = (EditText) findViewById(R.id.edtKm);
        Button btnConverterprog = (Button) findViewById(R.id.btnConverter);

        //Declaração do evento do botão btnConverterprog
        btnConverterprog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                float km, m;
                m = Float.parseFloat(edtMprog.getText().toString());
                km = m / 1000;
                edtKmprog.setText(String.valueOf(km));
            }
        });

    }
}