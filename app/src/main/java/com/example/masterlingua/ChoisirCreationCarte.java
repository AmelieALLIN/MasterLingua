package com.example.masterlingua;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

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

    public void afficherListeDeck(View view) {
        Intent intent = new Intent(this,AfficherListeDeck.class);
        startActivity(intent);
    }

    public void creationDeck(View view) {
        Intent intent = new Intent(this,CreerDeck.class);
        startActivity(intent);
    }


    public void goswipe(View view) {
        Intent intent = new Intent(this,SwipeListeCarte.class);
        startActivity(intent);
    }



}
