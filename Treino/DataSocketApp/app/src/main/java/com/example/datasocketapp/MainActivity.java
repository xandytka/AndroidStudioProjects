package com.example.datasocketapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutput;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //declaração dos componentes
        final EditText edtHost = (EditText) findViewById(R.id.edtHost);
        final EditText edtMessage = (EditText) findViewById((R.id.edtMessage));
        Button btnSend = (Button) findViewById(R.id.btnSend);

        //Declaração do evento bootao btnSend
        btnSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                BackgroundTask b = new BackgroundTask();
                b.execute(edtHost.getText().toString(), edtMessage.getText().toString());
            }
        });
        //Declaração da Thread do Servidor
        Thread myThread = new Thread(new MyServer());
        myThread.start();
    }

    class MyServer implements Runnable {
        ServerSocket ss;
        Socket mysocket;
        DataInputStream dis;
        //BufferedReader bufferedReader;
        String message;
        Handler handler = new Handler();

        @Override
        public void run() {
            try{
                ss = new ServerSocket(8910);
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(getApplicationContext(),"wait for client", Toast.LENGTH_SHORT).show();
                    }
                });

                while(true){
                    mysocket = ss.accept();
                    dis = new DataInputStream(mysocket.getInputStream());
                    message = dis.readUTF();
                    handler.post(new Runnable() {
                        @Override
                        public void run() {
                            Toast.makeText(getApplicationContext(),"msg receive from client:" + message, Toast.LENGTH_SHORT).show();
                        }
                    });
                }
            }catch (IOException e){
                    e.printStackTrace();
                    }


        }
    }

    class BackgroundTask extends AsyncTask<String, Void, String> {
        Socket s;
        DataOutputStream dos;
        String ip, message;

        @Override
        protected String doInBackground(String... params) {
            ip = params[0];
            message = params[1];

            try {
                s = new Socket(ip, 8910);
                dos = new DataOutputStream(s.getOutputStream());
                dos.writeUTF(message);

                dos.close();

                s.close();

            } catch (IOException e) {
                e.printStackTrace();
            }

            return null;
        }

    }

}


