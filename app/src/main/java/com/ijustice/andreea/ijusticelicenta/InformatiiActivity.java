package com.ijustice.andreea.ijusticelicenta;

import android.nfc.Tag;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.ijustice.andreea.ijusticelicenta.models.User;

import java.lang.annotation.Target;
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
        etOras=(EditText)findViewById(R.id.info_et_oras);
        etStrada=(EditText) findViewById(R.id.info_et_strada);
        etNr=(EditText)findViewById(R.id.info_et_nr);
        btnSalveaza=(Button) findViewById(R.id.info_btn_salveaza);
        auth=FirebaseAuth.getInstance();
        firebaseDatabase=FirebaseDatabase.getInstance();
        databaseReference=firebaseDatabase.getReference();
        FirebaseUser user=auth.getCurrentUser();
        userId=user.getUid();


            btnSalveaza.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String nume = etNume.getText().toString();
                    String prenume = etPrenume.getText().toString();
                    String email = etEmail.getText().toString();
                    String numarTelefon = etNrTelefon.getText().toString();
                    int cazuriRezolvate = Integer.parseInt(etCazuriRezolvate.getText().toString());
                    int cazuriPierdute = Integer.parseInt(etCazuriPierdute.getText().toString());
                    String oras = etOras.getText().toString();
                    String strada = etStrada.getText().toString();
                    int nr = Integer.parseInt(etNr.getText().toString());
                    User u = new User(nume, prenume, email, numarTelefon, cazuriRezolvate, cazuriPierdute, oras, strada, nr);

                    Map informatii = new HashMap<>();

                    informatii.put("nume", u.getNume());
                    informatii.put("prenume", u.getPrenume());
                    informatii.put("email", u.getEmail());
                    informatii.put("numarTelefon", u.getNumarTelefon());
                    informatii.put("cazuriRezolvate", u.getCazuriRezolvate());
                    informatii.put("cazuriPierdute", u.getCazuriPierdute());
                    informatii.put("oras", u.getOras());
                    informatii.put("strada", u.getStrada());
                    informatii.put("nr", u.getNr());
                    try {

                        databaseReference.child("users").child(userId).setValue(informatii);
                        Toast.makeText(getApplicationContext(),"Datele tale au fost salvate cu succes!",Toast.LENGTH_SHORT).show();
                    }catch(Exception ex){
                        Toast.makeText(getApplicationContext(), "Datele tale nu au fost salvate, te rog sa verifici conexiunea la internet", Toast.LENGTH_SHORT).show();


                    }


                }
            });


    }
}
