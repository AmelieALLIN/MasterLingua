package com.example.masterlingua;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

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
    List<CarteCategorie> cartesCat = CarteCategorie.listAll(CarteCategorie.class);
    List<Categorie>categories = Categorie.listAll(Categorie.class);
    List<QuestionText> questionsSelected = new ArrayList<>();
    List<String> idCartesSelected = new ArrayList<>();
    String idCat;
    Context context = this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_afficher_liste);

        nom_deck = findViewById(R.id.nom_deck);
        CarteListView = findViewById(R.id.myListView);
        spinCategories = findViewById(R.id.spinCategories);

        spinCategories.setOnItemSelectedListener(new OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String nomCat = spinCategories.getItemAtPosition(position).toString();
                for(int i=0; i<categories.size(); i++){
                    if(categories.get(i).getNomCategorie().equals(nomCat)){
                        idCat = categories.get(i).getIdCategorie();
                        break;
                    }
                }
                Categorie catChoisie = new Categorie(idCat, nomCat);
                System.out.println("AFFICHER PAR SELECTION " + catChoisie.getNomCategorie());

                // On consulte la table de jointure CarteCategorie
                // si une élément possède l'id de la catégorie choisie
                // il est selectionné
                for(int i=0; i<cartesCat.size(); i++){
                    if(catChoisie.getIdCategorie().equals(cartesCat.get(i).getIdCategorie())){
                        idCartesSelected.add(cartesCat.get(i).getIdCarte());
                        System.out.println("SELECT NIVEAU JOINTURE " + cartesCat.get(i).getIdCategorie());
                        break;
                    }
                    System.out.println("NO BITCH");
                }
                // une fois qu'on a la liste des id des cartes selectionnées on liste les objets carte
                for(int i=0; i<questions.size(); i++){
                    for(int j=0; j<idCartesSelected.size(); j++) {
                        if (questions.get(i).getIdCarte().equals(idCartesSelected.get(j))) {
                            questionsSelected.add(questions.get(i));
                            System.out.println("SELECT NIVEAU QUESTION " + questions.get(i).getNom_question());
                            break;
                        }
                    }
                }
                if(!questionsSelected.isEmpty()) {
                    questioncarte.clear();
                    for (int i = 0; i < questionsSelected.size(); i++) {
                        questioncarte.add(questionsSelected.get(i).getNom_question());
                    }
                }
                // si pas de questions selectionnes on affiche tout
                if(questionsSelected.isEmpty()) {
                    questioncarte.clear();
                    for (int i = 0; i < questions.size(); i++) {
                        questioncarte.add(questions.get(i).getNom_question());
                    }
                }
                ArrayAdapter<String> adapter = new ArrayAdapter<>(context, android.R.layout.simple_list_item_1, questioncarte);
                CarteListView.setAdapter(adapter);
                if(!questioncarte.isEmpty()) Toast.makeText(context, "Aucune carte", Toast.LENGTH_SHORT);
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
            @Override
            public void onNothingSelected(AdapterView<?> parent) { }
        });
    }
}