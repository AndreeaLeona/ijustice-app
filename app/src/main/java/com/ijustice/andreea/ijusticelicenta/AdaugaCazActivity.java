package com.ijustice.andreea.ijusticelicenta;

import android.app.FragmentTransaction;
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
        setContentView(R.layout.activity_adauga_caz);
        etNume = (EditText) findViewById(R.id.adauga_caz_et_nume);
        etObiect = (EditText) findViewById(R.id.adauga_caz_et_obiect);
        etNumar = (EditText) findViewById(R.id.adauga_caz_et_numar);
        etData = (EditText) findViewById(R.id.adauga_caz_et_data);
        etDescriere = (EditText) findViewById(R.id.adauga_caz_et_descriere);

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


        btnAdauga = (Button) findViewById(R.id.adauga_caz_btn_adauga);
        auth = FirebaseAuth.getInstance();
        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference();
        FirebaseUser user = auth.getCurrentUser();
        userId = user.getUid();
        btnAdauga.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (validare()) {
                    String obiect = etObiect.getText().toString();
                    int numar = Integer.parseInt(etNumar.getText().toString());
                    String nume = etNume.getText().toString();
                    String data = etData.getText().toString();
                    String descriere = etDescriere.getText().toString();


                    Map caz = new HashMap<>();
                    caz.put("obiect", obiect);
                    caz.put("numar", numar);
                    caz.put("numeSolicitant",nume);
                    caz.put("data", data);
                    caz.put("descriere", descriere);


                    databaseReference.child("cazuri").child(userId).push().setValue(caz);
                    Toast.makeText(getApplicationContext(), "Datele tale au fost salvate cu succes!", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
    public boolean validare(){
        boolean validare=true;
        if(etObiect.getText().toString().isEmpty()){
            etObiect.setError("Nu ai introdus obiectul cazului!");
            validare=false;
        }else if(etObiect.getText().toString().length()<5){
            etObiect.setError("Obiectul cazului trebuie să fie format din minim 5 caractere!");
            validare=false;
        }else if(etNumar.getText().toString().isEmpty()){
            etNumar.setError("Nu ai introdus numărul de ordine pe care îl aloci cazului!!");
            validare=false;
        }else if(etNume.getText().toString().isEmpty()){
            etNume.setError("Nu ai introdus numele solicitantului/clientului!");
            validare=false;
        }else if(etNume.getText().toString().length()<2){
            etNume.setError("Numele solicitantului/clientului trebuie să fie format din minim 2 caractere!");
            validare=false;
        }else if(etData.getText().toString().isEmpty()){
            etData.setError("Nu ai selectat data, selectează o dată pentru a putea continua!");
            validare=false;
        }else if(etDescriere.getText().toString().isEmpty()){
            etDescriere.setError("Nu ai introdus descrierrea cazului!");
            validare=false;
        }
        return validare;
    }
}
