package com.ijustice.andreea.ijusticelicenta.models;

import java.util.concurrent.atomic.AtomicInteger;

public class NotaAvocat {
    private String titlu;
    private String  data;
    private String detalii;
    private static final AtomicInteger count = new AtomicInteger(0);
    private int id;

    public NotaAvocat(String titlu, String data, String detalii) {

        this.titlu = titlu;
        this.data = data;
        this.detalii = detalii;
        this.id=count.incrementAndGet();
    }
    public NotaAvocat(){

    }

    public int getId() {
        return id;
    }

    public void setTitlu(String titlu) {
        this.titlu = titlu;
    }

    public void setData(String data) {
        this.data = data;
    }

    public void setDetalii(String detalii) {
        this.detalii = detalii;
    }

    public String getTitlu() {
        return titlu;
    }

    public String getData() {
        return data;
    }

    public String getDetalii() {
        return detalii;
    }

    @Override
    public String toString() {
        return "Titlu: " + this.titlu + "\n" + this.data;
    }
}
