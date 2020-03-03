package com.example.masterlingua;

import com.orm.SugarRecord;

import java.io.Serializable;
/*import java.util.HashMap;
import java.util.List;
import java.util.Map;*/

public class Categorie extends SugarRecord implements Serializable {
    private String idCategorie;
    private String nomCategorie;

    /** Une catégorie ne peut être créée qu'une fois, le nom doit être unique
     * il ne peut pas exister deux catégories avec le même nom
     * penser à faire le test avant de créer une catégorie **/

    public Categorie(){}

    public Categorie(String name){
        nomCategorie = name;
    }

    public String getIdCategorie() {
        return idCategorie;
    }
    public String getNomCategorie() {
        return nomCategorie;
    }

    //private static int id = 0; // à voir plus tard
    //private Map<Carte, Boolean>  = new HashMap<>();

    /** La map sert à stocker les cartes,
     le boolean associé à chaque carte permet de savoir si la carte est taguée
     (appartient à la catégorie) ou nom.
     On remplit la map avec la liste contenant toutes les cartes créées **/

    /*public Categorie(String nomTag, List<Carte> cartes) {
        nom = nomTag;
        for (int i = 0; i < cartes.size(); i++) {
            categorieAppartenance.put(cartes.get(i), false);
        }
    }

    public String getNom() {
        return nom;
    }

    public Map<Carte, Boolean> getCategorieAppartenance() {
        return categorieAppartenance;
    }

    public void addToCategory(Carte carte) {
        if (categorieAppartenance.containsKey(carte)) {
            categorieAppartenance.put(carte, true);

        }
    }
    public void deleteFromCategory(Carte carte) {
        if (categorieAppartenance.containsKey(carte)) {
            categorieAppartenance.remove(carte);
        }
    }*/
}
