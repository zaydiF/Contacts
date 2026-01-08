package com.example.it_proger;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;

public class MainActivity extends AppCompatActivity {

    private EditText user_name_field, user_bio_field;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        user_name_field = findViewById(R.id.user_name_field);
        user_bio_field = findViewById(R.id.user_bio_field);
    }

    public void saveData(View view){
        String user_name = user_name_field.getText().toString();
        String user_bio = user_bio_field.getText().toString();

        try {
            FileOutputStream fileOutput = openFileOutput("user_data.txt", MODE_PRIVATE);
            fileOutput.write((user_name + ". " + user_bio).getBytes());
            fileOutput.close();

            user_name_field.setText("");
            user_bio_field.setText("");
            Toast.makeText(this, "Текст сохранён", Toast.LENGTH_SHORT).show();
        } catch (FileNotFoundException e) {
            Toast.makeText(this, "Не можем обработать файл", Toast.LENGTH_SHORT).show();
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void getData(View view){
        try {
            FileInputStream fileInput = openFileInput("user_data.txt");
            InputStreamReader reader = new InputStreamReader(fileInput);
            BufferedReader bR = new BufferedReader(reader);

            StringBuffer stringBuffer = new StringBuffer();
            String lines = "";
            while((lines = bR.readLine()) != null){
                stringBuffer.append(lines +"\n");
            }

            Toast.makeText(this, stringBuffer, Toast.LENGTH_LONG).show();
        }  catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public  void goContacts(View view){
        Intent intent = new Intent(this, ContactsActivity2.class);
        startActivity(intent);
    }

    public  void goWeb(View view){
        Intent intent = new Intent(this, WebActivity.class);
        startActivity(intent);
    }

}






















