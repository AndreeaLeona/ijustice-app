package com.ijustice.andreea.ijusticelicenta;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import org.w3c.dom.Text;

public class DetaliiNotitaActivity extends AppCompatActivity {
    TextView tvTitlu;
    TextView tvDetalii;
    TextView tvData;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalii_notita);
        tvTitlu=(TextView) findViewById(R.id.tv_titlu_notita);
        tvData=(TextView)findViewById(R.id.tv_data_notita);
        tvDetalii=(TextView)findViewById(R.id.tv_detalii_notita);
        Intent intent=getIntent();
        String titlu=intent.getStringExtra("titlu");
        String data=intent.getStringExtra("data");
        String detalii=intent.getStringExtra("detalii");

        tvTitlu.setText(titlu.toString());
        tvData.setText(data.toString());
        tvDetalii.setText(detalii.toString());
    }
}
