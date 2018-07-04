package com.ijustice.andreea.ijusticelicenta;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.ijustice.andreea.ijusticelicenta.models.Client;
import com.ijustice.andreea.ijusticelicenta.models.NotaAvocat;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class AdaugaClientActivity extends AppCompatActivity {
    EditText etNume;
    EditText etPrenume;
    EditText etAdresa;
    Button btnInapoi;
    EditText etOras;
    EditText etTelefon;
    EditText etEmail;
    EditText etPrecizari;
    FirebaseAuth auth;
    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;
    private String userId;
    Button btnSalveaza;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adauga_client);
        etNume=(EditText)findViewById(R.id.adauga_client_et_nume);
        etPrenume=(EditText)findViewById(R.id.adauga_client_et_prenume);
        etAdresa=(EditText)findViewById(R.id.adauga_client_et_adresa);
        etOras=(EditText)findViewById(R.id.adauga_client_et_oras);
        etTelefon=(EditText)findViewById(R.id.adauga_client_et_telefon);
        etEmail=(EditText)findViewById(R.id.adauga_client_et_email);
        etPrecizari=(EditText)findViewById(R.id.adauga_client_et_precizari);

        btnSalveaza=(Button) findViewById(R.id.adauga_clienti_btn_adauga);
        btnInapoi=(Button) findViewById(R.id.adauga_clienti_btn_inapoi);
        auth=FirebaseAuth.getInstance();
        firebaseDatabase=FirebaseDatabase.getInstance();
        databaseReference=firebaseDatabase.getReference();
        FirebaseUser user=auth.getCurrentUser();
        userId=user.getUid();
        btnSalveaza.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String nume=etNume.getText().toString();
                String prenume=etPrenume.getText().toString();
                String adresa=etAdresa.getText().toString();
                String oras=etOras.getText().toString();
                String telefon=etTelefon.getText().toString();
                String email=etEmail.getText().toString();
                String precizari=etPrecizari.getText().toString();

           Client c=new Client(nume,prenume,adresa,oras,telefon,email,precizari);
                Map client = new HashMap<>();
                client.put("nume",c.getNume());
                client.put("prenume",c.getPrenume());
                client.put("adresa",c.getAdresa());
                client.put("oras",c.getOras());
                client.put("telefon",c.getNrTelefon());
                client.put("email",c.getAdresaEmail());
                client.put("precizari",c.getPrecizari());
                String idClient=String.valueOf(c.getId());

                databaseReference.child("clienti").child(userId).child(idClient).setValue(client);
                Toast.makeText(getApplicationContext(), "Datele tale au fost salvate cu succes!", Toast.LENGTH_SHORT).show();
            }
        });
        btnInapoi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),ContActivity.class));
            }
        });

    }
}
