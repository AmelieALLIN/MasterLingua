package com.example.masterlingua;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.SparseBooleanArray;
import android.view.ActionMode;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class AfficherListeQuestionImage extends AppCompatActivity {/*
    ListView CarteListView;
    List<Carte> carte = Carte.listAll(Carte.class);
    List<ReponseText> reponses = ReponseText.listAll(ReponseText.class);
    List<QuestionImage> questions = QuestionImage.listAll(QuestionImage.class);
    private EditText nom_deck;

    List<Bitmap> questioncarte ;
    ArrayList<Carte> l = new ArrayList<>();
    Deck deck;
    int count=0;
    View row;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.liste_question_image);

        nom_deck = findViewById(R.id.nom_deck);

        CarteListView = (ListView) findViewById(R.id.listee);

        CarteListView.setChoiceMode(AbsListView.CHOICE_MODE_MULTIPLE_MODAL);

        CarteListView.setMultiChoiceModeListener(new AbsListView.MultiChoiceModeListener() {
            @Override
            public void onItemCheckedStateChanged(ActionMode mode, int position, long id, boolean checked) {

                mode.setTitle("" + CarteListView.getCheckedItemCount() + " items selected");

            }
            @Override
            public boolean onCreateActionMode(ActionMode mode, Menu menu) {
                MenuInflater menuInflater = mode.getMenuInflater();
                menuInflater.inflate(R.menu.menu_contextual_actionbar,menu);
                return true;
            }
            @Override
            public boolean onPrepareActionMode(ActionMode mode, Menu menu) {
                return false;
            }
            @Override
            public boolean onActionItemClicked(ActionMode mode, MenuItem item) {
                String idcarte;
                switch (item.getItemId()){

                    case R.id.menu_delete:
//action on clicking contextual action bar menu item
                        SparseBooleanArray checkedItems = CarteListView.getCheckedItemPositions();
                        ArrayList<Integer> arrayList = null;
                        if (checkedItems != null && checkedItems.size() > 0) {
                            count = checkedItems.size();
                            arrayList = new ArrayList<>(count);
                            for (int i = 0; i < count; i++) {
                                arrayList.add(checkedItems.keyAt(i));
                                System.out.println("ajout de "+checkedItems.keyAt(i));
                            }
                        }
                        String id_deck = UUID.randomUUID().toString();
                        deck = new Deck(id_deck,nom_deck.toString());
                        deck.save();
                        for(int i=0; i<count; i++)
                        {
                            //System.out.println("JE SUIS LAAAAAAAA   question=" + questions.get(arrayList.get(i)).getNom_question());
                            System.out.println("id carte = " + questions.get(arrayList.get(i)).getIdCarte());
                            idcarte = questions.get(arrayList.get(i)).getIdCarte();

                            String id_cartedeck = UUID.randomUUID().toString();
                            CartesDeck cartesDeck = new CartesDeck(id_cartedeck,id_deck,idcarte);
                            cartesDeck.save();
                        }
                        Intent afficherDeck = new Intent(getApplicationContext(), AfficherDeck.class);
                        Bundle bundle = new Bundle();
                        bundle.putSerializable("deck", deck);
                        afficherDeck.putExtras(bundle);
                        startActivity(afficherDeck);
                        finish();
                        mode.finish();
                }
                return true;
            }
            @Override
            public void onDestroyActionMode(ActionMode mode) {

            }
        });
        for (int i = 0; i < questions.size(); i++) {
            questioncarte.add(BitmapFactory.decodeByteArray(questions.get(i).getImage(),0,questions.get(i).getImage().length));
        }


    }
        /*ArrayAdapter<Bitmap> adapter = new ArrayAdapter<Bitmap>(this, android.R.layout.simple_list_item_1, questioncarte);
        CarteListView.setAdapter(adapter);*/
    }


