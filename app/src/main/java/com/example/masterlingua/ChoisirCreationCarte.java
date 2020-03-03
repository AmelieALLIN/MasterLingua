package com.example.masterlingua;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class ChoisirCreationCarte extends AppCompatActivity {

    private Button goCreerCarte;
    public static ArrayList<Integer> scores = new ArrayList<>();
    TextView integerTextView, scoreTextViex;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bouton_creation_carte);
        this.goCreerCarte = findViewById(R.id.goCreerCarte);
        integerTextView = (TextView) findViewById(R.id.textView1);
        scoreTextViex = (TextView) findViewById(R.id.textView2);

        Intent intent = getIntent();

        if (intent != null && intent.getExtras() != null) {
            int score = intent.getIntExtra("scores", 0);
            int score_final = 0;
            scores.add(score);
            for (int i = 0; i < scores.size(); i++)
                score_final += scores.get(i);

            float sco = score_final / scores.size();
            String scoreTotalTest = String.valueOf(score_final);
            String scoreTotal = String.valueOf(sco) + " / 20 ";


            for (int i = 0; i < scores.size(); i++) {
                int ess = i + 1;
                integerTextView.setText(integerTextView.getText() + "Essai : " + ess + " --  resultat : " + scores.get(i) + " \n");
            }

            scoreTextViex.setText(scoreTextViex.getText() + "Score Total  : " + scoreTotal + " \n");
        }

        goCreerCarte.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent creerCarte = new Intent(getApplicationContext(), CreerCarte.class);
                startActivity(creerCarte);
                finish();
            }
        });

    }

    public void AfficherListe(View view) {
        Intent intent = new Intent(this, AfficherListe.class);
        startActivity(intent);
    }
}






