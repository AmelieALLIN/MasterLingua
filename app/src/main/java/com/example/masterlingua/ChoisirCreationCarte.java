package com.example.masterlingua;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.util.UUID;

public class ChoisirCreationCarte extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bouton_creation_carte);
    }

    public void goCreerCarte(View view) {
        Intent intent = new Intent(this,MenuCreationCarte.class);
        startActivity(intent);
    }

    public void AfficherListe(View view) {
        Intent intent = new Intent(this,AfficherListe.class);
        startActivity(intent);
    }

    public void afficherListeQuestionImage(View view) {
        Intent intent = new Intent(this,AfficherListeQuestionImage.class);
        startActivity(intent);
    }

    public void AfficherListeDeck(View view) {
        Intent intent = new Intent(this,AfficherListeDeck.class);
        startActivity(intent);
    }

    public void CreationDeck(View view) {
        Intent intent = new Intent(this,CreerDeck.class);
        startActivity(intent);
    }

    public void DeckMultpile(View view) {
        String id_deck = UUID.randomUUID().toString();
        String nomdeck = "Deck1";
        Deck deck = new Deck(id_deck,nomdeck);
        deck.save();

        String idcarte2 = "53d501a4-801e-4908-9380-6b6a38a19c52";
        String id_cartedeck2 = UUID.randomUUID().toString();
        CartesDeck cartesDeck2 = new CartesDeck(id_cartedeck2,id_deck,idcarte2);
        System.out.println("-----------------------------------------Carte jointure creer");
        cartesDeck2.save();

        String idcarte3 = "e0d25b03-488a-4365-aa7e-e1c8b3659776";
        String id_cartedeck3 = UUID.randomUUID().toString();
        CartesDeck cartesDeck3 = new CartesDeck(id_cartedeck3,id_deck,idcarte3);
        System.out.println("-----------------------------------------Carte jointure creer");
        cartesDeck3.save();

        String idcarte = "dbda28b6-2980-450a-b465-d3dcddf8ea50";
        String id_cartedeck = UUID.randomUUID().toString();
        CartesDeck cartesDeck = new CartesDeck(id_cartedeck,id_deck,idcarte);
        System.out.println("-----------------------------------------Carte jointure creer");
        cartesDeck.save();

        Intent afficher = new Intent(getApplicationContext(), JouerDeck3Type.class);
        Bundle jouerdeck = new Bundle();
        jouerdeck.putSerializable("deck", deck);
        afficher.putExtras(jouerdeck);
        startActivity(afficher);
    }
}
