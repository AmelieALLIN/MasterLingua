package com.example.masterlingua;

public class Reponse {
    private int id;
    private String nom;

    public Reponse(int id, String nom) {
        this.id = id;
        this.nom = nom;
    }

    public int getId() {
        return id;
    }

    public String getNom() {
        return nom;
    }
}
