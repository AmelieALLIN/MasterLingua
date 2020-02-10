package com.example.masterlingua;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Deck implements Serializable {
    private ArrayList<Carte> cartes;
    Carte carte;

    public Deck(ArrayList<Carte> c){
        cartes=c;
    }

    public ArrayList<Carte> getCartes() {
        return cartes;
    }


   public Carte contains(String question) {
        for (int i = 0; i < cartes.size(); i++) {
            if (cartes.get(i).getQuestion().equals(question))
                carte=cartes.get(i);
        }
        return carte;
    }


}
