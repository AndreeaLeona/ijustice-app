package com.ijustice.andreea.ijusticelicenta;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.GenericTypeIndicator;
import com.ijustice.andreea.ijusticelicenta.models.MessageDetails;

import java.util.HashMap;
import java.util.Map;

public class ChatActivity extends AppCompatActivity {
    LinearLayout layout;
    RelativeLayout layout_2;
    ImageView sendButton;
    EditText messageArea;
    ScrollView scrollView;
    FirebaseDatabase database;
    DatabaseReference reference1;
    DatabaseReference reference2;
    FirebaseAuth auth;
    String uID;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);
        layout = (LinearLayout) findViewById(R.id.layout1);
        layout_2 = (RelativeLayout)findViewById(R.id.layout2);
        sendButton = (ImageView)findViewById(R.id.sendButton);
        messageArea = (EditText)findViewById(R.id.messageArea);
        scrollView = (ScrollView)findViewById(R.id.scrollView);
        database=FirebaseDatabase.getInstance();
        auth=FirebaseAuth.getInstance();
        FirebaseUser user=auth.getCurrentUser();
        uID=user.getUid();
        MessageDetails.currentUser=uID;
        reference1=database.getReference("mesaje").child(MessageDetails.currentUser + "_" + MessageDetails.chatWith);
        reference2=database.getReference("mesaje").child(MessageDetails.chatWith + "_" + MessageDetails.currentUser);
        sendButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String messageText = messageArea.getText().toString();

                if(!messageText.equals("")) {
                   Map<String, String> map = new HashMap<String, String>();
                    map.put("message", messageText);
                    map.put("user", MessageDetails.currentUser);
                    reference1.push().setValue(map);
                    reference2.push().setValue(map);

                    messageArea.setText("");
                }
            }
        });

         reference1.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                Map<String,Object> map = (Map<String,Object>)dataSnapshot.getValue();
                String message = map.get("message").toString();
                String userName = map.get("user").toString();


               if(userName.equals(MessageDetails.currentUser)){
                addMessageBox("You:-\n" + message, 1);
               } else{
                  addMessageBox(MessageDetails.chatWith + ":-\n" + message, 2); }

                }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }


    public void addMessageBox(String message, int type){
        TextView textView = new TextView(ChatActivity.this);
        textView.setText(message);

        LinearLayout.LayoutParams lp2 = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        lp2.weight = 1.0f;

        if(type == 1) {
            lp2.gravity = Gravity.LEFT;
            textView.setBackgroundResource(R.color.grenaSecundar);
        }
        else{
            lp2.gravity = Gravity.RIGHT;
            textView.setBackgroundResource(R.color.darkblue);
        }
        textView.setLayoutParams(lp2);
        layout.addView(textView);
        scrollView.fullScroll(View.FOCUS_DOWN);
    }
}