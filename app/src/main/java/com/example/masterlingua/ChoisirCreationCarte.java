package com.example.masterlingua;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.io.Serializable;
import java.util.List;

public class ChoisirCreationCarte extends AppCompatActivity {

    private Button goCreerCarte;
    // ATTENTION SI ON AJOUTE UN NOM DE CATEGORIE PENSER A CHANGER LE NB ICI
    int nbCat = 15;
    List<Categorie> categories;
    Categorie categorie1, categorie2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bouton_creation_carte);
        this.goCreerCarte = findViewById(R.id.goCreerCarte);

        // Charger les catégories
        categorie1 = new Categorie(NomCategorie.FRANCAIS.toString());
        categorie1.save();
        categories.add(categorie1);
        categorie2 = new Categorie(NomCategorie.ANGLAIS.toString());
        categorie2.save();
        categories.add(categorie2);




        goCreerCarte.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent creerCarte = new Intent(getApplicationContext(), CreerCarte.class);
                Bundle bundle = new Bundle();
                bundle.putSerializable("categories", (Serializable) categories);
                creerCarte.putExtras(bundle);
                startActivity(creerCarte);
                finish();
            }
        });

    }

    public void AfficherListe(View view) {
        Intent intent = new Intent(this,AfficherListe.class);
        Bundle bundle = new Bundle();
        bundle.putSerializable("categories", (Serializable) categories);
        intent.putExtras(bundle);
        startActivity(intent);
    }



    public void QuestionImage(View view) {
        Intent intent = new Intent(this,CarteQuestionImage.class);
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
}
