package com.example.masterlingua;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Base64;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class CreerCarteReponseImage extends AppCompatActivity {
    private Carte carte;
    private CheckBox checkAnswer1, checkAnswer2, checkAnswer3;
    private EditText question;
    private int taille=0;
    private int x=0;
    private boolean []brep = new boolean[3];
    private boolean []image = new boolean[3];
    Context context = this;
    ImageView image1;
    ImageView image2;
    ImageView image3;
    String bytee;
    byte[]byte1;
    byte[]byte2;
    byte[]byte3;
    String img1;
    String img2;
    String img3;
    String type="rimage";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_creer_carte_reponse_image);

        question = findViewById(R.id.question);
        image1=findViewById(R.id.image1);
        image2=findViewById(R.id.image2);
        image3=findViewById(R.id.image3);

        Button validate = findViewById(R.id.validate);
        Button retour = findViewById(R.id.retour);
        final Button importer1 = findViewById(R.id.buttonimage1);
        Button importer2 = findViewById(R.id.buttonimage2);
        Button importer3 = findViewById(R.id.buttonimage3);

        importer1.setOnClickListener(new View.OnClickListener() {
                                         @Override
                                         public void onClick(View v) {
                                             taille = taille++;
                                             x=1;
                                             image[0]=true;
                                             Intent intent1=new Intent(Intent.ACTION_GET_CONTENT);
                                             Bundle bundle = new Bundle();
                                             bundle.putSerializable("image3", x);
                                             intent1.putExtras(bundle);
                                             intent1.setType("image/*");
                                             startActivityForResult(Intent.createChooser(intent1,"image1"),1);
                                         }
                                     }
        );

        importer2.setOnClickListener(new View.OnClickListener() {
                                         @Override
                                         public void onClick(View v) {
                                             taille = taille++;
                                             x=2;
                                             image[1]=true;
                                             Intent intent2=new Intent(Intent.ACTION_GET_CONTENT);
                                             Bundle bundle = new Bundle();
                                             bundle.putSerializable("image3", x);
                                             intent2.putExtras(bundle);
                                             intent2.setType("image/*");
                                             startActivityForResult(Intent.createChooser(intent2,"image2"),1);
                                         }
                                     }
        );

        importer3.setOnClickListener(new View.OnClickListener() {
                                         @Override
                                         public void onClick(View v) {
                                             taille = taille++;
                                             x=3;
                                             image[2]=true;
                                             Intent intent3=new Intent(Intent.ACTION_GET_CONTENT);
                                             Bundle bundle = new Bundle();
                                             bundle.putSerializable("image3", x);
                                             intent3.putExtras(bundle);
                                             intent3.setType("image/*");
                                             startActivityForResult(Intent.createChooser(intent3,"image3"),1);
                                         }
                                     }
        );


        validate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String idcarte = UUID.randomUUID().toString();
                String idquestion = UUID.randomUUID().toString();
                String idrep;
                boolean br;
                checkAnswer1 = findViewById(R.id.checkAnswer1);
                checkAnswer2 = findViewById(R.id.checkAnswer2);
                checkAnswer3 = findViewById(R.id.checkAnswer3);

                //si le champ de la question est vide : toast pour dire que la question est obligatoire pour valider la carte
                if (question.getText().toString().isEmpty()){
                    CharSequence text = getText(R.string.warning_question);
                    int duration = Toast.LENGTH_SHORT;
                    Toast.makeText(context, text, duration).show();
                }
                else {
                    //String bonneReponse = "";
                    // vérifier si chaque champ de réponse est vide, sinon ajouter la réponse à la liste answers
                    if (checkAnswer1.isChecked()) { // champ de réponse 1
                        brep[0]=true;
                    }
                    if (checkAnswer2.isChecked()) { // champ de réponse 2
                        brep[1] = true;
                    }
                    if (checkAnswer3.isChecked()) { // champ de réponse 3
                        brep[2] = true;
                    }
                    // en faire un logger
                    /*for(int i=0; i<answers.size(); i++){
                        System.out.println(answers.get(i) + i);
                    }*/
                    System.out.println(" LAAAAAAAAA   ok1");
                    CharSequence text = getText(R.string.card_created);
                    int duration = Toast.LENGTH_SHORT;

                    System.out.println(" LAAAAAAAAA   ok1");
                    carte = new Carte(idcarte,type);
                    QuestionText quest = new QuestionText(idquestion,question.getText().toString(),idcarte);
                    for (int i = 0; i < brep.length; i++) {
                        idrep = UUID.randomUUID().toString();
                        //si la rep a ete initialise on prend son tableau de byte
                        if((image[i]==true)&&(i==0)){
                            bytee = img1;
                        } else {
                            if((image[i]==true)&&(i==1)){
                                bytee = img2;
                            } else {
                                if((image[i]==true)&&(i==2)){
                                    bytee = img3;
                                } else {
                                    bytee = null;
                                }
                            }
                        }
                        if (brep[i] == true) {
                            br = true;
                        }
                        else {
                            br = false;
                        }
                        if(bytee!=null){
                            ReponseImage reponse = new ReponseImage(idrep,idcarte,br,bytee);
                            reponse.save();
                        }

                    }
                    carte.save();
                    quest.save();
                    Toast.makeText(context, text, duration).show();
                }

                //test affichage contenu carte
                List<QuestionText> quest = QuestionText.find(QuestionText.class,"idcarte = ?", carte.getIdCarte());
                if (!quest.isEmpty())
                    for(int m=0;m<quest.size();m++){
                        System.out.println(quest.get(m).getNom_question());
                    }
                // mettre dans le bundle les informations de la carte créée pour les transmetre à l'activité qui va afficher la carte
                Intent afficherCarte = new Intent(getApplicationContext(), JouerCarteReponseImage.class);
                Bundle bundle = new Bundle();
                bundle.putSerializable("carte", carte);
                afficherCarte.putExtras(bundle);
                afficherCarte.putExtra("valeur", 1);
                startActivity(afficherCarte);
                finish();
            }
        });

        retour.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                String idcarte = UUID.randomUUID().toString();
                String idquestion = UUID.randomUUID().toString();
                String idrep;
                boolean br;
                checkAnswer1 = findViewById(R.id.checkAnswer1);
                checkAnswer2 = findViewById(R.id.checkAnswer2);
                checkAnswer3 = findViewById(R.id.checkAnswer3);

                //si le champ de la question est vide : toast pour dire que la question est obligatoire pour valider la carte
                if (question.getText().toString().isEmpty()){
                    CharSequence text = getText(R.string.warning_question);
                    int duration = Toast.LENGTH_SHORT;
                    Toast.makeText(context, text, duration).show();
                }
                else {
                    //String bonneReponse = "";
                    // vérifier si chaque champ de réponse est vide, sinon ajouter la réponse à la liste answers
                    if (checkAnswer1.isChecked()) { // champ de réponse 1
                        brep[0]=true;
                    }
                    if (checkAnswer2.isChecked()) { // champ de réponse 2
                        brep[1] = true;
                    }
                    if (checkAnswer3.isChecked()) { // champ de réponse 3
                        brep[2] = true;
                    }
                    // en faire un logger
                    /*for(int i=0; i<answers.size(); i++){
                        System.out.println(answers.get(i) + i);
                    }*/
                    System.out.println(" LAAAAAAAAA   ok1");
                    CharSequence text = getText(R.string.card_created);
                    int duration = Toast.LENGTH_SHORT;

                    System.out.println(" LAAAAAAAAA   ok1");
                    carte = new Carte(idcarte,type);
                    QuestionText quest = new QuestionText(idquestion,question.getText().toString(),idcarte);
                    for (int i = 0; i < brep.length; i++) {
                        idrep = UUID.randomUUID().toString();
                        //si la rep a ete initialise on prend son tableau de byte
                        if((image[i]==true)&&(i==0)){
                            bytee = img1;
                        } else {
                            if((image[i]==true)&&(i==1)){
                                bytee = img2;
                            } else {
                                if((image[i]==true)&&(i==2)){
                                    bytee = img3;
                                } else {
                                    bytee = null;
                                }
                            }
                        }
                        if (brep[i] == true) {
                            br = true;
                        }
                        else {
                            br = false;
                        }
                        ReponseImage reponse = new ReponseImage(idrep,idcarte,br,bytee);
                        System.out.println(" BYYYYTTTTEEEE carte = "+reponse.getImage());
                        reponse.save();
                    }
                    carte.save();
                    quest.save();
                    Toast.makeText(context, text, duration).show();

                    // mettre dans le bundle les informations de la carte créée pour les transmetre à l'activité qui va afficher la carte
                    Intent afficherCarte = new Intent(getApplicationContext(), ChoisirCreationCarte.class);
                    startActivity(afficherCarte);
                    finish();
                }
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if (resultCode == RESULT_OK && requestCode == 1) {
            try {
                InputStream input = getContentResolver().openInputStream(data.getData());
                Bitmap bitmap = BitmapFactory.decodeStream(input);
                if(x==1) {
                    image1.setImageBitmap(bitmap);
                    ByteArrayOutputStream baos = new ByteArrayOutputStream();
                    bitmap.compress(Bitmap.CompressFormat.JPEG, 100, baos);
                    byte1 = baos.toByteArray();
                    img1=Base64.encodeToString(byte1, Base64.DEFAULT);
                    System.out.println("RRRRRRRRRRRRRRRRRRR img1 "+img1);
                } else {
                    if(x==2) {
                        image2.setImageBitmap(bitmap);
                        ByteArrayOutputStream baos = new ByteArrayOutputStream();
                        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, baos);
                        byte2 = baos.toByteArray();
                        img2=Base64.encodeToString(byte2, Base64.DEFAULT);
                    } else {
                        image3.setImageBitmap(bitmap);
                        ByteArrayOutputStream baos = new ByteArrayOutputStream();
                        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, baos);
                        byte3 = baos.toByteArray();
                        img3=Base64.encodeToString(byte3, Base64.DEFAULT);
                    }
                }
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }
    }

    public void onCheckBoxClicked(View view){
        checkAnswer1 = findViewById(R.id.checkAnswer1);
        checkAnswer2 = findViewById(R.id.checkAnswer2);
        checkAnswer3 = findViewById(R.id.checkAnswer3);

        // s'assurer qu'au total une seule réponse est cochée correcte
        checkAnswer1.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                if(((CheckBox)v).isChecked()){
                    checkAnswer2.setChecked(false);
                    checkAnswer3.setChecked(false);
                }
            }
        });
        checkAnswer2.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                if(((CheckBox)v).isChecked()){
                    checkAnswer1.setChecked(false);
                    checkAnswer3.setChecked(false);
                }
            }
        });
        checkAnswer3.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                if(((CheckBox)v).isChecked()){
                    checkAnswer1.setChecked(false);
                    checkAnswer2.setChecked(false);
                }
            }
        });

    }
}