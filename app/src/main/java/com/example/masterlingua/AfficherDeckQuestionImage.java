package com.example.masterlingua;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Base64;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

public class AfficherDeckQuestionImage extends AppCompatActivity {
    GridView liste_cartes;
    //TextView liste;
    List<Carte> cartes = Carte.listAll(Carte.class);

    static List<Bitmap> quests = new ArrayList<>();
    List<QuestionImage> questionsdecks = new ArrayList<>();
    static List<QuestionImage> malisteimage = new ArrayList<>();
    List<QuestionImage> questionscarte = new ArrayList<>();
    List<Carte> listcarte = new ArrayList<>();
    Carte carte;
    String ch;
    static Deck deck;
    private Context context;
    int j;
    boolean intent_deck=false;
    private static boolean  deja_initialise=false;
    private static int score=0;
    byte[] q=null;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.list_question_image);
        liste_cartes = (GridView) findViewById(R.id.gridd);
        //liste = findViewById(R.id.liste);


        Bundle bundle = getIntent().getExtras();
        Intent intent=getIntent();
        if(deja_initialise==false){deck = (Deck) bundle.getSerializable("deck");
            score=0;}


        if(deja_initialise==false){
            List<CartesDeck> cartedecks = CartesDeck.findWithQuery(CartesDeck.class, "Select * from CARTES_DECK where iddeck = ?", deck.getId_deck());
            for(int i=0; i<cartedecks.size(); i++){
                questionsdecks = QuestionImage.find(QuestionImage.class,"idcarte = ?", cartedecks.get(i).getId_carte());
                for(int n=0; n<questionsdecks.size();n++)
                {
                    System.out.println(deck.getId_deck());
                    System.out.println(cartedecks.get(n).getId_carte());
                    byte[] encodeByte = Base64.decode(questionsdecks.get(n).getImage(), Base64.DEFAULT);
                    Bitmap bmp= BitmapFactory.decodeByteArray(encodeByte,0,encodeByte.length);
                    malisteimage.add(questionsdecks.get(n));
                    quests.add(bmp);
                    System.out.println("la taille du deck esssssssttttt"+questionsdecks.size());
                    System.out.println("la taille du deck esssssssttttt"+quests.size());
                    System.out.println("la taille de ma liste est :"+malisteimage.size());


                }
            }
        }
        if(deja_initialise==true){score+=getIntent().getExtras().getInt("score");}

        CustomerAdapter customerAdapter = new CustomerAdapter(getApplicationContext(), quests);
        liste_cartes.setAdapter(customerAdapter);

        if(quests.size()!=0){
            liste_cartes.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                     ch =malisteimage.get(position).getImage();
                     System.out.println(ch);
                     System.out.println(position);

                    //String choix = parent.getItemAtPosition(position).toString();
                   // questionscarte = QuestionImage.findWithQuery(QuestionImage.class, "Select * from QUESTION_IMAGE where image = ?", q);

                    questionscarte = QuestionImage.find(QuestionImage.class,"image = ?", ch);
                    for(int n=0;n<questionscarte.size();n++) {
                        listcarte = Carte.find(Carte.class, "idcarte = ?", questionscarte.get(n).getIdCarte());
                    }
                    for(int n=0;n<listcarte.size();n++) {
                        carte = listcarte.get(n);
                    }
                    Intent afficher = new Intent(getApplicationContext(), JouerQuestionImage1.class);
                    Bundle bundle = new Bundle();
                    bundle.putSerializable("carte", carte);
                    afficher.putExtras(bundle);
                    startActivity(afficher);
                    finish();
                    quests.remove(position);
                    malisteimage.remove(position);
                    deja_initialise=true;


                }
            });}
        else
            Toast.makeText(getApplicationContext(), "votre score est de :"+score, Toast.LENGTH_SHORT).show();
        deja_initialise=false;


    }



    public static String stringFromBytes(byte byteData[]) {
        char charData[] = new char[byteData.length];
        for(int i = 0; i < charData.length; i++) {
            charData[i] = (char) (((int) byteData[i]) & 0xFF);
        }
        return new String(charData);
    }
}
