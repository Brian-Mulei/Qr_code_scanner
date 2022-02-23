package com.mulei.qr_code_scanner;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;

public class settings extends AppCompatActivity {
    ImageView imageView;
    TextView UserName,Email;
     Button send,logout;
    GoogleSignInClient mGoogleSignInClient;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);imageView=findViewById(R.id.imageView);
        Email=findViewById(R.id.Email);
        UserName=findViewById(R.id.UserName);
        logout=findViewById(R.id.logout);



        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(settings.this, login.class);
                startActivity(intent);
            }
        });
        GoogleSignInAccount acct = GoogleSignIn.getLastSignedInAccount(this);
        if (acct != null) {
            String personName = acct.getDisplayName();
            String personGivenName = acct.getGivenName();
            String personFamilyName = acct.getFamilyName();
            String personEmail = acct.getEmail();
            String personId = acct.getId();
            Uri personPhoto = acct.getPhotoUrl();

            //get user email info
            Email.setText(personEmail);
            UserName.setText(personName);
            Glide.with(this).load(String.valueOf(personPhoto)).into(imageView);

        }
    }



}