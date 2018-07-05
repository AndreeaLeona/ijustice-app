package com.ijustice.andreea.ijusticelicenta;

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

import java.util.HashMap;
import java.util.Map;

public class AdaugaCazActivity extends AppCompatActivity {

    EditText etObiect;
    EditText etNumar;
    EditText etNume;
    EditText etData;
    EditText etDescriere;
    FirebaseAuth auth;
    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;
    private String userId;
    Button btnAdauga;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adauga_client);
        etNume = (EditText) findViewById(R.id.adauga_caz_et_nume);
        etObiect = (EditText) findViewById(R.id.adauga_caz_et_obiect);
        etNumar = (EditText) findViewById(R.id.adauga_caz_et_numar);
        etData = (EditText) findViewById(R.id.adauga_caz_et_data);
        etDescriere = (EditText) findViewById(R.id.adauga_caz_et_descriere);


        btnAdauga = (Button) findViewById(R.id.adauga_caz_btn_adauga);
        auth = FirebaseAuth.getInstance();
        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference();
        FirebaseUser user = auth.getCurrentUser();
        userId = user.getUid();
        btnAdauga.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String obiect = etObiect.getText().toString();
                int numar = Integer.parseInt(etNumar.getText().toString());
                String nume = etNume.getText().toString();
                String data = etData.getText().toString();
                String descriere = etDescriere.getText().toString();


                Caz c = new Caz(obiect, numar, nume, data, descriere);
                Map caz = new HashMap<>();
                caz.put("obiect", c.getObiect());
                caz.put("numar", c.getNrOrdine());
                caz.put("nume", c.getNumeSolicitant());
                caz.put("data", c.getData());
                caz.put("descriere", c.getDescriere());

                String idCaz = String.valueOf(c.getId());

                databaseReference.child("cazuri").child(userId).child(idCaz).setValue(caz);
                Toast.makeText(getApplicationContext(), "Datele tale au fost salvate cu succes!", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
