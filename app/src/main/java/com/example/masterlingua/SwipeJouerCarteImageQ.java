package com.example.masterlingua;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Handler;
import android.util.Base64;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class SwipeJouerCarteImageQ extends AppCompatActivity {
    ListView reps;
    Carte carte;
    List<ReponseText> reponses = new ArrayList<>();
    List<QuestionImage> quest=new ArrayList<>();
    List<String> nom_rep = new ArrayList<>();
    ImageView question;
    String ok,iddeck;
    List<Carte> cartes;
    LinearLayout layout;
    static int score;
    Context context=this;
    static boolean jouer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.afficher_carte_image_question);
        reps =  findViewById(R.id.list);
        layout = findViewById(R.id.layout);
        question= findViewById(R.id.q);
        Bundle bundle = getIntent().getExtras();

        carte = (Carte) bundle.getSerializable("carte");
        cartes= (List<Carte>) bundle.getSerializable("liste");
        score=bundle.getInt("score");
        jouer=bundle.getBoolean("jouer");
        String idc = carte.getIdCarte();
        reponses = ReponseText.find(ReponseText.class,"idcarte = ?", idc);
        for(int i=0;i<reponses.size();i++)
        {
            nom_rep.add(reponses.get(i).getNom());
        }

        for(int y=0;y<reponses.size();y++){
            if(reponses.get(y).getbr() == true){
                ok = reponses.get(y).getNom();
            }
        }

        quest = QuestionImage.find(QuestionImage.class,"idcarte = ?", idc);
        for(int n=0; n<quest.size();n++){
            byte[] encodeByte = Base64.decode(quest.get(n).getImage(), Base64.DEFAULT);
            Bitmap bmp= BitmapFactory.decodeByteArray(encodeByte,0,encodeByte.length);
            question.setImageBitmap(bmp);
        }
        final ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1,nom_rep);
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
                    String pluriel = "";
                    if(score>1) pluriel = "s";
                    if (cartes.size() == 1) {
                            Toast.makeText(context, "Votre score est de " + score + " point" + pluriel, Toast.LENGTH_SHORT).show();
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
                    String pluriel = "";
                    if(score>1) pluriel = "s";
                    if (cartes.size() == 1) {
                            Toast.makeText(context, "Votre score est de " + score + " point" + pluriel, Toast.LENGTH_SHORT).show();

                            fin();}
                       else {
                        cartes.remove(0);
                        next();
                    }
                }
            }
        });

        layout.setOnTouchListener(new OnSwipeTouchListener(SwipeJouerCarteImageQ.this) {
            @Override
            public void onSwipeLeft() {
                super.onSwipeLeft();
                if(cartes.size()==1){
                    if(jouer==true){
                        String pluriel = "";
                        if(score>1) pluriel = "s";
                        Toast.makeText(context, "Votre score est de " +score + " point" + pluriel, Toast.LENGTH_SHORT).show();
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

                if(jouer==true){
                    String pluriel = "";
                    if(score>1) pluriel = "s";
                    Toast.makeText(context, "Votre score est de " +score + " point" + pluriel, Toast.LENGTH_SHORT).show();
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
            bundle.putBoolean("jouer",jouer);
            jouerCarte.putExtras(bundle);
            startActivity(jouerCarte);
            finish();

        } else if (carte.getType().equals("rimage")) {
            Intent jouerCarte = new Intent(getApplicationContext(), SwipeJouerCarteImageR.class);
            Bundle bundle = new Bundle();
            bundle.putSerializable("carte", cartes.get(0));
            bundle.putSerializable("liste", (Serializable) cartes);
            bundle.putInt("score",score);
            bundle.putBoolean("jouer",jouer);
            jouerCarte.putExtras(bundle);
            startActivity(jouerCarte);
            finish();
        } else if (carte.getType().equals("qimage")) {
            Intent jouerCarte = new Intent(getApplicationContext(), SwipeJouerCarteImageQ.class);
            Bundle bundle = new Bundle();
            bundle.putSerializable("carte", cartes.get(0));
            bundle.putSerializable("liste", (Serializable) cartes);
            bundle.putInt("score",score);
            bundle.putBoolean("jouer",jouer);
            jouerCarte.putExtras(bundle);
            startActivity(jouerCarte);
            finish();
        }
        else if (carte.getType().equals("qson")) {
            Intent jouerCarte = new Intent(getApplicationContext(), SwipeJouerCarteQuestionSon.class);
            Bundle bundle = new Bundle();
            bundle.putSerializable("carte", cartes.get(0));
            bundle.putSerializable("liste", (Serializable) cartes);
            bundle.putInt("score",score);
            bundle.putBoolean("jouer",jouer);
            jouerCarte.putExtras(bundle);
            startActivity(jouerCarte);
            finish();
        }}
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
    public void fin(){
        Intent fin = new Intent(getApplicationContext(), ChoisirCreationCarte.class);
        startActivity(fin);
        finish();
    }
}



