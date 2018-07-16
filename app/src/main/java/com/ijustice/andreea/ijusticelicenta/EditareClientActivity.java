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
import com.ijustice.andreea.ijusticelicenta.models.Client;
import com.ijustice.andreea.ijusticelicenta.models.UserAvocat;

import java.util.HashMap;
import java.util.Map;

public class EditareClientActivity extends AppCompatActivity {
    EditText etNume, etPrenume, etAdresa, etOras,etTelefon,etEmail,etPrecizari;
    Button btnAlveaza;
    FirebaseAuth auth;
    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;
    private String userId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editare_client);
        etNume=(EditText)findViewById(R.id.editeaza_client_et_nume);
        etPrenume=(EditText)findViewById(R.id.editeaza_client_et_prenume);
        etAdresa=(EditText)findViewById(R.id.editeaza_client_et_adresa);
        etOras=(EditText)findViewById(R.id.editeaza_client_et_oras);
        etTelefon=(EditText)findViewById(R.id.editeaza_client_et_telefon);
        etEmail=(EditText)findViewById(R.id.editeaza_client_et_email);
        etPrecizari=(EditText)findViewById(R.id.editeaza_client_et_precizari);
        btnAlveaza=(Button) findViewById(R.id.editeaza_clienti_btn_salveazamodificari);
        auth = FirebaseAuth.getInstance();
        firebaseDatabase = FirebaseDatabase.getInstance();
        FirebaseUser user = auth.getCurrentUser();
        userId = user.getUid();
            Intent intent=getIntent();

        if (intent.getExtras() != null) {

            final String cheie=intent.getStringExtra("cheie");
            databaseReference = firebaseDatabase.getReference("clienti").child(userId).child(cheie);

            String  nume = intent.getStringExtra("nume");
            String prenume = intent.getStringExtra("prenume");
            String adresa = intent.getStringExtra("adresa");
            String oras = intent.getStringExtra("oras");
            String telefon = intent.getStringExtra("telefon");
            String email = intent.getStringExtra("email");
            String precizari = intent.getStringExtra("precizari");
            etNume.setText(nume.toString());
            etPrenume.setText(prenume.toString());
            etAdresa.setText(adresa.toString());
            etOras.setText(oras.toString());
            etTelefon.setText(telefon.toString());
            etEmail.setText(email.toString());
            etPrecizari.setText(precizari.toString());

            btnAlveaza.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (validare()) {
                        Client c = new Client(etNume.getText().toString(), etPrenume.getText().toString(), etAdresa.getText().toString(),
                                etOras.getText().toString(), etTelefon.getText().toString(), etEmail.getText().toString(), etPrecizari.getText().toString(), cheie);
                        Map client = new HashMap<>();
                        client.put("nume", c.getNume());
                        client.put("prenume", c.getPrenume());
                        client.put("adresa", c.getAdresa());
                        client.put("oras", c.getOras());
                        client.put("telefon", c.getNrTelefon());
                        client.put("email", c.getAdresaEmail());
                        client.put("precizari", c.getPrecizari());
                        databaseReference.setValue(client);
                        Toast.makeText(getApplicationContext(), "Modificarile au fost realizate cu succes",
                                Toast.LENGTH_SHORT).show();
                    }
                }
            });


        }



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
