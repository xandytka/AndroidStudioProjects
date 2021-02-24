package com.example.testeutf8;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //componentes
        final TextView txtText1 = (TextView) findViewById(R.id.txtText1);
        final TextView txtText2 = (TextView) findViewById(R.id.txtText2);
        TextView txtText3 = (TextView) findViewById(R.id.txtText3);
        Button btnTeste = (Button) findViewById(R.id.btnTeste);

        final String texto1 = "餉の匂いが漂っていた";
        String texto2 = "Hello World";

        btnTeste.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                txtText1.setText(texto1);
                txtText2.setText(txtText1.getText());
            }
        });

    }



}