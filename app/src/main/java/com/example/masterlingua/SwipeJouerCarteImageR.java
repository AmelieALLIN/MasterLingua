package com.example.masterlingua;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Base64;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class SwipeJouerCarteImageR extends AppCompatActivity {
    GridView listeReponse;
    Carte carte;
    List<ReponseText> reponses = new ArrayList<>();
    List<ReponseImage> rep = new ArrayList<>();
    List<Bitmap> repImage = new ArrayList<>();
    TextView question;
    Bitmap bmp;
    int ind_br;
    Context context = this;
    List<Carte> cartes;
    LinearLayout layout;
    static int score;
    static boolean jouer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.afficher_carte_image_reponse);
        question = findViewById(R.id.question);
        layout=findViewById(R.id.layout);

        listeReponse = (GridView) findViewById(R.id.gridRep);

        Bundle bundle = getIntent().getExtras();
        Intent intent=getIntent();

        carte = (Carte) bundle.getSerializable("carte");
        String idc = carte.getIdCarte();
        cartes= (List<Carte>) bundle.getSerializable("liste");
        score=bundle.getInt("score");
        jouer=bundle.getBoolean("jouer");

        List<QuestionText> quest = QuestionText.find(QuestionText.class,"idcarte = ?", idc);
        for(int n=0; n<quest.size();n++){
            question.setText(quest.get(n).getNom_question());
        }

        rep = ReponseImage.find(ReponseImage.class,"idcarte = ?", idc);
        for(int n=0; n<rep.size();n++){
            if(rep.get(n).getImage()!=null){
                if(rep.get(n).getBr()==true)
                {
                    ind_br = n;
                }
                byte[] encodeByte = Base64.decode(rep.get(n).getImage(), Base64.DEFAULT);
                bmp= BitmapFactory.decodeByteArray(encodeByte,0,encodeByte.length);
                repImage.add(bmp);
            }
        }

        final CustomerAdapter cm = new CustomerAdapter(getApplicationContext(), repImage);
        listeReponse.setAdapter(cm);


        listeReponse.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if(position==ind_br)
                {showToastOk();
                    listeReponse.setEnabled(false);
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
                    listeReponse.setEnabled(false);
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


        layout.setOnTouchListener(new OnSwipeTouchListener(SwipeJouerCarteImageR.this) {
            @Override
            public void onSwipeLeft() {
                super.onSwipeLeft();
                if(cartes.size()==1){
                    if(jouer==true){
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

                if(jouer==true){
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
            bundle.putBoolean("jouer",jouer);
            jouerCarte.putExtras(bundle);
            startActivity(jouerCarte);
            finish();

        } else if (carte.getType().equals("Type.textimage")) {
            Intent jouerCarte = new Intent(getApplicationContext(), SwipeJouerCarteImageR.class);
            Bundle bundle = new Bundle();
            bundle.putSerializable("carte", cartes.get(0));
            bundle.putSerializable("liste", (Serializable) cartes);
            bundle.putInt("score",score);
            bundle.putBoolean("jouer",jouer);
            jouerCarte.putExtras(bundle);
            startActivity(jouerCarte);
            finish();
        } else if (carte.getType().equals("Type.imagetext")) {
            Intent jouerCarte = new Intent(getApplicationContext(), SwipeJouerCarteImageQ.class);
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

