package com.ijustice.andreea.ijusticelicenta;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.ijustice.andreea.ijusticelicenta.models.UserAvocat;
import com.ijustice.andreea.ijusticelicenta.models.UserClient;

public class EditareProfilActivity extends AppCompatActivity {
    EditText etNume, etAdresa, etTelefon,etEmail;
    Button btnEditeaza;
    FirebaseAuth auth;
    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;
    private String userId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editare_profil);
        etNume=(EditText)findViewById(R.id.edit_profil_client_nume);
        etAdresa=(EditText)findViewById(R.id.edit_profil_client_adresa);
        etTelefon=(EditText)findViewById(R.id.edit_profil_client_telefon);
        etEmail=(EditText)findViewById(R.id.edit_profil_client_email);
        btnEditeaza=(Button)findViewById(R.id.edit_profil_client_btn_editeaza);

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
                String telefon = dataSnapshot.child(userId).child("telefon").getValue(String.class);

                etNume.setText(nume);
                etAdresa.setText(adresa);
                etEmail.setText(email);
                etTelefon.setText(telefon);


                btnEditeaza.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        UserClient nou = new UserClient(etNume.getText().toString(),etAdresa.getText().toString(),
                                etTelefon.getText().toString(),etEmail.getText().toString());
                        databaseReference.child(userId).setValue(nou);
                        Toast.makeText(getApplicationContext(), "Modificarile au fost realizate cu succes1",
                                Toast.LENGTH_SHORT).show();
                    }
                });
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

    }
}
