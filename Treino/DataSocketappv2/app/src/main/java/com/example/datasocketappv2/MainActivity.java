package com.example.datasocketappv2;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.mlkit.common.model.DownloadConditions;
import com.google.mlkit.nl.translate.TranslateLanguage;
import com.google.mlkit.nl.translate.Translation;
import com.google.mlkit.nl.translate.Translator;
import com.google.mlkit.nl.translate.TranslatorOptions;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.DataInputStream;
import java.io.DataOutput;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //Componentes
        TextView txtFrom = (TextView) findViewById(R.id.txtFrom);

        //Thread Server
        Thread myThread = new Thread(new MyServer());
        myThread.start();

    }
    class MyServer implements Runnable {
        ServerSocket ss;
        Socket mysocket;
        InputStream input;
        BufferedReader reader;
        String message;
        Handler handler = new Handler();

        @Override
        public void run() {
            try{
                ss = new ServerSocket(8911);
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        //Toast.makeText(getApplicationContext(),"wait for client", Toast.LENGTH_SHORT).show();
                        ((TextView) findViewById((R.id.txtFrom))).setText("wait for client");
                    }
                });

                while(true){
                    mysocket = ss.accept();
                    input = mysocket.getInputStream();
                    reader = new BufferedReader(new InputStreamReader(input));
                    message = reader.readLine();

                    handler.post(new Runnable() {
                        @Override
                        public void run() {
                            //Toast.makeText(getApplicationContext(),"msg receive from client:" + message, Toast.LENGTH_SHORT).show();
                            ((TextView) findViewById((R.id.txtFrom))).setText(message);
                        }
                    });
                    //Traduz();



                }
            }catch (IOException e){
                e.printStackTrace();
            }


        }
    }


}