package com.example.masterlingua;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class AfficherDeck extends AppCompatActivity { /*
    ListView liste_cartes;
    TextView liste;
    ArrayList<Carte> cartes = new ArrayList<>();
    static ArrayList<String> questions = new ArrayList<>();
    Carte carte;
    static Deck deck;
    private Context context;
    int j;
    boolean intent_deck=false;
    private static boolean  deja_initialise=false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_affiche_deck);
        liste_cartes = (ListView) findViewById(R.id.liste_cartes);
        liste = findViewById(R.id.liste);
        Bundle bundle = getIntent().getExtras();
        Intent intent=getIntent();
        if(deja_initialise==false){deck = (Deck) bundle.getSerializable("deck");}


        if(deja_initialise==false){
            if (!deck.getCartes().isEmpty()) {
                for (int i = 0; i < deck.getCartes().size(); i++) {
                    questions.add(deck.getCartes().get(i).getQuestion());
                }
            }
        }

        final ArrayAdapter adapter_cartes = new ArrayAdapter(this, android.R.layout.simple_list_item_1, questions);
        liste_cartes.setAdapter(adapter_cartes);
        liste_cartes.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String choix = parent.getItemAtPosition(position).toString();
                carte = deck.contains(choix);
                System.out.println("la question de l'objet"+carte.getQuestion());
                System.out.println(carte.getBonne_rep());
                Intent afficher = new Intent(getApplicationContext(), JouerCarte.class);
                Bundle bundle = new Bundle();
                bundle.putSerializable("carte", carte);
                afficher.putExtras(bundle);
                startActivity(afficher);
                finish();
                questions.remove(position);
                deja_initialise=true;

            }


        });


    }*/
}
