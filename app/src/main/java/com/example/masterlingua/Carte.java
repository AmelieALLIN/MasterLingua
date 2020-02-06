package com.example.masterlingua;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.io.Serializable;
import java.util.ArrayList;
import com.orm.SugarRecord;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Carte extends SugarRecord implements Serializable  {
    public String question;
    private List<String> reponses = new ArrayList<>();
    private String bonne_rep;

    public Carte() {

    }

    public Carte(String s, String r1, String r2, String r3, String s2){
        question = s;
        reponses.add(r1);
        reponses.add(r2);
        reponses.add(r3);
        bonne_rep = s2;
    }
    public Carte(String s, String r1, String r2, String s2){
        question = s;
        reponses.add(r1);
        reponses.add(r2);
        bonne_rep = s2;
    }
    public Carte(String s, String r1, String s2){
        question = s;
        reponses.add(r1);
        bonne_rep = s2;
    }
    public Carte(String s,String s2){
        question = s;
        bonne_rep = s2;
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
}
