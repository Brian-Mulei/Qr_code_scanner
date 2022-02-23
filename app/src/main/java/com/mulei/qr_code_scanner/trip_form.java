package com.mulei.qr_code_scanner;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class trip_form extends AppCompatActivity {

    TextView number_plate,saccotext;
    Spinner spinner0;
    DatabaseReference dbRef,ref2;

    Button confirm,cancel;

    data data;
    ValueEventListener valueEventListener,listener;
    ArrayList<String> list;
    ArrayAdapter<String> adapter;
    DatabaseReference databaseReference;

    String numberplatess;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trip_form);
        numberplatess= getIntent().getStringExtra("code");

        dbRef= FirebaseDatabase.getInstance().getReference("Matatus/"+ numberplatess+"/ROUTES/");
        ref2= FirebaseDatabase.getInstance().getReference("Matatus/"+ numberplatess);
        saccotext=findViewById(R.id.sacco);

        confirm=findViewById(R.id.Confirm);
        cancel=findViewById(R.id.Cancel);

        databaseReference = FirebaseDatabase.getInstance().getReference("Receipts");


        spinner0=findViewById(R.id.spinner0);
        list =new ArrayList<String>();
        adapter=new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item,list);

        spinner0.setAdapter(adapter);
        

        number_plate = findViewById(R.id.numberplate);



        fetchdata();

        if (getIntent().getStringExtra("code") != null) {
             number_plate.setText("Number Plate : " +numberplatess);
        }

        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(trip_form.this, homepage.class));
            }
        });

        confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String route = spinner0.getSelectedItem().toString().trim();
                String sacco = saccotext.getText().toString().trim();
                String numberplate = numberplatess.toString().trim();
                GoogleSignInAccount acct = GoogleSignIn.getLastSignedInAccount(trip_form.this);
                String personEmail = acct.getEmail().toString();
                if (TextUtils.isEmpty(list.toString())  )
                {
                    Toast.makeText(trip_form.this, "Pick a route", Toast.LENGTH_SHORT).show();
                }
                else {

                    data= new data(numberplate,sacco,route,personEmail);
                    popup();
                }
            }
        });

    }

    private void popup() {

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle("Confirmation");
        builder.setMessage("Want to pay  " + data.getNumberplate()+ " of " +data.getSacco() + " for route "+ data.getTrip() +"?" );
        builder.setPositiveButton("Confirm",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        send();
                        startActivity(new Intent(trip_form.this, homepage.class));


                    }
                });
        builder.setNegativeButton(android.R.string.cancel, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                startActivity(new Intent(trip_form.this, homepage.class));

            }
        });

        AlertDialog dialog = builder.create();
        dialog.show();
    }

    public void send() {

        String uniquekey= databaseReference.push().getKey();
        databaseReference.child(uniquekey).setValue(data);
        Toast.makeText(this, "Payment Complete", Toast.LENGTH_SHORT).show();



    }

    public void fetchdata() {

        listener=ref2.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot data :snapshot.getChildren())
                    saccotext.setText(data.getValue().toString());
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        valueEventListener=dbRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot data :snapshot.getChildren())
                    list.add(data.getKey().toString()+" @ "+data.getValue().toString());

                adapter.notifyDataSetChanged();



            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }


}