package com.example.masterlingua;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;

public class AfficherTuto extends AppCompatActivity {
    static String className, direction;
    Class nameToGo;
    boolean b = false;
    static Deck deck;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tuto);
        final Bundle bundle = getIntent().getExtras();
        className = bundle.getString("className");
        if(!className.isEmpty() && bundle.getString("className") != null) direction = className;
        else if(className.isEmpty() || className == null) direction = "ChoisirCreationCarte";
        switch(className){
            case "ChoisirCreationCarte":
                nameToGo = ChoisirCreationCarte.class;
                break;
            case "MenuCreationCarte" :
                nameToGo = MenuCreationCarte.class;
                break;
            case "AfficherListe":
                nameToGo = AfficherListe.class;
                break;
            case "AfficherListeDeck":
                nameToGo = AfficherListeDeck.class;
                break;
            case "AfficherDeck":
                nameToGo = AfficherDeck.class;
                break;
        }
        ImageButton close = findViewById(R.id.close);
        close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent pageAccueil = new Intent(getApplicationContext(), nameToGo);
                startActivity(pageAccueil);
                finish();
            }
        });
    }
}
