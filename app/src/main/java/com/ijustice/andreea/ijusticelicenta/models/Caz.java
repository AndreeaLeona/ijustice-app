package com.ijustice.andreea.ijusticelicenta.models;

import java.util.concurrent.atomic.AtomicInteger;

public class Caz {
    private String obiect;
    private int nrOrdine;
    private String numeSolicitant;
    private String data;
    private String descriere;
    private String cheie;


    public Caz(String obiect, int nrOrdine, String numeSolicitant, String data, String descriere,String cheie) {
        this.obiect = obiect;
        this.nrOrdine = nrOrdine;
        this.numeSolicitant = numeSolicitant;
        this.data = data;
        this.descriere = descriere;
        this.cheie=cheie;

    }


    public String getCheie() {
        return cheie;
    }

    public String getObiect() {
        return obiect;
    }

    public void setObiect(String obiect) {
        this.obiect = obiect;
    }

    public int getNrOrdine() {
        return nrOrdine;
    }

    public void setNrOrdine(int nrOrdine) {
        this.nrOrdine = nrOrdine;
    }

    public String getNumeSolicitant() {
        return numeSolicitant;
    }

    public void setNumeSolicitant(String numeSolicitant) {
        this.numeSolicitant = numeSolicitant;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getDescriere() {
        return descriere;
    }

    public void setDescriere(String descriere) {
        this.descriere = descriere;
    }
}
