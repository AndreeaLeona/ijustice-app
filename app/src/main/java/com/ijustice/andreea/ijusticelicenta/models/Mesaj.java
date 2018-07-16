package com.ijustice.andreea.ijusticelicenta.models;

public class Mesaj {
    private String continut;
    private String numeTrimitator;


    public Mesaj(String continut, String numeTrimitator) {
        this.continut = continut;
        this.numeTrimitator = numeTrimitator;
    }

    public String getNumeTrimitator() {
        return numeTrimitator;
    }

    public String getContinut() {
        return continut;
    }

    public void setContinut(String continut) {
        this.continut = continut;
    }
}
