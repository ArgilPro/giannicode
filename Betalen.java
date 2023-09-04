package com.example.rapidjava;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Betalen extends AppCompatActivity {

    Button checkout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_betalen);

        checkout=findViewById(R.id.checkout);

        checkout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i= new Intent(Betalen.this,Mijn_kaarten.class);
                //extra parameter to hide delete button
                i.putExtra("Pay",true);// place the same in mijn kaarten button
                i.putExtra("amount",17);
                startActivity(i);
            }
        });
    }
}