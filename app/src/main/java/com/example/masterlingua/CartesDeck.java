package com.example.masterlingua;

import com.orm.SugarRecord;

public class CartesDeck extends SugarRecord {
    private String idCarteDeck;
    private String idDeck;
    private String idCarte;

    public CartesDeck() {}

    public CartesDeck(String id, String id_deck, String id_carte){
        this.idCarteDeck = id;
        this.idDeck = id_deck;
        this.idCarte = id_carte;
    }

    public String getId_deck(){
        return idDeck;
    }

    public String getId_carte(){
        return idCarte;
    }
}
