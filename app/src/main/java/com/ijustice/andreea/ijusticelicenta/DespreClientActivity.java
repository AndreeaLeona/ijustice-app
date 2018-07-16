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
        etNume = (EditText) findViewById(R.id.despre_client_et_nume);
        etAdresa = (EditText) findViewById(R.id.despre_client_et_adresa);
        etNrTelefon = (EditText) findViewById(R.id.despre_client_et_telefon);
        etEmail = (EditText) findViewById(R.id.despre_client_et_email);
        btnSalveaza = (Button) findViewById(R.id.despre_client_btn_salveaza);
        auth = FirebaseAuth.getInstance();
        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference();
        FirebaseUser user = auth.getCurrentUser();
        userId = user.getUid();

        btnSalveaza.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (validare()) {
                    String nume = etNume.getText().toString();
                    String adresa = etAdresa.getText().toString();
                    String email = etEmail.getText().toString();
                    String numarTelefon = etNrTelefon.getText().toString();
                    Map informatii = new HashMap<>();

                    informatii.put("nume", nume);
                    informatii.put("adresa", adresa);
                    informatii.put("telefon", email);
                    informatii.put("email", numarTelefon);

                    try {

                        databaseReference.child("users_clienti").child(userId).setValue(informatii);
                        Toast.makeText(getApplicationContext(), "Datele tale au fost salvate cu succes!", Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(getApplicationContext(), ClientActivity.class));

                    } catch (Exception ex) {
                        Toast.makeText(getApplicationContext(), "Datele tale nu au fost salvate, te rog sa verifici conexiunea la internet", Toast.LENGTH_SHORT).show();

                    }


                }
            }
        });
    }

    public boolean validare() {
        boolean validare = true;
        if (etNume.getText().toString().isEmpty()) {
            etNume.setError("Nu ai introdus numele!");
            validare = false;
        } else if (etNume.getText().toString().length() < 2) {
            etNume.setError("Numele introdus trebuie să fie format din minim două caractere!");
            validare = false;
        } else if (etAdresa.getText().toString().isEmpty()) {
            etAdresa.setError("Nu ai introdus adresa!");
            validare = false;
        } else if (etNrTelefon.getText().toString().isEmpty()) {
            etNrTelefon.setError("Nu ai introdus numărul de telefon!");
            validare = false;
        } else if (etNrTelefon.getText().toString().length() < 10 || etNrTelefon.getText().toString().length() > 10) {
            etNrTelefon.setError("Numărul de telefon este incorect!");
            validare = false;
        } else if (etEmail.getText().toString().isEmpty()) {
            etEmail.setError("Nu ai introdus adresa de email!");
            validare = false;
        }
        return validare;
    }
}
