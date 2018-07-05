package com.ijustice.andreea.ijusticelicenta;

import android.annotation.SuppressLint;
import android.app.FragmentManager;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class DetaliiClientActivity extends AppCompatActivity {
    TextView tvNume,tvPrenume,tvAdresa, tvOras,tvTelefon, tvEmail,tvPrecizari;
    ImageButton imgBtnEditare;
    ImageButton imgBtnStergere;
    FirebaseAuth auth;
    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;
    private String userId;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalii_client);
        auth=FirebaseAuth.getInstance();
        firebaseDatabase=FirebaseDatabase.getInstance();
        FirebaseUser user=auth.getCurrentUser();
        userId=user.getUid();
        tvNume=(TextView)findViewById(R.id.vizualizeaza_client_et_nume);
        imgBtnEditare=(ImageButton)findViewById(R.id.imageBtnEdit) ;
        imgBtnStergere=(ImageButton)findViewById(R.id.imageBtnDelete);
        tvPrenume=(TextView)findViewById(R.id.vizualizeaza_client_et_prenume);
        tvAdresa=(TextView)findViewById(R.id.vizualizeaza_client_et_adresa);
        tvTelefon=(TextView)findViewById(R.id.vizualizeaza_client_et_telefon);
        tvEmail=(TextView)findViewById(R.id.vizualizeaza_client_et_email);
        tvOras=(TextView)findViewById(R.id.vizualizeaza_client_et_oras);
        tvPrecizari=(TextView)findViewById(R.id.vizualizeaza_client_et_precizari);
        Intent intent = getIntent();
        if (intent.getExtras() != null) {
             final int id=intent.getIntExtra("id",-1);
             databaseReference=firebaseDatabase.getReference("clienti").child(userId).child(String.valueOf(id));
             final String  nume = intent.getStringExtra("Nume");
             final String prenume = intent.getStringExtra("Prenume");
             final String adresa = intent.getStringExtra("Adresa");
             final String oras = intent.getStringExtra("Oras");
             final String telefon = intent.getStringExtra("Telefon");
             final String email = intent.getStringExtra("Email");
             final String precizari = intent.getStringExtra("Precizari");
            tvNume.setText(nume);
            tvPrenume.setText(prenume);
            tvAdresa.setText(adresa);
            tvOras.setText(oras);
            tvTelefon.setText(telefon);
            tvEmail.setText(email);
            tvPrecizari.setText(precizari);
            imgBtnEditare.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent i=new Intent(getApplicationContext(),EditareClientActivity.class);
                    i.putExtra("id",id);
                    i.putExtra("nume",nume);
                    i.putExtra("prenume",prenume);
                    i.putExtra("adresa",adresa);
                    i.putExtra("oras",oras);
                    i.putExtra("telefon",telefon);
                    i.putExtra("email",email);
                    i.putExtra("precizari",precizari);
                    startActivity(i);
                }
            });
            imgBtnStergere.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    AlertDialog diaBox = AskOption();
                    diaBox.show();
                }
            });


        }


    }
    private AlertDialog AskOption()
    {
        AlertDialog myQuittingDialogBox =new AlertDialog.Builder(this)
                //set message, title, and icon
                .setTitle("Ștergere")
                .setMessage("Ești sigur că vrei să ștergi acest client?")
                .setIcon(R.drawable.ic_delete)

                .setPositiveButton("Șterge", new DialogInterface.OnClickListener() {

                    @SuppressLint("ResourceType")
                    public void onClick(DialogInterface dialog, int whichButton) {
                        databaseReference.removeValue();
                        dialog.dismiss();


                    }

                })

                .setNegativeButton("Anulează", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {

                        dialog.dismiss();

                    }
                })
                .create();
        return myQuittingDialogBox;

    }

}
