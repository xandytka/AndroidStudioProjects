package com.example.contador;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;


public class MainActivity extends AppCompatActivity {

    ProgressBar pbTH1;
    Thread th1;

    /** Called when the activity is first created. */

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        pbTH1 = (ProgressBar) findViewById(R.id.pbTH1);

    }

    public void startProgress(View view) {

        pbTH1.setProgress(0);
        //new Thread(new Task()).start();
        th1 = new Thread(new Task());
        th1.start();
    }
    public void stopProgress(View view){
        //th1.stop();  obsoleto

    }

    class Task implements Runnable {
        @Override
        public void run() {
            for (int i = 0; i <= 100; i++) {
                final int value = i;
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                pbTH1.setProgress(value);
                //((TextView) findViewById(R.id.txtText1)).setText(String.valueOf(i));
                //android.view.ViewRootImpl$CalledFromWrongThreadException:
                // Only the original thread that created a view hierarchy can touch its views.
                //Codigo acima gera erro
            }
        }

    }

}