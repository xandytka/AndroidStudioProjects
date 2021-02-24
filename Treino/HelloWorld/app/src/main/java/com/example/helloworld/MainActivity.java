package com.example.helloworld;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //Declaração dos componentes
        final EditText edtText1 = (EditText) findViewById(R.id.edtText1);
        final TextView txtText1 = (TextView) findViewById(R.id.txtText1);
        final TextView txtText2 = (TextView) findViewById(R.id.txtText2);
        Button btnButton1 = (Button) findViewById(R.id.btnButton1);
        Button btnButton2 = (Button) findViewById(R.id.btnButton2);
        Button btnButton3 = (Button) findViewById(R.id.btnButton3);

        //Declaração do evento do Botão btnButton1
        btnButton1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                txtText1.setText(edtText1.getText());
            }
        });

        //Declaração do evento do Botão btnButton2
        btnButton2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                edtText1.setText("");
                txtText1.setText("");
            }
        });

        //Declaração do evento do Botão btnButton3
        btnButton3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int aa = 40;
                int bb = 30;
                Quadrado caixap = new Quadrado();
                caixap.setLado_a(aa);
                caixap.setLado_b(bb);
                txtText2.setText(String.valueOf(caixap.MostraArea()));
            }
        });

    }
}