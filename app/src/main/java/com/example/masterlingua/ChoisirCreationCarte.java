package com.example.masterlingua;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class ChoisirCreationCarte extends AppCompatActivity {

    private Button goCreerCarte;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bouton_creation_carte);
        this.goCreerCarte = findViewById(R.id.goCreerCarte);


        goCreerCarte.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent creerCarte = new Intent(getApplicationContext(), CreerCarte.class);
                startActivity(creerCarte);
                finish();
            }
        });

    }

    public void AfficherListe(View view) {
        Intent intent = new Intent(this,AfficherListe.class);
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


}
