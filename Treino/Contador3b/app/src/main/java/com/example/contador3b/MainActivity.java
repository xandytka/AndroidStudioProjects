package com.example.contador3b;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        TextView txtText1 = (TextView) findViewById(R.id.txtText1);
    }

    public void click_btnStart(View view){
        // codigo botao
        HowHandlersWorks();
    }

    int i = 0;
    private void HowHandlersWorks(){
        new Thread(new Runnable() {
            @Override
            public void run() {
            for (i = 0; i < 100; i++){
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                new Handler(Looper.getMainLooper()).post(new Runnable() {
                    @Override
                    public void run() {
                        ((TextView) findViewById(R.id.txtText1)).setText(String.valueOf(i));
                        ((ProgressBar) findViewById(R.id.pbTH1)).setProgress(i);
                    }
                });
            }
            }
        }).start();
    }

}