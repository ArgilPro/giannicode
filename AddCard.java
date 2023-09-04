package com.example.rapidjava;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.StrictMode;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.stripe.Stripe;
import com.stripe.android.model.PaymentMethod;
import com.stripe.exception.StripeException;

import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class AddCard extends AppCompatActivity {

    @BindView(R.id.card_holder)
    EditText Cardholder;

    @BindView(R.id.card_number)
    EditText CardNumber;

    @BindView(R.id.expiredate)
    EditText expireDate;

    @BindView(R.id.cvv)
    EditText Cvv;

    @BindView(R.id.Add_card)
    Button AddCard;

    com.stripe.android.Stripe stripe;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_card);
        ButterKnife.bind(this);

        Stripe.apiKey="sk_test_51LIiuRHZorkf9EZn4xAynVAcZc1LwI0FiSUH9W0nuoCqenwFE5HHe8SUgX5tWGWIRPG01kfjw3iWT9JmMnuCjkK800F7DK9kFs";
        StrictMode.ThreadPolicy policy= new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
    }

    @OnClick(R.id.Add_card)
    void Add_card(){
        String name= Cardholder.getText().toString();
        String card= CardNumber.getText().toString();
        String date= expireDate.getText().toString();
        String code= Cvv.getText().toString();

        Map<String,Object> Card= new HashMap<>();
        Map<String,Object> billingDetails= new HashMap<>();

        date = expireDate.getText().toString();
        String[] parts = date.split("/"); // split the string into an array of substrings based on the "/"
        String m = parts[0];
        String y = parts[1];

        int month=Integer.parseInt(m);
        int year=Integer.parseInt(y);

        Card.put("number",card);
        Card.put("exp_month",month);
        Card.put("exp_year",year);
        Card.put("cvc",code);

        billingDetails.put("name",name);
        billingDetails.put("email","aloea@gmail.com");

        Map<String,Object> allDetail= new HashMap<>();

        allDetail.put("type","card");
        allDetail.put("card",Card);
        allDetail.put("billing_details",billingDetails);

        PaymentMethod paymentMethod=null;
        try {
            paymentMethod=PaymentMethod.create(allDetail);
            PaymentMethod paymentMethodRetriveID= null;
            paymentMethodRetriveID=PaymentMethod.retrieve(paymentMethod.getId());

            Map<String,Object> customer= new HashMap<>();
            customer.put("customer","cus_Mc7ZSR4MPhtAXi");

            PaymentMethod attachedCard= paymentMethodRetriveID.attach(customer);
            Toast.makeText(this,"Your card added succesfully",Toast.LENGTH_LONG).show();

        }catch (StripeException e){
            e.printStackTrace();
            Toast.makeText(this,e.getMessage(),Toast.LENGTH_LONG).show();


        }

    }
}