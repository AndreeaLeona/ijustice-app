package com.ijustice.andreea.ijusticelicenta;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

public class DetaliiClientActivity extends AppCompatActivity {
    TextView tvNume,tvPrenume,tvAdresa, tvOras,tvTelefon, tvEmail,tvPrecizari;
    ImageButton imgBtnEditare;
    ImageButton imgBtnStergere;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalii_client);
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

        }




    }

}
