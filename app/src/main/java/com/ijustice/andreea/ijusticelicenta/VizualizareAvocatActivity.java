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
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class VizualizareAvocatActivity extends AppCompatActivity {
    TextView tvNume;
    TextView tvSpecializare;
    TextView tvPrecizare;
    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;
    FirebaseAuth auth;
    String cheie;
    private String userId;
    Button btnTrimite;
    Button btnAnulare;
    int stareCurenta=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vizualizare_avocat);
        tvNume=(TextView)findViewById(R.id.textView_nume_avocat);
        tvSpecializare=(TextView)findViewById(R.id.textView_specializare_avocat);
        tvPrecizare=(TextView)findViewById(R.id.textView_precizare_specializare_avocat);
        btnTrimite=(Button)findViewById(R.id.button_trimite_solicitare);

        auth=FirebaseAuth.getInstance();
        firebaseDatabase=FirebaseDatabase.getInstance();
        databaseReference=firebaseDatabase.getReference();
        FirebaseUser user=auth.getCurrentUser();
        userId=user.getUid();
        Intent intent=getIntent();
            cheie=intent.getStringExtra("Key");
            String nume=intent.getStringExtra("Nume");
            String prenume=intent.getStringExtra("Prenume");
            String specializare=intent.getStringExtra("Specializare");
            String precizare=intent.getStringExtra("Precizare");

        tvNume.setText(nume + " " + prenume);
        tvSpecializare.setText(specializare);
        tvPrecizare.setText(precizare);

        btnTrimite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                trimiteSolicitaeColaborare();
            }
        });
        databaseReference.child("solicitari").child("cheie").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if(dataSnapshot.hasChild(userId)){
                    String solicitare=dataSnapshot.child(userId).child("tip solicitare").getValue().toString();
                    if(solicitare.equals("primita")) {
                        btnTrimite.setEnabled(true);
                        btnTrimite.setText("Anulează solicitare");
                        stareCurenta = 1;
                    }
                else if(solicitare.equals("trimisa")){
                    btnTrimite.setEnabled(true);
                    stareCurenta=2;
                }


                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

    }


    public void trimiteSolicitaeColaborare() {
        btnTrimite.setEnabled(false);
        if(stareCurenta==0){
            databaseReference.child("solicitari").child(cheie).child(userId).child("tip solicitare").
                    setValue("primita").addOnCompleteListener(new OnCompleteListener<Void>() {
                @Override
                public void onComplete(@NonNull Task<Void> task) {
                    databaseReference.child("solicitari").child(userId).child(cheie).child("tip solicitare").
                            setValue("trimisa").addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            btnTrimite.setEnabled(true);
                            btnTrimite.setText("Anulează");
                            stareCurenta=1;

                        }
                    });

                }
            });
        }
        btnTrimite.setEnabled(false);
        if(stareCurenta==1){
            databaseReference.child("solicitari").child(cheie).child(userId).child("tip solicitare").removeValue().addOnCompleteListener(new OnCompleteListener<Void>() {
                @Override
                public void onComplete(@NonNull Task<Void> task) {
                    btnTrimite.setEnabled(true);
                    btnTrimite.setText("Trimite solicitare");
                    stareCurenta=0;

                }
            });
        }
      Intent intentStare=new Intent(getApplicationContext(),VizualizareSolicitareClientActivity.class);
        intentStare.putExtra("stareCurent",stareCurenta);

    }
}
