package com.ijustice.andreea.ijusticelicenta;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageButton;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.ijustice.andreea.ijusticelicenta.models.UserClient;

public class ProfilClientActivity extends AppCompatActivity {
    TextView tvNume,tvEmail,tvTelefon,tvAdresa;
    FirebaseAuth auth;
    ImageButton btnEditeaza;
    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;
    private String userId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profil_client);
        tvNume=(TextView)findViewById(R.id.tv_profil_nume_client);
        tvAdresa=(TextView)findViewById(R.id.tv_profil_adresa_client);
        tvTelefon=(TextView)findViewById(R.id.tv_profil_telefon_client);
        tvEmail=(TextView)findViewById(R.id.tv_profil_email_client);
        auth = FirebaseAuth.getInstance();
        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference("users_clienti");
        FirebaseUser user = auth.getCurrentUser();
        userId = user.getUid();

        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                String nume = dataSnapshot.child(userId).child("nume").getValue(String.class);
                String adresa = dataSnapshot.child(userId).child("adresa").getValue(String.class);
                String email = dataSnapshot.child(userId).child("email").getValue(String.class);
                String nrTelefon = dataSnapshot.child(userId).child("telefon").getValue(String.class);

                UserClient userClient=new UserClient(nume,adresa,nrTelefon,email);
                tvNume.setText(userClient.getNume().toString());
                tvAdresa.setText(userClient.getAdresa().toString());
                tvEmail.setText(userClient.getEmail().toString());
                tvTelefon.setText(userClient.getNumarTelefon().toString());

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }
}
