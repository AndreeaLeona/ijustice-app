package com.ijustice.andreea.ijusticelicenta;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    Button btnAvocat;
    Button btnClient;
    

    public void init(){
        btnAvocat=(Button)findViewById(R.id.main_btn_avocat);
        btnClient=(Button)findViewById(R.id.main_btn_client);
        getSupportActionBar().setTitle("");

        btnAvocat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MainActivity.this,LogInAvocatActivity.class);
                startActivity(intent);
                btnClient.setEnabled(false);
                btnClient.setVisibility(View.GONE);

            }
        });
        btnClient.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MainActivity.this,LogInActivity.class);
                startActivity(intent);
                btnAvocat.setEnabled(false);
                btnAvocat.setVisibility(View.GONE);
            }
        });
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
    }
}
