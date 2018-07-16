package com.ijustice.andreea.ijusticelicenta;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class VizualizareSolicitareClientActivity extends AppCompatActivity {
    TextView tvNume,tvAdresa,tvTelefon,tvEmail;
    Button btnAccepta;
    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;
    FirebaseAuth auth;
    private String userId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vizualizare_solicitare_client);
        auth=FirebaseAuth.getInstance();
        firebaseDatabase=FirebaseDatabase.getInstance();
        databaseReference=firebaseDatabase.getReference();
        FirebaseUser user=auth.getCurrentUser();
        userId=user.getUid();

        tvNume=(TextView)findViewById(R.id.tv_nume_solicitant_cerere);
        tvAdresa=(TextView)findViewById(R.id.tv_adresa_solicitant_cerere);
        tvTelefon=(TextView)findViewById(R.id.tv_telefon_solicitant_cerere);
        btnAccepta=(Button)findViewById(R.id.button_accepta_solicitare) ;
        tvEmail=(TextView)findViewById(R.id.tv_email_solicitant_cerere);


        Intent intent2=getIntent();
        String nume=intent2.getStringExtra("Nume");
        String adresa=intent2.getStringExtra("Adresa");
        String telefon=intent2.getStringExtra("Telefon");
        String email=intent2.getStringExtra("Email");
        tvNume.setText(nume);
        tvAdresa.setText(adresa);
        tvTelefon.setText(telefon);
        tvEmail.setText(email);
        Intent intent=getIntent();
        final String cheie=intent.getStringExtra("Cheie");
        databaseReference.child("colaboratori").child(userId).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if(dataSnapshot.hasChild(cheie)){
                    btnAccepta.setText("Anulează");
                }else{
                    btnAccepta.setText("Acceptă solicitare");
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
      btnAccepta.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View v) {
              if(btnAccepta.getText().equals("Acceptă solicitare")) {
                  AcceptaSolicitaeColaborare();
              }else if(btnAccepta.getText().equals("Anulează")){
                  Anuleaza();
              }
          }
      });
  }



    public void Anuleaza(){
        Intent intent=getIntent();
        final String cheie=intent.getStringExtra("Cheie");
        databaseReference.child("colaboratori").child(cheie).child(userId).child("tip solicitare").removeValue().addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                databaseReference.child("colaboratori").child(userId).child(cheie).child("tip solicitare").removeValue().addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        Toast.makeText(getApplicationContext(),
                                "Ai anulat solicitarea, iar potențialul client a fost șters din lista ta de conversașii",
                                Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });
    }

    public void AcceptaSolicitaeColaborare() {
        Intent intent=getIntent();
        final String cheie=intent.getStringExtra("Cheie");

        databaseReference.child("solicitari").child(userId).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                    if (dataSnapshot.child(cheie).child("tip solicitare").getValue().equals("primita")) {

                        databaseReference.child("colaboratori").child(cheie).child(userId).child("tip solicitare").
                                setValue("colaborator").addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {
                                databaseReference.child("colaboratori").child(userId).child(cheie).child("tip solicitare").
                                        setValue("colaborator").addOnCompleteListener(new OnCompleteListener<Void>() {
                                    @Override
                                    public void onComplete(@NonNull Task<Void> task) {
                                        btnAccepta.setEnabled(true);
                                        btnAccepta.setText("Anulează");

                                    }
                                });

                            }
                        });
                    }
                }



            @Override
            public void onCancelled(DatabaseError databaseError) {


            }
        });


    }
}
