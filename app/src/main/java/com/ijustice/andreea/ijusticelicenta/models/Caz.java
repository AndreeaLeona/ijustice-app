package com.ijustice.andreea.ijusticelicenta.models;

import java.util.concurrent.atomic.AtomicInteger;

public class Caz {
    private String obiect;
    private int nrOrdine;
    private String numeSolicitant;
    private String data;
    private String descriere;
    private static final AtomicInteger count = new AtomicInteger(0);
    private int id;

    public Caz(String obiect, int nrOrdine, String numeSolicitant, String data, String descriere) {
        this.obiect = obiect;
        this.nrOrdine = nrOrdine;
        this.numeSolicitant = numeSolicitant;
        this.data = data;
        this.descriere = descriere;
        this.id=count.incrementAndGet();

    }

    public int getId() {
        return id;
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
