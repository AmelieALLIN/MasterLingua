package com.example.masterlingua;

import com.orm.SugarRecord;

import java.io.Serializable;

public class Deck extends SugarRecord implements Serializable {
    private String iddeck;
    private String nomdeck;

    public Deck(){

    }
    public Deck(String id) {
        this.id_deck = id;
        this.nom_deck = "";
    }

    public Deck(String id, String nom) {
        this.iddeck = id;
        this.nomdeck = nom;
    }


   public String getId_deck(){
        return iddeck;
   }

   public String getNom_deck(){
        return nomdeck;
   }
}
