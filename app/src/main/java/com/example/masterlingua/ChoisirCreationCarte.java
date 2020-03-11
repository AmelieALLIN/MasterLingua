package com.example.masterlingua;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

/*import java.io.Serializable;
import java.util.List;
import java.util.UUID;

import static com.example.masterlingua.R.array.category_names;*/

public class ChoisirCreationCarte extends AppCompatActivity {

    Button goCreerCarte;
    Categorie categorie1, categorie2, categorie3, categorie4, categorie5, categorie6;
    //List<Categorie> categories;
    //boolean catUnique;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bouton_creation_carte);
        this.goCreerCarte = findViewById(R.id.goCreerCarte);
        //categories = chargerCategories();
        goCreerCarte.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent creerCarte = new Intent(getApplicationContext(), CreerCarte.class);
               /* Bundle bundle = new Bundle();
                bundle.putSerializable("categories", (Serializable) categories);
                creerCarte.putExtras(bundle);*/
                startActivity(creerCarte);
                finish();
            }
        });

    }

   /* public List<Categorie> chargerCategories(){
        for(int i=0; i<categories.size(); i++) {
            if (categories.get(i).getNomCategorie() == nomCat) {
                catUnique = false;
            }
        }
        if(catUnique) {
            idCat = UUID.randomUUID().toString();
            catChoisie = new Categorie(idCat, nomCat);
        }
        String id = UUID.randomUUID().toString();
        categorie1 = new Categorie(id, NomCategorie.valueOf("FRANCAIS").toString());
        categorie1.save();
        categorie2 = new Categorie(id, NomCategorie.ANGLAIS.toString());
        categorie2.save();
        categorie3 = new Categorie(id, NomCategorie.ESPAGNOL.toString());
        categorie3.save();
        categorie4 = new Categorie(id, NomCategorie.FAUNE.toString());
        categorie4.save();
        categorie5 = new Categorie(id, NomCategorie.FLORE.toString());
        categorie5.save();
        categorie6 = new Categorie(id, NomCategorie.COSMETIQUES.toString());
        categorie6.save();

        List<Categorie> categories = Categorie.listAll(Categorie.class);
        return categories;
    }*/

    public void AfficherListe(View view) {
        Intent intent = new Intent(this,AfficherListe.class);
       /* Bundle bundle = new Bundle();
        bundle.putSerializable("categories", (Serializable) categories);
        intent.putExtras(bundle);*/
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
