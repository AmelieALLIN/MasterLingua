package com.example.masterlingua;

public class Question {
    private int id_question;
    private String nom_question;

    public Question(int id_question, String nom_question) {
        this.id_question = id_question;
        this.nom_question = nom_question;
    }

    public int getId_question() {
        return id_question;
    }

    public String getNom_question() {
        return nom_question;
    }
}
