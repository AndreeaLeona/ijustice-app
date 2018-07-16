package com.ijustice.andreea.ijusticelicenta.models;

public class Mesaj {
    private String text;
    private String numeTrimitator;


    public Mesaj(String text, String nume) {
        this.text = text;
        this.numeTrimitator=nume;
    }

    public String getNumeTrimitator() {
        return numeTrimitator;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }


}
