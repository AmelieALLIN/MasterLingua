package com.example.masterlingua;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class SwipeJouerCarteTexte extends AppCompatActivity {
    ListView reps;
    Carte carte;
    List<ReponseText> reponses = new ArrayList<>();
    List<String> nom_rep = new ArrayList<>();
    TextView question;
    String ok;
    Context context = this;
    List<Carte> cartes;
    LinearLayout layout;
    static int score;
    boolean jouer=false;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.afficher_carte_texte);
        reps = (ListView) findViewById(R.id.list);
        question = findViewById(R.id.question);
        layout = findViewById(R.id.layout);

        Bundle bundle = getIntent().getExtras();

        carte = (Carte) bundle.getSerializable("carte");
        cartes = (List<Carte>) bundle.getSerializable("liste");
        score=bundle.getInt("score");


        String idc = carte.getIdCarte();
        reponses = ReponseText.find(ReponseText.class, "idcarte = ?", idc);
        for (int i = 0; i < reponses.size(); i++) {
            nom_rep.add(reponses.get(i).getNom());
        }

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
                    reps.setEnabled(false);
                    score += 1;
                    jouer=true;

                    if (cartes.size() == 1) {
                        Toast.makeText(context, "votre score est:" +score, Toast.LENGTH_SHORT).show();
                        fin();}
                    else {
                        cartes.remove(0);
                        next();
                    }
                }
                else {
                    showToastNo();
                    reps.setEnabled(false);
                    jouer=true;
                    if (cartes.size() == 1) {
                        Toast.makeText(context, "votre score est:" +score, Toast.LENGTH_SHORT).show();
                        fin();}
                    else {
                        cartes.remove(0);
                        next();
                    }

                }

            }
        });



        layout.setOnTouchListener(new OnSwipeTouchListener(SwipeJouerCarteTexte.this) {
            @Override
            public void onSwipeLeft() {
                super.onSwipeLeft();
                if(cartes.size()==1){
                    if(!jouer){
                        Toast.makeText(context, "votre score est:" +score, Toast.LENGTH_SHORT).show();
                        fin();}

                    else fin();
                }
                else{
                    cartes.remove(0);
                    next();}

            }


            @Override
            public void onSwipeUp(){
                super.onSwipeUp();

                if(!jouer){
                    Toast.makeText(context, "votre score est:" +score, Toast.LENGTH_SHORT).show();
                    fin();}

                else fin();
            }
        });
    }


    public void next(){
        carte = cartes.get(0);
        System.out.println("iddd"+carte.getIdCarte());
        System.out.println("tyyype"+carte.getType());

        if (carte.getType().equals("texte")) {
            Intent jouerCarte = new Intent(getApplicationContext(), SwipeJouerCarteTexte.class);
            Bundle bundle = new Bundle();
            bundle.putSerializable("carte", cartes.get(0));
            bundle.putSerializable("liste", (Serializable) cartes);
            bundle.putInt("score",score);
            jouerCarte.putExtras(bundle);
            startActivity(jouerCarte);
            finish();

        } else if (carte.getType().equals("rimage")) {
            Intent jouerCarte = new Intent(getApplicationContext(), SwipeJouerCarteImageR.class);
            Bundle bundle = new Bundle();
            bundle.putSerializable("carte", cartes.get(0));
            bundle.putSerializable("liste", (Serializable) cartes);
            bundle.putInt("score",score);
            jouerCarte.putExtras(bundle);
            startActivity(jouerCarte);
            finish();
        } else if (carte.getType().equals("qimage")) {
            Intent jouerCarte = new Intent(getApplicationContext(), SwipeJouerCarteImageQ.class);
            Bundle bundle = new Bundle();
            bundle.putSerializable("carte", cartes.get(0));
            bundle.putSerializable("liste", (Serializable) cartes);
            bundle.putInt("score",score);
            jouerCarte.putExtras(bundle);
            startActivity(jouerCarte);
            finish();
        }}

    public void fin(){
        Intent fin = new Intent(getApplicationContext(), ChoisirCreationCarte.class);
        startActivity(fin);
        finish();
    }

    public void showToastOk() {
        LayoutInflater inflater = getLayoutInflater();
        View layout = inflater.inflate(R.layout.toast_no, (ViewGroup) findViewById(R.id.toast_root));

        ImageView toastImage = layout.findViewById(R.id.toast_image);
        toastImage.setImageResource(R.drawable.good);

        Toast toast = new Toast(getApplicationContext());
        toast.setGravity(Gravity.CENTER, 0, 0);
        toast.setDuration(Toast.LENGTH_SHORT);
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
        toast.setDuration(Toast.LENGTH_SHORT);
        toast.setView(layout);

        toast.show();
    }


    public void retour()
    {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {

                {Intent retour = new Intent(SwipeJouerCarteTexte.this, ChoisirCreationCarte.class);
                    retour.putExtra("score",score);
                    startActivity(retour);
                    finish();
                }

            }
        }, 2000);
    }

}



