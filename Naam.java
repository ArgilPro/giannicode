package com.example.rapidjava;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.rapidjava.stripeConnect.StripeClient;
import com.example.rapidjava.stripeConnect.StripeServices;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;

public class Naam extends AppCompatActivity implements View.OnClickListener {

    EditText editText,email;
    Button volgende;

    StripeServices stripeServices;
    CompositeDisposable compositeDisposable =new CompositeDisposable();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_naam);
        editText = findViewById(R.id.naam);
        email = findViewById(R.id.email);
        volgende = findViewById(R.id.volgende);

        stripeServices = StripeClient.getInstance().create(StripeServices.class);

        volgende.setOnClickListener(this);


    }
    @Override
    public void onClick(View view) {
        compositeDisposable.add(stripeServices.createCustomer(
                email.getText().toString(),
                editText.getText().toString())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(customerModel -> {

                    if(customerModel.getId()==null){
                        System.out.println("Probeer het opnieuw");

                    }else{
                        System.out.println("===> customer Id : "+customerModel.getId());
                    }
                } , throwable -> {
                    System.out.println(throwable.getMessage());
                })

        );
    }
}