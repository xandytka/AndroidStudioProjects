package com.example.datasocketappv3;

import androidx.appcompat.app.AppCompatActivity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutput;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        EditText edtHost = (EditText) findViewById(R.id.edtHost);
        EditText edtMessage = (EditText) findViewById((R.id.edtMessage));
    }

    public void btn_Escreve(View view){
        BackgroundTask b = new BackgroundTask();
        //b.execute(edtHost.getText().toString(), edtMessage.getText().toString());
        b.execute(((EditText) findViewById((R.id.edtHost))).getText().toString(), ((EditText) findViewById((R.id.edtMessage))).getText().toString());

    }

    public void btn_Clear(View view){
        ((EditText) findViewById((R.id.edtMessage))).setText("");
    }

    class BackgroundTask extends AsyncTask<String, Void, String> {
        Socket s;
        DataOutputStream dos;
        String ip, message;

        @Override
        protected String doInBackground(String... params) {
            ip = params[0];
            message = params[1];

            /*try {
                s = new Socket(ip, 8910); // Este bloco não funciona corretamente imprime caracteres antes da String message
                dos = new DataOutputStream(s.getOutputStream());
                dos.writeUTF(message);

                dos.close();
                s.close();

            } catch (IOException e) {
                e.printStackTrace();
            }*/

            try {
                s = new Socket(ip, 8910); // Bloco OK imprime corretamente
                dos = new DataOutputStream(s.getOutputStream());
                PrintWriter writer = new PrintWriter(dos, true);
                //writer.println(message); //imprime com "\n"
                writer.printf(message + "\r\n"); // imprime a string sem "\r\n"
                //writer.write(message); //não imprime nada NÃO FUNCIONA
                //writer.print(message);   //não imprime nada NÃO FUNCIONA

            } catch (IOException ex) {
                //System.out.println("Server exception: " + ex.getMessage());
                ex.printStackTrace();
            }

            return null;
        }

    }


}