package com.example.masterlingua;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.orm.query.Condition;
import com.orm.query.Select;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static com.orm.SugarRecord.findWithQuery;

public class AfficherPreference extends AppCompatActivity {

    List<Carte> carte;
    ArrayList<String> l = new ArrayList<>();
    Deck deck1;
    Spinner spin;
    Button aller;
    TextView texte;
    String niveau=" ";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.liste_selon_preference);

        spin = findViewById(R.id.spinner);
        aller = findViewById(R.id.aller);
        texte = findViewById(R.id.texte);
        spin.setAdapter(new ArrayAdapter<Niveau>(this, android.R.layout.simple_list_item_1, Niveau.values()));


        aller.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                carte = Carte.find(Carte.class, "question = ?", "Question1");
                 niveau = spin.getSelectedItem().toString();
                carte = findWithQuery(Carte.class, "SELECT * FROM carte WHERE niv like ? ", niveau);
                System.out.println(spin.getSelectedItem().toString());
                ArrayList<Carte> n = ((ArrayList) carte);
                deck1 = new Deck(n);
                Intent afficherDeckP = new Intent(getApplicationContext(), AfficherDeckPref.class);
                Bundle bundle = new Bundle();
                bundle.putSerializable("deck1", deck1);
                afficherDeckP.putExtras(bundle);
                startActivity(afficherDeckP);
                niveau=" ";
                finish();
                carte.clear();
                n.clear();
            }


        });


    }

    @Override
    public void onBackPressed() {
        Intent retourr = new Intent(AfficherPreference.this, ChoisirCreationCarte.class);
        startActivity(retourr);
        finish();;
    }

}
