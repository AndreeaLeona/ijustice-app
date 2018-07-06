package com.ijustice.andreea.ijusticelicenta;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageButton;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class DetaliiCazActivity extends AppCompatActivity {
    TextView tvObiect,tvNumar,tvNume,tvData,tvDescriere;
    ImageButton imgBtnEditare;
    ImageButton imgBtnStergere;
    FirebaseAuth auth;
    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;
    private String userId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalii_caz);
        auth=FirebaseAuth.getInstance();
        firebaseDatabase=FirebaseDatabase.getInstance();
        FirebaseUser user=auth.getCurrentUser();
        userId=user.getUid();

        tvObiect=(TextView)findViewById(R.id.tv_obiect);
        tvNumar=(TextView)findViewById(R.id.tv_numar);
        tvNume=(TextView)findViewById(R.id.tv_nume);
        tvData=(TextView)findViewById(R.id.tv_data);
        tvDescriere=(TextView)findViewById(R.id.tv_descriere);
        imgBtnEditare=(ImageButton)findViewById(R.id.imageBtnEditCaz) ;
        imgBtnStergere=(ImageButton)findViewById(R.id.imageBtnDeleteCaz);
        Intent intent = getIntent();
        if (intent.getExtras() != null) {
            final int id = intent.getIntExtra("id", -1);
            databaseReference = firebaseDatabase.getReference("cazuri").child(userId).child(String.valueOf(id));
            final String obiect = intent.getStringExtra("obiect");
            final int numar = intent.getIntExtra("numar", -1);
            final String nume = intent.getStringExtra("numeSolicitant");
            final String data = intent.getStringExtra("data");
            final String descriere = intent.getStringExtra("descriere");

            tvObiect.setText(obiect);
            tvNumar.setText(String.valueOf(numar));
            tvNume.setText(nume);
            tvData.setText(data);
            tvDescriere.setText(descriere);

        }


    }
}
