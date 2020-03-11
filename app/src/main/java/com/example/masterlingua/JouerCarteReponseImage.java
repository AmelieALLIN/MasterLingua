package com.example.masterlingua;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Handler;
import android.util.Base64;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class JouerCarteReponseImage extends AppCompatActivity {
    GridView listeReponse;
    Carte carte;
    List<ReponseText> reponses = new ArrayList<>();
    List<String> nom_rep = new ArrayList<>();
    List<ReponseImage> rep = new ArrayList<>();
    List<Bitmap> repImage = new ArrayList<>();
    String selectedItem;
    TextView question;
    Bitmap bmp;
    int ind_br;
    byte[]b;
    Context context = this;
    int duration = Toast.LENGTH_SHORT;
    private int scorecarte;
    int scorec;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jouer_carte_reponse_image);
        question = findViewById(R.id.question);

        listeReponse = (GridView) findViewById(R.id.gridRep);

        /*reponse1 = findViewById(R.id.rep1);
        reponse2 = findViewById(R.id.rep2);
        reponse3 = findViewById(R.id.rep3);*/
        Bundle bundle = getIntent().getExtras();
        Intent intent=getIntent();

        carte = (Carte) bundle.getSerializable("carte");
        String idc = carte.getIdCarte();

        List<QuestionText> quest = QuestionText.find(QuestionText.class,"idcarte = ?", idc);
        for(int n=0; n<quest.size();n++){
            question.setText(quest.get(n).getNom_question());
        }

        rep = ReponseImage.find(ReponseImage.class,"idcarte = ?", idc);
        for(int n=0; n<rep.size();n++){
            if(rep.get(n).getBr()==true)
            {
                ind_br = n;
            }
            byte[] encodeByte = Base64.decode(rep.get(n).getImage(), Base64.DEFAULT);
            bmp= BitmapFactory.decodeByteArray(encodeByte,0,encodeByte.length);
            repImage.add(bmp);
        }

        final CustomerAdapter cm = new CustomerAdapter(getApplicationContext(), repImage);
        listeReponse.setAdapter(cm);
        listeReponse.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                System.out.println("iiiiiiiiiiiiii ind_br"+ind_br);
                System.out.println("iiiiiiiiiiiiii position"+position);
                if(position==ind_br)
                {showToastOk();
                    retour();
                    listeReponse.setEnabled(false);
                    scorecarte=1;
                }
                else
                {showToastNo();
                    scorecarte=0;
                    listeReponse.setEnabled(false);
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

    public void retour()
    {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {

                {Intent retour = new Intent(JouerCarteReponseImage.this, ChoisirCreationCarte.class);
                    retour.putExtra("score",scorec);
                    startActivity(retour);
                    finish();
                }

            }
        }, 2000);
    }
}
