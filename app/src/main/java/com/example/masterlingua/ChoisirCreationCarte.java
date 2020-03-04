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
    List<Categorie> categories;
    Categorie categorie1, categorie2, categorie3, categorie4, categorie5, categorie6;

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
        categorie3 = new Categorie(NomCategorie.ESPAGNOL.toString());
        categorie3.save();
        categories.add(categorie3);
        categorie4 = new Categorie(NomCategorie.FAUNE.toString());
        categorie4.save();
        categories.add(categorie4);
        categorie5 = new Categorie(NomCategorie.FLORE.toString());
        categorie5.save();
        categories.add(categorie5);
        categorie6 = new Categorie(NomCategorie.COSMETIQUES.toString());
        categorie6.save();
        categories.add(categorie6);

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
