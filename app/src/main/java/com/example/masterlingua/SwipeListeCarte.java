package com.example.masterlingua;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.SparseArray;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class SwipeListeCarte extends AppCompatActivity {
    List<Carte> cartes = Carte.listAll(Carte.class);
    TextView texte;
    EditText nom;
    Button start;
    Deck deck;
    int compteur=0;




    List<Carte> idcarte = new ArrayList<>();

    Carte carte;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.swipelistestart);

        start = findViewById(R.id.start);
       texte=findViewById(R.id.texte);
       nom=findViewById(R.id.nomdeck);

        for (int n=0;n<cartes.size();n++)
        {
            carte = cartes.get(0);
        }

        start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(nom.getText().toString().isEmpty())
                {
                    Toast.makeText(getApplicationContext(), "Veuillez renseigner le nom de votre deck ", Toast.LENGTH_SHORT).show();
                }
                else {
                String iddeck= UUID.randomUUID().toString();
                deck = new Deck(iddeck,nom.getText().toString());
                deck.save();

                if(carte.getType().equals("texte")){
                    System.out.println(carte.getType());
                    Intent jouerCarteT = new Intent(getApplicationContext(), AfficherCarteTexte.class );
                    Bundle bundle = new Bundle();
                    bundle.putSerializable("carte", carte);
                    bundle.putSerializable("liste", (Serializable) cartes);
                    bundle.putString("iddeck",deck.getId_deck());
                    bundle.putInt("compteur",compteur);
                    jouerCarteT.putExtras(bundle);
                    startActivity(jouerCarteT);
                    finish();

                }

                else if(carte.getType().equals("rimage")){
                    System.out.println(carte.getType());
                    Intent jouerCarte = new Intent(getApplicationContext(), AfficherCarteReponsesImage.class );
                    Bundle bundle = new Bundle();
                    bundle.putSerializable("carte", carte);
                    bundle.putSerializable("liste", (Serializable) cartes);
                    bundle.putString("iddeck",deck.getId_deck());
                    bundle.putInt("compteur",compteur);
                    jouerCarte.putExtras(bundle);
                    startActivity(jouerCarte);
                    finish();
                }

                else if(carte.getType().equals("qimage")){
                    System.out.println(carte.getType());
                    Intent jouerCarte = new Intent(getApplicationContext(), AfficherCarteQuestionImage.class );
                    Bundle bundle = new Bundle();
                    bundle.putSerializable("carte", carte);
                    bundle.putSerializable("liste", (Serializable) cartes);
                    bundle.putString("iddeck",deck.getId_deck());
                    bundle.putInt("compteur",compteur);
                    jouerCarte.putExtras(bundle);
                    startActivity(jouerCarte);
                    finish();
                }
                else if(carte.getType().equals("qson")){
                    System.out.println(carte.getType());
                    Intent jouerCarte = new Intent(getApplicationContext(), AfficherCarteQuestionSon.class );
                    Bundle bundle = new Bundle();
                    bundle.putSerializable("carte", carte);
                    bundle.putSerializable("liste", (Serializable) cartes);
                    bundle.putString("iddeck",deck.getId_deck());
                    bundle.putInt("compteur",compteur);
                    jouerCarte.putExtras(bundle);
                    startActivity(jouerCarte);
                    finish();
                }

                System.out.println(carte.getIdCarte());
                System.out.println(cartes.size());


                }}


        });
    }
}



