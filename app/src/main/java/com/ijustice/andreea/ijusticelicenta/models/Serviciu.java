package com.ijustice.andreea.ijusticelicenta.models;

import java.util.concurrent.atomic.AtomicInteger;

public class Serviciu {
    private String denumire;
    private int imagine;
    private static final AtomicInteger count = new AtomicInteger(0);
    private int id;

    public Serviciu(String denumire, int imagine) {
        this.denumire = denumire;
        this.imagine = imagine;
        this.id=count.incrementAndGet();

    }

    public int getId() {
        return id;
    }

    public String getDenumire() {
        return denumire;
    }

    public int getImagine() {
        return imagine;
    }
}
