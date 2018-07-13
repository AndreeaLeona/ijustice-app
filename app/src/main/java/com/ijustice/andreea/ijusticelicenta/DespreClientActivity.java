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
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.ijustice.andreea.ijusticelicenta.models.UserAvocat;
import com.ijustice.andreea.ijusticelicenta.models.UserClient;

import java.util.HashMap;
import java.util.Map;

public class DespreClientActivity extends AppCompatActivity {
    EditText etNume;
    EditText etAdresa;
    EditText etEmail;
    EditText etNrTelefon;
    Button btnSalveaza;
    FirebaseAuth auth;
    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;
    private String userId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_despre_client);
        etNume=(EditText)findViewById(R.id.despre_client_et_nume) ;
        etAdresa=(EditText)findViewById(R.id.despre_client_et_adresa);
        etNrTelefon=(EditText) findViewById(R.id.despre_client_et_telefon);
        etEmail=(EditText)findViewById(R.id.despre_client_et_email);
        btnSalveaza=(Button) findViewById(R.id.despre_client_btn_salveaza);
        auth=FirebaseAuth.getInstance();
        firebaseDatabase=FirebaseDatabase.getInstance();
        databaseReference=firebaseDatabase.getReference();
        FirebaseUser user=auth.getCurrentUser();
        userId=user.getUid();

        btnSalveaza.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String nume = etNume.getText().toString();
                String adresa = etAdresa.getText().toString();
                String email = etEmail.getText().toString();
                String numarTelefon = etNrTelefon.getText().toString();
                UserClient client=new UserClient(nume,adresa,numarTelefon,email);
                Map informatii = new HashMap<>();

                informatii.put("nume", client.getNume());
                informatii.put("adresa", client.getAdresa());
                informatii.put("telefon", client.getNumarTelefon());
                informatii.put("email", client.getEmail());

                try {

                    databaseReference.child("users_clienti").child(userId).setValue(informatii);
                    Toast.makeText(getApplicationContext(), "Datele tale au fost salvate cu succes!", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(getApplicationContext(),ClientActivity.class));

                } catch (Exception ex) {
                    Toast.makeText(getApplicationContext(), "Datele tale nu au fost salvate, te rog sa verifici conexiunea la internet", Toast.LENGTH_SHORT).show();


                }


            }
        });
    }
}
