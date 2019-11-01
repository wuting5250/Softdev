package edu.gatech.seclass.words6300;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;
import com.google.gson.Gson;

public class DatabaseHelper extends SQLiteOpenHelper{

    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "Statistics.db";
    private static final String TABLE_WORDS = "words";
    private static final String Column1 = "word";
    private static final String TABLE_SCORE = "scores";
    private static final String gameScoreColumn = "gameScore";
    private static final String numberOfTurnsColumn = "numberOfTurns";
    private static final String averageScoreColumn = "averageScore";
    private static final String letterCountColumn = "letterCount";
    private static final String letterPointColumn = "letterPoint";
    private static final String maxTurnColumn = "maxTurn";
    private static final String TABLE_LETTERS = "letters";
    private static final String letterColumn = "letter";
    private static final String wordColumn = "word";
    private static final String swapColumn = "swap";
    private static final String drawColumn = "draw";


    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.getWritableDatabase();
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE words(wordid INTEGER PRIMARY KEY AUTOINCREMENT, word VARCHAR(255))");
        db.execSQL("CREATE TABLE scores(scoresid INTEGER PRIMARY KEY AUTOINCREMENT, gameScore Integer, numberOfTurns Integer, averageScore Integer, maxTurn Integer, letterCount TEXT, letterPoint TEXT)");
        db.execSQL("CREATE TABLE letters(lettersid INTEGER PRIMARY KEY AUTOINCREMENT, letter VARCHAR(255), word VARCHAR(255), swap VARCHAR(255), draw VARCHAR(255))");
    }


    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        String sql = "DROP TABLE IF EXISTS " + TABLE_WORDS;
        db.execSQL(sql);
        onCreate(db);
        String sql2 = "DROP TABLE IF EXISTS " + TABLE_SCORE;
        db.execSQL(sql2);
        onCreate(db);
        String sql3 = "DROP TABLE IF EXISTS " + TABLE_LETTERS;
        db.execSQL(sql3);
        onCreate(db);
    }

    public void putWord(String word) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(Column1, word);
        db.insert("words", null, values);
        db.close();
    }

    public Cursor getWordData() {
        SQLiteDatabase db = this.getReadableDatabase();
        String[] columns = new String[]{"MAX(wordid)", Column1, "COUNT(word)"};
        Cursor cursor = db.query(TABLE_WORDS, columns, null, null, Column1, null, "MAX(wordid) DESC");
        return cursor;
    }

    public void putScore(Integer gameScore, Integer numberOfTurns, Integer maxTurn, HashMap<String, Integer> letterCount, HashMap<String, Integer> letterPoint) {
        SQLiteDatabase db = this.getWritableDatabase();
        Double averageScore = Math.round(1000.0 * gameScore / numberOfTurns) / 1000.0;
        ContentValues values = new ContentValues();
        values.put(gameScoreColumn, gameScore);
        values.put(numberOfTurnsColumn, numberOfTurns);
        values.put(averageScoreColumn, averageScore);
        values.put(maxTurnColumn, maxTurn);
        values.put(letterCountColumn, json_converter_Helper(letterCount));
        values.put(letterPointColumn, json_converter_Helper(letterPoint));
        db.insert("scores", null, values);
        db.close();
    }

    public Cursor getGameScoreData() {
        SQLiteDatabase db = this.getWritableDatabase();
        String[] columns = new String[]{gameScoreColumn, numberOfTurnsColumn, averageScoreColumn, maxTurnColumn, letterCountColumn, letterPointColumn};
        Cursor cursor = db.query(TABLE_SCORE, columns, null, null, null, null, gameScoreColumn + " DESC");
        return cursor;
    }

    public static HashMap<String, Double> get_letter_map_from_json(String json_string) {
        Gson gson = new Gson();
        HashMap<String, Double> letter_map = gson.fromJson(json_string, HashMap.class);
        System.out.println(letter_map);
        return letter_map;
    }

    public void putLetters(HashMap<String, Integer> word, HashMap<String, Integer> swap, HashMap<String, Integer> draw) {
        SQLiteDatabase db = this.getWritableDatabase();
        ArrayList<String> letters = new ArrayList<>();
        for (int i = 65; i <= 90; i ++) {
            letters.add(Character.toString((char) i));
        }
        letters.add("/");

        for (String letter: letters) {
            ContentValues values = new ContentValues();
            values.put(letterColumn, letter);
            values.put(wordColumn, get_value_from_map(word, letter));
            values.put(swapColumn, get_value_from_map(swap, letter));
            values.put(drawColumn, get_value_from_map(draw, letter));
            db.insert(TABLE_LETTERS, null, values);
        }
        db.close();
    }

    private Integer get_value_from_map(HashMap<String, Integer> m, String key) {
        Integer val;
        try {
            val = m.get(key);
        } catch (Exception e) {
            val = 0;
        }
        return val;
    }

    public Cursor getLettersData() {
        SQLiteDatabase db = this.getReadableDatabase();
        String[] columns = new String[]{"MAX(lettersid)", letterColumn, "SUM(" + wordColumn + ")", "SUM(" + swapColumn + ")", "SUM(" + drawColumn + ")"};
        Cursor cursor = db.query(TABLE_LETTERS, columns, null, null, letterColumn, null, wordColumn + " ASC");
        return cursor;
    }

    public String json_converter_Helper(HashMap<String, Integer> mymap) {
        SQLiteDatabase db = this.getReadableDatabase();
        String json_count;
        Gson gson = new Gson();
        json_count = gson.toJson(mymap);

        return json_count;
    }
}