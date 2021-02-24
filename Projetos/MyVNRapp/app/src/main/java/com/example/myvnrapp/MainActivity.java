package com.example.myvnrapp;

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
import java.io.CharArrayWriter;
import java.io.DataInputStream;
import java.io.DataOutput;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.Reader;
import java.io.StringReader;
import java.io.Writer;
import java.net.ServerSocket;
import java.net.Socket;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        //Componentes
        TextView txtFrom = (TextView) findViewById(R.id.txtFrom);
        TextView txtToTrans = (TextView) findViewById(R.id.txtToTrans);

        //Thread Server
        Thread myThread = new Thread(new MyServer());
        myThread.start();





    }

    public void Traduz(){

        // Create an English-German translator:
        TranslatorOptions options =
                new TranslatorOptions.Builder()
                        .setSourceLanguage(TranslateLanguage.ENGLISH)
                        .setTargetLanguage(TranslateLanguage.PORTUGUESE)
                        .build();
        final Translator MyTranslator =
                Translation.getClient(options);

        DownloadConditions conditions = new DownloadConditions.Builder()
                .requireWifi()
                .build();
        MyTranslator.downloadModelIfNeeded(conditions)
                .addOnSuccessListener(
                        new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void v) {
                                // Model downloaded successfully. Okay to start translating.
                                // (Set a flag, unhide the translation UI, etc.)
                            }
                        })
                .addOnFailureListener(
                        new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                // Model couldn’t be downloaded or other internal error.
                                // ...
                            }
                        });
        String alex;
        alex = ((TextView) findViewById((R.id.txtFrom))).getText().toString();
        MyTranslator.translate(alex)
                .addOnSuccessListener(
                        new OnSuccessListener<String>() {
                            @Override
                            public void onSuccess(@NonNull String translatedText) {
                                // Translation successful.
                                ((TextView) findViewById((R.id.txtToTrans))).setText(translatedText.toString());
                                String bbb = "123";
                                BackgroundTask b = new BackgroundTask();
                                //b.execute(edtHost.getText().toString(), edtMessage.getText().toString());
                                //b.execute(((EditText) findViewById((R.id.edtHost))).getText().toString(), ((EditText) findViewById((R.id.edtMessage))).getText().toString());
                                b.execute("192.168.0.12", ((TextView) findViewById((R.id.txtToTrans))).getText().toString());
                            }
                        })
                .addOnFailureListener(
                        new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                // Error.
                                // ...
                            }
                        });

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
                    Traduz();



                }
            }catch (IOException e){
                e.printStackTrace();
            }


        }
    }

    class BackgroundTask extends AsyncTask<String, Void, String> {
        Socket s;
        DataOutputStream dos;
        String ip ="";
        String message = "";

        @Override
        protected String doInBackground(String... params) {
            ip = params[0];
            message = params[1];

            try {
                s = new Socket(ip, 8910); // Bloco OK imprime corretamente
                dos = new DataOutputStream(s.getOutputStream());
                PrintWriter writer = new PrintWriter(dos, true);

                writer.printf(message + "\r\n"); // imprime a string sem "\r\n"

            } catch (IOException e) {
                e.printStackTrace();
                }

            return null;
        }

    }




}