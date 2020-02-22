package com.example.masterlingua;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

public class DAOCarte extends SQLiteOpenHelper {
    private static final String TAG = "SQLite";

    // Database Version
    private static final int DATABASE_VERSION = 1;

    // Database Name
    private static final String DATABASE_NAME = "Note_Manager";

    // Table name: Carte.
    private static final String TABLE_CARTE = "Carte";
    private static final String COLUMN_ID_CARTE ="Id_Carte";
    private static final String COLUMN_ID_QUESTION = "Id_Question";
    private static final String COLUMN_ID_REP ="Id_reponse";
    private static final String COLUMN_ID_BR = "ID_br";

    // Table name: Question.
    private static final String TABLE_QUESTION = "Question";
    private static final String COLUMN_QUESTION ="Nom_Question";

    // Table name: Reponse.
    private static final String TABLE_REP = "Reponse";
    private static final String COLUMN_REPONSE ="Nom_Reponse";

    // Table name: Jointure.
    private static final String TABLE_JOINTURE = "Jointure";
    private static final String COLUMN_ID_JOINTURE ="Id_Jointure";


    public DAOCarte(Context context)  {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    // Create table
    @Override
    public void onCreate(SQLiteDatabase db) {
        Log.i(TAG, "MyDatabaseHelper.onCreate ... ");
        // Script.
        String scriptCC = "CREATE TABLE " + TABLE_CARTE + "("
                + COLUMN_ID_CARTE + " INTEGER PRIMARY KEY," + COLUMN_ID_QUESTION + " INTEGER,"
                + COLUMN_ID_BR + " INTEGER " + ")";
        // Execute Script.
        db.execSQL(scriptCC);

        String scriptCQ = "CREATE TABLE " + TABLE_QUESTION + "("
                + COLUMN_ID_QUESTION + " INTEGER PRIMARY KEY," + COLUMN_QUESTION + " TEXT" + ")";
        // Execute Script.
        db.execSQL(scriptCQ);

        String scriptCR = "CREATE TABLE " + TABLE_REP + "("
                + COLUMN_ID_REP + " INTEGER PRIMARY KEY," + COLUMN_REPONSE + " TEXT" + ")";
        // Execute Script.
        db.execSQL(scriptCR);

        String scriptCJ = "CREATE TABLE " + TABLE_JOINTURE + "("
                + COLUMN_ID_JOINTURE + " INTEGER PRIMARY KEY," + COLUMN_ID_REP + " INTEGER, " + COLUMN_ID_CARTE + " INTEGER " + ")";
        // Execute Script.
        db.execSQL(scriptCJ);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        Log.i(TAG, "MyDatabaseHelper.onUpgrade ... ");
        // Drop older table if existed
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_CARTE);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_REP);
        // Create tables again
        onCreate(db);
    }

    // If Note table has no data
    // default, Insert 2 records.
   /* public void createDefaultNotesIfNeed()  {
        int count = this.getNotesCount();
        if(count ==0 ) {
            Carte c1 = new Carte(0,"Question ?",0);
            Carte r1 = new Reponse(0,);
            this.addCarte(c1);
            this.addRep(r1);
        }
    }*/

    public void addCarte(Carte c1, Reponse r1) {
        //Log.i(TAG, "MyDatabaseHelper.addNote ... " + note.getNoteTitle());

        SQLiteDatabase db = this.getWritableDatabase();

        int id_carte = c1.getId();
        int id_question = c1.getQuestion().getId_question();
        int ind_br = c1.getInd_br();
        String question = c1.getQuestion().getNom_question();
        String scriptIC = "INSERT INTO " + TABLE_CARTE + " VALUES (id_carte),(id_question),(ind_br)";
        db.execSQL(scriptIC);

        String scriptIQ = "INSERT INTO " + TABLE_QUESTION + " VALUES (id_question),(question)";
        db.execSQL(scriptIQ);

        for(int i=0; i<c1.getReponses().size();i++)
        {
            int id_rep = c1.getReponses().get(i).getId();
            String rep = c1.getReponses().get(i).getNom();
            String scriptIR = "INSERT INTO " + TABLE_REP + " VALUES (id_rep),(rep)";
            db.execSQL(scriptIR);
        }

        /*ContentValues values = new ContentValues();
        values.put(COLUMN_NOTE_TITLE, note.getNoteTitle());
        values.put(COLUMN_NOTE_CONTENT, note.getNoteContent());

        // Inserting Row
        db.insert(TABLE_NOTE, null, values);*/

        // Closing database connection
        db.close();
    }


    /*public Carte getCarte(int id) {
        //Log.i(TAG, "MyDatabaseHelper.getNote ... " + id);

        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(TABLE_NOTE, new String[] { COLUMN_NOTE_ID,
                        COLUMN_NOTE_TITLE, COLUMN_NOTE_CONTENT }, COLUMN_NOTE_ID + "=?",
                new String[] { String.valueOf(id) }, null, null, null, null);
        if (cursor != null)
            cursor.moveToFirst();

        Note note = new Note(Integer.parseInt(cursor.getString(0)),
                cursor.getString(1), cursor.getString(2));
        // return note
        return note;
    }*/


    public List<Carte> getAllCartes() {
        //Log.i(TAG, "MyDatabaseHelper.getAllNotes ... " );

        List<Carte> carteList = new ArrayList<Carte>();
        // Select All Query
        String selectQuery = "SELECT  * FROM " + TABLE_CARTE;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                int id = Integer.parseInt(cursor.getString(0));
                String idc = Integer.toString(id);
                String id_question = "SELECT " + COLUMN_ID_QUESTION + " FROM " + TABLE_CARTE + " WHERE  COLUMN_ID_CARTE =" idc ;
                String question = "SELECT " + COLUMN_ID_QUESTION + " FROM " + TABLE_QUESTION;
                Question q = cursor.getString(2);
                Carte carte = new Carte(id,question);
                //carte.setId(Integer.parseInt(cursor.getString(0)));
                //carte.setQuestion(cursor.getString(1.getNom_question()));
                carte.setCarteContent(cursor.getString(2));
                // Adding note to list
                noteList.add(note);
            } while (cursor.moveToNext());
        }

        // return note list
        return carteList;
    }

    /*public int getNotesCount() {
        Log.i(TAG, "MyDatabaseHelper.getNotesCount ... " );

        String countQuery = "SELECT  * FROM " + TABLE_NOTE;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);

        int count = cursor.getCount();

        cursor.close();

        // return count
        return count;
    }*/


    /*public int updateNote(Note note) {
        Log.i(TAG, "MyDatabaseHelper.updateNote ... "  + note.getNoteTitle());

        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(COLUMN_NOTE_TITLE, note.getNoteTitle());
        values.put(COLUMN_NOTE_CONTENT, note.getNoteContent());

        // updating row
        return db.update(TABLE_NOTE, values, COLUMN_NOTE_ID + " = ?",
                new String[]{String.valueOf(note.getNoteId())});
    }*/

    /*public void deleteNote(Carte c) {
        //Log.i(TAG, "MyDatabaseHelper.updateNote ... " + note.getNoteTitle());

        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_CARTE, COLUMN_NOTE_ID + " = ?",
                new String[]{String.valueOf(note.getNoteId())});
        db.close();
    }*/
}
