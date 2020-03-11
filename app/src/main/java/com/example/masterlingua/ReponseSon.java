package com.example.masterlingua;

import com.orm.SugarRecord;

public class ReponseSon extends SugarRecord implements Reponse {
    private String idreponse;
    private boolean br;
    private String idcarte;

    public ReponseSon() {

    }

    public ReponseSon(String id, String carte, boolean br) {
        this.idreponse = id;
        this.idcarte = carte;
        this.br = br;
    }

    @Override
    public String getIdReponse() {
        return idreponse;
    }

    @Override
    public String getIdCarte(){
        return idcarte;
    }
}
