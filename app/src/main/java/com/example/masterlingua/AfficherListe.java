package com.example.masterlingua;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class AfficherListe extends AppCompatActivity {
    ListView CarteListView;
    List<QuestionText> questions = QuestionText.listAll(QuestionText.class);
    EditText nom_deck;
    // Bundle bundle = getIntent().getExtras();
    // List<Categorie> categories = Categorie.listAll(Categorie.class);

    List<String> questioncarte = new ArrayList<>();
    List<QuestionText> questionfind = new ArrayList<>();
    List<Carte> idcartes = new ArrayList<>();
    Carte carte;
    Spinner spinCategories;
    //ArrayAdapter<Categorie> adapt;
    List<CarteCategorie> cartesCat = CarteCategorie.listAll(CarteCategorie.class);
    List<Carte> cartesSelected = new ArrayList<>();
    String idCat;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_afficher_liste);

        nom_deck = findViewById(R.id.nom_deck);
        CarteListView = findViewById(R.id.myListView);
        spinCategories = findViewById(R.id.spinCategories);

        idCat = UUID.randomUUID().toString();
        spinCategories.setOnItemSelectedListener(new OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String nomCat = spinCategories.getItemAtPosition(position).toString();
                Categorie catChoisie = new Categorie(idCat, nomCat);
                System.out.println("YOOOO " + catChoisie.getNomCategorie());
                // cette boucle parcoure la liste des catégries existantes pour comparer à la catégorie choisie par
                // l'utilisateur et trouver l'ID correspondant, puis va parcourir la liste des cartes existantes
                // pour pouvoir trouver les cartes qui ont leur ID associé à la catégorie choisie dans la table de jointure
                // Ensuite on va chercher les questions filtrées par la catégorie
                for (int i = 0; i < cartesCat.size(); i++) {
                    if (cartesCat.get(i).getIdCategorie().equals(catChoisie.getIdCategorie())) {
                        for (int j = 0; j < idcartes.size(); j++) {
                            if (idcartes.get(j).getIdCarte().equals(cartesCat.get(i).getIdCarte())) {
                                cartesSelected.add(idcartes.get(j));
                                for (int k = 0; k < questions.size(); k++) {
                                    if (questions.get(k).getIdCarte().equals(cartesSelected.get(j).getIdCarte()))
                                        questioncarte.add(questions.get(k).getNom_question());
                                }
                            }
                        }
                    }
                }
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) { }
        });

        // Afficher les catégories/thèmes dans une liste déroulante
        /*if(!categories.isEmpty()) {
            adapt = new ArrayAdapter<>(getApplicationContext(), android.R.layout.simple_dropdown_item_1line, categories);
            spinCategories.setAdapter(adapt);
        }*/

        for (int i = 0; i < questions.size(); i++) {
            questioncarte.add(questions.get(i).getNom_question());
        }
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, questioncarte);
        CarteListView.setAdapter(adapter);
        CarteListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String nomC = (String) CarteListView.getItemAtPosition(position);
                questionfind = QuestionText.find(QuestionText.class, "nomquestion = ?", nomC);
                for (int i = 0; i < questionfind.size(); i++) {
                    idcartes = Carte.find(Carte.class, "idcarte = ?", questionfind.get(i).getIdCarte());
                }
                for (int n = 0; n < idcartes.size(); n++) {
                    carte = idcartes.get(n);
                }
                Intent jouerCarte = new Intent(getApplicationContext(), JouerCarte.class);
                Bundle bundle = new Bundle();
                bundle.putSerializable("carte", carte);
                jouerCarte.putExtras(bundle);
                startActivity(jouerCarte);
                finish();
            }
        });
    }
}