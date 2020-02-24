package com.example.masterlingua;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Categorie {
    private String nom;
    //private static int id = 0; // à voir plus tard
    private Map<Carte, Boolean> categorieAppartenance = new HashMap<>();

    /** La map sert à stocker les cartes,
     le boolean associé à chaque carte permet de savoir si la carte est taguée
     (appartient à la catégorie) ou nom.
     On remplit la map avec la liste contenant toutes les cartes créées **/

    public Categorie(String nomTag, List<Carte> cartes) {
        nom = nomTag;
        for (int i = 0; i < cartes.size(); i++) {
            categorieAppartenance.put(cartes.get(i), false);
        }
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
    }
}
