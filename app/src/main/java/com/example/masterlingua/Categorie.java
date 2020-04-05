package com.example.masterlingua;

import com.orm.SugarRecord;

import java.io.Serializable;

public class Categorie extends SugarRecord implements Serializable {
    private String idCategorie;
    private String nomCategorie;

    /* Une catégorie ne peut être créée qu'une fois, le nom doit être unique
     * il ne peut pas exister deux catégories avec le même nom
     * penser à faire le test avant de créer une catégorie */

    public Categorie(){}

    public Categorie(String id, String name){
        idCategorie = id;
        nomCategorie = name;
    }

    public String getIdCategorie() {
        return idCategorie;
    }
    public String getNomCategorie() {
        return nomCategorie;
    }

}
