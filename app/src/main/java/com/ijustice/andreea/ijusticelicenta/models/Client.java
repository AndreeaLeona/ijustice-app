package com.ijustice.andreea.ijusticelicenta.models;

import java.util.concurrent.atomic.AtomicInteger;

public class Client {
    private String nume;
    private String prenume;
    private String adresa;
    private String oras;
    private String nrTelefon;
    private String adresaEmail;
    private String precizari;
    private static final AtomicInteger count = new AtomicInteger(0);
    private int id;

    public Client(String nume, String prenume, String adresa, String oras, String nrTelefon, String adresaEmail, String precizari) {
        this.nume = nume;
        this.prenume = prenume;
        this.adresa = adresa;
        this.oras = oras;
        this.nrTelefon = nrTelefon;
        this.adresaEmail = adresaEmail;
        this.precizari = precizari;
        this.id=count.incrementAndGet();
    }

    public int getId() {
        return id;
    }

    public Client() {
    }

    public String getNume() {
        return nume;
    }

    public void setNume(String nume) {
        this.nume = nume;
    }

    public String getPrenume() {
        return prenume;
    }

    public void setPrenume(String prenume) {
        this.prenume = prenume;
    }

    public String getAdresa() {
        return adresa;
    }

    public void setAdresa(String adresa) {
        this.adresa = adresa;
    }

    public String getOras() {
        return oras;
    }

    public void setOras(String oras) {
        this.oras = oras;
    }

    public String getNrTelefon() {
        return nrTelefon;
    }

    public void setNrTelefon(String nrTelefon) {
        this.nrTelefon = nrTelefon;
    }

    public String getAdresaEmail() {
        return adresaEmail;
    }

    public void setAdresaEmail(String adresaEmail) {
        this.adresaEmail = adresaEmail;
    }

    public String getPrecizari() {
        return precizari;
    }

    public void setPrecizari(String precizari) {
        this.precizari = precizari;
    }
}
