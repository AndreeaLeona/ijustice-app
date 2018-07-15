package com.ijustice.andreea.ijusticelicenta;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class DetaliiCazActivity extends AppCompatActivity {
    TextView tvObiect,tvNumar,tvNume,tvData,tvDescriere;
    ImageButton imgBtnEditare;
    ImageButton imgBtnStergere;
    FirebaseAuth auth;
    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;
    private String userId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalii_caz);
        auth=FirebaseAuth.getInstance();
        firebaseDatabase=FirebaseDatabase.getInstance();
        FirebaseUser user=auth.getCurrentUser();
        userId=user.getUid();

        tvObiect=(TextView)findViewById(R.id.tv_obiect);
        tvNumar=(TextView)findViewById(R.id.tv_numar);
        tvNume=(TextView)findViewById(R.id.tv_nume);
        tvData=(TextView)findViewById(R.id.tv_data);
        tvDescriere=(TextView)findViewById(R.id.tv_descriere);
        imgBtnEditare=(ImageButton)findViewById(R.id.imageBtnEditCaz) ;
        imgBtnStergere=(ImageButton)findViewById(R.id.imageBtnDeleteCaz);
        Intent intent = getIntent();
        if (intent.getExtras() != null) {
            final String cheie=intent.getStringExtra("cheie");
            databaseReference = firebaseDatabase.getReference("cazuri").child(userId).child(cheie);
            final String obiect = intent.getStringExtra("obiect");
            final int numar = intent.getIntExtra("numar", -1);
            final String nume = intent.getStringExtra("numeSolicitant");
            final String data = intent.getStringExtra("data");
            final String descriere = intent.getStringExtra("descriere");

            tvObiect.setText(obiect);
            tvNumar.setText(String.valueOf(numar));
            tvNume.setText(nume);
            tvData.setText(data);
            tvDescriere.setText(descriere);

            imgBtnEditare.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent i=new Intent(getApplicationContext(),EditareCazActivity.class);
                    i.putExtra("cheie",cheie);
                    i.putExtra("obiect",obiect);
                    i.putExtra("numar",numar);
                    i.putExtra("numeSolicitant",nume);
                    i.putExtra("data",data);
                    i.putExtra("descriere",descriere);

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
                .setMessage("Ești sigur că vrei să ștergi acest caz?")
                .setIcon(R.drawable.ic_delete)

                .setPositiveButton("Șterge", new DialogInterface.OnClickListener() {

                    @SuppressLint("ResourceType")
                    public void onClick(DialogInterface dialog, int whichButton) {
                        databaseReference.removeValue();
                        dialog.dismiss();
                        Toast.makeText(getApplicationContext(),"Cazul a fost șters",Toast.LENGTH_SHORT).show();
                        tvObiect.setText("Aceste date au fost șterse!");
                        tvNumar.setText("Aceste date au fost șterse!");
                        tvNume.setText("Aceste date au fost șterse!");
                        tvData.setText("Aceste date au fost șterse!");
                        tvDescriere.setText("Aceste date au fost șterse!");




                    }

                })

                .setNegativeButton("Anulează", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {

                        dialog.dismiss();
                        Toast.makeText(getApplicationContext(),"Ștergerea a fost anulată",Toast.LENGTH_SHORT).show();


                    }
                })
                .create();
        return myQuittingDialogBox;

    }
}
