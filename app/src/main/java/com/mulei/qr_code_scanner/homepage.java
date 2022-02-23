package com.mulei.qr_code_scanner;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class homepage extends AppCompatActivity {

    Button qrscanbtn,walletbtn,settings;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_homepage);
        qrscanbtn =findViewById(R.id.qrscanbtn);
        settings =findViewById(R.id.settings);
        walletbtn=findViewById(R.id.walletbtn);

        qrscanbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startActivity(new Intent(homepage.this, scan_activity.class));         }
        });

        settings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(homepage.this, settings.class) );           }


        });

        walletbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(homepage.this, wallet.class) );           }
        });

    }

}