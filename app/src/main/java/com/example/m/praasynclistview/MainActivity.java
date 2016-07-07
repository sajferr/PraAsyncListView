package com.example.m.praasynclistview;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    String[] imiona ={"Marcin","Alojzy","Ryszard","Wilczok"};
    ListView lista;
    ArrayAdapter array ;
    ProgressBar progress;
    int count = 10;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        lista=(ListView)findViewById(R.id.listView);
        progress=(ProgressBar)findViewById(R.id.progressBar);
        array = new ArrayAdapter(getApplicationContext(),R.layout.simply_row,R.id.textView,new ArrayList());
        lista.setAdapter(array);
        new Async().execute();
    }
















    public class Async extends AsyncTask<Void, String, String> {
        ArrayAdapter adapterr;
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
             adapterr = (ArrayAdapter<String>)lista.getAdapter();
            progress.setMax(40);



        }

        @Override
        protected String doInBackground(Void... params) {
            for(String item:imiona){
                publishProgress(item);
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }


            }

            return "Gotowe";
        }

        @Override
        protected void onProgressUpdate(String...values) {
            super.onProgressUpdate(values);
            adapterr.add(values[0]);
            progress.setProgress(count);
            count+=10;
        }

        @Override
        protected void onPostExecute(String aVoid) {
            super.onPostExecute(aVoid);
            Toast.makeText(getApplicationContext(),aVoid,Toast.LENGTH_LONG).show();
        }
    }


}
