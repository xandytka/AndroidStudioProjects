package com.example.mateapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //Declaração do componente Button KmMt , MtKm
        Button btnKmMtprog = (Button) findViewById(R.id.btnKmMt);
        Button btnMtKmprog = (Button) findViewById(R.id.btnMtKm);
        //Declaração do evento do botão KmMt
        btnKmMtprog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Declaração do Intent para chamada de uma nova Activity
                Intent intent = new Intent(MainActivity.this, KmM.class);
                //chamada para Activity KmM
                startActivity(intent);

            }
        });
        //Declaração do evento do botão MtKm
        btnMtKmprog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Declaração do Intent para chamada de uma nova Activity
                Intent intent = new Intent(MainActivity.this, MKm.class);
                //chamdada para Activity MKm
                startActivity(intent);
            }
        });
    }
}