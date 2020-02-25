package com.example.masterlingua;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
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

        validate.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                // création de la catégorie si la liste de carte n'est pas nulle
                if (cartes != null) {
                    cat = new Categorie(nameCategory.toString(), cartes);
                    CharSequence text = getText(R.string.category_created);
                    int duration = Toast.LENGTH_SHORT;
                    Toast.makeText(context, text, duration).show();
                    categories.add(cat);
                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                        {
                            Intent retour = new Intent(CreerCategorie.this, AfficherListe.class);
                            Bundle bundle = new Bundle();
                            bundle.putSerializable("listeCategories", (Serializable) categories);
                            retour.putExtras(bundle);
                            startActivity(retour);
                            finish();
                        }

                        }
                    }, 3200);
                }
            }
        });
    }
}

