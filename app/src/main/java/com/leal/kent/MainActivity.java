package com.leal.kent;



import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class MainActivity extends AppCompatActivity {

    EditText eFname, eGender, eAge;
    TextView tMsg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        eFname = findViewById(R.id.inputName);
        eGender = findViewById(R.id.inputGender);
        eAge = findViewById(R.id.inputAge);
        tMsg = findViewById(R.id.displayText);
    }

    public void saveInfo (View v){
        FileOutputStream fos = null;
        try {
            fos = openFileOutput("data2.txt", Context.MODE_PRIVATE);
            String fname = eFname.getText().toString() + "\n";
            String age = eAge.getText().toString() + "\n";
            String gender = eGender.getText().toString() + "\n";
            fos.write(fname.getBytes());
            fos.write(age.getBytes());
            fos.write(gender.getBytes());
            Toast.makeText(this, "Data saved...", Toast.LENGTH_LONG).show();
        } catch (Exception e) {
            Toast.makeText(this, "Error writing data...", Toast.LENGTH_LONG).show();
        }finally {
            try {
                fos.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void displayInfo(View v) {
        try {
            FileInputStream fin = openFileInput("data2.txt" );
            int c;
            StringBuffer buffer = new StringBuffer();
            while ((c = fin.read())!= -1) {
                buffer.append((char) c);
            }
            String message = "" + buffer;
            tMsg.setText(message);
        } catch (Exception e) {
            Toast.makeText(this, "Error reading...", Toast.LENGTH_LONG).show();
        }
    }
}