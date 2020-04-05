package com.example.masterlingua;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

public class AfficherListeDeck extends AppCompatActivity {
    ListView DeckListView;
    List<Deck> decks = Deck.listAll(Deck.class);
    List<String> nom_deck = new ArrayList<>();
    List<Deck> deckfindid = new ArrayList<>();
    Deck deck;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_affiche_listedeck);

        DeckListView = findViewById(R.id.myListViewDeck);

        for (int i = 0; i < decks.size(); i++) {
            nom_deck.add(decks.get(i).getNom_deck());
            System.out.println(" LAAAAA = " + decks.get(i).getNom_deck());
            System.out.println("LLAAAAAA nom deck = "+nom_deck.get(i));
        }

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, nom_deck);
        DeckListView.setAdapter(adapter);

        DeckListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String nomd = (String) DeckListView.getItemAtPosition(position);
                deckfindid = Deck.find(Deck.class, "nomdeck = ?", nomd);
                for(int i=0;i<deckfindid.size();i++){
                    deck = deckfindid.get(i);
                }
                Intent afficherDeck = new Intent(getApplicationContext(), JouerDeck3Type.class);
                Bundle bundle = new Bundle();
                bundle.putSerializable("deck", deck);
                afficherDeck.putExtras(bundle);
                startActivity(afficherDeck);
                finish();
            }
        });
    }
}