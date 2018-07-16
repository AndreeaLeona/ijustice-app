package com.ijustice.andreea.ijusticelicenta;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.ijustice.andreea.ijusticelicenta.models.UserAvocat;

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
    EditText etSpecializare;
    EditText etSpecializarePrecizare;
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
            etSpecializare=(EditText)findViewById(R.id.edit_et_specializare);
            etSpecializarePrecizare=(EditText)findViewById(R.id.edit_et_specializare_precizare);
            btnSalveaza = (Button) findViewById(R.id.edit_btn_salveaza_modificari);
            auth = FirebaseAuth.getInstance();
            firebaseDatabase = FirebaseDatabase.getInstance();
            databaseReference = firebaseDatabase.getReference("users");
            final FirebaseUser user = auth.getCurrentUser();
            userId = user.getUid();


            databaseReference.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(final DataSnapshot dataSnapshot) {
                    String nume = dataSnapshot.child(userId).child("nume").getValue(String.class);
                    String prenume = dataSnapshot.child(userId).child("prenume").getValue(String.class);
                    String email = dataSnapshot.child(userId).child("email").getValue(String.class);
                    String nrTelefon = dataSnapshot.child(userId).child("numarTelefon").getValue(String.class);
                    int cazuriRezolvate = dataSnapshot.child(userId).child("cazuriRezolvate").getValue(int.class);
                    int cazuriPierdute = dataSnapshot.child(userId).child("cazuriPierdute").getValue(int.class);
                    String oras = dataSnapshot.child(userId).child("oras").getValue(String.class);
                    String strada = dataSnapshot.child(userId).child("strada").getValue(String.class);
                    int nr = dataSnapshot.child(userId).child("nr").getValue(int.class);
                    String specializare=dataSnapshot.child(userId).child("specializare").getValue(String.class);
                    String specializarePrecizare=dataSnapshot.child(userId).child("specializarePrecizare").getValue(String.class);




              etNume.setText(nume);
              etPrenume.setText(prenume);
              etEmail.setText(email);
              etNrTelefon.setText(nrTelefon);
              etCazuriRezolvate.setText(String.valueOf(cazuriRezolvate));
              etCazuriPierdute.setText(String.valueOf(cazuriPierdute));
              etSpecializare.setText(specializare);
              etSpecializarePrecizare.setText(specializarePrecizare);
              etOras.setText(oras);
              etStrada.setText(strada);
              etNr.setText(String.valueOf(nr));



                    btnSalveaza.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            if (validare()) {

                                databaseReference.child(userId).child("nume").setValue(etNume.getText().toString());
                                databaseReference.child(userId).child("prenume").setValue(etPrenume.getText().toString());
                                databaseReference.child(userId).child("email").setValue(etEmail.getText().toString());
                                databaseReference.child(userId).child("numarTelefon").setValue(etNrTelefon.getText().toString());
                                databaseReference.child(userId).child("cazuriRezolvate").setValue(Integer.parseInt(etCazuriRezolvate.getText().toString()));
                                databaseReference.child(userId).child("cazuriPierdute").setValue(Integer.parseInt(etCazuriPierdute.getText().toString()));
                                databaseReference.child(userId).child("specializare").setValue(etSpecializare.getText().toString());
                                databaseReference.child(userId).child("specializarePrecizare").setValue(etSpecializarePrecizare.getText().toString());
                                databaseReference.child(userId).child("oras").setValue(etOras.getText().toString());
                                databaseReference.child(userId).child("strada").setValue(etStrada.getText().toString());
                                databaseReference.child(userId).child("nr").setValue(Integer.parseInt(etNr.getText().toString()));
                                Toast.makeText(getApplicationContext(), "Modificarile au fost realizate cu succes1",
                                        Toast.LENGTH_SHORT).show();
                            }
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
    public boolean validare(){
        boolean validare=true;
        if(etNume.getText().toString().isEmpty()){
            etNume.setError("Nu ai intodus numele!");
        }else if(etNume.getText().toString().length()<2){
            etNume.setError("Numele introdus trebuie să fie format din minim două caractere");
        }else if(etPrenume.getText().toString().isEmpty()){
            etPrenume.setError("Nu ai introdus prenumele!");
        }else if(etPrenume.getText().toString().length()<2){
            etPrenume.setError("Introdu un prenume format din minim două caractere!");
        }else if(etEmail.getText().toString().isEmpty()){
            etEmail.setError("Nu ai introdus adresa de email!");
        }else if(etNrTelefon.getText().toString().isEmpty()){
            etNrTelefon.setError("Nu ai introdus numărul de telefon!");
        }else if(etNrTelefon.getText().toString().length()<10 || etNrTelefon.getText().toString().length()>10){
            etNrTelefon.setError("Numărul de telefon introdus nu este valid!");
        }else if(etCazuriRezolvate.getText().toString().isEmpty()){
            etCazuriRezolvate.setError("Nu ai introdus numărul cazurilor rezolvate!");
        }else if(etCazuriPierdute.getText().toString().isEmpty()){
            etCazuriPierdute.setError("Nu ai introdus numărul cazurilor pierdute!");
        }else if(etSpecializare.getText().toString().isEmpty()){
            etSpecializare.setError("Nu ai introdus specializarea!");
        }else if(etSpecializarePrecizare.getText().toString().isEmpty()){
            etSpecializarePrecizare.setError("Nu ai introdus precizarea specializării!");
        }else if(etOras.getText().toString().isEmpty()){
            etOras.setError("Nu ai introduss orașul!");
        }else if(etStrada.getText().toString().isEmpty()){
            etStrada.setError("Nu ai introdus strada!");
        }else if(etNr.getText().toString().isEmpty()){
            etNr.setError("Nu ai introdus numărul!");
        }
        return validare;
    }



}
