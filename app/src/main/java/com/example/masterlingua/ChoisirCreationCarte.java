package com.example.masterlingua;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class ChoisirCreationCarte extends AppCompatActivity implements Serializable {

    List<Carte> cartes = Carte.listAll(Carte.class);
    List<Carte> idcarte = new ArrayList<>();
    Carte carte;
    int score = 0;
    boolean jouer = false;


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

    public void goToTuto (View view){
        String nameToGo = "ChoisirCreationCarte";
        Intent intent = new Intent(this,AfficherTuto.class);
        Bundle bundle = new Bundle();
        bundle.putString("className", nameToGo);
        intent.putExtras(bundle);
        startActivity(intent);
    }

    public void listeCartes(View view) {

        if (cartes.isEmpty()) {
            Intent intent = new Intent(this, ExceptionNoCardCreated.class);
            startActivity(intent);
            finish();
        } else {
            for (int n = 0; n < cartes.size(); n++) {
                carte = cartes.get(0);
            }

            if (carte.getType().equals("texte")) {
                System.out.println(carte.getType());
                Intent jouerCarteT = new Intent(getApplicationContext(), SwipeJouerCarteTexte.class);
                Bundle bundle = new Bundle();
                bundle.putSerializable("carte", carte);
                bundle.putSerializable("liste", (Serializable) cartes);
                bundle.putInt("score", score);
                bundle.putBoolean("jouer", jouer);
                jouerCarteT.putExtras(bundle);
                startActivity(jouerCarteT);
                finish();
            } else if (carte.getType().equals("rimage")) {
                System.out.println(carte.getType());
                Intent jouerCarte = new Intent(getApplicationContext(), SwipeJouerCarteImageR.class);
                Bundle bundle = new Bundle();
                bundle.putSerializable("carte", carte);
                bundle.putSerializable("liste", (Serializable) cartes);
                bundle.putInt("score", score);
                bundle.putBoolean("jouer", jouer);
                jouerCarte.putExtras(bundle);
                startActivity(jouerCarte);
                finish();
            } else if (carte.getType().equals("qimage")) {
                System.out.println(carte.getType());
                Intent jouerCarte = new Intent(getApplicationContext(), SwipeJouerCarteImageQ.class);
                Bundle bundle = new Bundle();
                bundle.putSerializable("carte", carte);
                bundle.putSerializable("liste", (Serializable) cartes);
                bundle.putInt("score", score);
                bundle.putBoolean("jouer", jouer);
                jouerCarte.putExtras(bundle);
                startActivity(jouerCarte);
                finish();
            } else if (carte.getType().equals("qson")) {
                System.out.println(carte.getType());
                Intent jouerCarte = new Intent(getApplicationContext(), SwipeJouerCarteQuestionSon.class);
                Bundle bundle = new Bundle();
                bundle.putSerializable("carte", carte);
                bundle.putSerializable("liste", (Serializable) cartes);
                bundle.putInt("score", score);
                bundle.putBoolean("jouer", jouer);
                jouerCarte.putExtras(bundle);
                startActivity(jouerCarte);
                finish();
            }
        }
    }
}
