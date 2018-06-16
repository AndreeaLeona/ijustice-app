package com.ijustice.andreea.ijusticelicenta;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.ijustice.andreea.ijusticelicenta.models.User;

public class EditareInformatiiActivity extends AppCompatActivity {
    EditText etNume;
    EditText etPrenume;
    EditText etEmail;
    EditText etNrTelefon;
    EditText etCazuriRezolvate;
    EditText etCazuriPierdute;
    EditText etOras;
    EditText etStrada;
    EditText etNr;
    Button btnSalveaza;
    FirebaseAuth auth;
    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;
    private String userId;


    @Override
    protected void onCreate(Bundle savedInstanceState){
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_editare_informatii);
            try{
            etNume = (EditText) findViewById(R.id.edit_et_nume);
            etEmail = (EditText) findViewById(R.id.edit_et_email);
            etPrenume = (EditText) findViewById(R.id.edit_et_prenume);
            etNrTelefon = (EditText) findViewById(R.id.edit_et_nr_telefon);
            etCazuriRezolvate = (EditText) findViewById(R.id.edit_et_cazuri_rezolvate);
            etCazuriPierdute = (EditText) findViewById(R.id.edit_et_cazuri_pierdute);
            etOras = (EditText) findViewById(R.id.edit_et_oras);
            etStrada = (EditText) findViewById(R.id.edit_et_strada);
            etNr = (EditText) findViewById(R.id.edit_et_nr);
            btnSalveaza = (Button) findViewById(R.id.edit_btn_salveaza_modificari);
            auth = FirebaseAuth.getInstance();
            firebaseDatabase = FirebaseDatabase.getInstance();
            databaseReference = firebaseDatabase.getReference("users");
            FirebaseUser user = auth.getCurrentUser();
            userId = user.getUid();


            databaseReference.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    String nume = dataSnapshot.child(userId).child("nume").getValue(String.class);
                    String prenume = dataSnapshot.child(userId).child("prenume").getValue(String.class);
                    String email = dataSnapshot.child(userId).child("email").getValue(String.class);
                    String nrTelefon = dataSnapshot.child(userId).child("numarTelefon").getValue(String.class);
                    int cazuriRezolvate = dataSnapshot.child(userId).child("cazuriRezolvate").getValue(int.class);
                    int cazuriPierdute = dataSnapshot.child(userId).child("cazuriPierdute").getValue(int.class);
                    String oras = dataSnapshot.child(userId).child("oras").getValue(String.class);
                    String strada = dataSnapshot.child(userId).child("strada").getValue(String.class);
                    int nr = dataSnapshot.child(userId).child("nr").getValue(int.class);
                    User u = new User(nume, prenume, email, nrTelefon, cazuriRezolvate, cazuriPierdute, oras, strada, nr);




              etNume.setText(u.getNume().toString());
              etPrenume.setText(u.getPrenume().toString());
              etEmail.setText(u.getEmail().toString());
              etNrTelefon.setText(u.getNumarTelefon().toString());
              etCazuriRezolvate.setText(String.valueOf(u.getCazuriRezolvate()));
              etCazuriPierdute.setText(String.valueOf(u.getCazuriPierdute()));
              etOras.setText(u.getOras().toString());
              etStrada.setText(u.getStrada().toString());
              etNr.setText(String.valueOf(u.getNr()));



                    btnSalveaza.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            User nou = new User(etNume.getText().toString(), etPrenume.getText().toString(), etEmail.getText().toString(), etNrTelefon.getText().toString(),
                                    Integer.parseInt(etCazuriRezolvate.getText().toString()), Integer.parseInt(etCazuriPierdute.getText().toString()), etOras.getText().toString(),
                                    etStrada.getText().toString(), Integer.parseInt(etNr.getText().toString()));
                            databaseReference.child(userId).setValue(nou);
                            Toast.makeText(getApplicationContext(), "Modificarile au fost realizate cu succes1",
                                    Toast.LENGTH_SHORT).show();
                        }
                    });
                }

                @Override
                public void onCancelled(DatabaseError databaseError) {
                }
            });
            }catch (Exception ex){
                Toast.makeText(getApplicationContext(),"Nu exista date de editat",Toast.LENGTH_SHORT).show();
            }


        }



}
