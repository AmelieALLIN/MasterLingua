package com.example.masterlingua;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class AfficherCarte extends AppCompatActivity {
    Carte carte;
    Carte carte2;
    TextView question;
    TextView rep1,rep2,rep3;
    Context context = this;
    int duration = Toast.LENGTH_SHORT;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.afficher_carte);
        question = findViewById(R.id.question);
        Bundle bundle = getIntent().getExtras();
        Intent intent = getIntent();
        String r1 = intent.getStringExtra("rep1");
        String r2 = intent.getStringExtra("rep2");
        String r3 = intent.getStringExtra("rep3");
        question = findViewById(R.id.question);
        rep1 = findViewById(R.id.rep1);
        rep2 = findViewById(R.id.rep2);
        rep3 = findViewById(R.id.rep3);
        carte = (Carte) bundle.getSerializable("carte");
        String q = carte.getQuestion();
        question.setText(carte.getQuestion());
        rep1.setText(r1);
        rep2.setText(r2);
        rep3.setText(r3);

        final String ok =intent.getStringExtra("bonne_rep");

        rep1.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                String b=rep1.getText().toString();
                if(b==ok)
                    Toast.makeText(context, "bonne réponse", duration).show();
                else
                    Toast.makeText(context, "fausse réponse", duration).show();

            }

        });
        //carte2 = new Carte(q, r1, b1, r2, b2, r3, b3);
    }
}
