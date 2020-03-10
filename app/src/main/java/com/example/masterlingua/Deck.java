package com.example.masterlingua;

import com.orm.SugarRecord;

import java.io.Serializable;

public class Deck extends SugarRecord implements Serializable {
    private String id_deck;
    private String nom_deck;

    public Deck(){

    }
    public Deck(String id) {
        this.id_deck = id;
        this.nom_deck = "";
    }

    public Deck(String id, String nom) {
        this.id_deck = id;
        this.nom_deck = nom;
    }


   public String getId_deck(){
        return id_deck;
   }

   public String getNom_deck(){
        return nom_deck;
   }
}
