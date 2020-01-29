package com.example.masterlingua;

import java.util.ArrayList;
import java.util.List;

public class Carte {
    private String question;
    private List<String> reponses = new ArrayList<>();
    private String bonne_rep;
    private String langueQuestion;
    private String langueReponse;

    public Carte(String q, String r, String s, String r1, String r2, String r3, String br){
        langueQuestion = q;
        langueReponse = r;
        question = s;
        reponses.add(r1);
        reponses.add(r2);
        reponses.add(r3);
        bonne_rep = br;
    }
    public Carte(String q, String r, String s, String r1, String r2, String br){
        langueQuestion = q;
        langueReponse = r;
        question = s;
        reponses.add(r1);
        reponses.add(r2);
        bonne_rep = br;
    }
    public Carte(String q, String r, String s, String r1, String br){
        langueQuestion = q;
        langueReponse = r;
        question = s;
        reponses.add(r1);
        bonne_rep = br;
    }
    public Carte(String q, String r, String s){
        langueQuestion = q;
        langueReponse = r;
        question = s;
    }

    public void setQuestion(String s){
        question = s;
    }
    public void ajoutReponse(String s){
        reponses.add(s);
    }

    public String getQuestion(){
        return question;
    }

    public List<String> getReponse()
    {
        return reponses;
    }

    public String getBonne_rep(){
        return bonne_rep;
    }

    public String getLangueReponse() {
        return langueReponse;
    }

    public void setLangueQuestion(String langueQuestion) {
        this.langueQuestion = langueQuestion;
    }

    public void setLangueReponse(String langueReponse) {
        this.langueReponse = langueReponse;
    }

    public String getLangueQuestion() {
        return langueQuestion;
    }
}
