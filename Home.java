package com.example.rapidjava;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import androidx.activity.result.ActivityResultLauncher;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.example.rapidjava.stripeConnect.StripeClient;
import com.example.rapidjava.stripeConnect.StripeServices;
import com.journeyapps.barcodescanner.ScanContract;
import com.journeyapps.barcodescanner.ScanOptions;

import io.reactivex.disposables.CompositeDisposable;

public class Home extends AppCompatActivity {

    Button btn_button3;
    Button btn_button4;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        btn_button3 = findViewById(R.id.button3);
        btn_button4 = findViewById(R.id.button4);


        btn_button4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //to make button navigate
                Intent intent = new Intent(getApplicationContext(), Verzoek.class);
                startActivity(intent);
                finish();
            }
        });

        //scanner
        btn_button3.setOnClickListener(v->{
            scanCode();
        });
    }

    private void scanCode() {
        ScanOptions options = new ScanOptions();
        options.setPrompt("druk volume knop voor flash");
        options.setBeepEnabled(false);
        options.setOrientationLocked(true);
        options.setCaptureActivity(CaptureAct.class);
        barLauncher.launch(options);
    }

    ActivityResultLauncher<ScanOptions> barLauncher = registerForActivityResult(new ScanContract(), result -> {
        if(result.getContents() !=null) {
            Intent intent=new Intent(getApplicationContext(),Betalen.class);
            intent.putExtra("ScanResult", result.getContents());
            startActivity(intent);
        }
    });

    //sidebar menu
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.sidebar, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        if (id==R.id.Account) {
            Intent intent = new Intent(Home.this,Mijn_Account.class);
            startActivity(intent);
            finish();
            return true;
        }

        if (id==R.id.Kaart) {
            Intent intent = new Intent(Home.this,AddCard.class);
            startActivity(intent);
            finish();
            return true;
        }

        if (id == R.id.Meldingen) {
            Intent intent = new Intent(Home.this,Meldingen.class);
            startActivity(intent);
            finish();
            return true;
        }

        if(id==R.id.kaarten) {
            Intent intent = new Intent(Home.this,Mijn_kaarten.class);
            intent.putExtra("Pay",false);//make false here
            startActivity(intent);
            finish();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}













