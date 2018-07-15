package com.ijustice.andreea.ijusticelicenta;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class VizualizareSolicitareClientActivity extends AppCompatActivity {
    TextView tvNume,tvAdresa,tvTelefon,tvEmail;
    Button btnAccepta;
    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;
    FirebaseAuth auth;
    String cheie;
    int stareCurenta=2;
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
        Intent intent=getIntent();
        String nume=intent.getStringExtra("Nume");
        String adresa=intent.getStringExtra("Adresa");
        String telefon=intent.getStringExtra("Telefon");
        String email=intent.getStringExtra("Email");
        tvNume.setText(nume);
        tvAdresa.setText(adresa);
        tvTelefon.setText(telefon);
        tvEmail.setText(email);

        btnAccepta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AcceptaSolicitaeColaborare();
            }
        });

    }
    public void AcceptaSolicitaeColaborare() {
        btnAccepta.setEnabled(false);

        if(stareCurenta==2){
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
}
