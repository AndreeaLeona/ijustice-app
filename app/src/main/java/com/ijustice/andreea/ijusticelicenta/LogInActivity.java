package com.ijustice.andreea.ijusticelicenta;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class LogInActivity extends AppCompatActivity {

    private Button btnSignUp;
    private Button btnLogIn;
    private Button btnUitatParola;
    private EditText etEmail;
    private EditText etParola;
    private FirebaseAuth auth;
    private ProgressDialog progressDialog;



    public void init(){

        btnSignUp=(Button)findViewById(R.id.log_in_btn_client_sign_up);
        btnLogIn=(Button)findViewById(R.id.log_in_client_btn_log);
        btnUitatParola=(Button)findViewById(R.id.log_in_client_btn_reset_parola);
        etEmail=(EditText)findViewById(R.id.log_in_client_et_email);
        etParola=(EditText)findViewById(R.id.log_in_client_et_parola);
        getSupportActionBar().setTitle("Justice");


        auth=FirebaseAuth.getInstance();
        if(auth.getCurrentUser()!=null){
            finish();
            startActivity(new Intent(getApplicationContext(),ClientActivity.class));
        }
        progressDialog=new ProgressDialog(this);


        btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getApplicationContext(),SignUpClientActivity.class);
                startActivity(intent);
            }
        });
       // btnUitatParola.setOnClickListener(new View.OnClickListener() {
          //  @Override
           // public void onClick(View v) {
             //   Intent intent=new Intent(getApplicationContext(),ResetPasswordActivity.class);
               // startActivity(intent);
           // }
      //  });
        btnLogIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                UserLogIn();

            }
        });
    }
    public void UserLogIn(){
        String email=etEmail.getText().toString();
        final  String parola=etParola.getText().toString();
        if(TextUtils.isEmpty(email)){
            Toast.makeText(getApplicationContext(), R.string.atentionare_email_neintrodus,Toast.LENGTH_SHORT).show();
            return;
        }
        if(TextUtils.isEmpty(parola)){
            Toast.makeText(getApplicationContext(), R.string.atentionare_parola_neintrodusa,Toast.LENGTH_SHORT).show();
            return;
        }
        progressDialog.setMessage(getString(R.string.progrss_dialog_text));
        progressDialog.show();
        auth.signInWithEmailAndPassword(email,parola).addOnCompleteListener((Activity) getApplicationContext(), new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                progressDialog.dismiss();
                if(task.isSuccessful()){
                    finish();
                    Intent intent=new Intent(getApplicationContext(),ClientActivity.class);
                    startActivity(intent);

                }

            }
        });

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_in);
        init();
    }

}
