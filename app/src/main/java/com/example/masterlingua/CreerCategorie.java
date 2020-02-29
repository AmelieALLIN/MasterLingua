package com.example.masterlingua;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class CreerCategorie extends AppCompatActivity {
    private static List<Carte> cartes;
    private static List<Categorie> categories;
    //categories = Categorie.listAll(Categorie.class);
    private EditText nameCategory;
    private Categorie cat;
    Context context = this;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_creer_categorie);
        Bundle bundle = getIntent().getExtras();
        cartes = (List<Carte>) bundle.getSerializable("cartes");
        // à mettre en commentaire quand on utilisera la bd ici aussi ?
        categories = new ArrayList<>();
        nameCategory = findViewById(R.id.categoryName);
        Button validate = findViewById(R.id.validate);

        validate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(nameCategory.getText().toString().isEmpty()) {
                    CharSequence text = getText(R.string.warning_category);
                    int duration = Toast.LENGTH_SHORT;
                    Toast.makeText(context, text, duration).show();
                }

                // création de la catégorie si il y a un nom
                else {
                    cat = new Categorie(nameCategory.getText().toString(), cartes);
                    CharSequence text = getText(R.string.category_created);
                    int duration = Toast.LENGTH_SHORT;
                    Toast.makeText(context, text, duration).show();
                    categories.add(cat);

                    Intent retourListeCartes = new Intent(getApplicationContext(), AfficherListe.class);
                    Bundle bundle = new Bundle();
                    bundle.putSerializable("listeCategories", (Serializable) categories);
                    retourListeCartes.putExtras(bundle);
                    System.out.println("TRANSMISSION DES DONNEES A L'ACTIVITE SUIVANTE "+categories.get(0).getNom());
                    startActivityForResult(retourListeCartes,1);
                    finish();
                }
            }
        });
    }
}

