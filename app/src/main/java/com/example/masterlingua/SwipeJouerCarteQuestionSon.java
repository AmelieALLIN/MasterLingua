package com.example.masterlingua;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class SwipeJouerCarteQuestionSon extends AppCompatActivity {
    static int score;
    static boolean jouer;
    ListView reps;
    Carte carte;
    List<ReponseText> reponses = new ArrayList<>();
    List<String> nom_rep = new ArrayList<>();
    String ok, idc;
    String monfichier;
    Context context = this;
    List<Carte> cartes;
    Button play, stop;
    MediaPlayer mediaPlayer;
    RelativeLayout layout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.jouer_carte_question_son);
        reps = findViewById(R.id.list);
        play = findViewById(R.id.play);
        stop = findViewById(R.id.stop);
        layout = findViewById(R.id.layout);
        Bundle bundle = getIntent().getExtras();
        carte = (Carte) bundle.getSerializable("carte");
        cartes = (List<Carte>) bundle.getSerializable("liste");
        score = bundle.getInt("score");
        jouer = bundle.getBoolean("jouer");
        idc = carte.getIdCarte();
        List<QuestionSon> quest = QuestionSon.find(QuestionSon.class, "idcarte = ?", idc);
        for (
                int n = 0; n < quest.size(); n++) {
            monfichier = quest.get(n).getUrl();
        }

        mediaPlayer = new

                MediaPlayer();

        try {
            mediaPlayer.setDataSource(monfichier);
            mediaPlayer.prepare();
        } catch (
                IOException e) {
            e.printStackTrace();
        }

        reponses = ReponseText.find(ReponseText.class, "idcarte = ?", idc);
        for (
                int i = 0; i < reponses.size(); i++) {
            nom_rep.add(reponses.get(i).getNom());
        }

        for (
                int y = 0; y < reponses.size(); y++) {
            if (reponses.get(y).getbr() == true) {
                ok = reponses.get(y).getNom();
            }
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
                    jouer = true;

                    if (cartes.size() == 1) {
                        String pluriel = "";
                        if (score > 1) pluriel = "s";
                        Toast.makeText(context, "Votre score est de " + score + "point" + pluriel, Toast.LENGTH_SHORT).show();
                        fin();
                    } else {
                        cartes.remove(0);
                        next();
                    }
                } else {
                    showToastNo();
                    reps.setEnabled(false);
                    jouer = true;
                    if (cartes.size() == 1) {
                        String pluriel = "";
                        if (score > 1) pluriel = "s";
                        Toast.makeText(context, "Votre score est de " + score + "point" + pluriel, Toast.LENGTH_SHORT).show();
                        fin();
                    } else {
                        cartes.remove(0);
                        next();
                    }
                }
            }
        });

        play.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                stop.setEnabled(true);
                play.setEnabled(false);
                mediaPlayer.start();
                new Handler().postDelayed(new Runnable() {
                                              public void run() {
                                                  stop.setEnabled(false);
                                                  play.setEnabled(true);
                                              }
                                          }, mediaPlayer.getDuration()
                );

            }

        });
        layout.setOnTouchListener(new OnSwipeTouchListener(SwipeJouerCarteQuestionSon.this) {
            @Override
            public void onSwipeLeft() {
                super.onSwipeLeft();
                if (cartes.size() == 1) {
                    if (jouer == true) {
                        String pluriel = "";
                        if (score > 1) pluriel = "s";
                        Toast.makeText(context, "Votre score est de " + score + "point" + pluriel, Toast.LENGTH_SHORT).show();
                        fin();
                    } else fin();
                } else {
                    cartes.remove(0);
                    next();
                }

            }


            @Override
            public void onSwipeUp() {
                super.onSwipeUp();
                String pluriel = "";
                if (score > 1) pluriel = "s";
                if (jouer == true) {
                    Toast.makeText(context, "Votre score est de " + score + "point" + pluriel, Toast.LENGTH_SHORT).show();
                    fin();
                } else fin();
            }
        });

        stop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mediaPlayer.stop();
                play.setEnabled(true);
                stop.setEnabled(false);
                try {
                    mediaPlayer.prepare();
                } catch (IOException e) {
                    e.printStackTrace();
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

    public void fin() {
        Intent fin = new Intent(getApplicationContext(), ChoisirCreationCarte.class);
        startActivity(fin);
        finish();
    }

    public void next() {
        carte = cartes.get(0);
        System.out.println("iddd" + carte.getIdCarte());
        System.out.println("tyyype" + carte.getType());

        if (carte.getType().equals("texte")) {
            Intent jouerCarte = new Intent(getApplicationContext(), SwipeJouerCarteTexte.class);
            Bundle bundle = new Bundle();
            bundle.putSerializable("carte", cartes.get(0));
            bundle.putSerializable("liste", (Serializable) cartes);
            bundle.putInt("score", score);
            bundle.putBoolean("jouer", jouer);
            jouerCarte.putExtras(bundle);
            startActivity(jouerCarte);
            finish();

        } else if (carte.getType().equals("rimage")) {
            Intent jouerCarte = new Intent(getApplicationContext(), SwipeJouerCarteImageR.class);
            Bundle bundle = new Bundle();
            bundle.putSerializable("carte", cartes.get(0));
            bundle.putSerializable("liste", (Serializable) cartes);
            bundle.putInt("score", score);
            bundle.putBoolean("jouer", jouer);
            jouerCarte.putExtras(bundle);
            startActivity(jouerCarte);
            finish();
        } else if (carte.getType().equals("qimage")) {
            Intent jouerCarte = new Intent(getApplicationContext(), SwipeJouerCarteImageQ.class);
            Bundle bundle = new Bundle();
            bundle.putSerializable("carte", cartes.get(0));
            bundle.putSerializable("liste", (Serializable) cartes);
            bundle.putInt("score", score);
            bundle.putBoolean("jouer", jouer);
            jouerCarte.putExtras(bundle);
            startActivity(jouerCarte);
            finish();
        } else if (carte.getType().equals("qson")) {
            Intent jouerCarte = new Intent(getApplicationContext(), SwipeJouerCarteQuestionSon.class);
            Bundle bundle = new Bundle();
            bundle.putSerializable("carte", cartes.get(0));
            bundle.putSerializable("liste", (Serializable) cartes);
            bundle.putInt("score", score);
            bundle.putBoolean("jouer", jouer);
            jouerCarte.putExtras(bundle);
            startActivity(jouerCarte);
            finish();
        }
    }
}

