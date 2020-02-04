package com.example.masterlingua;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Carte implements Serializable {
    private String question;
    private List<String> reponses = new ArrayList<>();
    private String bonne_rep;

    public Carte(String s, String r1, String r2, String r3, String br){
        question = s;
        reponses.add(r1);
        reponses.add(r2);
        reponses.add(r3);
        bonne_rep = br;
    }
    public Carte(String s, String r1, String r2, String br){
        question = s;
        reponses.add(r1);
        reponses.add(r2);
        bonne_rep = br;
    }
    public Carte(String s, String r1, String br){
        question = s;
        reponses.add(r1);
        bonne_rep = br;
    }
    public Carte(String s){
        question = s;
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

    public void setQuestion(String s){
        question = s;
    }

    public void ajoutReponse(String s){
        reponses.add(s);
    }
}
