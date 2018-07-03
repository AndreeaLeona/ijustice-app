package com.ijustice.andreea.ijusticelicenta.models;

public class UserAvocat {
    private String nume;
    private String prenume;
    private String email;
    private String numarTelefon;
    private int cazuriRezolvate;
    private int cazuriPierdute;
    private String oras;
    private String strada;
    private int nr;
    private String specializare;

    public UserAvocat(String nume, String prenume, String email, String numarTelefon,
                      int cazuriRezolvate, int cazuriPierdute, String oras, String strada, int nr, String specializare) {
        this.nume = nume;
        this.prenume = prenume;
        this.email = email;
        this.numarTelefon = numarTelefon;
        this.cazuriRezolvate = cazuriRezolvate;
        this.cazuriPierdute = cazuriPierdute;
        this.oras = oras;
        this.strada = strada;
        this.nr = nr;
        this.specializare=specializare;
    }

    public UserAvocat() {
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

    public int getCazuriRezolvate() {
        return cazuriRezolvate;
    }

    public void setCazuriRezolvate(int cazuriRezolvate) {
        this.cazuriRezolvate = cazuriRezolvate;
    }

    public int getCazuriPierdute() {
        return cazuriPierdute;
    }

    public void setCazuriPierdute(int cazuriPierdute) {
        this.cazuriPierdute = cazuriPierdute;
    }

    public String getOras() {
        return oras;
    }

    public void setOras(String oras) {
        this.oras = oras;
    }

    public String getStrada() {
        return strada;
    }

    public void setStrada(String strada) {
        this.strada = strada;
    }

    public int getNr() {
        return nr;
    }

    public String getSpecializare() {
        return specializare;
    }

    public void setSpecializare(String specializare) {
        this.specializare = specializare;
    }

    public void setNr(int nr) {
        this.nr = nr;
    }

    @Override
    public String toString() {
        return  "Nume avocat: " + this.nume + " " + this.prenume + "\n" +
                "Specializare: " + this.specializare + "\n" +
                "Informatii contact:" + this.email + " " + this.numarTelefon + "\n"+
                "Cazuri pierdute: " + this.cazuriPierdute + ", " + "Cazuri rezolvate: "
                +this.cazuriRezolvate + "\n" + "Adresa: "   + this.oras + ", " + this.strada + ", " +this.nr + "\n";
    }
}
