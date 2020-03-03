package com.example.masterlingua;

import android.content.Context;
//import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
//import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;


public class AfficherCarte extends AppCompatActivity {
    ListView reps;
    Carte carte;
    List<ReponseText> reponses = new ArrayList<>();
    List<String> nom_rep = new ArrayList<>();
    TextView question;
    String ok;
    Context context = this;
    int duration = Toast.LENGTH_SHORT;
    int scores;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.carte);
        reps = (ListView) findViewById(R.id.list);
        question = findViewById(R.id.question);
        Bundle bundle = getIntent().getExtras();
        Intent intent = getIntent();

        carte = (Carte) bundle.getSerializable("carte");
        String idc = carte.getIdCarte();
        reponses = ReponseText.find(ReponseText.class, "idcarte = ?", idc);
        for (int i = 0; i < reponses.size(); i++) {
            nom_rep.add(reponses.get(i).getNom());
        }
        /*if(!reponses.isEmpty()) {
            for (int i = 0; i<carte.getReponses().size(); i++){
                reponses.add(carte.getReponses().get(i));
            }
        }*/
        for (int y = 0; y < reponses.size(); y++) {
            if (reponses.get(y).getbr() == true) {
                ok = reponses.get(y).getNom();
            }
        }

        List<QuestionText> quest = QuestionText.find(QuestionText.class, "idcarte = ?", idc);
        for (int n = 0; n < quest.size(); n++) {
            question.setText(quest.get(n).getNom_question());
        }
        final ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, nom_rep);
        reps.setAdapter(adapter);
        reps.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String choix = parent.getItemAtPosition(position).toString();
                if (choix.equals(ok)) {
                    showToastOk();
                    scores=20;
                    retour();
                } else {
                    showToastNo();
                    scores=0;
                    retour();
                }
            }
        });

    }

    public void showToastOk() {
        LayoutInflater inflater = getLayoutInflater();
        View layout = inflater.inflate(R.layout.toast_no, (ViewGroup) findViewById(R.id.toast_root));

        ImageView toastImage = layout.findViewById(R.id.toast_image);
        toastImage.setImageResource(R.drawable.good);

        Toast toast = new Toast(getApplicationContext());
        toast.setGravity(Gravity.CENTER, 0, 0);
        toast.setDuration(Toast.LENGTH_LONG);
        toast.setView(layout);

        toast.show();
    }

    public void showToastNo() {
        LayoutInflater inflater = getLayoutInflater();
        View layout = inflater.inflate(R.layout.toast_no, (ViewGroup) findViewById(R.id.toast_root));

        ImageView toastImage = layout.findViewById(R.id.toast_image);
        toastImage.setImageResource(R.drawable.bad);

        Toast toast = new Toast(getApplicationContext());
        toast.setGravity(Gravity.CENTER, 0, 0);
        toast.setDuration(Toast.LENGTH_LONG);
        toast.setView(layout);

        toast.show();
    }

    public void retour() {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {

                {
                    Intent retour = new Intent(AfficherCarte.this, ChoisirCreationCarte.class);
                    retour.putExtra("scores", scores);
                    startActivity(retour);
                    finish();
                }

            }
        }, 2000);
    }
}