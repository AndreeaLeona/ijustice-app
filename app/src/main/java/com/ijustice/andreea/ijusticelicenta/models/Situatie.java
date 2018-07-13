package com.ijustice.andreea.ijusticelicenta.models;

public class Situatie {
    private String situatie;
    private String solutie;
    private String specializare;

    public Situatie(String situatie, String solutie, String specializare) {
        this.situatie = situatie;
        this.solutie = solutie;
        this.specializare = specializare;
    }

    public String getSituatie() {
        return situatie;
    }

    public void setSituatie(String situatie) {
        this.situatie = situatie;
    }

    public String getSolutie() {
        return solutie;
    }

    public void setSolutie(String solutie) {
        this.solutie = solutie;
    }

    public String getSpecializare() {
        return specializare;
    }

    public void setSpecializare(String specializare) {
        this.specializare = specializare;
    }

    @Override
    public String toString() {
        return situatie ;
    }
}
