package com.example.masterlingua;

import com.orm.SugarRecord;

public class ReponseImage extends SugarRecord implements Reponse{
    private String idreponse;
    private boolean br;
    private String idcarte;

    public ReponseImage() {

    }

    public ReponseImage(String id, String carte, boolean br) {
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
