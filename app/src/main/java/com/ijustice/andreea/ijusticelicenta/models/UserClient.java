package com.ijustice.andreea.ijusticelicenta.models;

public class UserClient {
    private String nume;
    private String adresa;
    private String email;
    private String numarTelefon;

    public UserClient(String nume, String adresa, String email, String numarTelefon) {
        this.nume = nume;
        this.adresa = adresa;
        this.email = email;
        this.numarTelefon = numarTelefon;
    }

    public String getNume() {
        return nume;
    }

    public void setNume(String nume) {
        this.nume = nume;
    }

    public String getAdresa() {
        return adresa;
    }

    public void setAdresa(String adresa) {
        this.adresa = adresa;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNumarTelefon() {
        return numarTelefon;
    }

    public void setNumarTelefon(String numarTelefon) {
        this.numarTelefon = numarTelefon;
    }

    @Override
    public String toString() {
        return "UserClient{" +
                "nume='" + nume + '\'' +
                ", adresa='" + adresa + '\'' +
                ", email='" + email + '\'' +
                ", numarTelefon='" + numarTelefon + '\'' +
                '}';
    }
}
