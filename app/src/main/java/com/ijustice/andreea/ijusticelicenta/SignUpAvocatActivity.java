package com.ijustice.andreea.ijusticelicenta;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class SignUpAvocatActivity extends AppCompatActivity {
   private  Button btnInregistrare;
   private  EditText etEmail;
   private  EditText etParola;
   private  ProgressDialog progressDialog;
   private  FirebaseAuth auth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up_avocat);
        getSupportActionBar().setTitle(" ");
        init();
        ClickInregistrare();
    }
    public void init(){
        btnInregistrare=(Button)findViewById(R.id.sign_up_btn_inregistrare);
        etEmail=(EditText)findViewById(R.id.sign_up_et_email);
        etParola=(EditText)findViewById(R.id.sign_up_et_parola);

        progressDialog=new ProgressDialog(this);

        auth=FirebaseAuth.getInstance();
        if(auth.getCurrentUser()!=null){
            finish();
            startActivity(new Intent(getApplicationContext(),ContActivity.class));
        }
    }
    public void InregistrareUser(){
        String email=etEmail.getText().toString().trim();
        String parola=etParola.getText().toString().trim();

        if(TextUtils.isEmpty(email)){
            Toast.makeText(this, R.string.email_neintrodus,Toast.LENGTH_SHORT).show();
            return;
        }
        if(TextUtils.isEmpty(parola)){
            Toast.makeText(this, R.string.parola_neintrodusa,Toast.LENGTH_SHORT).show();
            return;
        }
        progressDialog.setMessage(getString(R.string.inregistrare_utilizator));
        progressDialog.show();

        auth.createUserWithEmailAndPassword(email,parola).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                        finish();
                        startActivity(new Intent(getApplicationContext(),InformatiiActivity.class));
                }else{
                    Toast.makeText(SignUpAvocatActivity.this, R.string.inregistrare_nereusita,Toast.LENGTH_SHORT).show();


                }
                progressDialog.dismiss();


            }
        });

    }
    public void ClickInregistrare(){
        btnInregistrare.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                InregistrareUser();
            }
        });
    }
}
