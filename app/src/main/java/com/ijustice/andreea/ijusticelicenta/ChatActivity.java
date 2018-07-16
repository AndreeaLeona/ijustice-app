package com.ijustice.andreea.ijusticelicenta;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.GenericTypeIndicator;
import com.google.firebase.database.ValueEventListener;
import com.ijustice.andreea.ijusticelicenta.models.AdapterMesaje;
import com.ijustice.andreea.ijusticelicenta.models.Mesaj;
import com.ijustice.andreea.ijusticelicenta.models.MessageDetails;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ChatActivity extends AppCompatActivity {
    ImageView sendButton;
    EditText messageArea;
    FirebaseDatabase database;
    DatabaseReference reference;
    AdapterMesaje adapter;
    ListView lvMesaje;
    FirebaseAuth auth;
    List<Mesaj> mesaje;
    String uID;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);
        sendButton = (ImageView) findViewById(R.id.sendButton);
        messageArea = (EditText) findViewById(R.id.messageArea);
        database = FirebaseDatabase.getInstance();
        reference=database.getReference();
        auth = FirebaseAuth.getInstance();
        lvMesaje=(ListView) findViewById(R.id.lv_mesaje);
        mesaje=new ArrayList<Mesaj>();
        FirebaseUser user = auth.getCurrentUser();
        uID = user.getUid();
        Intent intent=getIntent();
        final String cheie= intent.getStringExtra("cheie");
        reference.child("mesaje").child(uID).child(cheie).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for(DataSnapshot ds:dataSnapshot.getChildren()){
                    String textMesaj=ds.child("text").getValue(String.class);
                    String nume=ds.child("user").getValue(String.class);
                    Mesaj mesaj=new Mesaj(textMesaj,nume);

                    mesaje.add(mesaj);

                }
                if(mesaje.size()!=0){
                    adapter=new AdapterMesaje(getApplicationContext(), (ArrayList<Mesaj>) mesaje);
                    lvMesaje.setAdapter(adapter);

                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
        reference.child("users").child(uID).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
              final  String nume= dataSnapshot.child("nume").getValue(String.class);
               final String prenume=dataSnapshot.child("prenume").getValue(String.class);

                sendButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String messageText = messageArea.getText().toString();

                        if (!messageText.equals("")) {
                            final  Map<String, String> map = new HashMap<String, String>();
                            map.put("text", messageText);
                            map.put("user", nume + " " + prenume);
                            Intent intent=getIntent();
                           final String cheie= intent.getStringExtra("cheie");
                            reference.child("mesaje").child(cheie).child(uID).push().setValue(map).addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    reference.child("mesaje").child(uID).child(cheie).push().setValue(map);
                                }
                            });


                            messageArea.setText("");
                        }
                    }
                });
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });



    }
}
