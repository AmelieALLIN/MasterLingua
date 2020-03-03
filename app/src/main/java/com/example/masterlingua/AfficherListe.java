package com.example.masterlingua;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.SparseBooleanArray;
import android.view.ActionMode;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class AfficherListe extends AppCompatActivity {
    ListView CarteListView;
    List<Carte> cartes = Carte.listAll(Carte.class);
    List<ReponseText> reponses = ReponseText.listAll(ReponseText.class);
    List<QuestionText> questions = QuestionText.listAll(QuestionText.class);
    private EditText nom_deck;
    Bundle bundle = getIntent().getExtras();
    List<Categorie> categories = (List<Categorie>) bundle.getSerializable("listeCategories");

    List<String> questioncarte = new ArrayList<>();
    List<QuestionText> questionfind = new ArrayList<>();
    List<Carte> idcarte = new ArrayList<>();
    ArrayList<Carte> l = new ArrayList<>();
    Carte carte;
    int count=0;
    Spinner spinCategories;
    ArrayAdapter<Categorie> adapt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_afficher_liste);

        spinCategories = findViewById(R.id.);
        adapt = new ArrayAdapter<>(getApplicationContext(), android.R.layout.simple_dropdown_item_1line, categories);
        spinCategories.setAdapter(adapt);

        nom_deck = findViewById(R.id.nom_deck);

        CarteListView = (ListView) findViewById(R.id.myListView);

        for (int i = 0; i < questions.size(); i++) {
            questioncarte.add(questions.get(i).getNom_question());
        }

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, questioncarte);
        CarteListView.setAdapter(adapter);

        CarteListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String nomC = (String) CarteListView.getItemAtPosition(position).toString();
                questionfind = QuestionText.find(QuestionText.class, "nomquestion = ?", nomC);
                for(int i=0;i<questionfind.size();i++){
                    idcarte = Carte.find(Carte.class, "idcarte = ?", questionfind.get(i).getIdCarte());
                }
                for (int n=0;n<idcarte.size();n++)
                {
                    carte = idcarte.get(n);
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

    public void creationCategorie(View v){
        Intent creer_categorie = new Intent(getApplicationContext(), CreerCategorie.class);
        Bundle bundle = new Bundle();
        bundle.putSerializable("cartes", (Serializable) carte);
        creer_categorie.putExtras(bundle);
        startActivity(creer_categorie);
        finish();
    }
}
