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

import java.util.HashMap;
import java.util.Map;

public class InformatiiActivity extends AppCompatActivity {
    EditText etNume;
    EditText etPrenume;
    EditText etEmail;
    EditText etNrTelefon;
    EditText etCazuriRezolvate;
    EditText etCazuriPierdute;
    EditText etOras;
    EditText etStrada;
    EditText etNr;
    EditText etSpecializare;
    EditText etSpecializarePrecizare;
    Button btnSalveaza;
    FirebaseAuth auth;
    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;
    private String userId;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_informatii);
        etNume=(EditText)findViewById(R.id.info_et_nume);
        etEmail=(EditText)findViewById(R.id.info_et_email);
        etPrenume=(EditText) findViewById(R.id.info_et_prenume);
        etNrTelefon=(EditText)findViewById(R.id.info_et_nr_telefon);
        etCazuriRezolvate=(EditText)findViewById(R.id.info_et_nr_cazuri_rezolvate);
        etCazuriPierdute=(EditText)findViewById(R.id.info_et_nr_cazuri_pierdute);
        etSpecializare=(EditText)findViewById(R.id.info_et_specializare) ;
        etSpecializarePrecizare=(EditText)findViewById(R.id.info_et_specializare_precizare) ;
        etOras=(EditText)findViewById(R.id.info_et_oras);
        etStrada=(EditText) findViewById(R.id.info_et_strada);
        etNr=(EditText)findViewById(R.id.info_et_nr);
        btnSalveaza=(Button) findViewById(R.id.info_btn_salveaza);
        auth=FirebaseAuth.getInstance();
        firebaseDatabase=FirebaseDatabase.getInstance();
        databaseReference=firebaseDatabase.getReference();
        FirebaseUser user=auth.getCurrentUser();
        userId=user.getUid();

        try {



            btnSalveaza.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (validare()) {
                        String nume = etNume.getText().toString();
                        String prenume = etPrenume.getText().toString();
                        String email = etEmail.getText().toString();
                        String numarTelefon = etNrTelefon.getText().toString();
                        int cazuriRezolvate = Integer.parseInt(etCazuriRezolvate.getText().toString());
                        int cazuriPierdute = Integer.parseInt(etCazuriPierdute.getText().toString());
                        String oras = etOras.getText().toString();
                        String strada = etStrada.getText().toString();
                        int nr = Integer.parseInt(etNr.getText().toString());
                        String specializare = etSpecializare.getText().toString();
                        String specializarePrecizare = etSpecializarePrecizare.getText().toString();

                        Map informatii = new HashMap<>();

                        informatii.put("nume", nume);
                        informatii.put("prenume", prenume);
                        informatii.put("email", email);
                        informatii.put("numarTelefon", numarTelefon);
                        informatii.put("cazuriRezolvate", cazuriRezolvate);
                        informatii.put("cazuriPierdute", cazuriPierdute);
                        informatii.put("oras", oras);
                        informatii.put("strada", strada);
                        informatii.put("nr", nr);
                        informatii.put("specializare", specializare);
                        informatii.put("specializarePrecizare", specializarePrecizare);
                        try {

                            databaseReference.child("users").child(userId).setValue(informatii);
                            Toast.makeText(getApplicationContext(), "Datele tale au fost salvate cu succes!", Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(getApplicationContext(), ContActivity.class));

                        } catch (Exception ex) {
                            Toast.makeText(getApplicationContext(), "Datele tale nu au fost salvate, te rog sa verifici conexiunea la internet", Toast.LENGTH_SHORT).show();


                        }


                    }
                }
            });
        }catch(Exception ex){
            Toast.makeText(getApplicationContext(), "Eroare!", Toast.LENGTH_SHORT).show();
        }


    }
    public boolean validare(){
        boolean validare=true;
        if(etNume.getText().toString().isEmpty()){
            etNume.setError("Nu ai intodus numele!");
        }else if(etNume.getText().toString().length()<2){
            etNume.setError("Numele introdus trebuie să fie format din minim două caractere");
        }else if(etPrenume.getText().toString().isEmpty()){
            etPrenume.setError("Nu ai introdus prenumele!");
        }else if(etPrenume.getText().toString().length()<2){
            etPrenume.setError("Introdu un prenume format din minim două caractere!");
        }else if(etEmail.getText().toString().isEmpty()){
            etEmail.setError("Nu ai introdus adresa de email!");
        }else if(etNrTelefon.getText().toString().isEmpty()){
            etNrTelefon.setError("Nu ai introdus numărul de telefon!");
        }else if(etNrTelefon.getText().toString().length()<10 || etNrTelefon.getText().toString().length()>10){
            etNrTelefon.setError("Numărul de telefon introdus nu este valid!");
        }else if(etCazuriRezolvate.getText().toString().isEmpty()){
            etCazuriRezolvate.setError("Nu ai introdus numărul cazurilor rezolvate!");
        }else if(etCazuriPierdute.getText().toString().isEmpty()){
            etCazuriPierdute.setError("Nu ai introdus numărul cazurilor pierdute!");
        }else if(etSpecializare.getText().toString().isEmpty()){
            etSpecializare.setError("Nu ai introdus specializarea!");
        }else if(etSpecializarePrecizare.getText().toString().isEmpty()){
            etSpecializarePrecizare.setError("Nu ai introdus precizarea specializării!");
        }else if(etOras.getText().toString().isEmpty()){
            etOras.setError("Nu ai introduss orașul!");
        }else if(etStrada.getText().toString().isEmpty()){
            etStrada.setError("Nu ai introdus strada!");
        }else if(etNr.getText().toString().isEmpty()){
            etNr.setError("Nu ai introdus numărul!");
        }
        return validare;
    }
}
