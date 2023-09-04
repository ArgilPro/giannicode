package com.example.rapidjava;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.journeyapps.barcodescanner.BarcodeEncoder;

public class Verzoek extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_verzoek);

        EditText editText= findViewById(R.id.editTextNumberDecimal);
        Button btnbutton5= findViewById(R.id.button5);
        ImageView ImageQr= findViewById(R.id.imageView);

        btnbutton5.setOnClickListener(view -> {
            String myText = editText.getText().toString().trim();

            MultiFormatWriter mWriter = new MultiFormatWriter();

            try {
                BitMatrix mMatrix = mWriter.encode(myText, BarcodeFormat.QR_CODE, 400,400);

                BarcodeEncoder mEncoder = new BarcodeEncoder();

                Bitmap mBitmap = mEncoder.createBitmap(mMatrix);

                ImageQr.setImageBitmap(mBitmap);

                // to hide keyboard

                InputMethodManager manager = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);

                manager.hideSoftInputFromWindow(editText.getWindowToken(),0);

            }catch (WriterException e){
                e.getStackTrace();
            }
        });
    }
}