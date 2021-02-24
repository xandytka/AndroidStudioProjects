package com.example.contador2;

import androidx.appcompat.app.AppCompatActivity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //Declaração componentes
        TextView txtText1 = (TextView) findViewById(R.id.txtText1);
        ProgressBar pbTH1 = (ProgressBar) findViewById(R.id.pbTH1);
        //Button btnStart = (Button) findViewById(R.id.btnStart);

    }

    public void click_btnStart(View view) {
        new MyTask().execute(0,100);
    }


    private class MyTask extends AsyncTask<Integer, Integer, Boolean>{

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected Boolean doInBackground(Integer... params) {
            //Worker Thread
            for(int i = params[0]; i < params[1]; i++ ){
                try {
                    Thread.sleep(100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
            publishProgress(i);
            }
            return true;
        }

        @Override
        protected void onPostExecute(Boolean aBoolean) {
            super.onPostExecute(aBoolean);
        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            super.onProgressUpdate(values);
            ((TextView) findViewById(R.id.txtText1)).setText(String.valueOf(values[0]));
            ((ProgressBar) findViewById(R.id.pbTH1)).setProgress(values[0]);
        }



    }

}