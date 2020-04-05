package com.example.masterlingua;

import com.orm.SugarRecord;
import java.util.UUID;

public class CarteCategorie extends SugarRecord {
    private String idCarte;
    private String idCategorie;
    private String idCarteCategorie;

    public CarteCategorie(){}

    public CarteCategorie(String idCarte, String idCategorie){
        idCarteCategorie = UUID.randomUUID().toString();
        this.idCarte = idCarte;
        this.idCategorie = idCategorie;
    }

    public String getIdCarte(){
        return this.idCarte;
    }

    public String getIdCategorie() {
        return idCategorie;
    }

    public String getIdCarteCategorie() {
        return idCarteCategorie;
    }

    public void setIdCarte(String idCarte) {
        this.idCarte = idCarte;
    }
}
