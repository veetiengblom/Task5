package com.example.week7task5;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;


public class MainActivity extends AppCompatActivity {

    String s;
    EditText userInput;
    EditText fileName;

    Context context = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        context = MainActivity.this;
        userInput = (EditText) findViewById(R.id.userInput);
        fileName = (EditText) findViewById(R.id.fileName);
        System.out.println("Kansion sijainti: " + context.getFilesDir());
    }

    public void readFile(View v) {
        try {
            InputStream ins = context.openFileInput(String.valueOf(fileName.getText()));

            BufferedReader br = new BufferedReader(new InputStreamReader(ins));
            String s = "";

            while ((s = br.readLine()) != null){
                userInput.setText(s);
            }
            ins.close();
        } catch (IOException e){
            Log.e("IOException", "Virhe syötteessä ReadFile");
        } finally {
            System.out.println("LUETTU");
        }
    }

    public void writeFile(View v) {
        try {
            s = fileName.getText().toString();
            OutputStreamWriter osw = new OutputStreamWriter(context.openFileOutput(s, MODE_PRIVATE));

            osw.write(String.valueOf(userInput.getText()));
            osw.close();
        } catch (IOException e){
            Log.e("IOException", "Virhe syötteessä WriteFile");
        } finally {
            System.out.println("KIRJOITETTU");
        }

    }
}
