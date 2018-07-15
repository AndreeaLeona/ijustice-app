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
        auth=FirebaseAuth.getInstance();
        firebaseDatabase=FirebaseDatabase.getInstance();
        databaseReference=firebaseDatabase.getReference();
        FirebaseUser user=auth.getCurrentUser();
        userId=user.getUid();
        btnSalveaza.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (validare()) {
                    String nume = etNume.getText().toString();
                    String prenume = etPrenume.getText().toString();
                    String adresa = etAdresa.getText().toString();
                    String oras = etOras.getText().toString();
                    String telefon = etTelefon.getText().toString();
                    String email = etEmail.getText().toString();
                    String precizari = etPrecizari.getText().toString();

                    Map client = new HashMap<>();
                    client.put("nume", nume);
                    client.put("prenume", prenume);
                    client.put("adresa",adresa);
                    client.put("oras", oras);
                    client.put("telefon", telefon);
                    client.put("email", email);
                    client.put("precizari", precizari);

                    databaseReference.child("clienti").child(userId).push().setValue(client);
                    Toast.makeText(getApplicationContext(), "Datele tale au fost salvate cu succes!", Toast.LENGTH_SHORT).show();
                }
            }
        });


    }
    public boolean validare(){
        boolean validare=true;
        if(etNume.getText().toString().isEmpty()){
            etNume.setError("Nu ai introdus numele clientului!");
            validare=false;
        }else if(etNume.getText().toString().length()<2){
            etNume.setError("Introdu un nume format din minim două caractere");
            validare=false;
        }else if(etPrenume.getText().toString().isEmpty()){
            etPrenume.setError("Nu ai introdus prenumele clientului!");
            validare=false;
        }else if(etPrenume.getText().toString().length()<2){
            etPrenume.setError("Prenumele introdus trebuie să aibă o lungime de minim două caractere!");
            validare=false;
        }else if(etAdresa.getText().toString().isEmpty()){
            etAdresa.setError("Adresa nu a fost introdusă!");
            validare=false;
        }else if(etOras.getText().toString().isEmpty()){
            etOras.setError("Nu ai introdus orașul!");
            validare=false;
        }else if(etTelefon.getText().toString().isEmpty()){
            etTelefon.setError("Nu ai introdus numărul de telefon!");
            validare=false;
        }else if(etEmail.getText().toString().isEmpty()){
            etEmail.setError("Nu ai introdus adresa de email!");
            validare=false;
        }else if(etPrecizari.getText().toString().isEmpty()){
            etTelefon.setError("Nu ai introdus precizările!");
            validare=false;
        }
        return validare;
    }
}
