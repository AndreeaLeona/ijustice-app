package com.ijustice.andreea.ijusticelicenta;

import android.app.FragmentTransaction;
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
import com.ijustice.andreea.ijusticelicenta.models.Caz;
import com.ijustice.andreea.ijusticelicenta.models.Client;
import com.ijustice.andreea.ijusticelicenta.models.DateDialog;

import java.util.HashMap;
import java.util.Map;

public class EditareCazActivity extends AppCompatActivity {
    EditText etObiect, etNumar, etNume, etData,etDescriere;
    Button btnAlveaza;
    FirebaseAuth auth;
    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;
    private String userId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editare_caz);

        auth = FirebaseAuth.getInstance();
        firebaseDatabase = FirebaseDatabase.getInstance();
        FirebaseUser user = auth.getCurrentUser();
        userId = user.getUid();
        etObiect=(EditText)findViewById(R.id.editeaza_caz_et_obiect);
        etNumar=(EditText)findViewById(R.id.editeaza_caz_et_numar);
        etNume=(EditText)findViewById(R.id.editeaza_caz_et_nume);
        etData=(EditText)findViewById(R.id.editeaza_caz_et_data);
        etDescriere=(EditText)findViewById(R.id.editeaza_caz_et_descriere);
        btnAlveaza=(Button) findViewById(R.id.editeaza_caz_btn_salveazamodificari);

        Intent intent=getIntent();

        if (intent.getExtras() != null) {

            final String cheie = intent.getStringExtra("cheie");
            databaseReference = firebaseDatabase.getReference("cazuri").child(userId).child(cheie);

            String obiect = intent.getStringExtra("obiect");
            int numar = intent.getIntExtra("numar", -1);
            String nume = intent.getStringExtra("numeSolicitant");
            String data = intent.getStringExtra("data");
            String descriere = intent.getStringExtra("descriere");

            etObiect.setText(obiect.toString());
            etNumar.setText(String.valueOf(numar));
            etNume.setText(nume.toString());
            etData.setText(data.toString());
            etDescriere.setText(descriere.toString());
            etData.setOnFocusChangeListener(new View.OnFocusChangeListener() {
                @Override
                public void onFocusChange(View v, boolean hasFocus) {
                    if(hasFocus){
                        DateDialog dialog=new DateDialog(v);
                        FragmentTransaction transaction=getFragmentManager().beginTransaction();
                        dialog.show(transaction,"Date Picker");
                    }
                }
            });
            btnAlveaza.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (validare()) {
                        Caz c = new Caz(etObiect.getText().toString(), Integer.parseInt(etNumar.getText().toString()), etNume.getText().toString(),
                                etData.getText().toString(), etDescriere.getText().toString(), cheie);
                        Map caz = new HashMap<>();
                        caz.put("obiect", c.getObiect());
                        caz.put("numar", c.getNrOrdine());
                        caz.put("numeSolicitant", c.getNumeSolicitant());
                        caz.put("data", c.getData());
                        caz.put("descriere", c.getDescriere());
                        databaseReference.updateChildren(caz);
                        Toast.makeText(getApplicationContext(), "Modificarile au fost realizate cu succes",
                                Toast.LENGTH_SHORT).show();
                    }
                }
            });
            }
        }

    public boolean validare() {
        boolean validare = true;
        if (etObiect.getText().toString().isEmpty()) {
            etObiect.setError("Nu ai introdus obiectul cazului!");
            validare = false;
        } else if (etObiect.getText().toString().length() < 5) {
            etObiect.setError("Obiectul cazului trebuie să fie format din minim 5 caractere!");
            validare = false;
        } else if (etNumar.getText().toString().isEmpty()) {
            etNumar.setError("Nu ai introdus numărul de ordine pe care îl aloci cazului!!");
            validare = false;
        } else if (etNume.getText().toString().isEmpty()) {
            etNume.setError("Nu ai introdus numele solicitantului/clientului!");
            validare = false;
        } else if (etNume.getText().toString().length() < 2) {
            etNume.setError("Numele solicitantului/clientului trebuie să fie format din minim 2 caractere!");
            validare = false;
        } else if (etData.getText().toString().isEmpty()) {
            etData.setError("Nu ai selectat data, selectează o dată pentru a putea continua!");
            validare = false;
        } else if (etDescriere.getText().toString().isEmpty()) {
            etDescriere.setError("Nu ai introdus descrierrea cazului!");
            validare = false;
        }
        return validare;
    }
    }
