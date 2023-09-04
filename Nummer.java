package com.example.rapidjava;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class Nummer extends AppCompatActivity {

    EditText editTextPhone;
    Button btn_button2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nummer);

        final EditText editTextPhone = findViewById(R.id.editTextPhone);
        Button btn_button2 = findViewById(R.id.button2);

        btn_button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(editTextPhone.getText().toString().trim().isEmpty()) {
                    Toast.makeText(Nummer.this, "Enter Phonenumber", Toast.LENGTH_SHORT).show();
                    return;
                }
                Intent intent = new Intent(getApplicationContext(),Home.class);
                intent.putExtra("mobiel",editTextPhone.getText().toString());
                startActivity(intent);
            }
        });


    }
}