package com.ijustice.andreea.ijusticelicenta.models;

public class ChatUser {
    public String email;
    public String Uid;
    public String firebaseToken;

    public ChatUser(){

    }

    public ChatUser(String email, String uid, String firebaseToken) {
        this.email = email;
        Uid = uid;
        this.firebaseToken = firebaseToken;
    }
}
