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
import com.ijustice.andreea.ijusticelicenta.models.NotaAvocat;

import java.util.HashMap;
import java.util.Map;

public class DetaliiEvenimentActivity extends AppCompatActivity {
    EditText etTitlu;
    EditText etData;
    EditText etDetalii;
    FirebaseAuth auth;
    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;
    private String userId;
    Button btnSalveaza;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalii_eveniment);
        etTitlu=(EditText)findViewById(R.id.detalii_nota_et_titlu);
        etData=(EditText)findViewById(R.id.detalii_nota_et_data);
        etDetalii=(EditText)findViewById(R.id.detalii_nota_et_detalii);
        btnSalveaza=(Button) findViewById(R.id.detalii_nota_btn_salveaza);
        Intent intent=getIntent();
        String dataC=intent.getStringExtra("dataCalendar");
        etData.setText(dataC.toString());
        auth=FirebaseAuth.getInstance();
        firebaseDatabase=FirebaseDatabase.getInstance();
        databaseReference=firebaseDatabase.getReference();
        FirebaseUser user=auth.getCurrentUser();
        userId=user.getUid();

        btnSalveaza.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(validare()){
                String titlu=etTitlu.getText().toString();
                String data=etData.getText().toString();
                String detalii=etDetalii.getText().toString();

                NotaAvocat nota=new NotaAvocat(titlu, data,detalii);
                Map notite = new HashMap<>();
                notite.put("titlu", nota.getTitlu());
                notite.put("data",nota.getData());
                notite.put("detalii",nota.getDetalii());
                String idNotita=String.valueOf(nota.getId());

                databaseReference.child("notite").child(userId).child(idNotita).setValue(notite);
                Toast.makeText(getApplicationContext(), "Datele tale au fost salvate cu succes!", Toast.LENGTH_SHORT).show();

            }
            }
        });


    }
    public boolean validare(){
        boolean validare=true;
        if(etTitlu.getText().toString().isEmpty()){
            etTitlu.setError("Nu ai introdus titlul notiței");
            validare=false;
        }else if(etData.getText().toString().isEmpty()){
            etData.setError("Nu ai selectat data pentru care vrei să adaugi această notiță");
            validare=false;
        }else if(etDetalii.getText().toString().isEmpty()){
            etDetalii.setError("Nu ai introdus detaliile ce definesc notița");
            validare=false;
        }
        return  validare;
    }
}
