package com.ijustice.andreea.ijusticelicenta;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class SchimbaParolaActivity extends AppCompatActivity {
    EditText etParola;
    Button btnSchimba;
    FirebaseAuth auth;
    ProgressDialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_schimba_parola);
        etParola=(EditText)findViewById(R.id.et_parola_schimba);
        dialog=new ProgressDialog(this);
        auth = FirebaseAuth.getInstance();
        btnSchimba=(Button)findViewById(R.id.schimba_btn_schimba_);
        btnSchimba.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                schimba(v);
            }
        });


    }

    public void schimba(View v){
        FirebaseUser user=FirebaseAuth.getInstance().getCurrentUser();
        if(user!=null){

            dialog.setMessage("Paorola este schimbată...");
            dialog.show();

            user.updatePassword(etParola.getText().toString()).addOnCompleteListener(new OnCompleteListener<Void>() {
                @Override
                public void onComplete(Task<Void> task) {
                    if( task.isSuccessful()){
                        dialog.dismiss();
                        Toast.makeText(getApplicationContext(),"Parola a fost schimbată",Toast.LENGTH_SHORT).show();
                        auth.signOut();
                        finish();
                        startActivity(new Intent(getApplicationContext(),LogInAvocatActivity.class));

                    }else{
                        dialog.dismiss();
                        Toast.makeText(getApplicationContext(),"Parola nu a fost schimbată, te rog să verifici conexiunea la Internet",Toast.LENGTH_SHORT).show();


                    }


                }
            });
        }

    }
}
